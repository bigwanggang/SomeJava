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
