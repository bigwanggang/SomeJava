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
    
![image](https://github.com/bigwanggang/JAVA/tree/master/picture/jvm.jpg)
![image](https://github.com/ButBueatiful/dotvim/raw/master/screenshots/vim-screenshot.jpg)

### 多线程之wait、notify、notifyAll

### ThreadLocal

### 写个程序证明延迟初始化在多线程中是不安全的
    
### @GuardedBy注解的作用
    https://blog.csdn.net/lihenair/article/details/61913542
    在《java并发编程实战》一书中，使用了@GuardedBy和@ThreadSafe\@ThreadUnsafe等注解，这些注解没有起到任何作用，只是提示作用
    
### Thread的join方法
    在A线程中调用B线程的join方法，则要等待B线程运行结束之后才执行A线程
    JoinTest例子中是一个普通的多线程例子，如果把t.join()注释掉就是十个线程并行运行，主线程也并行运行
    如果不注释掉t.join()，就是顺序执行10个线程，所有的线程执行结束后，再执行主线程的输出
    
