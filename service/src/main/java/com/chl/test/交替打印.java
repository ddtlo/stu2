package com.chl.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/5
 */
public class 交替打印 {
    static Thread ta,tb;

    static char[] a = "1234567".toCharArray();
    static char[] b = "abcdefg".toCharArray();

    public static void main(String[] args) {
//        park();
        lock();
    }

    /**
     * park方式实现
     */
    private static void park() {
        ta = new Thread(() -> {
            for (char c : a) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(tb);
            }
        });

        tb = new Thread(() -> {
            for (char c : b) {
                System.out.println(c);
                LockSupport.unpark(ta);
                LockSupport.park();
            }
        });

        ta.start();
        tb.start();
    }

    /**
     * lock方式
      */
    public static void lock(){
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        ta = new Thread(() -> {

            lock.lock();
            countDownLatch.countDown();
            try {
                for (char c : a) {
                    System.out.println(c);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
                condition2.signal();
                System.out.println("a线程结束");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        tb = new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.lock();
            try {
                for (char c : b) {
                    System.out.println(c);
                    condition2.signal();
                    condition1.await();
                }
                System.out.println("b线程结束");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        ta.start();
        tb.start();
    }
}
