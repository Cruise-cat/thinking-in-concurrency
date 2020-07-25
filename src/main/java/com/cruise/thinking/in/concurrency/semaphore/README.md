## Semaphore
<p>Semaphore 可以翻译为信号量、信号灯。</p>
<p>类 Semaphore 是 synchronized 关键字的升级版，Semaphore 可以实现 synchronized 的效果，但是它额外提供了可以控制并发线程数量的功能，这个是 synchronized 不能支持的。</p>
<p>类 Semaphore 的主要作用就是控制并发线程的数量，如果不控制并发线程的数量，则 CPU 资源很快就会被耗尽，每个线程执行相当缓慢，因为 CPU 要把时间片分配给不同的线程对象，而且线程上下文切换也是耗时的，所以限制系统并发线程的数量是十分有必要的。</p>

### Semaphore API
![Image text](Semaphore%20API.png)

### Semaphore 的同步性
<p>实现 synchronized 关键字的效果</p>

[代码示例](SemaphoreDemo1.java)

### acquire(int permits) 的作用

[代码示例](SemaphoreDemo2.java)

### 动态添加许可数量

[代码示例](SemaphoreDemo3.java)

### 方法 acquireUninterruptibly() 的作用
<p>方法 acquireUninterruptibly() 的作用是使等待进入 acquire() 方法的线程，不允许被中断。</p>
<p>先看一个允许被中断的例子。</p>

[代码示例](SemaphoreDemo4.java)

<p>那么不允许被中断是如何？</p>

[代码示例](SemaphoreDemo5.java)

### 方法 availablePermits() 的作用
<p>方法 availablePermits() 返回此 Semaphore 对象当前可用的许可的数量，此方法通常用来调试，因为许可数量实时变化并不是固定的。</p>

[代码示例](SemaphoreDemo6.java)

### 方法 drainPermits() 的作用
<p>方法 drainPermits() 返回可用的许可个数，并将许可个数清零。</p>

[代码示例](SemaphoreDemo7.java)

### 方法 getQueueLength() 和方法 hasQueuedThreads() 的作用
<p>方法 getQueueLength() 的作用是取得等待许可的线程个数。</p>
<p>方法 hasQueuedThreads() 的作用是判断有没有线程在等待这个许可。</p>
<p>这两个方法都是用来判断当前有没有线程正在等待许可时使用。</p>

[代码示例](SemaphoreDemo8.java)

### 公平信号量与非公平信号量
<p>有些时候，获得许可的顺序与线程启动的顺序有关，这时信号量就分为公平和非公平的。所谓的公平信号量就是获得许可的顺序与线程启动的顺序有关，但不代表 100% 的获得信号量，仅仅是在概率上得到保证。而非公平信号量就是顺序无关的了。</p>

[代码示例](SemaphoreFairDemo.java)

### 方法 tryAcquire() 的使用
<p>方法 tryAcquire() 的作用是尝试获取一个许可，如果获取不到则返回 false，此方法通常与 if 语句配合使用。此方法是非阻塞的，非阻塞使线程不会在同步处一直处于等待的状态。</p>

[代码示例](SemaphoreDemo9.java)

### 方法 tryAcquire(int permits) 的使用
<p>方法 tryAcquire(int permits) 的作用是尝试获取 x 个许可，如果获取不到则返回 false。</p>

[代码示例](SemaphoreDemo10.java)

### 方法 tryAcquire(long timeout, TimeUnit unit) 的使用
<p>方法 tryAcquire(int permits) 的作用是在指定时间内尝试获取 1 个许可，如果获取不到则返回 false。</p>

[代码示例](SemaphoreDemo11.java)

### 方法 tryAcquire(int permits, long timeout, TimeUnit unit) 的使用
<p>方法 tryAcquire(int permits) 的作用是在指定时间内尝试获取 x 个许可，如果获取不到则返回 false。</p>

[代码示例](SemaphoreDemo12.java)

### 多进路、多处理、多出路实验
<p>本实验的目的是允许多个线程同时处理任务，也就是说，每个线程处理各自的任务。</p>

[代码示例](SemaphoreDemo13.java)

### 多进路、单处理、多出路实验
<p>本实验的目的是允许多个线程同时处理任务，但是执行任务的顺序是同步的。</p>

[代码示例](SemaphoreDemo14.java)

### 使用 Semaphore 创建字符串池
<p>Semaphore 可以有效的控制并发执行任务的线程数量，这种功能可以应用在 pool 池技术中，可以设置同时访问 pool 池中数据的线程数量。</p>
<p>本实验的功能是同时有若干个线程可以访问池中的数据，但同时只有一个线程可以取得数据，使用完毕后再放回池中。</p>

[代码示例](ListPool.java)

### 使用 Semaphore 实现多生产者/多消费者模式
<p>使用 Semaphore 实现多生产者/多消费者模式的同时还要控制消费者/生产者的数量。</p>

[代码示例](ProducerAndConsumer.java)