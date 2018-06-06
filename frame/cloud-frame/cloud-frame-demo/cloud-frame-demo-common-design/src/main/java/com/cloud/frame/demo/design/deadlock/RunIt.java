package com.cloud.frame.demo.design.deadlock;

/**
 * Created by wd on 2018/6/6.
 */
public class RunIt {

    public static void main(String[] args) {

        //循环主要是为了加大死锁概率
        for (int i = 0; i < 100; i++) {
            new Thread(new SynThread(1, 2)).start();
            new Thread(new SynThread(2, 1)).start();
        }
    }
}
