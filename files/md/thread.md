### 非线程安全存在于实例变量，不会存在于方法内部的私有变量
### synchronized取得的锁是对象锁。例如：
    class Resource1{
      synchronized public void f(){
        //do something;
      }
    }
    如果多个线程同时拥有一个资源实例，同一时刻，只有一个现在能执行f()方法，其他线程要同步等待
    但是如果多个线程拥有多个实例，不需要同步，多个线程可以同时执行多个实例的f()方法
    class Resource2{
      synchronized static public void g(){
        //do something;
      }
    }
    如果一个方法同时被synchronized和static修饰，即使多个线程拥有多个实例，也是需要同步，因为这个方法是static，方法不属于实例，而属于类
    
    synchronized void method(){
        //code
    } 
    void method(){
        synchronized(this){
            //code
        }
    }
    以上两个是等同的
    
### Executor
    newSingleThreadExecutor 的例子：
```java
        public class NewSingleThreadExecutorTest {
        public static void main(String[] args) {
            ExecutorService service = Executors.newSingleThreadExecutor();
            for (int i = 0; i < 10; i++) {
                Runnable runnable = new MyRunnable(i);
                service.submit(runnable);
            }
        }
    }

    class MyRunnable implements Runnable {

        int i;

        public MyRunnable(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            for (int j = 0; j < 50; j++) {
                System.out.println(Thread.currentThread().getName() + "--" + i + ":" + j);
            }
        }
    }
```
    输出是：
```Bash
    pool-1-thread-1--0:0
    ...
    pool-1-thread-1--0:49
    pool-1-thread-1--1:0
    ...
    pool-1-thread-1--1:49
    pool-1-thread-1--2:0
    ...
```
    所有的线程都运行完（没有打印输出），程序还没有结束
    

### 多线程之wait、notify、notifyAll
-   notifyAll之后，所有的wait线程是不是要抢notify放开对象的锁呢？WaitNotifyDemo里证明了每个线程还是要抢锁
-   两个线程轮流打印奇偶数，AlternatePrintOddEven_Wrong的栗子是个错误的栗子，仔细分析错误原因    



### 写个程序证明延迟初始化在多线程中是不安全的
    
### @GuardedBy注解的作用
    https://blog.csdn.net/lihenair/article/details/61913542
    在《java并发编程实战》一书中，使用了@GuardedBy和@ThreadSafe\@ThreadUnsafe等注解，这些注解没有起到任何作用，只是提示作用
    
### Thread的join方法
    在A线程中调用B线程的join方法，则要等待B线程运行结束之后才执行A线程
    JoinTest例子中是一个普通的多线程例子，如果把t.join()注释掉就是十个线程并行运行，主线程也并行运行
    如果不注释掉t.join()，就是顺序执行10个线程，所有的线程执行结束后，再执行主线程的输出
    注：有的人喜欢把多个线程先依次start，然后再依次join，目的是把所有线程都启动了之后再等待所有线程执行，这样做的好处是不用sleep方法
    
    IntPPDemo例子中，虽然启动了10个线程来执行线程不安全的i++操作，但是由于线程start之后立即join，因此这10个线程是顺序执行的，所以输出10000
    但是如果把join去掉，或者先依次start，然后再依次join，最后输出的结果肯定小于10000
    
### CyclicBarrier
    实现一个例子，一个数组长度为n,启动n个线程，每个线程往数组里填写一个值，所有线程都把值填入完毕后打印整个数组，CyclicBarrierDemo

### ThreadLocal
    每个线程都保存一份副本，每个线程ThreadLocal.get()的值，都是该线程最后ThreadLocal.set的值，每个线程的值互不影响，《java并发编程实战》
    中：可以将ThreadLocal<T>视为包含了Map<Thread,T>对象，例子ThreadLocalDemo中，main主线程中和t线程都对ThreadLoca变量set新值，但是，
    get的值都是本线程的值

### 多线程书籍
- java多线程编程核心技术
- java并发编程的艺术

### 停止一个线程，isInterrupted和interrupted方法
    停止一个线程： https://www.cnblogs.com/jenkov/p/juc_interrupt.html  
    例子中InterruptTest的线程中执行for循环，在主线程中通过interrupt（）方法来中断线程，线程并没有中断，因为interrupt方法只是改变中断标志位  
    
### 比如：一个方法是阻塞等待返回的，怎么设置超时终止？
    一个办法是将该方法放在一个线程中执行，通过Callable的返回Future，Future的get可以设置超时终止，详见：StopBlockingThread


### volatile
    volatile能提供变量在多线程中的可见性，但是不要过度依赖volatile的可见性（java并发编程实战）  
    
### LockSupport
    LockSupport的park和unpark方法实现的功能和wait、notify差不多，但是它比后者好的地方是，wait和notify一定要先wait，后notify，才能生效，否则\
    wait会一直等下去，但是park和unpark却没有这样的问题
-   试着用LockSupport实现两个线程轮流打印奇偶数    


