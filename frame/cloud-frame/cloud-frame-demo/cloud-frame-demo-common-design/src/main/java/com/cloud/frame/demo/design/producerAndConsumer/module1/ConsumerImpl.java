package com.cloud.frame.demo.design.producerAndConsumer.module1;

import com.cloud.frame.demo.design.producerAndConsumer.base.AbstractConsumer;
import com.cloud.frame.demo.design.producerAndConsumer.base.Consumer;
import com.cloud.frame.demo.design.producerAndConsumer.base.Task;

import java.util.concurrent.BlockingQueue;

/**
 * Created by wd on 2018/4/24.
 */
public class ConsumerImpl extends AbstractConsumer implements Consumer, Runnable {

    private BlockingQueue<Task> queue;

    public ConsumerImpl(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void consume() throws InterruptedException {
        Task task = this.queue.take();
        // 固定时间范围的消费，模拟相对稳定的服务器处理过程
        Thread.sleep(500 + (long) (Math.random() * 500));
        System.out.println(Thread.currentThread().getName() + " | consume: " + task.getNo());
    }
}
