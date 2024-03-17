package com.chl.test.thread;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/16
 */
public class TestThread extends Thread {
    private Thread[] threads;

    TestThread(Thread... threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            int count = 0;
            for (Thread thread : threads) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (!thread.getState().equals(State.TERMINATED)) {
                    count++;
                }else {
                    System.out.println("线程已停止:"+thread.getName());
                }
            }
            if (count == 0) {
                System.out.println("线程全部停止");
                break;
            } else {
                System.out.println("运行线程数:" + count);
            }
        }
    }
}
