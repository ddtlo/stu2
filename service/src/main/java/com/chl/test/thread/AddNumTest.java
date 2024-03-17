package com.chl.test.thread;

import lombok.Data;
import org.apache.dubbo.common.function.ThrowableAction;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2023/12/29
 */
@Data
public class AddNumTest {
    private volatile int no = 0;

    private Lock lock = new ReentrantLock();

    private void addNum() {
        lock.lock();
        try {
            Thread.sleep(5);
//            lock.lockInterruptibly();
            no++;
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddNumTest addNumTest = new AddNumTest();
        for (int i = 0; i < 100; i++) {
            new Thread(addNumTest::addNum).start();
        }
        Thread.sleep(1000);
        System.out.println(addNumTest.getNo());
    }
}
