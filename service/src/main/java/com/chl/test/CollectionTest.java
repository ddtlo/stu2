package com.chl.test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2023/12/18
 */
public class CollectionTest {

    public void stackTest(){
        Deque<String> stack = new ArrayDeque<>();
        stack.push("1");
        stack.push("2");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
    public static void main(String[] args) {
        CollectionTest collectionTest = new CollectionTest();
        collectionTest.stackTest();
    }
}
