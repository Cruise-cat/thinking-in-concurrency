## CountDownLatch
<p>Latch 的中文翻译为“门锁”，所以当门没有被打开时，N 个人是不能进入屋内的，放在编程环境里就是 N 个线程不能继续向下执行，
支持这样的特性可以控制线程执行任务的时机，使线程以“组团”的方式一起执行任务。</p>
<p>类 CountDownLatch 也是一个同步功能的辅助类，使用效果是给定一个计数，当使用这个 CountDownLatch 类的线程判断计数不为 0 时，则呈 wait 状态，如果为 0 时则继续运行。</p>
<p>实现等待与继续运行的效果分别需要使用 await() 和 countdown() 方法来进行。调用 await() 方法时判断计数是否为 0，如果不为 0 则呈等待状态。
其他线程可以调用 countdown() 方法将计数减 1，当计数减到为 0 时，呈等待的线程继续运行。而 getCount() 方法就是获得当前计数的个数。</p>
<p>要说明的是，计数无法被重置，如果需要重置计数，请考虑使用 CyclicBarrier。类 CountDownLatch 的计数是减法操作。</p>

### CountDownLatch API
![Image text](CountDownLatch%20API.png)


### 初步使用

[代码示例](CountDownLatchDemo1.java)

### 裁判在等全部的运动员
<p>本实验主要实现的是裁判等待所有的运动员达到赛道的效果，主要论证的就是多个线程与同步点间阻塞的特性，线程必须都到达同步点后才能继续向下执行。</p>

[代码示例](CountDownLatchDemo2.java)

### 各就各位准备比赛
<p>本实验主要实现的是裁判等待所有运动员各就各位后全部准备完毕，再开始比赛。</p>

[代码示例](CountDownLatchDemo3.java)

### 完整的比赛流程

[代码示例](CountDownLatchDemo4.java)

### await(long timeout, TimeUnit unit)
<p>方法 await(long timeout, TimeUnit unit) 的作用是使线程在指定的时间内进入等待状态，如果超过这个时间线程自动唤醒继续向下执行。</p>

[代码示例](CountDownLatchDemo5.java)

### getCount() 
<p>方法 getCount() 是获取当前计数的值。</p>

[代码示例](CountDownLatchDemo6.java)