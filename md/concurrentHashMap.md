
## 1.6
#### 构造函数
```java
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
  2. 通过while循环，计算出大于concurrencyLevel的并且是2的n次幂的一个值，ssize就是这个值，sshift的值符合2^sshift = ssize;
      例如：concurrencyLevel=16，ssize=16，sshift=4;
            concurrencyLevel=17~32，ssize=32，sshift=5;
  3. 创建Segment数组，数组的大小为ssize     
  4. 根据ConcurrentHashMap的初始容量，算出能容下该容量的每个Segment中容量的大小，并且该大小为2的n次幂            
            

#### Segment
  Segment<K,V> extends ReentrantLock 所以Segment是个重入锁
  transient volatile HashEntry<K,V>[] table; 这个table是数组加链表的方式来存储数据
  Segment的构造函数，只有一个构造函数
```java
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
```java
    final Segment<K,V> segmentFor(int hash) {
        return segments[(hash >>> segmentShift) & segmentMask];
    }
```
  该方法是取hash值的高几位，例如默认情况下，segmentShift为28， segmentMask为15（0xFFFF）,把hash无符号右移28位再与OxFFFF取与，就是取hash的高4位

## JDK1.8
