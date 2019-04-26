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
