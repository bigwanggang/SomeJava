### Mysql 5.5从零开始学的部分sql语句
```sql
create database school;


create table S(
	SID int primary key,
	SName char(25),
	SSex char(1),
	SProId int,
	SAge int
);

create table T(
	TWorkID int primary key,
	TName char(25),
	CourseId int
);

create table C (
	CID int primary key,
	CName char(25),
	Credit int,
	TWorkerID int,
	CTime datetime
);

create table STC(
	SID int,
	CID int,
	Score int
);


select CName, SID, SName from S, T, C, STC 
	Where T.CourseId in (select T.CourseId from T Where TName = "zhangdamin") 
	and STC.CID = T.CourseId
	and C.CID = T.CourseId
	and Stc.SID in (select stc.SID from STC Where Score > 90)
	and s.sid = stc.sid
	order by cname, score desc;
	
		
create table tbl1(
  num1 int not null
);
create table tbl2(
  num2 int not null
);

	
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

	insert into fruits(f_id,s_id,f_name,f_price) values('a1',101,'apple', 5.2),
	('b1', 101,'blackberry', 10.2),
	('bs1', 102, 'orange', 11.2),
	('bs2', 105,'melon', 8.2),
	('t1',102,'banana',10.3 ),
	('t2',102,'grape', 5.3 ),
	('o2',103,'coconut', 9.2 ),
	('c0',101,'cherry', 3.2 ),
	('a2',103,'apricot', 2.2 ),
	('l2',104,'lemon', 6.4 ),
	('b2',104,'berry', 7.6 ),
	('m1',106,'mango',  15.6),
	('m2',105,'xbabay', 2.6),
	('t4',107,'xbababa', 3.6),
	('m3',105,'xxtt', 11.6 ),
	('b5',107,'xxxx', 3.6);
	
	insert into suppliers(s_id,s_name, s_call) values
	(101,'FastFruit Inc', '1123123'),
	(102,'LT Suppliers', '1123123'),
	(103,'ACME', '1123123'),
	(104,'FNK Inc', '1123123'),
	(105,'Good Set', '1123123'),
	(106,'Just Eat Ours', '1123123'),
	(107,'DK Inc', '1123123');

select * from fruits where f_price between 2 and 10.2;
select * from fruits where f_name like 'b%y';
select * from fruits where f_price like '1%';
select suppliers.s_id, s_name, f_name, f_price from fruits inner join suppliers on fruits.s_id=suppliers.s_id;


insert into fruits values 

insert into tbl1 VALUEs(1),(5),(13),(27);
insert into tbl2 VALUEs(6),(14),(11),(20);

select num1 from tbl1 where num1 > any(select num2 from tbl2);
select * from fruits where exists (select * from suppliers where s_id=102);

create table test_table1(
    id int(11) not null primary key auto_increment,
    name  char(100) not null,
    address char(100),
    description char(100)
);
create table test_table2(
    id int(11) primary key,
    firstname char(50) not null,
    middlename char(50) not null,
    lastname char(50) not null,
    birth DATE not null,
    title char(100)
);

alter table test_table2 add index ComDateIdx(birth);
alter table test_table2 drop index ComDateIdx;
alter table test_table2 add unique index UniqIdx2(id DESC);
create index MultiComIdx2 on test_table2(firstname,middlename, lastname);
create FULLTEXT index FTIdx on test_table2(title);

	drop table if exists employee;
	create table employee(
		e_no int(11) primary key,
		e_name varchar(50) not null,
		e_gender char(2) not null,
		dept_no int not null,
		e_job varchar(50) not null,
		e_salary int not null,
		hireDate DATE not null, 
		CONSTRAINT fk_dno FOREIGN KEY(dept_no) references dept(d_no)
	);
	
	drop table if exists dept;
	create table dept(
		d_no int not null primary key auto_increment,
		d_name varchar(50),
		d_location varchar(100)
	);
	
	insert into employee values
	(1001, 'SMITH', 'm', 20, 'CLERK', 800,20051112),
	(1002, 'ALLEN', 'f', 30, 'SALESMAN', 1600,20030512),
	(1003, 'WORD', 'f', 30, 'SALESMAN', 1250,20030512),
	(1004, 'JONES', 'm', 20, 'MANAGER', 2975,19980518),
	(1005, 'MARTIN', 'm', 30, 'SALESMAN', 1250,20010612),
	(1006, 'BLAKE', 'f', 30, 'MANAGER', 2850,19970215),
	(1007, 'CLARK', 'm', 10, 'MANAGER', 2450,20020912),
	(1008, 'SCOTT', 'm', 20, 'ANALYST', 3000,20030512),
	(1009, 'KING', 'f', 10, 'PRESIDENT', 5000,19950101),
	(1010, 'TURNER', 'f', 30, 'SALESMAN', 1500,19971012),
	(1011, 'ADAMS', 'm', 20, 'CLERK', 1100,19991005),
	(1012, 'JAMES', 'f', 30, 'CLERK', 950,20080615);
	
	
	insert into dept values
	(10, 'ACCOUNTING', 'shanghai'),
	(20, 'RESEARCH', 'beijing'),
	(30, 'SALES', 'shenzhen'),
	(40, 'POERATIONS', 'fujian');
	
	select e_job , sum(e_salary) as total_salary from employee group by e_job

```
