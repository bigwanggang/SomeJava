
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
        segmentMask = ssize - 1;
        this.segments = Segment.newArray(ssize); //3

        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        int c = initialCapacity / ssize;
        if (c * ssize < initialCapacity)
            ++c;              //4
        int cap = 1;
        while (cap < c)
            cap <<= 1;

        for (int i = 0; i < this.segments.length; ++i)
            this.segments[i] = new Segment<K,V>(cap, loadFactor);
    }
```
  1. concurrencyLevel是构造函数传入的值，就是Segment数组的个数，也就是分段的个数，最大值为MAX_SEGMENTS（1<<16）
  2. 通过while循环，计算出大于concurrencyLevel的并且是2的n次幂（power-of-two）的一个值，ssize就是这个值，sshift的值符合2^sshift = ssize;
      例如：concurrencyLevel=16，ssize=16，sshift=4;
            concurrencyLevel=17~32，ssize=32，sshift=5;
  3. 创建Segment数组，数组的大小为ssize     
  4. 根据ConcurrentHashMap的初始容量，算出能容下该容量的每个Segment中容量的大小，c为平均到每个Segment里的个数，然后cap是保证分配到每个Segment的  
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
  >>>为无符号移位
  
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
## JDK1.8
