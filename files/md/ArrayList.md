- 主要成员变量  
  size:存储的元素个数    
  Object[] elementData ： 存储元素的数组  
  modCount: ArrayList被改变的次数， add和remove 都会增加  
- ArrayList里的Arrays.copyOf和System.arraycopy区别  
   public static native void arraycopy(Object src,  int  srcPos, Object dest, int destPos, int length);  
   表示从数组src的srcPos位置开始，往数组dest的destPos位置开始复制length个元素，复制的个数自己控制，否则抛异常  
   arraycopy使用native修饰的，表示是调用原生方法（可能是C或C++）  
   Arrays.copyOf 本质的也是调用System.arraycopy  
-  Arrays.asList是可以把数组转换成list，但是抓换的list不能add、remove、clear，对list的元素修改，也同时对数组修改
-  https://github.com/haiyusun/Interview-Notes/blob/master/%E9%9D%A2%E8%AF%95%E9%97%AE%E9%A2%98%E6%80%BB%E7%BB%93.md里面有个题\
  两个线程对ArrayList同时add会发生什么情况？ 其中一种是出现ArrayIndexOutOfBoundsException异常，原因是例如，ArrayList中的数组长度为100\
  已经存了99个数据，ArrayList的add方法处理流程是先查看数组是否有一个多余的空间可以存放元素，这时如果两个线程同时检查了数组是否有多余的空间存放\
  元素，则往数组里存元素，第二个存的就会跑出ArrayIndexOutOfBoundsException:100异常，对于这个问题还有一个知识点就是抛异常的可能为：\
  10、15、55、33、49、73,因为ArrayList的默认数组长度为10，之后扩容是以1.5倍的大小来扩容
