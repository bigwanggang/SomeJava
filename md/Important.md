- java内存分析工具， MemoryAnalyzer : http://www.eclipse.org/mat/  
  MemoryAnalyzer分析：https://www.cnblogs.com/trust-freedom/p/6744948.html  
- extend继承父类时，如果想对父类的方法引用，可以通过super.funcname()的方式来调用，例如ClassC栗子
- 今天在看spring源码时，对子类是否继承父类的private属性不是很确定，基础知识要巩固(子类是不能继承父类的private方法和属性的);  
- 工作中遇到的问题：git提交在不同的操作系统下换行符不一致导致出现大面积的文件改动（其实没有变化，只是换行符不同）, notepad++可以查看文件的所有字符，包括换行符，notepad++ --> view --> show symbol --> show all character  
> 解决办法：Format format = Format.getCompactFormat();  
        format.setLineSeparator("\r\n");  //CRLF  
        或者format.setLineSeparator("\n");   //LF
        
- 获取springboot工程resources下文件的方式
```
Resource resource = new ClassPathResource("123.xml");
        File f = resource.getFile();
```
- 路径的问题： 问题的出现可能是在windows下使用路径"\\path\\workspace\\code"，如果在linux下改路径一般不合法，要改成正斜杠：/，怎么能让该路径在  
windows和linux环境自动转换成相应的路径分隔符，下面的方式跑出异常:java.lang.IllegalArgumentException: character to be escaped is missing
```
    String s = "\\path\\workspace\\code";
    String s1 = s.replaceAll("\\\\", File.separator);
```        


## 一天弄懂一个面试题
- 2019-01-08：HashMap的底层实现原理？ 主要熟悉put和resize方法
