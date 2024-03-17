package com.chl.test.dcl;

import java.util.TreeSet;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/16
 */
public class Main {
    private static int threadNum = 10;

    public static void main(String[] args) throws InterruptedException {
        Singleton singleton = new Singleton();
        cyclicBarrierTest(singleton);
    }

    public static void countDownLatchTest(Singleton singleton) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Singleton.getInstance().hashCode());
            }).start();
        }
        countDownLatch.countDown();
    }

    public static void cyclicBarrierTest(Singleton singleton){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Singleton.getInstance().hashCode());
            }).start();
        }
    }

    public static void threadTest(Singleton singleton) {
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> System.out.println(Singleton.getInstance().hashCode())).start();
        }
    }
}
