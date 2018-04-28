package com.cloud.frame.demo.design.producerAndConsumer.base;

/**
 * 不同的模型实现中，生产者、消费者的具体实现也不同，所以需要为模型定义抽象工厂方法
 * Created by wd on 2018/4/24.
 */
public interface Module {

    Runnable newRunnableConsumer();

    Runnable newRunnableProducer();
}
