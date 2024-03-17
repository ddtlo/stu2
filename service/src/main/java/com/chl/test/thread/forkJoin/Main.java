package com.chl.test.thread.forkJoin;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/12
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkRecursiveTask task = new ForkRecursiveTask(1l,10_0000_0000l);
        long startTime = System.currentTimeMillis();
        Future<Long> result = forkJoinPool.submit(task);
        System.out.println(result.get());
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间："+(endTime-startTime)+"ms");
    }
}
