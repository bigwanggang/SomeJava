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
-	store是将栈顶元素弹出，存入变量中，istort_1是存入变量1中， load是将局部变量的值压入栈，iload_1是将局部变量1的值压入栈
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

###  通过javap 字节码分析集中ArrayList的遍历方式的区别
```java
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (String s : list) {
            System.out.println(s);
        }

        for (Iterator<String> iter = list.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
```
```
         0: new           #2                  // class java/util/ArrayList
         3: dup
         4: invokespecial #3                  // Method java/util/ArrayList."<init>":()V
         7: astore_1
         8: iconst_0
         9: istore_2
        10: iload_2
        11: aload_1
        12: invokeinterface #4,  1            // InterfaceMethod java/util/List.size:()I
        17: if_icmpge     42
        20: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
        23: aload_1
        24: iload_2
        25: invokeinterface #6,  2            // InterfaceMethod java/util/List.get:(I)Ljava/lang/Object;
        30: checkcast     #7                  // class java/lang/String
        33: invokevirtual #8                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        36: iinc          2, 1
        39: goto          10
		
        42: aload_1
        43: invokeinterface #9,  1            // InterfaceMethod java/util/List.iterator:()Ljava/util/Iterator;
        48: astore_2
        49: aload_2
        50: invokeinterface #10,  1           // InterfaceMethod java/util/Iterator.hasNext:()Z
        55: ifeq          78
        58: aload_2
        59: invokeinterface #11,  1           // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
        64: checkcast     #7                  // class java/lang/String
        67: astore_3
        68: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
        71: aload_3
        72: invokevirtual #8                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        75: goto          49
		
        78: aload_1
        79: invokeinterface #9,  1            // InterfaceMethod java/util/List.iterator:()Ljava/util/Iterator;
        84: astore_2
        85: aload_2
        86: invokeinterface #10,  1           // InterfaceMethod java/util/Iterator.hasNext:()Z
        91: ifeq          112
        94: getstatic     #5                  // Field java/lang/System.out:Ljava/io/PrintStream;
        97: aload_2
        98: invokeinterface #11,  1           // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
       103: checkcast     #7                  // class java/lang/String
       106: invokevirtual #8                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       109: goto          85
```
-   通过分析可知，foreach的方式和用迭代器的方式是类似的，编译之后，本质上也是用迭代器的方式循环


### jvm
-	Java -verbose:gc 中参数-verbose:gc 表示输出虚拟机中GC的详细情况.
-   一个new语句编译成字节码, 比如：NewInstance n =new NewInstance();
```
     0: new           #2                  // class com/gustavo/basic/NewInstance
     3: dup
     4: invokespecial #3                  // Method "<init>":()V
     7: astore_1
     8: return
```		
    从字节码可以看出，通过new初始化一个对象，实际上分为3步

-	10 个重要的jvm参数： https://www.oschina.net/translate/hotspot-jvm-options-java-examples

### jvm参数
-	通过https://www.cnblogs.com/smyhvae/p/4736162.html来学习jvm的几个重要的参数
YoungGenDemo的程序是创建10个长度为1M的字节数组，通过VM参数：-verbose:gc -XX:+PrintGCDetails，运行：
```
 PSYoungGen      total 57344K, used 14192K [0x0000000780000000, 0x0000000784000000, 0x00000007c0000000)
  eden space 49152K, 28% used [0x0000000780000000,0x0000000780ddc0d0,0x0000000783000000)
  from space 8192K, 0% used [0x0000000783800000,0x0000000783800000,0x0000000784000000)
  to   space 8192K, 0% used [0x0000000783000000,0x0000000783000000,0x0000000783800000)
 ParOldGen       total 131072K, used 0K [0x0000000700000000, 0x0000000708000000, 0x0000000780000000)
  object space 131072K, 0% used [0x0000000700000000,0x0000000700000000,0x0000000708000000)
 Metaspace       used 3334K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 355K, capacity 388K, committed 512K, reserved 1048576K
```
根据打印信息可知， 默认情况下，新生代默认有57344K 大小的空间，老年代有131072K空间大小，共188416k，新生代占32%
根据VM参数调整堆的大小，VM参数：-Xmx20m -Xms20m -XX:+PrintGCDetails
结果：
```
[GC [PSYoungGen: 4215K->432K(5952K)] 4215K->1456K(19648K), 0.0021844 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC [PSYoungGen: 4847K->336K(5952K)] 5871K->2384K(19648K), 0.0045379 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 PSYoungGen      total 5952K, used 3587K [0x00000000ff960000, 0x0000000100000000, 0x0000000100000000)
  eden space 5120K, 63% used [0x00000000ff960000,0x00000000ffc8cdb0,0x00000000ffe60000)
  from space 832K, 40% used [0x00000000fff30000,0x00000000fff84010,0x0000000100000000)
  to   space 832K, 0% used [0x00000000ffe60000,0x00000000ffe60000,0x00000000fff30000)
 PSOldGen        total 13696K, used 2048K [0x00000000fec00000, 0x00000000ff960000, 0x00000000ff960000)
  object space 13696K, 14% used [0x00000000fec00000,0x00000000fee00020,0x00000000ff960000)
 PSPermGen       total 21248K, used 3501K [0x00000000f9a00000, 0x00000000faec0000, 0x00000000fec00000)
  object space 21248K, 16% used [0x00000000f9a00000,0x00000000f9d6b700,0x00000000faec0000)
```  
当堆内存通过参数设置为20M的时候，新生代分配了5952k，老年代分配了13696k，共19648k， 新生代占30%


