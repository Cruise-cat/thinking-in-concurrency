## CyclicBarrier
<p>Cyclic 的意思是循环，Barrier 的意思是障碍。</p>
<p>类 CyclicBarrier 不仅可以实现 CountDownLatch 的功能，还可以实现屏障等待的功能，也就是阶段性同步。它在使用上的意义在于
可以循环的实现线程要一起做任务的目标，而不是像 CountDownLatch 一样仅仅能支持一次线程与同步阻塞点的特性。</p>
<p>类 CyclicBarrier 也是一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点，这些线程必须实时的互相等待，这种情况
下就可以使用 CyclicBarrier 实现这个效果。</p>
<p>类 CyclicBarrier 与 类 CountDownLatch 有些类似，但是细节上还是有些区别：</p>
<ul>
    <li>CountDownLatch 作用：一个线程或者多个线程等待另一个线程或多个线程完成某个事情后才能继续执行。</li>
    <li>CyclicBarrier 作用：多个线程之间互相等待，任何一个线程完成之前，所有线程必须互相等待。</li>
    <li>CountDownLatch 是两个角色互相等待，而 CyclicBarrier 是同类之间互相等待。</li>
    <li>和 CountDownLatch 不同，CyclicBarrier 是加法操作。</li>
</ul>

### CyclicBarrier API

![Image text](CyclicBarrier%20API.png)

### 初步使用

[代码示例](CyclicBarrierDemo1.java)

<p>此示例中 parties 个数和线程数相同，如果线程数大于 parties 数是否会分批处理？</p>

[代码示例](CyclicBarrierDemo2.java)

### 验证屏障重置性以及 getNumberWaiting() 方法的使用
<p>getNumberWaiting() 方法的作用是获得有几个线程已经达到屏障点。</p>

[代码示例](CyclicBarrierDemo3.java)

### 用 CyclicBarrier 实现阶段跑比赛
<p>借助 CyclicBarrier 具有计数重置性实现阶段跑比赛。</p>

[代码示例](CyclicBarrierDemo4.java)

### 方法 isBroken() 使用
<p>此方法的作用是查询此屏障是否处于损坏状态。</p>

[代码示例](CyclicBarrierDemo5.java)

### 方法 await(long timeout, TimeUnit unit) 超时出现异常的测试
<p>此方法的功能是如果在指定时间内达到 parties 数量，程序继续执行，否则如果超时，则抛出 TimeoutException。</p>

[代码示例](CyclicBarrierDemo6.java)

### 方法 getNumberWaiting() 和 getParties()
<p>方法 getNumberWaiting() 的作用是由几个线程到达屏障点。</p>
<p>方法 getParties() 的作用是取得 parties 个数。</p>

[代码示例](CyclicBarrierDemo7.java)

### 方法 reset() 
<p>此方法的作用是重置屏障。</p>

[代码示例](CyclicBarrierDemo8.java)
