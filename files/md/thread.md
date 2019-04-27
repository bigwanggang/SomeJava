### 非线程安全存在于实例变量，不会存在于方法内部的私有变量
### synchronized取得的锁是对象锁。例如：
```java
    class Resource1{
      synchronized public void f(){
        //do something;
      }
    }
```   

-   如果多个线程同时拥有一个资源实例，同一时刻，只有一个现在能执行f()方法，其他线程要同步等待
    但是如果多个线程拥有多个实例，不需要同步，多个线程可以同时执行多个实例的f()方法
```java
    class Resource2{
      synchronized static public void g(){
        //do something;
      }
    }
```    

-   如果一个方法同时被synchronized和static修饰，即使多个线程拥有多个实例，也是需要同步，因为这个方法是static，方法不属于实例，而属于类
```java
    synchronized void method(){
        //code
    } 
    void method(){
        synchronized(this){
            //code
        }
    }
```
   以上两个是等同的
    
 
-   一个类如果有多个synchronized static方法，所有的synchronized static方法是同步的，也就是多线程即使访问不同的synchronized static方法，也是要阻塞，栗子：SynchronizedStaticDemo
-   以下两个同步的方法是相同的， 实际上第一个同步方式即使不是static，和第二个方法也是互斥的
```java
public static void g() {
        synchronized (SynchronizedStaticDemo.class) {}
public synchronized static void g() {        
}
```
-   下面两个用synchronized实现的锁有什么区别：synchronizedLockClassStatic的栗子中就是一个Service类中有两个synchronized（Class）方法，但是两个
方法一个有static，一个没有static，通过测试发现，两个方法依然同步
```java
public static void g() {
        synchronized (SynchronizedStaticDemo.class) {}
public void g() {
        synchronized (SynchronizedStaticDemo.class) {}
```


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
-   在A线程中调用B线程的join方法，则要等待B线程运行结束之后才执行A线程
    JoinTest例子中是一个普通的多线程例子，如果把t.join()注释掉就是十个线程并行运行，主线程也并行运行
    如果不注释掉t.join()，就是顺序执行10个线程，所有的线程执行结束后，再执行主线程的输出
    注：有的人喜欢把多个线程先依次start，然后再依次join，目的是把所有线程都启动了之后再等待所有线程执行，这样做的好处是不用sleep方法
    
    IntPPDemo例子中，虽然启动了10个线程来执行线程不安全的i++操作，但是由于线程start之后立即join，因此这10个线程是顺序执行的，所以输出10000
    但是如果把join去掉，或者先依次start，然后再依次join，最后输出的结果肯定小于10000
    
-   通过DeepInJoin的栗子可以对join有个深入的理解，   可以通过打印发现执行完join之后，要该线程执行完之后才能执行下一个线程。
    
### CyclicBarrier
-   实现一个例子，一个数组长度为n,启动n个线程，每个线程往数组里填写一个值，所有线程都把值填入完毕后打印整个数组，CyclicBarrierDemo
-   CyclicBarrier和CountDownLatch的区别，CyclicBarrier是在await方法上阻塞，await的线程的数量达到了既定的数量，才会同时继续执行，而CountDownLatch却不阻塞，因此如果是放在线程池中执行任务，对于CyclicBarrier一定要确保有足够的容量才行
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
-   volatile能提供变量在多线程中的可见性，但是不要过度依赖volatile的可见性（java并发编程实战）  
-   volatile可见性的栗子，例如：VolatileDemo,通常要是要复现是有条件的，要是64位jvm，并且-server模式运行（java多线程编程核心技术）  
    
### LockSupport
-   LockSupport的park和unpark方法实现的功能和wait、notify差不多，但是它比后者好的地方是，wait和notify一定要先wait，后notify，才能生效，否则
    wait会一直等下去，但是park和unpark却没有这样的问题,LockSupport.unpark是个某个线程一个许可，之后如果遇到LockSupport.park,就不会阻塞，但是多个unpark并不能给多个许可，要park之后才能再次给许可
-   试着用LockSupport实现两个线程轮流打印奇偶数，由于执行wait是释放锁，等待其他线程执行notify，才能达到轮流交替的效果，而LockSupport.park并不释放锁，因而用LockSupport不能实现轮流打印奇偶数

### CopyOnWriteArrayList和ConcurrentSkipListMap了解


### 用DelayQueue模拟实现抢票一定时间内没有付款，票自动失效的栗子
-   在blockingqueue.buyTicket，实现了简单的DelayQueue实现买票的功能，但是需要完善
-   DelayQueueRemoveDemo的栗子演示多线程对DelayQueue调用Remove, 为什么List就会出现ConcurrentModificationException（栗子：ArrayListRemoveDemo），fail-fast机制研究下
### 延迟队列
-   首先要一个实现Delayed接口的类, 并实现接口的两个方法，compareTo是Comparable的方法(两个方法都要实现才可以)。
```java
long getDelay(TimeUnit unit)；
public int compareTo(T o);
```
实现该接口主要完成一件事：计算出该事件延迟的，通常是下面的方法算出来的，
```java
this.executeTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MICROSECONDS) + System.nanoTime();
```
第一部分是TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MICROSECONDS)，把传入的值转成纳秒,其实就是很简单的单位转换,
但是这里一定要转换成纳秒，因为只有System.nanoTime(),没有别的，关于System.nanoTime()和System.currentTimeMillis()的区别，之后熟悉
秒、毫秒、微妙、纳秒，从前往后，以此是1000倍，其实也可以不用这个方法来转，可以直接乘1000的来算
```java
        System.out.println(TimeUnit.MICROSECONDS.convert(1, TimeUnit.MILLISECONDS)); //1000
        System.out.println(TimeUnit.MICROSECONDS.convert(1, TimeUnit.SECONDS));      //1000*1000
        System.out.println(TimeUnit.NANOSECONDS.convert(1, TimeUnit.MICROSECONDS));  //1000
```
getDelay方法我看网上很多都是这么写的，要把刚才计算出来的时间跟当前的nanoTime比较，如果返回正数说明还没到，如果返回负数就是时间到了
```java
    public long getDelay(TimeUnit unit) {  
        return unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);  
    }
```
有个疑问：如果只是查看this.excuteTime - System.nanoTime()的正负，为什么还非要单位转换一下，1000 纳秒，转成啥都是正值啊，只不过由1000和1的区别
后来想想，还是有区别的，999对于纳秒是个挺大的值，但是如果转成微妙就是0，如果0对于getDelay有意义的话，这么转换就有必要，不管怎么样，就按照这个方法写吧。
compareTo方法也是比较executeTime，详细参考Message类
