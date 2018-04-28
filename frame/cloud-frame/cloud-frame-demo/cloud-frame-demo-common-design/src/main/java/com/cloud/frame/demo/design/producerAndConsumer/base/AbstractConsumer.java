package com.cloud.frame.demo.design.producerAndConsumer.base;

/**
 * Created by wd on 2018/4/24.
 */
public abstract class AbstractConsumer implements Consumer, Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
