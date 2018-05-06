package com.cloud.frame.demo.design.producerAndConsumer.moudle2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wd on 2018/5/2.
 */
public class Resource {
    private BlockingQueue resourceQueue = new LinkedBlockingQueue(10);

    /**
     * 向资源池中添加资源
     */
    public void produce() {
        try {
            resourceQueue.put(1);
            System.out.println("生产者" + Thread.currentThread().getName()
                    + "生产一件资源," + "当前资源池有" + resourceQueue.size() +
                    "个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向资源池中移除资源
     */
    public void consume() {
        try {
            resourceQueue.take();
            System.out.println("消费者" + Thread.currentThread().getName() +
                    "消耗一件资源," + "当前资源池有" + resourceQueue.size()
                    + "个资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
