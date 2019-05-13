## 查书
- 豆瓣读书里查找： https://book.douban.com/
- 图灵社区：http://www.ituring.com.cn/
## git仓库分类
- spring相关 ： https://github.com/OhLaughing/SpringLearn
## 要看的书单
- 大型网站系统与Java中间件开发实践
- 分布式Java应用 : 基础与实践
- Head First 设计模式（中文版）
- 大规模分布式存储系统 : 原理解析与架构实战
- spring揭秘
- spring实战
- mysql必知必会
- Java性能优化权威指南 
- 深入理解JVM虚拟机 -- https://github.com/zlserver/jvm_code
- Java虚拟机规范(Java SE 7版)
- Java并发编程实战
- Java并发编程艺术
- Effective Java -- https://github.com/marhan/effective-java-examples
- MySQL技术内幕：innodb存储引擎
- 大型网站技术架构 : 核心原理与案例分析
- 码出高效：Java开发手册
- 深入分析Java Web技术内幕（修订版）
- 重构，改善既有代码的设计
- Docker——容器与容器云
- 第一本Docker书 修订版:https://github.com/turnbullpress/dockerbook-code
- Spark快速大数据分析: https://github.com/databricks/learning-spark
- Kubernetes权威指南:从Docker到Kubernetes实践全接触： http://www.3322.cc/soft/33984.html
- 深入剖析Tomcat： https://github.com/OhLaughing/HowTomcatWorks
-  https://www.jianshu.com/p/7122916a63fa
- https://blog.csdn.net/u012410733/article/details/51869105

## 遗留技术问题
- Thread.currentThread().getContextClassLoader() 和 Class.getClassLoader()区别

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
- 一个字符串：helloworld hellonihao helloworld，怎么通过正则把后面不是world的hello改成goodbye，
str.replaceAll("hello(?!world)", "goodbye");

 (?=pattern) 为肯定式前瞻，(?!pattern) 为 否定式前瞻
后顾则用的比较少，形式为
(?<=pattern) 肯定式后顾 ， (?<!pattern) 否定式后顾
- 正则的文章： https://blog.csdn.net/u013197629/article/details/73499129
- 动态代理： https://blog.csdn.net/zpf336/article/details/82751925
- 同步和异步可以同打电话和发短信来形象的比喻
- InnerClassDemo是内部类和静态内部类的区别demo
- LinkedList在1.6是双向循环链表，1.7之后是双向链表（没有循环）
- 为什么双重检查锁需要两次null检查
- Fail-Fast机制研究：FailFastForEachRemoveDemo这个栗子是产生Fail-fast问题的栗子，执行该程序会抛出ConcurrentModificationException，
而FailFastIteratorRemoveDemo就可以解决这个问题，详细原因可通过javap反编译字节码来分析（javap -v),详细反编译后的代码不贴出，将两个class文件反编译后对比发现，通过foreach的方式remove相当于调用List的remove, 而通过Iterator的方式remove是调用的Iterator迭代器的remove
这两个remove有什么区别？原理ArrayList和LinkedList里都有一个modCount字段，每当list有变动，add或remove，modCount都加1，
modCount的作用用jdk里面的描述是：The number of times this list has been structurally modified，出现fail-fast的原因是每个集合都可以通过迭代器的方式访问，ArrayList的迭代器中有个expectedModCount字段，每次通过迭代器访问List都会先检查expectedModCount和ArrayList的modCount的值是否相等，如果不等就抛ConcurrentModificationException，调用List的remove相当于ArrayList的modCount值加，而迭代器的expectedModCount值没变，因此下次执行next就会抛异常，而Iterator的方式remove是将modCount和expectedModCount的值同时修改

## 好书多看看计划（只写一本，不要写很多，然后写完就忘了，也不看）
- SpringBoot 揭秘 : 快速构建微服务体系 看透！

## java多线程编程核心技术阅读记录
1. P19 countoperate this.isAlive()
2. interrupt()
3. gc线程是守护线程
4. synchronized(x) 和 synchronized 方法 、synchronized（this） 等同
5. synchronized(A.class)方法加static与不加static的区别
6. jps与jstack命令查看死锁
7. P110 静态内部类PublicClass栗子
8. P131 synchronized保证多线程可见性栗子
9. 假死： 所有线程都在waiting，没有线程notify
10. wait、notify熟练生产者、消费者和交替打印
11. join内部用wait实现
12. P188 Run1栗子
13. 单例模式之readResolve()


## 好的文章，好用的工具，好的github项目
- redis工具： https://github.com/MicrosoftArchive/redis
- Thymeleaf: https://www.thymeleaf.org/documentation.html, 里面有个在线交互的学习教程
- Thymeleaf+ Spring 整合，中文翻译版本：https://www.cnblogs.com/jiangchao226/p/5937458.html
- Visual Studio Code这个工具不错，有时间研究研究
- vue 官方guide: https://vuejs.org/v2/guide/
- springboot+vue 工程搭建的流程： https://my.oschina.net/u/3491123/blog/1592935?nocache=1514124797918

## 消息
  ActiveMQ 是jms消息的实现，jms消息提供的消息的规范和接口，是比较老的消息队列（相比RabbitMq）
  
  
## 各种面试经历及问题
- 蚂蚁金服： https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247485251&idx=1&sn=21e5da0d76dd71d165f19015ebeba780&chksm=cea24888f9d5c19e041a145e6da3d4fa94f63b34c71d43f10c29340c7d51a4a23971904d19b5&mpshare=1&scene=1&srcid=&pass_ticket=jtM94aiSbJdtNX9PjlycM%2BEW7hqmh5%2FAs89%2FsvqJEOn8%2BKjx%2FO5Vu6k5SswU2TmE#rd
- 蚂蚁金服：https://github.com/Snailclimb/JavaGuide/blob/master/docs/essential-content-for-interview/BATJrealInterviewExperience/%E8%9A%82%E8%9A%81%E9%87%91%E6%9C%8D%E5%AE%9E%E4%B9%A0%E7%94%9F%E9%9D%A2%E7%BB%8F%E6%80%BB%E7%BB%93(%E5%B7%B2%E6%8B%BF%E5%8F%A3%E5%A4%B4offer).md
- 蚂蚁金服：https://github.com/Snailclimb/JavaGuide/blob/master/docs/essential-content-for-interview/BATJrealInterviewExperience/5%E9%9D%A2%E9%98%BF%E9%87%8C,%E7%BB%88%E8%8E%B7offer.md
- https://github.com/Snailclimb/JavaGuide/blob/master/docs/essential-content-for-interview/BATJrealInterviewExperience/2019alipay-pinduoduo-toutiao.md
- 海量数据的面试题：https://blog.csdn.net/v_july_v/article/details/7382693
- https://blog.csdn.net/v_JULY_v/article/details/6256463
- 最小堆动画图：http://www.benfrederickson.com/heap-visualization/
- java   https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247484884&amp;idx=1&amp;sn=0d9b841ce0fc300c78ade2a87ffbfb46&source=41#wechat_redirect
- 搞定 JVM 垃圾回收就是这么简单： https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247484877&idx=1&sn=f54d41b68f0cd6cc7c0348a2fddbda9f&chksm=cea24a06f9d5c3102bfef946ba6c7cc5df9a503ccb14b9b141c54e179617e4923c260c0b0a01&token=1082669959&lang=zh_CN&scene=21#wechat_redirect
- TCP/IP : https://www.cnblogs.com/wulala1119/p/4749892.html


## 面试题知识点
- 2019-01-08：HashMap的底层实现原理？ 主要熟悉put和resize方法
- 2019-01-09: java中的错误和异常：Throwable、Error、Exception、RunTimeException
- 2019-01-10: java中的集合框架
- 2019-01-16: java中不可变类的理解。例如String
- 2019-01-16: 实现单例的方式及注意事项
- 2019-01-22: 两个线程交替打印奇偶数（第一次徒手没有写出来，后来参考资料才写出来，下午再写一次，要熟练，wait\notify 和Condition两种方式都要会）
- a = a + b 与 a += b 的区别
- Serial 与 Parallel GC之间的不同之处？
- Java 中 WeakReference 与 SoftReference的区别？
- 解释 Java 堆空间及 GC？
- a.hashCode() 有什么用？与 a.equals(b) 有什么关系？
- .float f=3.4;是否正确?
- Java 中，Comparator 与 Comparable 有什么不同？
- synchronized的三个作用？
- 一个类的两个synchronized static方法同步吗?
- 双重检查锁
- Runnable接口和Callable接口区别
- AtomicInteger
- 编译与解释共存
- 父类的私有属性和构造方法并不能被继承，所以 Constructor 也就不能被 override
- 重写： 发生在父子类中，方法名、参数列表必须相同，返回值范围小于等于父类，抛出的异常范围小于等于父类，访问修饰符范围大于等于父类；如果父类方法访问修饰符为 private 则子类就不能重写该方法。
- Java 面向对象编程三大特性
- 类中所有的private方法都隐式地指定为final。
- 静态内部类和非静态内部类区别
- 静态代码块对于定义在它之后的静态变量，可以赋值，但是不能访问.
- RandomAccess接口：RandomAccess 接口只是标识，并不是说 ArrayList 实现 RandomAccess 接口才具有快速随机访问功能的！
- 1.8HashMap的hash函数：高16位与低16位异或
- hashMap是怎么导致死循环的？画图搞懂！
- ConcurrentHashMap就了解1.7和1.8就星了，1.6就不用看了
- 单例的instance上加不加final的区别？
- Integer相关的算法要注意溢出问题
- long l = Integer.MAX_VALUE + 1; // l = -2147483648,  如果不想溢出可以用：long a = 1; long l = Integer.MAX_VALUE + a; 
- -1>>1,和-1>>>1的值分别是什么？
- MessageFormate 如果遇到单引号会不能替换，可以通过两个单引号来解决：https://winse.iteye.com/blog/1830987
- 对于Resultset 和Statement 对象可以不进行显式回收，但Connection 一定要显式回收，因为Connection 在任何时候都无法自动回收，
而Connection一旦回收，Resultset 和Statement 对象就会立即为NULL。但是如果使用连接池，情况就不一样了，除了要显式地关闭连接，
还必须显式地关闭Resultset Statement 对象（关闭其中一个，另外一个也会关闭），否则就会造成大量的Statement 对象无法释放，
从而引起内存泄漏。这种情况下一般都会在try里面去的连接，在finally里面释放连接。
- HotSpot虚拟机不区分虚拟机栈和本地方法栈
- java类哪些场景执行初始化？（6个场景）
- 方法区是虚拟机规范，永久代是HotSpot对方法区的实现
- finalize()方法不需要学会使用
- 常量池中有类的符号引用
- JVM不是一开始就把所有的类都加载进内存中，而是只有第一次遇到某个需要运行的类时才会加载，且只加载一次。
- 多线程向ArrayList和LinkedList中add元素会发生什么？
- javap jdk自带工具，能够对class字节码反编译
