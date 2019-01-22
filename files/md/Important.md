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
  WAITING：如果一个线程执行wait方法，则其状态为WAITING。见栗子：ThreadStatusDemo_WaitNotify\
  TIMED_WAITING:如果线程调用Thread.sleep()或者TimeUnit.SECONDS.sleep();其状态为TIMED_WAITING，
- 关于线程的状态，很多资料上都是说有5中状态：NEW、RUNNABLE、RUNNING、BLOCKED、DEAD，但是通过thread.getState()方法，或查看State源码发现\
  状态有NEW、RUNNABLE、BLOCKED、WAITING、TIMED_WAITING、TERMINATED六个状态，咋整？？？
  
  
## 好书多看看计划（只写一本，不要写很多，然后写完就忘了，也不看）
- 《码出高效》

## 一天弄懂一个面试题
- 2019-01-08：HashMap的底层实现原理？ 主要熟悉put和resize方法
- 2019-01-09: java中的错误和异常：Throwable、Error、Exception、RunTimeException
- 2019-01-10: java中的集合框架
- 2019-01-16: java中不可变类的理解。例如String
- 2019-01-16: 实现单例的方式及注意事项
- 2019-01-22: 两个线程交替打印奇偶数（第一次徒手没有写出来，后来参考资料才写出来，下午再写一次，要熟练，wait\notify 和Condition两种方式都要会）
