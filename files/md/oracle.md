### 技术记录
- &的用处，执行update sf_org set org_name='&name' where org_id=1;就会弹出窗口Enter value for name: 然后输入值后才会执行
- DML操作和单行SELECT语句会使用隐式游标，它们是： 
> * 插入操作：INSERT。 
> * 更新操作：UPDATE。 
> * 删除操作：DELETE。 
> * 单行查询操作：SELECT ... INTO ...。 
- 游标for循环，不需要显示打开，关闭游标

### pl/sql 
- File-New-Command Window就可以打开命令行窗口，输入 set serveroutput on； 打开命令行窗口的打印输出
- @ 加脚本路径： @ d:\workspace\learnoracle\1.txt， 就可以执行文件中脚本的内容
- 如果使用DBMS_OUTPUT.PUT_LINE()而不先执行set serveroutput on；命令，是看不到输出的
- oracle中斜杠（/）的含义：斜杠就是让服务器执行前面所写的sql脚本。如果是普通的select语句，一个分号，就可以执行了。但是如果是存储过程，那么遇到分号，就不能马上执行了。这个时候，就需要通过斜杠(/)来执行。
- ORACLE表中插入多值，是不支持这种方式的：INSERT INTO 某表 VALUES(各个值)，VALUES(各个值),.....;在oracle中如果插入多条记录可以用：
```
"INSERT ALL INTO  a表
VALUES(各个值)  
INTO a表 VALUES (其它值) 
INTO a表 VALUES(其它值) .... 再跟一个SELECT 语句"。
```
- oracle创建函数，可以把函数写入文件中，然后通过命令行执行：@ d:\sql\add1.txt，如提示Function created，就说明函数创建成功，就可以像使用系统内置函数一样使用自己创建的函数
- oracle中有个特别的表：dual.
```
 select user from dual;  //查询当前连接用户
 select sysdate from dual; -- 查询当前连接日期时间
 ```
- instr函数为查找字符串位置的功能，例如：下面的两个select分别返回5和7
```
select instr('helloworld', 'o') from dual;   
select instr('helloworld', 'or') from dual;   
```
- substr 是返回字符串自串的功能，例如，下面两个命令都返回：hel
```
select substr('helloworld', 1, 3) from dual
select substr('helloworld', 0, 3) from dual
```
- 存储过程procedure和函数function的区别？
- procedure入门：http://blog.itpub.net/31358702/viewspace-2153725/
- oracle查看表结构：describe， 而mysql用：desc 
- 存储过程call和exec的区别？栗子：存储过程如下：
```
create or replace procedure hello
as
begin
DBMS_OUTPUT.PUT_LINE('hello');
end;
/
```
命令行：执行call hello(); 并不打印，再次执行exec hello()会打印两个hello，为什么？
- execute immediate是可以执行单条语句的意思：
```
 execute immediate 'select * from user';
 execute immediate 'call selectEmp()';
```
