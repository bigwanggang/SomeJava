```sql
create database school;
```

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