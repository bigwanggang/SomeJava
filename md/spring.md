### 随记（书中或自己理解）
  服务对象和Repository分离，而不是将应用程序和数据访问耦合到一起，导致僵化设计，实现松耦合主要通过接口或Spring的Bean两种方式  
  纯粹的JPA方式远胜于基于模板的JPA（JpaTemplate）
  spring data 依赖配置： https://github.com/spring-projects/spring-data-examples/tree/master/bom  
  JPA里的repository就是传统的DAO，UserRepository 就是传统的UserDao，MyBatis里用Mapper，都是一个意思
  JPA里Repository只有接口，没有实现类
  
  @NoRepositoryBean:

  一般用作父类的repository，有这个注解，spring不会去实例化该repository。  
  springboot的Classpath目录：src/main/resources  
  springbootinaction中50页，配置文件application.properties或application.yml的优先级，“当前目录的“/config”子目录”的当前  
  目录指的是src同一级别的目录，另外yml配置文件优先级高于properites文件  
  @ConfigurationProperties注解是从properties配置文件中获取信息，但是该属性要有setter方法
### JdbcTemplate了解下

### SpringBoot相关
        spring.datasource.driverClassName=com.mysql.jdbc.Driver
        spring.datasource.url=jdbc:mysql://localhost:3306/test
        spring.datasource.username=root
        spring.datasource.password=root123
        以上为配置数据源
        通常无需指定JDBC驱动，springboot会帮助识别驱动，但是如果自动识别出现问题，就显示加上
        spring.datasource.drive-class-name=com.mysql.jdbc.Driver
        
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
    
    
    spring开发时如果要用H2数据库，除了把H2加入到maven依赖之外，还要在application.properties里增加H2的配置,增加的目的是可以通过  
    localhost：8080/h2-console访问内存数据库
    spring.datasource.url=jdbc:h2:mem:myproject，配置h2数据库的连接地址
    spring.datasource.driver-class-name=org.h2.Driver，配置JDBC Driver
    spring.datasource.username=root，配置数据库用户名
    spring.datasource.password=123456，配置数据库密码
    
    spring.h2.console.enabled=true
    spring.h2.console.path=/h2-console
    
    之后就可以通过浏览器访问H2数据库，访问：localhost：8080/h2-console,url\username\password都填入application.properties  
    的配置内容

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

### WebSecurityConfigurerAdapter
  springboot里的请求授权通常是扩展（extends）WebSecurityConfigurerAdapter，然后重写几个方法来完成

### maven
  maven工程的pom.xml文件的packaging标签有三个选项：pom、jar、war。pom表示该模块是个父模块，是被其他子模块继承的模块  
  maven子模块的依赖继承父模块的


### Jpa
  spring jpa mysql的配置方式  
  #Specify the DBMS  
  spring.jpa.database = MYSQL  
  #Show or not log for each sql query  
  spring.jpa.show-sql = true  
  #Hibernate ddl auto (create, create-drop, update)  
  spring.jpa.hibernate.ddl-auto = update  
  spring.jpa.hibernate.naming-strategy = org.hibernate.cfg.ImprovedNamingStrategy  
  #stripped before adding them to the entity manager)  
  spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect  

  OneToMany注解一般要联合@JoinColumn注解使用，作用是外键约束的字段名称，  
  @JoinColumn(name = “order_id”)中的order_id为many一方的外键字段  
  如下例子有Order和OrderRecord两个entity，在spring启动时会自动创建两个表
```java
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Long id;

    @OneToMany
    @JoinColumn(name = "order_id")
    private Set<OrderRecord> orderRecords;
}
@Entity
public class OrderRecord {
    @Id
    private Long id;
    private String name;
}
```
  使用show create table order_record\G; 查看表的详细信息
```sql  
  Create Table: CREATE TABLE `order_record` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq7tqcnthko0xae7xntn93n2ft` (`order_id`),
  CONSTRAINT `FKq7tqcnthko0xae7xntn93n2ft` FOREIGN KEY (`order_id`) REFERENCES `
orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
```
  可见order_id是作为order_record的一个字段，并且该字段外键关联Order的id
