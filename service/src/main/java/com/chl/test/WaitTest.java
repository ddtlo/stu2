package com.chl.test;

import com.chl.Book;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2023/12/27
 */
public class WaitTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger a = new AtomicInteger(1);
        Book b = new Book();
        b.setName("bbb");
//        synchronized (b) {
//            System.out.println(b.getName());
            b.wait();
            b.notifyAll();
            System.out.println(b.getName());
//        }
    }
}
