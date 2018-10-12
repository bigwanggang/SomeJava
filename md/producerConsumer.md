### 用阻塞队列实现生产者消费者模式
  见 ProducerConsumer.java, 该例子中，用ArrayBlockingQueue实现，这个队列最大能存储5个元素，一个生产者，一个消费者

### 用wait() notiry()实现生产者消费者模式
  执行wait()\ notify()之前，首先要获得对象的锁，锁的对象和执行wait的对象应该是一个对象，而不应该锁一个对象，而去执行另一个对象的wait notify
  例如：下面的例子：Resource中锁的是list，然后对Resource对象执行wait是不对的，Resource1 和 Resource2是正确的
```java
  public class Resource {
    LinkedList<String> list = new LinkedList<>();

    public void addProduce() {
        synchronized (list) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
  
public class Resource1 {
    LinkedList<String> list = new LinkedList<>();

    public void addProduce() {
        synchronized (list) {
            try {
                list.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Resource2 {
    LinkedList<String> list = new LinkedList<>();

    public synchronized void addProduce() {

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

```
  
