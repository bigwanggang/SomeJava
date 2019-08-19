package com.gustavo.basic;

/**
 * 本测试通过javap查看生产字节码指令如下：
 *          0: ldc           #2                  // String helloworld
 *          2: astore_1
 *          3: ldc           #3                  // String hello
 *          5: astore_2
 *          6: ldc           #4                  // String world
 *          8: astore_3
 *          9: new           #5                  // class java/lang/StringBuilder
 *         12: dup
 *         13: invokespecial #6                  // Method java/lang/StringBuilder."<init>":()V
 *         16: aload_2
 *         17: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 *         20: aload_3
 *         21: invokevirtual #7                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 *         24: invokevirtual #8                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 *         27: astore        4
 *         29: getstatic     #9                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         32: aload_1
 *         33: aload         4
 *         35: if_acmpne     42
 *         38: iconst_1
 *         39: goto          43
 *         42: iconst_0
 *         43: invokevirtual #10                 // Method java/io/PrintStream.println:(Z)V
 *         46: return
 */
public class StringEqualsDemo {
    public static void main(String[] args) {
        String str1 = "hello" + "world";
        String str2 = "hello";
        String str3 = "world";
        String str4 = str2 + str3;
        System.out.println(str1 == str4);
    }
}
