- 主要成员变量
  size:存储的元素个数  
  Object[] elementData ： 存储元素的数组 
  modCount: ArrayList被改变的次数， add和remove 都会增加
- ArrayList里的Arrays.copyOf和System.arraycopy区别
   public static native void arraycopy(Object src,  int  srcPos, Object dest, int destPos, int length);
   表示从数组src的srcPos位置开始，往数组dest的destPos位置开始复制length个元素，复制的个数自己控制，否则抛异常
   arraycopy使用native修饰的，表示是调用原生方法（可能是C或C++）
   Arrays.copyOf 本质的也是调用System.arraycopy
