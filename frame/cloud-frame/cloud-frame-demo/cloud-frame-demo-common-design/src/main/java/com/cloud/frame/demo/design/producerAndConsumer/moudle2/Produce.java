package com.cloud.frame.demo.design.producerAndConsumer.moudle2;

/**
 * Created by wd on 2018/5/2.
 */
public class Produce extends Thread {

    private Resource resource;

    public Produce(Resource resource) {
        this.resource = resource;
//        setName("生产者");
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep((long) (100 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.produce();
        }
    }
}
