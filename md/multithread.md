#### 练习题：excel中有3w行数据，将这些数据缓存到mysql数据库，分别比较单线程和多线程所花费的时间

```sql
  create table info(
    name varchar(50),
    age varchar(10),
    height varchar(10),
    phone varchar(30),
    sex varchar(10),
    location varchar(50),
    job varchar(50),
    hometown varchar(50),
    education varchar(30),
    major varchar(40),
    message varchar(255)
  );
