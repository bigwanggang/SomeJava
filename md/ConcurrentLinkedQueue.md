### Node 类, 主要有2个成员变量：
```java
        volatile E item;
        volatile Node<E> next;
```        
  
### add
```java
    public boolean add(E e) {
        return offer(e);
    }
    public boolean offer(E e) {
        checkNotNull(e);
        final Node<E> newNode = new Node<E>(e);

        for (Node<E> t = tail, p = t;;) {
            Node<E> q = p.next;
            if (q == null) {
                // p is last node
                if (p.casNext(null, newNode)) {                         //1
                    // Successful CAS is the linearization point
                    // for e to become an element of this queue,
                    // and for newNode to become "live".
                    if (p != t) // hop two nodes at a time
                        casTail(t, newNode);  // Failure is OK.
                    return true;
                }
                // Lost CAS race to another thread; re-read next
            }
            else if (p == q)
                // We have fallen off list.  If tail is unchanged, it
                // will also be off-list, in which case we need to
                // jump to head, from which all live nodes are always
                // reachable.  Else the new tail is a better bet.
                p = (t != (t = tail)) ? t : head;
            else
                // Check for tail updates after two hops.
                p = (p != t && t != (t = tail)) ? t : q;
        }
    }

```
        1. p.casNext(null, newNode), 这个代码的作用是让p.next = newNode, castNext本质的利用CAS的方式来让p的next指向newNode,  
        但是compareAndSwapObject方法可能成功，也可能失败，失败的原因是其他线程先在p后插入的一个新元素
```java
        boolean casNext(Node<E> cmp, Node<E> val) {
            return UNSAFE.compareAndSwapObject(this, nextOffset, cmp, val);
        }
```
### add jdk1.6
```java
    public boolean offer(E e) {
        if (e == null) throw new NullPointerException();
        Node<E> n = new Node<E>(e);
        retry:
        for (;;) {
            Node<E> t = tail;
            Node<E> p = t;
            for (int hops = 0; ; hops++) {
                Node<E> next = succ(p);
                if (next != null) {                             //1
                    if (hops > HOPS && t != tail)
                        continue retry;
                    p = next;
                } else if (p.casNext(null, n)) {
                    if (hops >= HOPS)                           //2
                        casTail(t, n); // Failure is OK.
                    return true;   
                } else {
                    p = succ(p);
                }
            }
        }
    }
```
        1. 如果next!=null, 可能其他线程先与本线程，在p后增加了新元素，所以next有可能不为null，
        2. hops >=HOPS(1) 是因为 前面进入了next != null
       
