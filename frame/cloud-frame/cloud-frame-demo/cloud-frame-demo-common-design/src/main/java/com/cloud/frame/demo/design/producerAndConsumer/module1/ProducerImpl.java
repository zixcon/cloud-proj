package com.cloud.frame.demo.design.producerAndConsumer.module1;

import com.cloud.frame.demo.design.producerAndConsumer.base.AbstractProducer;
import com.cloud.frame.demo.design.producerAndConsumer.base.Producer;
import com.cloud.frame.demo.design.producerAndConsumer.base.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by wd on 2018/4/24.
 */
public class ProducerImpl extends AbstractProducer implements Producer, Runnable {

    private BlockingQueue<Task> queue;
    private AtomicInteger incr;

    public ProducerImpl(BlockingQueue<Task> queue, AtomicInteger incr) {
        this.queue = queue;
        this.incr = incr;
    }

    @Override
    public void produce() throws InterruptedException {
        // 不定期生产，模拟随机的用户请求
        Thread.sleep((long) (Math.random() * 1000));
        Task task = new Task(incr.getAndIncrement());
        queue.put(task);
        System.out.println(Thread.currentThread().getName() + " | produce: " + task.getNo());
    }
}
