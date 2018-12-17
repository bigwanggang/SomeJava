### 技术
-    通过命令行输入sql语句时，有时会输错，这时可以通过上下箭头切换到刚才输入错误的sql，  
	然后通过左右箭头重新输入错误的语句，但是即使按住左箭头按键，输入标识符的前进速度  
	依然很慢，这时可以按住ctrl按键，同时按←按键，速度就很快，因为是按字母来跳跃前进  
-   unoin是把两个表的全集打印出来，但是不包括重复的元素， 而union all 包括重复的元素

-   mysql不支持full join,可以用left join和right join的union all 来实现

-   对多个列实现降序排列要在多个列加desc
-   where 字句中多个条件，condition1 or condition2 and condition3,是condition1 or （condition2 and condition3),因为or的优先级没有and高  
如果想要先or后and可以这样：(condition1 or condition2) and condition3,
-  老版本mysql只是MyISAM引擎类型支持全文索引FULLTEXT ，新版本的InnoDB也已经支持。  

### join
	技术贴：http://www.runoob.com/mysql/mysql-join.html

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
- auto_increment
```sql
	create table student(
		id int primary key auto_increment,
		name varchar(25)
	) auto_increment=100;
```
* SELECT 和LIKE百分号通配符%匹配字符
* 查询authors 以B开头的book表中的bookid,authors,info,'B%'表示以B开头，%表示匹配0个或多个字符
```sql
	select bookid, authors,info from book where authors LIKE 'B%';
```
* SELECT 和LIKE 下划线‘_’匹配一个字符

 ### 问题
 - mysql主从复制原理及流程
 - primary key 和 UNIQUE区别
 - 一张表,里面有ID自增主键,当insert了17条记录之后,删除了第15,16,17条记录,再把Mysql重启,再insert一条记录,这条记录的ID是18还是15 ?
- 请简述项目中优化sql语句执行效率的方法
- 为什么平衡二叉树不适合做mysql的索引
- 阿里手册里的mysql每项都要了解
- MySQL中varchar与char的区别以及varchar(50)中的50代表的涵义

### 索引
```sql
	create table book(
	bookid int not null,
	bookname varchar(255) not null,
	authors varchar(255) not null,
	info varchar(255) default null,
	comment varchar(255) default null,
	year_publication YEAR not null,
	INDEX(year_publication)
	);
```
使用show create table book\G; 可以查看已经在year_publication字段加上索引
使用explain语句查看索引是否正在使用，explain select * from book where year_publication=1990 \G;
在已经存在的表创建索引：
```sql
	alter table book add index BkNameIdx(bookname(30));
```
- 可以使用 show index from book \G; 查看表中的索引
- 在book表的bookId字段建立名称为UniquedIdx的唯一索引
```sql
	alter table book add unique index uniqidIdx(bookid);
```
- 在book表的authors和info字段建立组合索引
```sql
	alter table book add unique index BkAuAndInfoIdx(authors(20),info(50));
	create index BkAuAndInfoIdx ON book(authors(20),info(50));
```
- 查看表的索引： show index from book \G;


### 总结
- 外键约束不能夸引擎使用
- 一个表只能有一个字段使用auto_increment,且该字段必须为主键的一部分
- 可以通过建表时增加auto_increment=100或alter table student auto_increment=100的方式修改默认初始值，但是Innodb引擎如果mysql服务端重启之后auto_increment的默认初始值会被刷新，而MyIsam引擎是将auto_increment的默认初始值存在文件中，即使服务器重启也不会改变
- select多列排序:
```sql
	select name,price from fruits order by name,price
```
	如果第一列是唯一的就只对第一列排序，如果第一列不唯一，才对第二列排序
	
	
	
```sql
	create table fruits(
		f_id char(10) primary key,
		s_id int(11) not null,
		f_name char(255) not null,
		f_price decimal(8,2) not null
	);
	create table suppliers(
		s_id int(11) primary key auto_increment,
		s_name char(50) not null,
		s_city char(50),
		s_zip char(10),
		s_call char(50) not null
		);
```

### mysql练习
	https://blog.csdn.net/javazejian/article/details/61614366
```sql
	create table items(
		id int(11) primary key auto_increment,
		name varchar(32) not null,
		price float(10,1) not null,
		detail text,
		pic varchar(64),
		createtime datetime not null
	);
	
	create table orderdetail(
		id int(11) primary key auto_increment,
		orders_id int(11) not null,
		items_id int(11) not null,
		items_num int(11),
		 CONSTRAINT `FK_orderdetail_1` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_orderdetail_2` FOREIGN KEY (`items_id`) REFERENCES `items` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
	);
	
	create table orders(
		id int(11) primary key auto_increment,
		user_id int(11) not null,
		number varchar(32) not null,
		createtime datetime not null,
		note varchar(100), 
		CONSTRAINT FK_orders_id FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE NO ACTION ON UPDATE NO ACTION
	);
	create table user(
		id int(11) primary key auto_increment,
		username varchar(32) not null,
		birthday date,
		sex char(1),
		address varchar(255) 
	);
	
	 insert into user(id,username,sex) 
	 values (1,'王五' ,'1');
	  insert into user(id,username,birthday,sex,address) 
	 values (2,'张曹宇' ,'19900802','1', '广东省汕头市'),
	 (10,'张三', '19990606', '0', 'beijing-caoyang'),
	 (16,'任在名',19961201, '1', 'guangzhou'),
	 (22,'陈小明', 19950510, '0', 'shenzhen'),
	 (24,'任传海', 19920308, '1', 'sanya'),
	 ('3','新数据','1909-12-12','1','常年在外');
	 
	insert into items(id,name,price,createtime) values
	(1,'bag', 200, 20180101),
	(2, 'iphone x', 8888, 20170201),
	(3, 'thinking in java', 55.4, 20130101),
	(4, 'netty guading', 34.4, 20140531);
	
	insert into orders(id,user_id,number,createtime) values
	(1,2,'13424234234',now()),
	(2,3, '2534532435' ,now()),
	(3,10,'534534534534', '20180929120000'),
	(4,2,'234823749', now());
	
	insert into orderdetail values(1,3,1,1),(2,3,2,3),(3,2,2,3),(4,2,4,3);
```
	查询所有交易的所有商品的名称、单价、总价
```sql	
	select items.name ,items.price, od.items_num ,(items.price * od.items_num) as sum_price from orderdetail as od inner join items on od.items_id = items.id;
```	
	查询所有用户的订单信息(用户名称、订单编号number和订单创建时间)，没有订单的用户也要查询出来，
	如果使用内关联查询，只能查询出orders表有值的所有数据，此时可以用左外链接查询
```sql
	select u.username, o.number, o.createtime from user as u left join orders as o on u.id=o.user_id;
```

### 外键约束
	上面的例子中，表orders中的user_id 关联 user表中的主键id，主键所在的表为主表（父表）， 与主表关联的表为从表（子表）
	
### mysql插入数据，单线程与多线程比较
	InsertData是单线程插入1万条数据，InsertDataMultithread是10个线程，每个线程插入1000条数据，但是多线程插入数据比单线程还要慢一下。
	
### PreparedStatement和Statement的区别，execute和executeUpdate的区别
	PreparedStatement和Statement都是接口，PreparedStatement继承Statement，PreparedStatement可以使用?占位符,然后执行之前通过set方法位占位符注入参数
	StatementExecuteDemo中是Statement的例子，不能用占位符
	一般情况用PreparedStatement就可以，PreparedStatement最常用的有execute、 executeQuery 和 executeUpdate 三个方法
	例子ExecuteDemo中使用execute()方法，结果返回false，但是数据已经插入，因为查询返回true，如果是更新或插入的话就返回false
	所以更新或插入应该使用executeUpdate，
	
### 事务
    例子TransactionDemo中就是一个事务的例子，先通过conn.setAutoCommit(false);把自动提交取消，取消之后执行pstmt.executeUpdate()就不会提交
    必须通过conn.commit()才能提交，这个例子中，executeUpdate了两次，其中第二次会抛出异常（因为不满足条件），在catch块中，rollback()来回滚数据
    如果不回滚，可以把rollback()注释掉，下面如果有commit还是会把之前executeUpdate的数据提交
    
    https://www.cnblogs.com/panwenbin-logs/p/8366940.html
