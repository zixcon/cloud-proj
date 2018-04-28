package com.cloud.frame.demo.design.producerAndConsumer.base;

/**
 * Created by wd on 2018/4/24.
 */
public abstract class AbstractProducer implements Producer, Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
