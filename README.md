# JAVA

Reflections maven 依赖：
<dependency>
    <groupId>org.reflections</groupId>
    <artifactId>reflections</artifactId>
    <version>0.9.10</version>
</dependency>

#注意点：
导入hamcrest包后执行单元测试，有时会出现错误：
java.lang.NoSuchMethodError: org.hamcrest.core.AllOf.allOf(Lorg/hamcrest/Matcher;Lorg/hamcrest/Matcher;)Lorg/hamcrest/Matcher;

导致该错误的原因是jar引入顺序导致，因为如果hamcrest的jar包放到了junit jar包的后面则程序会默认调用junit jar包里面的 hamcrest core，就会出现上面的问题了
### 常用技术
- [java 基础](https://github.com/bigwanggang/JAVA/blob/master/nubility.md)
- [多线程](https://github.com/bigwanggang/JAVA/blob/master/thread.md)
- [mysql](https://github.com/bigwanggang/JAVA/blob/master/mysql.md)
