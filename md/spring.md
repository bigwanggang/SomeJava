### 随记（书中或自己理解）
  服务对象和Repository分离，而不是将应用程序和数据访问耦合到一起，导致僵化设计，实现松耦合主要通过接口或Spring的Bean两种方式  
  纯粹的JPA方式远胜于基于模板的JPA（JpaTemplate）
  spring data 依赖配置： https://github.com/spring-projects/spring-data-examples/tree/master/bom  
  JPA里的repository就是传统的DAO，UserRepository 就是传统的UserDao，MyBatis里用Mapper，都是一个意思
  JPA里Repository只有接口，没有实现类
  
  @NoRepositoryBean:

  一般用作父类的repository，有这个注解，spring不会去实例化该repository。
### JdbcTemplate了解下


