#### Unsafe


#### getAndIncrement() jdk1.8
```java
      public final int getAndIncrement() {
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }
```
  Unsafe类的getAndAddInt方法如下：
```java
    public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }
```   
      getIntVolatile和compareAndSwapInt是Unsafe对象的native方法，
```java
  /***
   * Retrieves the value of the integer field at the specified offset in the
   * supplied object with volatile load semantics.
   * 获取obj对象中offset偏移地址对应的整型field的值,支持volatile load语义。
   * 
   * @param obj the object containing the field to read.
   *    包含需要去读取的field的对象
   * @param offset the offset of the integer field within <code>obj</code>.
   *       <code>obj</code>中整型field的偏移量
   */
  public native int getIntVolatile(Object obj, long offset);

  /***
   * Compares the value of the integer field at the specified offset
   * in the supplied object with the given expected value, and updates
   * it if they match.  The operation of this method should be atomic,
   * thus providing an uninterruptible way of updating an integer field.
   * 在obj的offset位置比较integer field和期望的值，如果相同则更新。这个方法
   * 的操作应该是原子的，因此提供了一种不可中断的方式更新integer field。
   * 
   * @param obj the object containing the field to modify.
   *            包含要修改field的对象
   * @param offset the offset of the integer field within <code>obj</code>.
   *               <code>obj</code>中整型field的偏移量
   * @param expect the expected value of the field.
   *               希望field中存在的值
   * @param update the new value of the field if it equals <code>expect</code>.
   *           如果期望值expect与field的当前值相同，设置filed的值为这个新值
   * @return true if the field was changed.
   *                             如果field的值被更改
   */
  public native boolean compareAndSwapInt(Object obj, long offset,
                                          int expect, int update);
```                                         


### getAndIncrement() JDK1.6
      JDK1.6和1.8 的getAndIncrement()有些不一样
```java
    public final int getAndIncrement() {
        for (;;) {
            int current = get();
            int next = current + 1;
            if (compareAndSet(current, next))
                return current;
        }
    }
    public final boolean compareAndSet(int expect, int update) {
	return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
```    

### AtomicInteger原子操作
      https://blog.csdn.net/javazejian/article/details/72772470 里面有个例子AtomicIntegerDemo 为了证明AtomicInteger的  
      原子操作，但是这个例子并不好，因为该例子使用了join，即使不用AtomicInteger，用int，输出也依然是10000，查看我的例子：  
      IntPPDemo，因为join使10个线程串行执行，并没有产生抢占资源的情况，查看我的例子：AtomicIntegerDemo，该例子中，让10个线程  
      并行运行，然后sleep几秒之后， 查看AtomicInteger的结果
