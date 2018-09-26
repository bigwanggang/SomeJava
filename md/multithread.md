#### 练习题：excel中有3w行数据，将这些数据缓存到mysql数据库，分别比较单线程和多线程所花费的时间

```sql
  create table info(
    name varchar(50) not null,
    age varchar(10) not null,
    height varchar(10) not null,
    phone varchar(30) not null,
    sex varchar(10) not null,
    location varchar(50) not null,
    job varchar(50) not null,
    hometown varchar(50) not null,
    education varchar(30) not null,
    major varchar(40) not null,
    message varchar(255) not null
  );
