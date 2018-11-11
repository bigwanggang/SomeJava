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
  目录指的是src同一级别的目录
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


