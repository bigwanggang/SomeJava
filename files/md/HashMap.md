- 计算hash的方法  
```
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
```
  >>>是无符号右移，前面空位用0补齐  
  具体原因看下： https://www.cnblogs.com/liujinhong/p/6576543.html
