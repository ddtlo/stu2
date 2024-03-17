package com.chl.test.thread.forkJoin;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/12
 */

import java.util.concurrent.RecursiveTask;
public class ForkRecursiveTask<T> extends RecursiveTask<Long> {

    Long start;
    Long end;
    Long threshold = 1000000000l;

    public ForkRecursiveTask(Long start, Long end)
    {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Long compute() {
        if((end - start)<threshold)
        {
            Long sum = 0l;
            for (Long i=start;i<=end;i++)
            {
                sum+=i;
            }
            return sum;
        }
        else {
            Long middle = (start + end) / 2;
            ForkRecursiveTask left = new ForkRecursiveTask(start, middle);
            ForkRecursiveTask right = new ForkRecursiveTask(middle + 1, end);
            left.fork();
            right.fork();
            Long leftResult = (Long) left.join();
            Long rightResult = (Long) right.join();
            return leftResult + rightResult;
        }
    }
}
