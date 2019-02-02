### jstack工具的使用方法
- jstack可以打印运行中的进程的线程堆栈信息， 步骤如下：
（1）jps -lvm 用于查看当前机器上运行的java进程。 打印有些乱，不清晰，可以通过增加 > 1.txt 把打印信息输入到1.txt文件下，方便查看
（2）jstack -l pid  打印某个进程的堆栈信息


- 通过个栗子来熟悉用jstack查看堆栈信息的流程
1. 自己写个栗子MultiThreadMap_Put，该栗子是多线程往HashMap里put数据，由于HashMap的结构特性，该操作可能导致内存占用冲高，\
详见MultiThreadMap_Put， 先启动该程序
2. jps -lvm > 1.txt,然后在1.txt文档里查看我的进程的pid
3. jstack -l pid > 2.txt,然后在2.txt里查看打印的堆栈信息,文件里有如下信息， 说明线程一直死RUNNABLE状态，永远没能停止

```
"thread###92" #103 prio=5 os_prio=0 tid=0x000000001e475000 nid=0x16df0 runnable [0x000000002506e000]
   java.lang.Thread.State: RUNNABLE
	at java.util.HashMap$TreeNode.balanceInsertion(HashMap.java:2234)
	at java.util.HashMap$TreeNode.treeify(HashMap.java:1943)
	at java.util.HashMap$TreeNode.split(HashMap.java:2175)
	at java.util.HashMap.resize(HashMap.java:714)
	at java.util.HashMap.putVal(HashMap.java:663)
	at java.util.HashMap.put(HashMap.java:612)
	at com.gustavo.map.MultiThreadMap_Put$Putting.run(MultiThreadMap_Put.java:24)
	at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
	- None
```

### 通过jstack来学习线程的状态
- ThreadStatusDemo_Blocked_jstack的栗子是两个线程抢一把锁，抢到了就永远循环，为RUNNABLE状态， 另一个线程就是BLOCKED， 通过jstack查看有如下信息：
```
"thread2" #12 prio=5 os_prio=0 tid=0x000000001bc96800 nid=0x11dfc waiting for monitor entry [0x000000001d01f000]
   java.lang.Thread.State: BLOCKED (on object monitor)
	at com.gustavo.thread.ThreadStatusDemo_Blocked1$Runnable1.run(ThreadStatusDemo_Blocked1.java:27)
	- waiting to lock <0x00000007801c3050> (a java.lang.Object)
	at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
	- None

"thread1" #11 prio=5 os_prio=0 tid=0x000000001bc92000 nid=0x17134 runnable [0x000000001bebf000]
   java.lang.Thread.State: RUNNABLE
	at com.gustavo.thread.ThreadStatusDemo_Blocked1.sleep(ThreadStatusDemo_Blocked1.java:19)
	at com.gustavo.thread.ThreadStatusDemo_Blocked1$Runnable1.run(ThreadStatusDemo_Blocked1.java:27)
	- locked <0x00000007801c3050> (a java.lang.Object)
	at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
	- None
```  

- ThreadStatusDemo_WaitNotify_jstack的栗子是测试Object.wait()方法时，线程的状态，通过jstack分析可知是WAITING状态
```
"Thread-1" #12 prio=5 os_prio=0 tid=0x000000001bdf3000 nid=0x17f80 in Object.wait() [0x000000001d01f000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x00000007801c2ec0> (a java.lang.Object)
	at java.lang.Object.wait(Object.java:502)
	at com.gustavo.thread.ThreadStatusDemo_WaitNotify_jstack$MyRunn.run(ThreadStatusDemo_WaitNotify_jstack.java:24)
	- locked <0x00000007801c2ec0> (a java.lang.Object)
	at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
	- None
```

#### WAITING和TIMED_WAITING 状态分析

- 如果Object.wait()就是WAITING状态，如果是Object.wait(long time)则线程的状态就是TIMED_WAITING
