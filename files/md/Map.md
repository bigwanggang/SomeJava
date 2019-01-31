## HashMap

#### 1.6 构造函数
```java
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                                               initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                                               loadFactor);

        // Find a power of 2 >= initialCapacity
        int capacity = 1;
        while (capacity < initialCapacity)
            capacity <<= 1;

        this.loadFactor = loadFactor;
        threshold = (int)(capacity * loadFactor);
        table = new Entry[capacity];
        init();
    }
```    



- HashMap计算hash的方法  
```java
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```
  '>>>'是无符号右移，前面空位用0补齐  
  具体原因看下： https://www.cnblogs.com/liujinhong/p/6576543.html

## TreeMap
- 用红黑树实现，key值排序，key必须实现Comparator，所以可以不能为null，但value可以为null
- HashMap使用hashCode和equals方法去重，而TreeMap用Comparable或Compaator去重
- TreeMap的源码阅读时，着重看下四个有/** From CLR */ 注释的方法，From CLR是用算法导论里的算法
