
## 1.6
#### 构造函数
```
  public ConcurrentHashMap(int initialCapacity,
                             float loadFactor, int concurrencyLevel) {
        if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0)
            throw new IllegalArgumentException();

        if (concurrencyLevel > MAX_SEGMENTS)
            concurrencyLevel = MAX_SEGMENTS;  //1

        // Find power-of-two sizes best matching arguments
        int sshift = 0;
        int ssize = 1;
        while (ssize < concurrencyLevel) {  //2
            ++sshift;
            ssize <<= 1;
        }
        segmentShift = 32 - sshift;
        segmentMask = ssize - 1;             //3
        this.segments = Segment.newArray(ssize); //4

        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        int c = initialCapacity / ssize;
        if (c * ssize < initialCapacity)
            ++c;              //5
        int cap = 1;
        while (cap < c)
            cap <<= 1;

        for (int i = 0; i < this.segments.length; ++i)
            this.segments[i] = new Segment<K,V>(cap, loadFactor);
    }
```
  1. concurrencyLevel是构造函数传入的值，就是Segment数组的个数，也就是分段的个数，最大值为MAX_SEGMENTS（1<<16）
  2. 通过while循环，计算出大于concurrencyLevel的并且是2的n次幂（power-of-two）的一个值，ssize就是这个值，sshift的值符合2^sshift = ssize;
      例如：concurrencyLevel=16，ssize=16，sshift=4;\
            concurrencyLevel=17~32，ssize=32，sshift=5;
  3. segmentMask是ssize-1,也就是segmentMask的值是有sshift个1, segmentShift是32-sshift,也就是segmentMask这个数的1的个数与segmentShift之和是32
  4. 创建Segment数组，数组的大小为ssize     
  5. 根据ConcurrentHashMap的初始容量，算出能容下该容量的每个Segment中容量的大小，c为平均到每个Segment里的个数，然后cap是保证分配到每个Segment的  
  大小为2的n次幂（power-of-two）            
            

#### Segment
  Segment<K,V> extends ReentrantLock 所以Segment是个重入锁
  transient volatile HashEntry<K,V>[] table; 这个table是数组加链表的方式来存储数据
  Segment的构造函数，只有一个构造函数
```
          Segment(int initialCapacity, float lf) {
            loadFactor = lf;
            setTable(HashEntry.<K,V>newArray(initialCapacity));
        }
        void setTable(HashEntry<K,V>[] newTable) {
            threshold = (int)(newTable.length * loadFactor);
            table = newTable;
        }
```
  loadFactor 跟HashMap的loadFactor同样作用，0到1之间，默认0.75，当存储的个数占总容量的比例达到loadFactor的时候，就需要扩容
  threshold 门限值，threshold = 总容量 * loadFactor, 达到门限值就扩容

#### segmentFor方法
```
    final Segment<K,V> segmentFor(int hash) {
        return segments[(hash >>> segmentShift) & segmentMask];
    }
```
  该方法是取hash值的高几位，例如默认情况下，segmentShift为28， segmentMask为15（0x000F）,把hash无符号右移28位再与Ox000F取与，就是取hash的高4位  
  '>>>'为无符号移位, 既然‘>>>’是无符号右移，移segmentShift位之后不就是取高（32-segmengShift）位了吗？怎么还要与segmengMask取与，为什么？？？
  
#### 先从put方法说起
```
    public V put(K key, V value) {
        if (value == null)
            throw new NullPointerException();
        int hash = hash(key.hashCode());  //1
        return segmentFor(hash).put(key, hash, value, false);   		//2
    }
```    
    1. 算出key值的hash值
	2. 首先通过segmengFor方法定位到具体哪个Segment， 然后调用Segment的put方法
	Segment的put方法如下：
```
	V put(K key, int hash, V value, boolean onlyIfAbsent) {
            lock();
            try {
                int c = count;
                if (c++ > threshold) // ensure capacity
                    rehash();							//1
                HashEntry<K,V>[] tab = table;
                int index = hash & (tab.length - 1);
                HashEntry<K,V> first = tab[index];
                HashEntry<K,V> e = first;
                while (e != null && (e.hash != hash || !key.equals(e.key)))	//2
                    e = e.next;

                V oldValue;
                if (e != null) {						//3
                    oldValue = e.value;
                    if (!onlyIfAbsent)
                        e.value = value;
                }
                else {								//4
                    oldValue = null;
                    ++modCount;
                    tab[index] = new HashEntry<K,V>(key, hash, first, value);
                    count = c; // write-volatile
                }
                return oldValue;
            } finally {
                unlock();
            }
        }
```
	1. 如果容量达到了门限值，则需要rehash（）方法来扩容,详细分析见下面的分析
	2. 依次遍历链表（如果链表存在），如果有hash值相同，或key值相同的，停止遍历
	3. 该条件说明存在hash值相同或key值相同的元素，如果onlyIfAbsent为false，则替换元素
	4. e == null,有两种可能，一种是原来该数组上，index这个节点就是null，另一种是遍历到最后，没有hash值相同或key值相同的元素，但是不管哪种情形，都把新增的元素加到该链表前
```
        void rehash() {
            HashEntry<K,V>[] oldTable = table;
            int oldCapacity = oldTable.length;
            if (oldCapacity >= MAXIMUM_CAPACITY)
                return;

            HashEntry<K,V>[] newTable = HashEntry.newArray(oldCapacity<<1);	//1
            threshold = (int)(newTable.length * loadFactor);
            int sizeMask = newTable.length - 1;
            for (int i = 0; i < oldCapacity ; i++) {				//2
                // We need to guarantee that any existing reads of old Map can
                //  proceed. So we cannot yet null out each bin.
                HashEntry<K,V> e = oldTable[i];					//3

                if (e != null) {
                    HashEntry<K,V> next = e.next;
                    int idx = e.hash & sizeMask;				//4

                    //  Single node on list
                    if (next == null)
                        newTable[idx] = e;					//5

                    else {							//6
                        // Reuse trailing consecutive sequence at same slot
                        HashEntry<K,V> lastRun = e;
                        int lastIdx = idx;
                        for (HashEntry<K,V> last = next;			
                             last != null;
                             last = last.next) {
                            int k = last.hash & sizeMask;			//7
                            if (k != lastIdx) {
                                lastIdx = k;
                                lastRun = last;
                            }
                        }							//8
                        newTable[lastIdx] = lastRun;

                        // Clone all remaining nodes
                        for (HashEntry<K,V> p = e; p != lastRun; p = p.next) {
                            int k = p.hash & sizeMask;
                            HashEntry<K,V> n = newTable[k];
                            newTable[k] = new HashEntry<K,V>(p.key, p.hash,
                                                             n, p.value);	//9
                        }
                    }
                }
            }
            table = newTable;
        }
```
	1. 首先将数组扩容2倍
	2. 通过for循环遍历oldTable,
	3. 通过for循环依次获取oldTable数组上每个元素，注：不是每个元素都有值，有的为null
	4. 算出e落到新数组newTable里的index
	5. 如果e没有next，就直接把e放入newTable数组里就可以了
	6. 如果e.next不为null。情况就比较复杂
	7. 计算当前节点落到newTable数组中的哪个index上
	8. for循环从e.next一直遍历到链表的最后，得到最后一个和上一个节点不会落到新数组同一个index的节点，举例：
		for循环遍历分别为 1 0 1 1 0 0共个节点，在第2、3、5个节点（值分别为0、1、0）,才进入if条件，最后记录第5个以及对应的值0,
		q其实这个for循环的目的就是为了找出最后几个一致的节点，然后记录这几个一致的节点的第一个，然后把这个第一个节点放到新数组里，它后面的几个也都一起过去了。
	9. 从e开始遍历链表，遍历到刚才说的几个一致的节点链表的第一个节点（不包括该节点），把每个节点加入到新数组newTable上
	
#### 在说get()
	ConcurrentHashMap的get方法如下：
```
    public V get(Object key) {
        int hash = hash(key.hashCode());
        return segmentFor(hash).get(key, hash);
    }
````
	由get方法可知，先定位到Segment数组的某个节点，然后在该Segment里执行get方法
	Segmeng的get方法如下：
```
	V get(Object key, int hash) {
            if (count != 0) { // read-volatile
                HashEntry<K,V> e = getFirst(hash);
                while (e != null) {
                    if (e.hash == hash && key.equals(e.key)) {
                        V v = e.value;
                        if (v != null)
                            return v;
                        return readValueUnderLock(e); // recheck
                    }
                    e = e.next;
                }
            }
            return null;
        }
```	

## 1.7
#### 构造函数
```java
 public ConcurrentHashMap(int initialCapacity,
                             float loadFactor, int concurrencyLevel) {
        if (!(loadFactor > 0) || initialCapacity < 0 || concurrencyLevel <= 0)
            throw new IllegalArgumentException();
        if (concurrencyLevel > MAX_SEGMENTS)
            concurrencyLevel = MAX_SEGMENTS;
        // Find power-of-two sizes best matching arguments
        int sshift = 0;
        int ssize = 1;
        while (ssize < concurrencyLevel) {
            ++sshift;
            ssize <<= 1;
        }
        this.segmentShift = 32 - sshift;
        this.segmentMask = ssize - 1;
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        int c = initialCapacity / ssize;
        if (c * ssize < initialCapacity)
            ++c;
        int cap = MIN_SEGMENT_TABLE_CAPACITY;
        while (cap < c)
            cap <<= 1;
        // create segments and segments[0]
        Segment<K,V> s0 =
            new Segment<K,V>(loadFactor, (int)(cap * loadFactor),
                             (HashEntry<K,V>[])new HashEntry[cap]);
        Segment<K,V>[] ss = (Segment<K,V>[])new Segment[ssize];    //1
        UNSAFE.putOrderedObject(ss, SBASE, s0); // ordered write of segments[0]    //2
        this.segments = ss;
    }
```  
-	初始化segment数组的第一个，用UNSAFE的方式赋值，如果1、2出不是很理解，以后慢慢理解，只需要知道1.7跟1.6的区别是没有初始化数组的所有元素，而是先把第一个元素赋值，其他的元素需要的时候在初始化（通过CAS的方式）	

#### CAS相关
```java
    private static final sun.misc.Unsafe UNSAFE;
    private static final long SBASE;
    private static final int SSHIFT;
    private static final long TBASE;
    private static final int TSHIFT;
    private static final long HASHSEED_OFFSET;
    private static final long SEGSHIFT_OFFSET;
    private static final long SEGMASK_OFFSET;
    private static final long SEGMENTS_OFFSET;
     static {
        int ss, ts;
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class tc = HashEntry[].class;
            Class sc = Segment[].class;
            TBASE = UNSAFE.arrayBaseOffset(tc);    //1
            SBASE = UNSAFE.arrayBaseOffset(sc);
            ts = UNSAFE.arrayIndexScale(tc);   //2
            ss = UNSAFE.arrayIndexScale(sc);
            HASHSEED_OFFSET = UNSAFE.objectFieldOffset(
                ConcurrentHashMap.class.getDeclaredField("hashSeed"));
            SEGSHIFT_OFFSET = UNSAFE.objectFieldOffset(
                ConcurrentHashMap.class.getDeclaredField("segmentShift"));
            SEGMASK_OFFSET = UNSAFE.objectFieldOffset(
                ConcurrentHashMap.class.getDeclaredField("segmentMask"));
            SEGMENTS_OFFSET = UNSAFE.objectFieldOffset(
                ConcurrentHashMap.class.getDeclaredField("segments"));
        } catch (Exception e) {
            throw new Error(e);
        }
        if ((ss & (ss-1)) != 0 || (ts & (ts-1)) != 0)     //3
            throw new Error("data type scale not a power of two");
        SSHIFT = 31 - Integer.numberOfLeadingZeros(ss);
        TSHIFT = 31 - Integer.numberOfLeadingZeros(ts);
    }
```  
-	1数组的基础偏移
-	2数组每个元素的宽度, 通过数组的偏移和每个元素的宽度就可以算出每个数组元素的地址
-	3通过(ss & (ss-1)) != 0 来判断ss的值是2的n次幂
#### put
```java
    public V put(K key, V value) {
        Segment<K,V> s;
        if (value == null)
            throw new NullPointerException();
        int hash = hash(key);
        int j = (hash >>> segmentShift) & segmentMask;
        if ((s = (Segment<K,V>)UNSAFE.getObject(segments, (j << SSHIFT) + SBASE)) == null) //  1
            s = ensureSegment(j);                          //2
        return s.put(key, hash, value, false);
    }
```  
-	1 通过UNSAFE.getObject(segments, (j << SSHIFT) + SBASE)来获取segment数组的第j个元素
-	2 如果segment数组的第j个元素为null，通过ensureSegment来给segment[j]通过CAS的方式来赋值， 然后调用Segment的put方法
```java
  private Segment<K,V> ensureSegment(int k) {
        final Segment<K,V>[] ss = this.segments;
        long u = (k << SSHIFT) + SBASE; // raw offset
        Segment<K,V> seg;
        if ((seg = (Segment<K,V>)UNSAFE.getObjectVolatile(ss, u)) == null) {
            Segment<K,V> proto = ss[0]; // use segment 0 as prototype
            int cap = proto.table.length;
            float lf = proto.loadFactor;
            int threshold = (int)(cap * lf);
            HashEntry<K,V>[] tab = (HashEntry<K,V>[])new HashEntry[cap];
            if ((seg = (Segment<K,V>)UNSAFE.getObjectVolatile(ss, u))
                == null) { // recheck
                Segment<K,V> s = new Segment<K,V>(lf, threshold, tab);
                while ((seg = (Segment<K,V>)UNSAFE.getObjectVolatile(ss, u))
                       == null) {
                    if (UNSAFE.compareAndSwapObject(ss, u, null, seg = s))             //1
                        break;
                }
            }
        }
        return seg;
    }
    
```
-	1 通过循环CAS的方式进行对segment[j]赋值
Segment的put方法：
```java
	final V put(K key, int hash, V value, boolean onlyIfAbsent) {
            HashEntry<K,V> node = tryLock() ? null :
                scanAndLockForPut(key, hash, value);
            V oldValue;
            try {
                HashEntry<K,V>[] tab = table;
                int index = (tab.length - 1) & hash;          //1
                HashEntry<K,V> first = entryAt(tab, index);    //2
                for (HashEntry<K,V> e = first;;) {
                    if (e != null) {
                        K k;
                        if ((k = e.key) == key ||
                            (e.hash == hash && key.equals(k))) {
                            oldValue = e.value;
                            if (!onlyIfAbsent) {
                                e.value = value;
                                ++modCount;
                            }
                            break;
                        }
                        e = e.next;
                    }
                    else {
                        if (node != null)
                            node.setNext(first);
                        else
                            node = new HashEntry<K,V>(hash, key, value, first);
                        int c = count + 1;
                        if (c > threshold && tab.length < MAXIMUM_CAPACITY)
                            rehash(node);
                        else
                            setEntryAt(tab, index, node);
                        ++modCount;
                        count = c;
                        oldValue = null;
                        break;
                    }
                }
            } finally {
                unlock();
            }
            return oldValue;
        }
	
      static final <K,V> HashEntry<K,V> entryAt(HashEntry<K,V>[] tab, int i) {
        return (tab == null) ? null :
            (HashEntry<K,V>) UNSAFE.getObjectVolatile
            (tab, ((long)i << TSHIFT) + TBASE);
    }
```
-	1 通过(tab.length - 1) & hash通过hash算出落到HashEntry数组的哪个位置上
-	2 entryAt是用CAS的方式取数组的第i个元素，i是上面计算出来的

#### 1.7 get()
```java
   public V get(Object key) {
        Segment<K,V> s; // manually integrate access methods to reduce overhead
        HashEntry<K,V>[] tab;
        int h = hash(key);                    //1
        long u = (((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE;       //2
        if ((s = (Segment<K,V>)UNSAFE.getObjectVolatile(segments, u)) != null &&
            (tab = s.table) != null) {                                            //3
            for (HashEntry<K,V> e = (HashEntry<K,V>) UNSAFE.getObjectVolatile
                     (tab, ((long)(((tab.length - 1) & h)) << TSHIFT) + TBASE);
                 e != null; e = e.next) {                                         //4
                K k;
                if ((k = e.key) == key || (e.hash == h && key.equals(k)))
                    return e.value;
            }
        }
        return null;
    }
``` 
-	1 通过hash方法算出key的hash值
-	2 (h >>> segmentShift) & segmentMask得到的是hash值定位到Segment数组的哪个元素（例如算出为第a个元素），
	(((h >>> segmentShift) & segmentMask) << SSHIFT) + SBASE的值是第a个元素在通过Unsafe内存计算时的数组偏移
-	3 通过Unsafe获取Segmeng数组的第a个元素，并且要改元素的table都不为null
-	4 遍历链表，如果找到返回值，其中(tab.length - 1) & h是计算key值对于的hash在table数组中落到哪个元素上

## JDK1.8
#### 构造函数
```java
    public ConcurrentHashMap(int initialCapacity,
                             float loadFactor, int concurrencyLevel) {
        if (!(loadFactor > 0.0f) || initialCapacity < 0 || concurrencyLevel <= 0)
            throw new IllegalArgumentException();
        if (initialCapacity < concurrencyLevel)   // Use at least as many bins
            initialCapacity = concurrencyLevel;   // as estimated threads
        long size = (long)(1.0 + (long)initialCapacity / loadFactor);
        int cap = (size >= (long)MAXIMUM_CAPACITY) ?
            MAXIMUM_CAPACITY : tableSizeFor((int)size);      //1
        this.sizeCtl = cap;
    }
```
-   1 tableSizeFor方法是计算大于size的最小的2的n次幂, jdk1.7是用:
```java
while (cap < c)
            cap <<= 1;
```
   的方式来算的
    
-	构造函数中的concurrencyLevel参数已经在JDK1.8中的意义发生了很大的变化，其并不代表所允许的并发数，其只是用来确定sizeCtl大小
#### 1.8 put(), 有点复杂，慢慢理解
```java
    transient volatile Node<K,V>[] table;                             //1

 public V put(K key, V value) {
        return putVal(key, value, false);
    }

    /** Implementation for put and putIfAbsent */
    final V putVal(K key, V value, boolean onlyIfAbsent) {
        if (key == null || value == null) throw new NullPointerException();
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) {
            Node<K,V> f; int n, i, fh;
            if (tab == null || (n = tab.length) == 0)                       //2
                tab = initTable();
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {        //3
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
            else if ((fh = f.hash) == MOVED)                                //4
                tab = helpTransfer(tab, f);
            else {                                                          //5
                V oldVal = null;
                synchronized (f) {
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {
                            binCount = 1;
                            for (Node<K,V> e = f;; ++binCount) {            //6
                                K ek;
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                        else if (f instanceof TreeBin) {
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)
                        treeifyBin(tab, i);                                  //7 方法见下面
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        addCount(1L, binCount);
        return null;
    }
    static final int spread(int h) {
            return (h ^ (h >>> 16)) & HASH_BITS;
     }
     
    static final <K,V> Node<K,V> tabAt(Node<K,V>[] tab, int i) {
        return (Node<K,V>)U.getObjectVolatile(tab, ((long)i << ASHIFT) + ABASE);
    }
    
    private final void treeifyBin(Node<K,V>[] tab, int index) {
        Node<K,V> b; int n, sc;
        if (tab != null) {
            if ((n = tab.length) < MIN_TREEIFY_CAPACITY)              //判断数组的长度如果小于64，则不转成红黑树，先对数组扩容
                tryPresize(n << 1);
            else if ((b = tabAt(tab, index)) != null && b.hash >= 0) {
                synchronized (b) {
                    if (tabAt(tab, index) == b) {
                        TreeNode<K,V> hd = null, tl = null;
                        for (Node<K,V> e = b; e != null; e = e.next) {
                            TreeNode<K,V> p =
                                new TreeNode<K,V>(e.hash, e.key, e.val,
                                                  null, null);
                            if ((p.prev = tl) == null)
                                hd = p;
                            else
                                tl.next = p;
                            tl = p;
                        }
                        setTabAt(tab, index, new TreeBin<K,V>(hd));
                    }
                }
            }
        }
    }

```
-   1 jdk1.8中不是用Segment数组分段，引入Node数组的概念
-   2 条件1： 数组还没初始化，就初始化数组
-   3 条件2： 根据hash值算出数组下标，如果数组该下标的值为null，则把key\value的值赋给它，取值方法tabAt和赋值方法casTabAt都是用Unsafe的方式
-   4 条件3： 如果f的hash值为MOVED，则说明有线程正在执行移动，执行helpTransfer去帮助转移数据，这里还没有完全理解，之后慢慢理解
-   5 条件4： 如果数组通过hash算出的下标的元素有值，并且没有其他线程正在执行transfer，则该线程锁住该元素，遍历链表（该元素时链表头）
-   6: 该for循环遍历链表，如果找到找到key值相等的元素，则替换，如果遍历整个链表都没有相同的key值，则把新key\value值加入到链表后面
-   有个疑问：如果遇到key值相同的，会返回原来的value值，但是看代码中是直接break了，怎么返回的？？？
-   7 binCount是链表的长度，如果链表长度超过8个，就执行treeifyBin方法，把链表改成红黑树
-   jdk 1.8 concurrentHashmap的代码分析：https://www.cnblogs.com/zerotomax/p/8687425.html
-   sizeCtl变量在jdk 1.8中引入的，



### 案例
-	根据网元文件备份恢复想到一个场景，一个ConcurrentHashMap<String, List<String>> 往里面加元素，key值从0~99，value值任意
详见栗子：ConcurrentHashMapDemo， 通过运行发现，要么获得的结果与预期的值不一致，要么就会出现抛异常，该异常是因为多线程往List里put元素
解决该问题可以通过synchronized加锁来解决，或者

