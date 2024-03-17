package com.chl;

import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/3/17
 */
public class sadfsdf {
    @Test
    public void testFileOutputStream() {
        // FileOutputStream(String name, boolean append) 追加写入 // FileOutputStream(String name) 覆盖写入
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("mrpersimmon.txt"))) {
            String str = "Hi,Mrpersimmon!"; // write(byte b[]) : 将字节数组 b 写入到输出流，等价于 write(b, 0, b.length)
            bos.write(str.getBytes("UTF-8")); // str.getBytes() 字符串 -> 字节数组
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}