package com.cloud.frame.demo.design.producerAndConsumer.moudle2;

/**
 * Created by wd on 2018/5/2.
 */
public class Consume extends Thread {

    private Resource resource;

    public Consume(Resource resource) {
        this.resource = resource;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.consume();
        }
    }
}
