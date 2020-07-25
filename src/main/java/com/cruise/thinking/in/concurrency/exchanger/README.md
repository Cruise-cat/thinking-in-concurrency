## Exchanger
<p>类 Exchanger 的主要作用是使两个线程之间传输数据，它比生产者/消费者模式中的 wait、notify 要简单。而且传输的数据类型不受限制。</p>

### 方法 exchange(V x) 具有阻塞性
<p>类 Exchanger 的方法 exchange() 具有阻塞性，也就是此方法被调用后等待其他线程来取得数据，如果没有其他线程来取得数据就会一直阻塞。</p>

[代码示例](ExchangerDemo1.java)

### 方法 exchange(V x) 传递数据

[代码示例](ExchangerDemo2.java)

### 方法 exchange(V x, long timeout, TimeUnit unit) 与超时
<p>当调用方法 exchange(V x, long timeout, TimeUnit unit) 后，在指定时间没有内没有其他线程获取数据则抛出超时异常。 </p>

[代码示例](ExchangerDemo3.java)