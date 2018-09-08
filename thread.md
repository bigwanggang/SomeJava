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
    
## Executor
    - [newSingleThreadExecutor 的例子]：（https://github.com/bigwanggang/JAVA/blob/master/src/main/java/com/bigwanggang/multithread/NewSingleThreadExecutorTest.java）
