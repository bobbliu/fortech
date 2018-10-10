package com.liubo.base.concurrent._01disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 消费者：
 * 我们还需要一个事件消费者，也就是一个事件处理器。这个事件处理器简单地把事件中存储的数据打印到终端：
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("消费者接收========> "+longEvent.getValue());
    }
}
