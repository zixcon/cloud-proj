package com.cloud.frame.demo.design.deadlock;

/**
 * Created by wd on 2018/6/6.
 */
public class SynThread implements Runnable{

    int a ,b;

    public SynThread(int a,int b){
        this.a=a;
        this.b=b;
    }
    @Override
    public void run() {
        synchronized (Integer.valueOf(a)) {//必须用valueOf()方法
            synchronized (Integer.valueOf(b)) {
                System.err.println("a+b=="+(a+b));
            }
        }
    }

}