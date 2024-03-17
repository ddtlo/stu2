package com.chl.test.thread;

/**
 * @Description 用中断和信号两种方式中断线程
 * @Author 陈汉霖
 * @Date 2024/2/16
 */
public class EndThreadMain {
    public static void main(String[] args) {
        BThread t1 = new BThread();
        t1.setName("1");
        BThread t2 = new BThread();
        t2.setName("2");

        t1.start();
        t2.start();

        TestThread t3 = new TestThread(t1,t2);
        t3.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t1.setCode(10);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        t2.interrupt();

    }

}
