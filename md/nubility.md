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

### SpringBoot相关
        spring.datasource.driverClassName=com.mysql.jdbc.Driver
        spring.datasource.url=jdbc:mysql://localhost:3306/test
        spring.datasource.username=root
        spring.datasource.password=root123
        以上为配置数据源
        
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jackson.serialization.indent_output=true
        (以上引自 java开发颠覆者springboot P266)
        
        
      springboot 搭建eureka 服务，pox.xml如下，如果没有加dependencyManagement，就不能引入相关jar
     <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.4.7.RELEASE</version>
    </parent>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Camden.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
    </dependencies>

### idea springboot 热部署
        1.添加依赖
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
         2.添加插件
         <build>
                <plugins>
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                        <configuration>
                            <fork>true</fork>
                        </configuration>
                    </plugin>
                </plugins>
        </build>
        3. idea setting
        file->Settings->Build,Execution,Deplment->Compiler , 选择Build project automatically 点击OK按钮
        4. 组合键：Shift+ALT+Ctrl+/ ，选择“Registry”，回车，找到“complier.automake.allow.when.app.running” 
        
### 较新版的Spring Boot取消了@SpringApplicationConfiguration这个注解
        用@SpringBootTest就可以了
        @SpringBootTest
        @RunWith(SpringRunner.class)
	
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
        
### 需要强化
	1. 多线程基础，多线程的应用
	2. jvm基础
	3. mysql
	4. spring

### java日志学习

### idea 快捷键
	
	