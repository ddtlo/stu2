package com.chl.test.dcl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/16
 */
public class Main2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(1);

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                try {
                    latch.await();
                    Singleton instance = Singleton.getInstance();
                    System.out.println(Thread.currentThread().getName() + " got instance: " + instance);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        latch.countDown();

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        // 由于重排序，可能会看到多个实例
        System.out.println("Instances created: " + Singleton.getInstance().hashCode());
    }
}
