## Phaser
<p>通过使用 CyclicBarrier 类解决了 CountDownLatch 类的种种缺点，但不可否认的是，CyclicBarrier 类还是有一些自身上的缺陷，
比如不可以动态添加 parties 计数，调用一次 await() 方法仅仅占用 1 个 parties 计数，所以在 JDK 7 中新增加了一个名称为 Phaser 的类来解决这样的问题。</p>
<p>Phaser 中文翻译为移相器。类 Phaser 对计数的操作是加法操作。</p>

### Phaser API

![Image text](Phaser%20API.png)

### 类 Phaser 的 arriveAndAwaitAdvance() 方法测试1
<p>方法 arriveAndAwaitAdvance() 的作用与 CountDownLatch 类中的 await() 方法大体一样，通过从方法的名称解释来看，arrive 是到达的意思，
wait 是等待的意思，而 advance 是前进、促进的意思，所以执行这个方法的作用就是当前线程已经到达屏障，在此等待一段时间，等条件满足后继续向下一个
屏障继续执行。通过前面的解释可以发现，类 Phaser 具有设置多屏障的功能，有些类似于体育竞赛中“赛段”的作用，运动员第一赛段结束后，开始休整准备，
然后集体到达第二赛段的起跑点，等待比赛开始后，运动员们又继续比赛了，说明 Phaser 类与 CyclicBarrier 类在功能上有重叠。</p>

[代码示例](PhaserDemo1.java)

### 类 Phaser 的 arriveAndAwaitAdvance() 方法测试2
<p>本实验的目标是当计数不足时，线程呈阻塞状态，不继续向下运行。</p>

[代码示例](PhaserDemo2.java)
<p>从运行结果来看，说明线程 C，中途退出了比赛，导致后面的比赛不能正常继续，因为线程 C 仅仅执行了一次 arriveAndAwaitAdvance() 方法导致这样的运行结果，
那可不可以当线程 C 不想参加下一轮的比赛时，有一个“注销比赛”的功能呢？使用 Phaser 类可以实现。</p>

### 类 Phaser 的 arriveAndDeregister() 方法测试
<p>方法 arriveAndDeregister() 的作用是使当前线程退出，并且使 parties 值减 1。</p>

[代码示例](PhaserDemo3.java)

### 类 Phaser 的 getPhase() 和 onAdvance() 方法测试
<p>方法 getPhase() 获取的是已经到达第几个屏障。</p>

[代码示例](PhaserDemo4.java)

<p>方法 onAdvance() 的作用是通过新的屏障时被调用。</p>

[代码示例](PhaserDemo5.java)

### 类 Phaser 的 getRegisteredParties() 方法和 register() 测试
<p>方法 getRegisteredParties() 获得注册的 parties 数量。</p>
<p>每执行一次方法 register() 就动态添加一个 parties 值。</p>

[代码示例](PhaserDemo6.java)

### 类 Phaser 的 bulkRegister() 方法测试
<p>方法 bulkRegister() 可以批量增加 parties 数量。</p>

[代码示例](PhaserDemo7.java)

### 类 Phaser 的 getArrivedParties() 和 getUnarrivedParties() 方法测试
<p>方法 getArrivedParties() 获得已经被使用的 parties 个数。</p>
<p>方法 getUnarrivedParties() 获得未被使用的 parties 个数。</p>

[代码示例](PhaserDemo8.java)

### 类 Phaser 的 arrive() 方法测试1
<p>方法 arrive() 的作用是使 parties 值加 1，并且不在屏障处等待，直接向下面的代码继续运行，并且 Phaser 类有计数重置功能。</p>

[代码示例](PhaserDemo9.java)

<p>方法 arrive() 的功能是使 getArrivedParties() 计数加 1，不等待其他线程到达屏障。在控制台中多次出现 getArrivedParties=0 的运行结果，
可以分析出 Phaser 类在经过屏障点后计数能被重置。</p>

### 类 Phaser 的 arrive() 方法测试2
<p>本节的实验目标还是测试当计数不足时，线程 A 和 B 依然呈等待状态。</p>

[代码示例](PhaserDemo10.java)

### 类 Phaser 的 awaitAdvance(int phase) 方法测试
方法 awaitAdvance(int Phase) 的作用是：如果传入参数 phase 值和当前 getPhase() 方法返回值一样，则在屏障处等待，否则继续向下面运行，有些类似于旁观者的作用，
当观察的条件满足了就等待（旁观），如果条件不满足，则程序向下继续运行。

[代码示例](PhaserDemo10.java)

方法 awaitAdvance(int Phase) 并不参与 parties 计数的操作，仅仅具有判断的功能。

### 类 Phaser 的 awaitAdvanceInterruptibly(int) 方法测试1
方法 awaitAdvance(int) 是不可中断的。

[代码示例](PhaserDemo12.java)

控制台并没有出现异常，说明线程并未中断。

### 类 Phaser 的 awaitAdvanceInterruptibly(int) 方法测试2
方法 awaitAdvanceInterruptibly (int) 是可中断的。

[代码示例](PhaserDemo13.java)

控制台出现异常，线程被中断了。

### 类 Phaser 的 awaitAdvanceInterruptibly(int) 方法测试3
方法 awaitAdvanceInterruptibly(int) 的作用是当线程执行的栏数不符合指定的参数值时，则继续执行下面的代码。

[代码示例](PhaserDemo14.java)

程序快速继续向下运行，运行的时间都是一样的，继续向下运行的原因是栏数不符合 10 个。

### 类 Phaser 的 awaitAdvanceInterruptibly(int, long,TimeUnit) 方法测试4
方法 awaitAdvanceInterruptibly(int, long, TimeUnit) 的作用是在指定的栏数等待最大的单位时间，如果在指定的时间内，栏数未变，则出现异常，否则继续向下运行。

[代码示例](PhaserDemo15.java)

### 类 Phaser 的 forceTermination() 和 isTerminated() 方法测试
方法 forceTermination() 使 Phaser 对象的屏障功能失效，而方法 isTerminated() 是判断 Phaser 对象是否已经呈销毁状态。

[代码示例](PhaserDemo16.java)

### 控制 Phaser 类的运行时机
前面的实验都是线程一起到达屏障后继续运行，有些情况下是需要进行控制的，也就是到达屏障后不允许继续运行。

[代码示例](PhaserDemo17.java)

此实验说明运行的时机是可以通过逻辑控制的，主要的原理就是计数 +1，然后通过逻辑代码的方式来决定线程是否继续向下运行。

### 总结
类 Phaser 提供了动态增减 parties 计数，这点比 CyclicBarrier 类操作 parties 更加方便，通过若干个方法来控制多个线程之间同步运行的效果，
还可以实现针对某一个线程取消同步运行的效果，而且支持在指定屏障处等待，在等待时还支持中断或非中断等功能，使用 Java 并发类对线程进行分组同步控制时，
Phaser 比 CyclicBarrier 类功能更加强大，建议使用。



