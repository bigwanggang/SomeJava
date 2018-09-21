## ThreadPoolExecutor的构造函数：
```java
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
```
#### corePoolSize
    corePoolSize是线程池的核心线程数量，即使没有线程加入到线程池，通过getCorePoolSize（）方法也可以获得核心线程数量，<br>例子：CorePoolAlwaysAlive
    
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
```java
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
https://www.cnblogs.com/trust-freedom/p/6681948.html#label_3_1 关于ThreadPoolExecutor源码的分析，这个文章也不错，jdk1.8
