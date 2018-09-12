http://www.importnew.com/13107.html

将常量压入栈：当int取值-1~5采用iconst指令，取值-128~127采用bipush指令，取值-32768~32767采用sipush指令，取值-2147483648~2147483647采用 ldc 指令。
```java
		int i=5; 
		int j=-1;
		int b = 6;
		int c = 3000;
    int d = Integer.MAX_VALUE;
```
将以上java代码编译后：
```java
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
        其中2147483647是存储在常量池中
