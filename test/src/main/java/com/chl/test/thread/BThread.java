package com.chl.test.thread;

import lombok.Data;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/2/16
 */
@Data
public class BThread extends Thread {
    private int code;

    @Override
    public void run() {
        while (!isInterrupted()) {
//            try {
//                if (code > 0) {
//                    break;
//                }
//                Thread.sleep(1000);
//                System.out.println(String.format("线程%s还活着", getName()));
//            } catch (InterruptedException e) {
////                throw new RuntimeException(e);
//                break;
//            }
            if (code > 0) {
                break;
            }
        }
        System.out.println(String.format("线程%s结束了", getName()));
    }
}
