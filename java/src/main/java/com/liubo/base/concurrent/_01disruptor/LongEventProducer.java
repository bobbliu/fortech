package com.liubo.base.concurrent._01disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 生产者：
 * 很明显的是：当用一个简单队列来发布事件的时候会牵涉更多的细节，这是因为事件对象还需要预先创建。
 * 发布事件最少需要两步：获取下一个事件槽并发布事件（发布事件的时候要使用try/finnally保证事件一定会被发布）。
 * 如果我们使用RingBuffer.next()获取一个事件槽，那么一定要发布对应的事件。
 * 如果不能发布事件，那么就会引起Disruptor状态的混乱。
 * 尤其是在多个事件生产者的情况下会导致事件消费者失速，从而不得不重启应用才能会恢复。
 */

public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * onData用来发布事件，每调用一次就发布一次事件
     * 它的参数会用过事件传递给消费者
     */
    public void onData(ByteBuffer bb) {
        //1.可以把ringBuffer看做一个时间队列，那么next就是下一个事件槽
        long sequence = ringBuffer.next();
        try {
            //2.用上面的索引取出一个空的索引来填充（获取该序号对应的事件对象）
            LongEvent event = ringBuffer.get(sequence);
            //3.获取通过事件传递的业务数据
            event.setValue(bb.getLong(0));
        } finally {
            //4.发布事件
            //注意：最后的ringBuffer.publish()方法必须包含在finally中，以确保得到调用；
            //          如果请求的sequence未被提交，将会堵塞后续发布的操作或者其他的producer
            ringBuffer.publish(sequence);
            System.out.println("生产者发送========> " + ringBuffer.getCursor());
        }
    }
}
