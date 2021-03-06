## ThreadPoolExecutor的构造函数：
```
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
```
#### corePoolSize
    corePoolSize是线程池的核心线程数量，即使没有线程加入到线程池，通过getCorePoolSize（）方法也可以获得核心线程数量，  
    例子：CorePoolAlwaysAlive
    
#### maximumPoolSize
    maximumPoolSize = corePoolSize(核心线程数) + 非核心线程数
    
#### keepAliveTime 和 unit
    非核心线程的闲置超时时间
    
#### workQueue
    常用的有三种队列，SynchronousQueue,LinkedBlockingDeque,ArrayBlockingQueue。
    
#### ThreadFactory

#### handler


https://blog.csdn.net/qq_25806863/article/details/71126867 这篇文章关于ThreadPoolExecutor讲解的很细致

## execute方法
```
    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        /*
         * Proceed in 3 steps:
         *
         * 1. If fewer than corePoolSize threads are running, try to
         * start a new thread with the given command as its first
         * task.  The call to addWorker atomically checks runState and
         * workerCount, and so prevents false alarms that would add
         * threads when it shouldn't, by returning false.
         *
         * 2. If a task can be successfully queued, then we still need
         * to double-check whether we should have added a thread
         * (because existing ones died since last checking) or that
         * the pool shut down since entry into this method. So we
         * recheck state and if necessary roll back the enqueuing if
         * stopped, or start a new thread if there are none.
         *
         * 3. If we cannot queue task, then we try to add a new
         * thread.  If it fails, we know we are shut down or saturated
         * and so reject the task.
         */
        int c = ctl.get();
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        else if (!addWorker(command, false))
            reject(command);
    }
```    
- https://www.cnblogs.com/trust-freedom/p/6681948.html#label_3_1 关于ThreadPoolExecutor源码的分析，这个文章也不错，jdk1.8
- 多个线程执行execute的时候，需不需要同步？栗子：IfExecuteNeedSync

## 分析
    内部主要变量如下：
```
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;
```    
    COUNT_BITS=29；
    CAPACITY=(1 << 29) - 1 = 0x1FFFFFFF (低29位都是1)
    RUNNING = -1 << 29 = 0xFFFFFFFF << 29 = 0xE0000000 (高3为为1), -1的二进制表示为32个1（0xFFFFFFFF）
    SHUTDOWN 的高3位为000
    STOP 的高3位为001
    TIDYING 的高3位为010
    TERMINATED 的高3位为011
    几个变量的大小关系位：RUNNING<SHUTDOWN<STOP<TIDYING<TERMINATED，RUNNING为负值
    
    内部的几个方法
```
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
```    
    runStateOf 是获取高3位的线程状态
    workerCountOf 是获取低29为的线程个数
    ctlOf 是将线程状态和线程个数 打包成一个值
