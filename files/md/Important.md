## 查书
- 豆瓣读书里查找： https://book.douban.com/
- 图灵社区：http://www.ituring.com.cn/
## 要看的书单
- 大型网站系统与Java中间件开发实践
- 分布式Java应用 : 基础与实践
- Head First 设计模式（中文版）
- 大规模分布式存储系统 : 原理解析与架构实战
- spring揭秘
- spring实战
- mysql必知必会
- Java性能优化权威指南 
- 深入理解JVM虚拟机
- Java并发编程实战
- Java并发编程艺术
- Effective Java -- https://github.com/marhan/effective-java-examples
- MySQL技术内幕：innodb存储引擎
- 大型网站技术架构 : 核心原理与案例分析
- 码出高效：Java开发手册
- 深入分析Java Web技术内幕（修订版）
-  https://www.jianshu.com/p/7122916a63fa
- https://blog.csdn.net/u012410733/article/details/51869105

## 技术记录

- java内存分析工具， MemoryAnalyzer : http://www.eclipse.org/mat/  
  MemoryAnalyzer分析：https://www.cnblogs.com/trust-freedom/p/6744948.html  
- extend继承父类时，如果想对父类的方法引用，可以通过super.funcname()的方式来调用，例如ClassC栗子
- 今天在看spring源码时，对子类是否继承父类的private属性不是很确定，基础知识要巩固(子类是不能继承父类的private方法和属性的);  
- 工作中遇到的问题：git提交在不同的操作系统下换行符不一致导致出现大面积的文件改动（其实没有变化，只是换行符不同）, notepad++可以查看文件的所有字符，包括换行符，notepad++ --> view --> show symbol --> show all character  
> 解决办法：Format format = Format.getCompactFormat();  
        format.setLineSeparator("\r\n");  //CRLF  
        或者format.setLineSeparator("\n");   //LF
        
- 获取springboot工程resources下文件的方式
```
Resource resource = new ClassPathResource("123.xml");
        File f = resource.getFile();
```
- 路径的问题： 问题的出现可能是在windows下使用路径"\\path\\workspace\\code"，如果在linux下改路径一般不合法，要改成正斜杠：/，怎么能让该路径在  
windows和linux环境自动转换成相应的路径分隔符，下面的方式跑出异常:java.lang.IllegalArgumentException: character to be escaped is missing
```java
    String s = "\\path\\workspace\\code";
    String s1 = s.replaceAll("\\\\", File.separator);
```        
- HashMap里的hash和tableSizeFor方法要弄清楚怎么回事
- ClassLoader只能加载classpath下面的类，而URLClassLoader可以加载任意路径下的类
- String 深入学习,解释下面的例子输出的原因,??? 为什么这个测试在main中执行与UT的方式执行，得到的结果不一样？？？
```
        String s3 = new String("1") + new String("1");
        String s5 = s3.intern();
        String s4 = "11";
        System.out.println(s5 == s3);
        System.out.println(s5 == s4);
        System.out.println(s3 == s4);
```        
- 拆箱和装箱： https://www.cnblogs.com/dolphin0520/p/3780005.html， Integer、Short、Long、Boolean、Byte、Charactor,这六个类在由\
  基本类型到类的装箱的过程，本质都是调用valueOf方法，而且除Boolean之外，都是缓存-128-127之间的数，Boolean装箱方式赋值，是同一个值
```java
          Integer a1 = 100;
        Integer a2 = 100;

        Integer b1 = 200;
        Integer b2 = 200;

        System.out.println(a1==a2); //true
        System.out.println(b1==b2); //false
```
- GET请求发送一个TCP包，POST请求发送两个TCP包
- ping命令就是基于UDP的传输层协议
- 线程的几种状态，以及通过代码的方式理解线程的状态切换, 线程状态的理解，new 一个Thread就是NEW状态，调用start()之后，线程执行之前就是RUNNABLE状态\
  但是有一点代码的演示和我的理解有偏差，不是执行run（）就应该是RUNNING状态吗，怎么ThreadStatusDemo栗子中在run方法中打印线程的状态还是RUNNABLE状态？\
  BLOCKED状态：如果一个两个线程抢同一个资源，其中一个线程抢到了，另一个线程的状态就是BLOCKED,见栗子：ThreadStatusDemo_Blocked\
  WAITING：如果一个线程执行wait方法，则其状态为WAITING。见栗子：ThreadStatusDemo_WaitNotify, 执行join也是同样，栗子：ThreadStatusDemo_Join\
  TIMED_WAITING:如果线程调用Thread.sleep()或者TimeUnit.SECONDS.sleep();其状态为TIMED_WAITING，
- 关于线程的状态，很多资料上都是说有5中状态：NEW、RUNNABLE、RUNNING、BLOCKED、DEAD，但是通过thread.getState()方法，或查看State源码发现\
  状态有NEW、RUNNABLE、BLOCKED、WAITING、TIMED_WAITING、TERMINATED六个状态，咋整？？？\
  关于线程状态的一个文章：https://blog.csdn.net/pange1991/article/details/53860651
- 通过ThreadStatusDemo_Join和ThreadStatusDemo_JoinWithTime对比发现，join方法如果带参数（超时），则是TIMED_WAITING，如果不带参数则是WAITING状态
  
- 通过实现Runnable接口的方式在run方法里面通常不能抛出异常，但是可以通过setUncaughtExceptionHandler来实现，见栗子：SetUncatchedExceptionDemo 
- 不管用nio还是bio还是netty实现的服务器，都可以用telnet localhost 8080 来模拟客户端访问，其实，也可以用浏览器localhost：8080来访问，服务端收到的是HTTP的GET请求，但是通常服务端返回给客户端的信息在浏览器不能解析，所以客户端显示：“localhost 发送的响应无效。”。
- 尽量使用UT而不是浏览器的方式来测试应用
- jdk 5,6,7,8 对于版本号49,50,51,52
- Runtime.getRuntime().availableProcessors()可以查看运行电脑的cpu个数
- Integer.numberOfLeadingZeros 方法可以算出一个int值的2进制数（32位）从左第一个开始的连续的零的个数
- 总所周知，接口List继承自接口Collection，为什么Collection已经有了size()\isEmpty()等方法，List中还是再定义一次？？？
- 栗子：ArrayListFailFast中，如果把four一行注释掉，就运行正常，否则会抛出ConcurrentModificationException异常,(《码出高效》中栗子)， 原因分析：
用foreach方法遍历ArrayList，本质上是用迭代器的方式遍历，也就是调用迭代器的hasNext()\next()方法，如下，
```java
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }
```
- 多个线程往DelayQueue里remove元素，需不需要加锁, 不需要因为DelayQueue的remove里删除的操作有锁保护
- Iterator和Iterable的区别，Iterator是迭代器，Iterable的作用是让类有迭代功能，可以通过foreach方式遍历，详见例子StringIter



## 好书多看看计划（只写一本，不要写很多，然后写完就忘了，也不看）
- 《netty实战》


## 消息
  ActiveMQ 是jms消息的实现，jms消息提供的消息的规范和接口，是比较老的消息队列（相比RabbitMq）


## 一天弄懂一个面试题
- 2019-01-08：HashMap的底层实现原理？ 主要熟悉put和resize方法
- 2019-01-09: java中的错误和异常：Throwable、Error、Exception、RunTimeException
- 2019-01-10: java中的集合框架
- 2019-01-16: java中不可变类的理解。例如String
- 2019-01-16: 实现单例的方式及注意事项
- 2019-01-22: 两个线程交替打印奇偶数（第一次徒手没有写出来，后来参考资料才写出来，下午再写一次，要熟练，wait\notify 和Condition两种方式都要会）
