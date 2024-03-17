package com.chl.test.base;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/18
 */
public class FinallyTest {
    public static void main(String[] args) {
        System.out.println(get());
    }

    public static int get(){
        int a = 0;
        try {
            a++;
            return a;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            a--;
            System.out.println("f:"+a);
        }
    }
}
