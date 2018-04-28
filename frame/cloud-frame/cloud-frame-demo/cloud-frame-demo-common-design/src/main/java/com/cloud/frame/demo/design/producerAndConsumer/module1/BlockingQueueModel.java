package com.cloud.frame.demo.design.producerAndConsumer.module1;

import com.cloud.frame.demo.design.producerAndConsumer.base.Module;
import com.cloud.frame.demo.design.producerAndConsumer.base.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wd on 2018/4/24.
 */
public class BlockingQueueModel implements Module {

    private final BlockingQueue<Task> queue;

    private final AtomicInteger incr = new AtomicInteger(0);

    public BlockingQueueModel(int cap) {
        this.queue = new LinkedBlockingDeque<>(cap);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl(queue);
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl(queue, incr);
    }
}
