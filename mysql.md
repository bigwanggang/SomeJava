### 数据库操作：
- 显示所有数据库：
```sql
show databases;
```
- 创建数据库：
```sql
create database database_name;
```
- 切换数据库：
```sql
use database_name;
```
- 查看创建好的数据库的定义：
```sql
show create database database_name\G;
```
- 删除数据库：执行删除数据库，该数据库中的数据一同被删除
```sql
drop database database_name;
```
### 表的操作
- 主键约束：
```sql
create table S(
	SID int primary key,
	SName char(25),
	SSex char(1),
	SProId int,
	SAge int
);
```
- 多字段联合主键：
```sql
create table S(
	SID int,
	SName char(25),
	SSex char(1),
	SProId int,
	SAge int,
	PRIMARY KEY(SID,SName)
);
```
- 外键约束：
```sql
create table department(
	id int(11) primary key,
	name varchar(22) not null,
	location varchar(50)
);
create table employee(
	id int(11) primary key,
	name varchar(25) unique,
	deptId int(11),
	salary FLOAT,
	CONSTRAINT fk_emp_dept FOREIGN KEY(deptId) REFERENCES department(id)
);
```

- 查看表结构：
```sql
 	desc table_name
```
- 查看表的详细结构：
```sql
	show create table table_name\G;
```
- 修改表的存储类型
```sql
	alter table table_name engine=myisam;
```
 ### 问题
 - mysql主从复制原理及流程
 - primary key 和 UNIQUE区别
 - 一张表,里面有ID自增主键,当insert了17条记录之后,删除了第15,16,17条记录,再把Mysql重启,再insert一条记录,这条记录的ID是18还是15 ?
- 请简述项目中优化sql语句执行效率的方法

### 总结
- 外键约束不能夸引擎使用
- 一个表智能有一个字段使用auto_increment,且该字段必须为主键的一部分
