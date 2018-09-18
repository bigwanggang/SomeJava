ThreadPoolExecutor的构造函数：
```java
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
```
- corePoolSize
    corePoolSize是线程池的核心线程数量，即使没有线程加入到线程池，通过getCorePoolSize（）方法也可以获得核心线程数量，例子：CorePoolAlwaysAlive
    
- maximumPoolSize
    maximumPoolSize = corePoolSize(核心线程数) + 非核心线程数
    
- keepAliveTime 和 unit
    非核心线程的闲置超时时间
    
- workQueue
    常用的有三种队列，SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue。
    
- ThreadFactory

- handler


