### ArrayBlockingQueue
  ArrayBlockingQueue是由数组实现，创建对象时就要指定数组长度
```java
    /** The queued items */
    final Object[] items;

    /** items index for next take, poll, peek or remove */
    int takeIndex;

    /** items index for next put, offer, or add */
    int putIndex;

    /** Number of elements in the queue */
    int count;
```
### SynchronousQueue内部没有缓存，take和put方法都要阻塞
  例子：SynchronousQueueTest1和SynchronousQueueTest2，SynchronousQueueTest1是main主线程take，创建新线程等待3秒后put，SynchronousQueueTest2是main主线程put，创建新线程等待3秒后take

### 为什么 ArrayBlockingQueue可以选公平锁和非公平锁，而LinkedBlockingDeque只是默认的非公平锁
