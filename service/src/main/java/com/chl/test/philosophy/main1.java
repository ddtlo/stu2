package com.chl.test.philosophy;

import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 哲学家问题,有筷子筒版本
 * @Author 陈汉霖
 * @Date 2024/1/4
 */
public class main1 {
    public static void main(String[] args) throws InterruptedException {

        Map<Integer, Chopstick> chopstickContainer = new ConcurrentHashMap<>();

        Philosophy philosophy1 = new Philosophy(0, 5, chopstickContainer);
        Philosophy philosophy2 = new Philosophy(1, 5, chopstickContainer);
        Philosophy philosophy3 = new Philosophy(2, 5, chopstickContainer);
        Philosophy philosophy4 = new Philosophy(3, 5, chopstickContainer);
        Philosophy philosophy5 = new Philosophy(4, 5, chopstickContainer);

        Chopstick chopstick1 = new Chopstick(0);
        Chopstick chopstick2 = new Chopstick(1);
        Chopstick chopstick3 = new Chopstick(2);
        Chopstick chopstick4 = new Chopstick(3);
        Chopstick chopstick5 = new Chopstick(4);

        chopstickContainer.put(chopstick1.getNo(), chopstick1);
        chopstickContainer.put(chopstick2.getNo(), chopstick2);
        chopstickContainer.put(chopstick3.getNo(), chopstick3);
        chopstickContainer.put(chopstick4.getNo(), chopstick4);
        chopstickContainer.put(chopstick5.getNo(), chopstick5);

        philosophy1.start();
        philosophy2.start();
        philosophy3.start();
        philosophy4.start();
        philosophy5.start();

        System.out.println("运行完毕");
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
        private final int total;
        private Chopstick left;
        private Chopstick right;
        private int eatSum = 0;
        private int forTime = 4;
        private final Map<Integer, Chopstick> chopstickContainer;

        public Philosophy(int no, int total, Map<Integer, Chopstick> chopstickContainer) {
            this.no = no;
            this.total = total;
            this.chopstickContainer = chopstickContainer;
        }

        /**
         * 0左，1右
         *
         * @param flag
         * @return
         */
        private Boolean getChopstick(int flag) {
            if (flag == 0 && left == null) {
                Chopstick chopstick = chopstickContainer.remove(no);
                left = chopstick;
                return left == null ? false : chopstick.get(this);
            }
            if (flag == 1 && right == null) {
                Chopstick chopstick = chopstickContainer.remove(no == total - 1 ? total - 1 : no - 1);
                right = chopstick;
                return right == null ? false : chopstick.get(this);
            }
            return false;
        }

        private boolean eat() throws InterruptedException {
            Boolean result = false;
            getChopstick(0);
            getChopstick(1);

            if (left != null && right != null) {
                System.out.println(String.format("哲学家%s开始使用筷子%s和%s吃饭", no, left.getNo(), right.getNo()));
                Thread.sleep(1000);
//                System.out.println(String.format("哲学家%s结束使用筷子%s和%s吃饭", no, left.getNo(), right.getNo()));
                eatSum++;
                result = true;
            } else {
                String msg;
                if (left == null && right != null) {
                    msg = String.format("哲学家%s吃饭失败,没有左边的筷子", no);
                } else if (right == null && left != null) {
                    msg = String.format("哲学家%s吃饭失败,没有右边的筷子", no);
                } else {
                    msg = String.format("哲学家%s吃饭失败,没有两边的筷子", no);
                }
                System.out.println(msg);
                Thread.sleep(400);
            }
            if (left != null) {
                left.giveUp(this, chopstickContainer);
                left = null;
            }
            if (right != null) {
                right.giveUp(this, chopstickContainer);
                right = null;
            }
            return result;
        }

        @Override
        public void run() {
            while (forTime > 0) {
                try {
                    eat();
                    forTime--;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            super.run();
        }
    }
}
