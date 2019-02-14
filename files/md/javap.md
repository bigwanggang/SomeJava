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

-	dup 指令的作用:dup指令为复制操作数栈顶值，并将其压入栈顶，也就是说此时操作数栈上有连续相同的两个对象地址
- 	通过javap反编译代码对比StringBuilder和String的性能：StringBuilderDemo, 通过编译的代码可以发现，用String和加号+的方式原理是每次循环都创建一个StringBuilder对象，效率相比StringBuilder的append的方式低
```
  public static java.lang.String getStr();
    descriptor: ()Ljava/lang/String;
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=0
         0: ldc           #2                  // String start
         2: astore_0
         3: iconst_0
         4: istore_1
         5: iload_1
         6: bipush        100
         8: if_icmpge     37
        11: new           #3                  // class java/lang/StringBuilder
        14: dup
        15: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
        18: aload_0
        19: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        22: ldc           #6                  // String hello
        24: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        27: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        30: astore_0
        31: iinc          1, 1
        34: goto          5
        37: aload_0
        38: areturn
      LineNumberTable:
        line 4: 0
        line 5: 3
        line 6: 11
        line 5: 31
        line 8: 37
      StackMapTable: number_of_entries = 2
        frame_type = 253 /* append */
          offset_delta = 5
          locals = [ class java/lang/String, int ]
        frame_type = 250 /* chop */
          offset_delta = 31

  public static java.lang.String getStr1();
    descriptor: ()Ljava/lang/String;
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=0
         0: new           #3                  // class java/lang/StringBuilder
         3: dup
         4: invokespecial #4                  // Method java/lang/StringBuilder."<init>":()V
         7: astore_0
         8: aload_0
         9: ldc           #2                  // String start
        11: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        14: pop
        15: iconst_0
        16: istore_1
        17: iload_1
        18: bipush        100
        20: if_icmpge     36
        23: aload_0
        24: ldc           #6                  // String hello
        26: invokevirtual #5                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        29: pop
        30: iinc          1, 1
        33: goto          17
        36: aload_0
        37: invokevirtual #7                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        40: areturn
      LineNumberTable:
        line 11: 0
        line 12: 8
        line 13: 15
        line 14: 23
        line 13: 30
        line 16: 36
```	
