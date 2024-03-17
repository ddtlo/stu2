package com.chl.test.philosophy;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 哲学家问题, 互相持有版本
 * @Author 陈汉霖
 * @Date 2024/1/4
 */
public class main2 {
    public static void main(String[] args) throws InterruptedException {

        Chopstick chopstick1 = new Chopstick(0);
        Chopstick chopstick2 = new Chopstick(1);
        Chopstick chopstick3 = new Chopstick(2);
        Chopstick chopstick4 = new Chopstick(3);
        Chopstick chopstick5 = new Chopstick(4);

        Philosophy philosophy1 = new Philosophy(0, chopstick1, chopstick5);
        Philosophy philosophy2 = new Philosophy(1, chopstick2, chopstick1);
        Philosophy philosophy3 = new Philosophy(2, chopstick3, chopstick2);
        Philosophy philosophy4 = new Philosophy(3, chopstick4, chopstick3);
        Philosophy philosophy5 = new Philosophy(4, chopstick5, chopstick4);

        philosophy1.start();
        philosophy2.start();
        philosophy3.start();
        philosophy4.start();
        philosophy5.start();

//        System.out.println("运行完毕");
//        Thread.sleep(10000);
    }

    /**
     * @Description
     * @Author 陈汉霖
     * @Date 2024/1/4
     */
    @Data
    public static class Chopstick {
        private final int no;
        private Philosophy philosophy;

        public Chopstick(int no) {
            this.no = no;
        }

        public Boolean get(Philosophy philosophy) {
            if (philosophy == this.philosophy) {
                return true;
            }
            if (this.philosophy == null) {
                this.philosophy = philosophy;
                return true;
            }
            return false;
        }

        public Boolean giveUp(Philosophy philosophy, Map<Integer, Chopstick> chopstickContainer) {
            if (philosophy == this.philosophy) {
                this.philosophy = null;
                chopstickContainer.put(no, this);
                return true;
            }
            return false;
        }
    }

    /**
     * @Description
     * @Author 陈汉霖
     * @Date 2024/1/4
     */
    @Data
    public static class Philosophy extends Thread {
        private final int no;
        private Chopstick left;
        private Chopstick right;

        public Philosophy(int no, Chopstick left, Chopstick right) {
            this.no = no;
            this.left = left;
            this.right = right;
        }

        /**
         * 死锁模式
         */
        @Override
        public void run() {
            synchronized (left) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (right) {
                    System.out.println(String.format("哲学家%s吃饭", this.no));
                }
            }
        }
    }
}
