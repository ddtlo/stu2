package com.chl.test;
import java.util.Random;

public class 概率递增模拟 {
    public static void main(String[] args) {
        int aGradeItems = 10000; // 初始A级物品数量
        int bGradeItems = 0; // 初始B级物品数量

        Random random = new Random();
        int successRate = 20; // 初始成功率
        int consecutiveFailures = 0; // 连续失败次数

        while (aGradeItems >= 2) {
            // 合成过程
            for (int i = 0; i < 2; i++) {
                int randomChance = random.nextInt(100) + 1; // 生成1到100的随机数
                if (randomChance <= successRate) {
                    // 合成成功
                    aGradeItems--; // 消耗两个A级物品
                } else {
                    // 合成失败
                    consecutiveFailures++; // 增加连续失败次数
                    if (consecutiveFailures >= 5) {
                        // 每连续5次失败成功率增加10%
                        successRate += 10;
                        if (successRate > 100) {
                            successRate = 100; // 确保成功率不超过100%
                        }
                    }
                }
            }

            // 制造一个B级物品
            bGradeItems++;

            if (aGradeItems < 2) {
                break; // 如果剩余的A级物品不足两个，无法再继续合成B级物品，跳出循环
            }
        }

        System.out.println("最终合成的B级物品数量为：" + bGradeItems);
    }
}
