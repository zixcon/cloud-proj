package com.cloud.frame.demo.design.producerAndConsumer.module1;

/**
 * Created by wd on 2018/4/24.
 */
public class RunIt {

    public static void main(String[] args) {
        BlockingQueueModel model = new BlockingQueueModel(2);
        for (int i = 0; i < 2; i++) {
            new Thread(model.newRunnableConsumer()).start();
        }
        for (int i = 0; i < 5; i++) {
            new Thread(model.newRunnableProducer()).start();
        }
    }
}
