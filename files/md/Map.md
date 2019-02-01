## HashMap

#### jdk 1.6 构造函数
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
-   根据构造函数可以得知，HashMap这个类主要有三个重要的参数，loadFactor是传入的加载因子，默认为0.75， capacity容量是根据传入的值算出的一个2的次幂的值， 门限值 threshold = (int)(capacity * loadFactor);

#### jdk 1.6 hash方法 
```java
    static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
```

#### jdk 1.6 put方法
```java
 public V put(K key, V value) {
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key.hashCode());
        int i = indexFor(hash, table.length);                             //1
        for (Entry<K,V> e = table[i]; e != null; e = e.next) {            //2
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }

        modCount++;
        addEntry(hash, key, value, i);                          //3
        return null;
    }
    static int indexFor(int h, int length) {
        return h & (length-1);
    }
    void addEntry(int hash, K key, V value, int bucketIndex) {
	Entry<K,V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<K,V>(hash, key, value, e);     //4
        if (size++ >= threshold)
            resize(2 * table.length);
    }
```
-   1 通过indexFor算出key的hash值落在数组的哪个元素上，计算方法就是用按位与的方式（由于数组的个数一定是2的n次幂）
-   2 通过for循环遍历链表，如果找到就替换
-   3 如果链表里没找到，或者该数组的元素为null，就通过addEntry方法把要添加的元素加进数组
-   4 可以看出， 每次增加新元素，都是增加到这个链表的开头


#### jdk 1.8 hash方法 
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
