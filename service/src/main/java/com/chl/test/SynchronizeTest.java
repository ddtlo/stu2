package com.chl.test;

import com.chl.Book;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2023/12/27
 */
public class SynchronizeTest {
    public static void main(String[] args) throws InterruptedException {
        int a = 1;
        s();
        System.out.println(a);
    }

    public static synchronized void s(){

    }

    public static synchronized void f(){
        for (int i = 0; i < 1000; i++) {

            synchronized(Book.class){

            }
        }
    }
}
