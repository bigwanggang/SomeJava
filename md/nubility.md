### idea 社区版创建web工程，并部署tomcat
1. 创建webapp工程
2. Edit Configurations->add maven   Command Line: tomcat7:run
3. 先在pom.xml中加入tomcat7的插件
        <build>
            <finalName>TestArtif</finalName>
            <plugins>
              <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.1</version>
                <configuration>
                  <port>9090</port>
                  <path>/</path>
                  <uriEncoding>UTF-8</uriEncoding>
                  <server>tomcat7</server>
                </configuration>
              </plugin>
            </plugins>
         </build>
引用自： https://blog.csdn.net/u012364631/article/details/47682011

### ArrayList的elementData为什么要修饰为transient?
        ArrayList的实现原理是动态数组，elementData是个存储数据的数组，随着不断的往数组add数据，如果数组容量不够，要进行扩容，ArrayList扩容是在当前数组容量的1.5倍扩容，ArrayList里真正存储数据的个数是size，size肯定是小于elementData数组的容量的，elementData用transient修饰的目的是，序列化的过程中不序列化elementData，而是通过实现readObject（）和writeObject（）两个方法来实现序列化，问：readObject（）和writeObject（）是private的，是怎么调用的？

	
### 注意点： 导入hamcrest包后执行单元测试，有时会出现错误： java.lang.NoSuchMethodError: org.hamcrest.core.AllOf.allOf(Lorg/hamcrest/Matcher;Lorg/hamcrest/Matcher;)Lorg/hamcrest/Matcher;

导致该错误的原因是jar引入顺序导致，因为如果hamcrest的jar包放到了junit jar包的后面则程序会默认调用junit jar包里面的 hamcrest core，就会出现上面的问题了
        
### Redis 查看所有的key的命令： keys *  
### git相关
 - git clean -xfd 清除所有
 - git branch -a 查看远程分支
 - git branch 查看本地分支
 - git branch branchName 创建本地分支
 - git branch -d branchName 删除本地分支
 - git checkout branchName 切换到新分支(该branchName 是git branch命令 查看显示的分支，是本地分支)
 - git checkout -b branchName 相当于 git branch branchName + git checkout branchName
        
- 从远程分支拉到本地分支
        git checkout -b localName remoteBranchName  
        git branch -vv 查看本地分支和远程分支的对应关系  
        git tag 可以查看标签  
        查看完标签可以用git reset tag 切换到标签  
        git add . 增加文件，删除文件时 git add -A .  
        git push origin master 将本地的master分支推送到origin主机的master分支  
        git reset 提交号 回到某次提交（提交号通过git log查看）  
        git reset --hard 提交号 文件恢复到某次提交  
        这时如果想要回到最新状态 git reset --hard origin/master           
                               git reset --hard origin/V12.17.10  
                               
        unlink of file .. failed should I try ... 出现此告警提示，执行如下操作：
        git gc --auto
        git repack -d -l
        
        查看某个文件的提交记录
        git log 文件名
        git log --pretty=oneline 文件名
        
        删除远程分支 git branch -r -d origin/branchname
        git push origin :branchName
	
	git提交提示错误：can not push in mirror
	原因解析git 没有配置pushInsteadOf , 可以通过命令配置，也可以通过修改.gitconfig
	.gitconfig 位置 window ： c:\\users\\user 下
			linux : /root/ 下
			
        
### spring cloud
        每个使用配置管理的客户端项目中一个名为bookstrap.yml的本地配置文件，用来设定连接配置管理服务器、应用的名称、以及需要有配置管理服务器提供的配置文件等参数
        例如：
        spring:
          application:
            name:web
          profiles:
            active:development
          cloud:
            config:
              uri:http://localhost:8888
                        
     以上配置是一个配置管理客户端的bookstrap.yml的配置信息，它包含的信息是：该客户端使用的配置文件是http://localhost:8888配置服务器的web-development.yml配置文件，如果profiles.active不配置，则对应web.yml配置文件
     
     @RefreshScope是解决配置信息在线实时更新的，可以用在服务端和客户端，但是必须要有spring-boot-starter-actuator 这个依赖才可以

### docker
        docker build -t demo:v1 . 制作镜像
        
        
### Dockerfile
        FROM 基础镜像,必须为第一条指令
        VOLUME 可以实现挂载功能
        语法为： VOLUME ["/data"]
        RUN 运行指定的命令，例如：
                RUN /bin/bash -c 'echo hello'
                RUN ["/bin/bash", "-c", "echo hello"]
        ADD 复制命令，把文件复制到镜像中， 如果把虚拟机与容器想象成两台linux服务器的话，那么这个命令就类似于scp
        ENTRYPOINT 功能是启动是的默认命令
        语法如下：

        1. ENTRYPOINT ["executable", "param1", "param2"]
        2. ENTRYPOINT command param1 param2

### hashMap和HashTable 区别
### 任务
        java实现死锁
        java锁的理解， synchronize，Lock 区别和优缺点
        pubilc synchronized void test() {}
 
 
        pubilc synchronized void test() {}   //synchronized用在方法上
        
        synchronized也可以用在代码块上：
        public void test() {
             synchronized(obj) {
                  System.out.println("===");
             }
        }
        synchronized 用在方法和代码块上有什么区别呢？
### Java 基础
- 有点类构造方法里会有super(),目的是引用父类的无参构造器，这个super()是可以省略的
- 在idea里执行 Thread.activeCount() 为什么为2
- Integer和AtomicInteger区别
- 优先使用ConcurrentHashMap,而不是Collections.SynchronizedMap或Hashtable
- Runnable与Callable的区别
- 引用类的final static常量，不会初始化该类，引用static变量，会初始化该类
	例子：

``` java
        public class InitialTest {
            public static void main(String[] args) {
                System.out.println(Class1.a);
                System.out.println(Class2.a);
            }
	            class Class1 {
            final static String a = "hello";

            static {
                System.out.println("Class1 init");
            }
        }

        class Class2 {
            static String a = "hello";

            static {
                System.out.println("Class2 init");
            }
        }
        }
```
	输出为：
	hello
	Class2 init
	hello
	
- BlockingQueue 了解一下
- Thread的interrupt()不会中断正则执行的线程，只会中断sleep、wait、join的阻塞状态的线程
- 查看程序InterruptTest和InterruptTest1的区别，只在InterruptTest1里面中断了阻塞的状态，抛出异常：java.lang.InterruptedException: sleep interrupted，通过interrupt()中断阻塞的线程，只是将线程的中断标志位置1，该中断标志位可通过isInterrupted()获得。
- ReentrantLock常用的有三个锁的方法，lock(), tryLock(),lockInterruptibly（）,弄清楚
- LockTest大概演示了lock（）的用法，当一个线程想要获取一个锁的时候，如果这个锁被其他线程占有，这个线程就处于阻塞的状态，此时如果执行该线程的interrupt()方法，会把该线程的中断标志位置1，并不会立刻终止该线程，LockTest的输出为：
```Bash
	Thread-0 in run()...
	Thread-0 out run()...
	Thread-1 in run()...
	Thread-1 interrupt()...
```
	说明t2线程执行interrupt（）之后，还是继续等待并获取了锁，只是在执行阻塞方法sleep时，由于该线程的中断标志位为1，所以直接抛出异常
	
- LockInterruptTest 演示了lockInterruptibly()方法，当一个线程想要获取一个锁的时候，如果这个锁被其他线程占有，这个线程就处于阻塞的状态，此时如果执行该线程的interrupt()方法，立刻抛出异常，因为lockInterruptibly()方法抛出InterruptedException
	
- JdbcTemplate 了解一下：https://www.cnblogs.com/tuhooo/p/6491913.html
- 平衡二叉树和红黑树熟悉一下
### 反射了解一下
- RTTI，编译器在编译时打开和检查.class文件
- 反射，运行时打开和检查.class文件   		
### 事务了解一下

### IDEA 编译中文乱码的问题
	bin文件夹下面idea64.exe.vmoptions和idea.exe.vmoptions这两个文件，分别在这两个文件中添加：-Dfile.encoding=UTF-8  
	找到intellij idea的file---settings---Editor---FileEncodings的GlobalEncoding和ProjectEncoding和  
	Default encoding for properties都配置成UTF-8

### 例子WriteInfo中，向excel写数据，有时会出现java.lang.OutOfMemoryError: Java heap space 异常，不同的机器出现异常几率不同
	解决方案：1. IDEA通过-Xms512M -Xmx800M，增加jvm内存值，-Xms表示最小内存， -Xmx表示最大内存
	
### 关于byte
```java
        byte b = -1;
        System.out.println(b);
        System.out.println(b & 0xff);
```
	输出为：-1,255
	原因是System.out.println的输入如果是byte类型，是要转换成int，-1的byte表示为1111_1111, -1的int表示
	为1111_1111_1111_1111_1111_1111_1111_1111, b & 0xff 相当于只截取低8位的值 

### 关于double的精度
```java
	double d = 19.99;
        double d1 = d*10;
	double d2 = 19.989999999999998, 
```
	d=19.99,但是d1=199.89999999999998,（可以试着打印输出看看）这是为什么呢？  
	因为不管是double（64位）还是float（32位），都是由固定的几位来存储小数，各个位的精度为1/2、1/4、1/8、1/16..., 当把19.99赋  
	值给d时，  一个double类型就不可能完全存储19.99的值，必然有一些精度的误差，但不是所有的double类型都不能完全存储，比如19.5就  
	可以完全存储在double  	类型中，  d和d2在内存中存储的是一样的，可以通过MyUtils.double2Bytes将double转成byte数组来查看 
	也可通过Double.doubleToRawLongBits来查看两个double类型转成long类型的值  
	如果对小数精度有要求，最好用BigDecimal，BigDecimal有3个构造方法，最好不要用double的构造方法，可以用BigDecimal.valueOf或  
	String的构造方法。下面里例子，输出值b1为1999.0，b3为1998.9999999999998
	
```java
        BigDecimal b1 = new BigDecimal("19.99").multiply(new BigDecimal("100"));
        System.out.println(b1);
	BigDecimal b3 = new BigDecimal(19.99).multiply(new BigDecimal(100));
        System.out.println(b3.doubleValue());
```
	BigDecimal的除法如果不能整除会抛异常，使用时必须先指定小数点精度  
	MyUtils.double2Bytes方法里面先通过Double.doubleToRawLongBits把double转成long，然后在把long的8个字节存入到byte数组中  
	q因为double和long都是64为的、8个字节，占用的空间是一样的，也可以通过Double.longBitsToDouble把long型转成double型数据  
        
### 需要强化
	1. 多线程基础，多线程的应用
	2. jvm基础
	3. mysql
	4. spring

### java日志学习

### idea 快捷键
	for循环快捷键：
	https://blog.csdn.net/mingjie1212/article/details/51143444
	
### github md 语法
	换行的方法：两个空格加一个回车，直接回车不能换行
	
### Gradle
	gradle下载慢的问题，用阿里的国内云仓库：
	repositories {
        //mavenCentral()
        maven {
            url 'http://maven.aliyun.com/nexus/content/groups/public/'
        }
    }
    
### @SuppressWarnings的作用
	@SuppressWarnings的作用是让编辑器对代码的一些警告保持沉默，例如代码中引用的过期的（@deprecation）方法，在ide中就会在该方法上加个横杠  
	以表示该方法已过期，但是如果不想在IDE里出现这样的提示就可以用@SuppressWarnings（"deprecation"）来解决， 还有类型转换时IDE也会提示，  
	如果想要忽略提示，就可以用@SuppressWarnings（"unchecked"）
	
### junit jmockit
	测试时发现个问题没有解决，如果想要mock一个静态方法，而该方法没有返回值（void）,网上找了一些方法都是有result 来返回的，但是不返回的方法还没找到  
	测试发现，不返回的static方法不用加result 只有一个方法mock就可以  
	有个例子可以继续研究DBUtils里有个静态getConn方法，StaticClass方法有个静态方法getSqlResult，引用了该方法  
	有个jmockit的的使用例子：http://jmockit.cn/showArticle.htm?channel=2&id=4　　
	
### java File
    最近的工作涉及文件相关的一些操作，记录下来
```
        for (String arg : args) {
            System.out.println("string: " + arg);
            File file = new File(arg);
            System.out.println("getAbsolutePath: " + file.getAbsolutePath());
            System.out.println("isDirectory: " + file.isDirectory());
            System.out.println("isAbsolute: " + file.isAbsolute());
            System.out.println("isFile: " + file.isFile());
            System.out.println("exist: " + file.exists());
        }
``` 
    如果输入为一个存在的目录输出为：
```
String: C:\Users\gustaov\Desktop\tmp
getAbsolutePath: C:\Users\gustaov\Desktop\tmp
isDirectory: true
isAbsolute: true
isFile: false
exist: true
```
    如果输入为一个不存在的目录：
```
String: C:\Users\gustaov\Desktop\tmp1
getAbsolutePath: C:\Users\gustaov\Desktop\tmp1
isDirectory: false
isAbsolute: true
isFile: false
exist: false
```    
    如果输入一个文件：
```
String: C:\Users\gustaov\Desktop\tmp\test.class
getAbsolutePath: C:\Users\gustaov\Desktop\tmp\test.class
isDirectory: false
isAbsolute: true
isFile: true
exist: true
```    
    如果只输入一个文件的相对路径：
```
String: test.class
getAbsolutePath: C:\Users\gustaov\Desktop\tmp\test.class
isDirectory: false
isAbsolute: false
isFile: true
exist: true
```    
    如果输入目录的相对路径
```
String: src
getAbsolutePath: C:\Users\gustaov\Desktop\tmp\src
isDirectory: true
isAbsolute: false
isFile: false
exist: true
```    

    只有文件存在（exist为true），才可能是文件或是目录，否则isDirectory和isFile肯定都是false
    