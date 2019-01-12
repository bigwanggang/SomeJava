http://www.importnew.com/13107.html

将常量压入栈：当int取值-1-5采用iconst指令，取值-128-127采用bipush指令，取值-32768-32767采用sipush指令，取值-2147483648-2147483647采用 ldc 指令。
```
		int i=5; 
		int j=-1;
		int b = 6;
		int c = 3000;
    int d = Integer.MAX_VALUE;
```
将以上java代码编译后：
```
         0: iconst_5
         1: istore_1
         2: iconst_m1
         3: istore_2
         4: bipush        6
         6: istore_3
         7: sipush        3000
        10: istore        4
        12: ldc           #3                  // int 2147483647
        14: istore        5
```        
        其中2147483647是存储在常量池中,通过ldc命令，把常量池总的数压入栈
	istore_1是将栈顶元素弹出，存入局部变量1中

```
	void spin() {
	int i; 
	for (i = 0; i < 100; i++) {
	; // Loop body is empty 
		} 
	}
```
```
	 0: iconst_0		#将0压入栈
         1: istore_1		#将栈顶元素弹出，存入局部变量1中
         2: iload_1		#将局部变量1的值压入栈
         3: bipush        100	#将100压入栈
         5: if_icmpge     14	#如果局部变量1的值大于等于（greater equal）100 跳转到14
         8: iinc          1, 1	#局部变量1的值加1
        11: goto          2
        14: return
```

	dup 指令的作用
