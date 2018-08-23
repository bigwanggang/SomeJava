# idea 社区版创建web工程，并部署tomcat
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

# ArrayList的elementData为什么要修饰为transient?

# SpringBoot相关
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

# idea springboot 热部署
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
        
# 较新版的Spring Boot取消了@SpringApplicationConfiguration这个注解
        用@SpringBootTest就可以了
        @RunWith(SpringRunner.class)
