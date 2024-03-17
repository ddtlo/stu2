package com.chl.test.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2023/12/29
 */
public class ABCParkTest {

    public static void ta (Thread t){
        System.out.println("A");
        LockSupport.unpark(t);
    }
    public static void tb (Thread t){
        LockSupport.park(t);
        System.out.println("B");
        LockSupport.unpark(t);
    }
    public static void tc(){
        LockSupport.park();
        System.out.println("C");
    }

    public static void main(String[] args) {
        Thread c = new Thread(()->tc());
        Thread b = new Thread(()->tb(c));
        Thread a = new Thread(()->ta(b));
        c.start();
        b.start();
        a.start();
    }
}
