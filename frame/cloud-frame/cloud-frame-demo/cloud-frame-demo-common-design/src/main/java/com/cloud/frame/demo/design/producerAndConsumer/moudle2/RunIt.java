package com.cloud.frame.demo.design.producerAndConsumer.moudle2;

/**
 * Created by wd on 2018/5/2.
 */
public class RunIt {

    public static void main(String[] args) {
        Resource resource = new Resource();
        //生产者线程
        Produce p = new Produce(resource);
        //多个消费者
        Consume c1 = new Consume(resource);
        Consume c2 = new Consume(resource);
        Consume c3 = new Consume(resource);

        p.start();
        c1.start();
        c2.start();
        c3.start();
    }
}
