package com.chl.test;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2023/12/18
 */
public class StringTest {
    final String str1 = "Hello";//定义实例变量时指定初始值
    final String str11 = "Hello";//定义实例变量时指定初始值

    final String str2;//非静态初始化块中对实例变量进行初始化
    final String str3;//构造器中对实例变量进行初始化

    {
        str2 = "Hello";
    }
    public StringTest() {
        str3 = "Hello";
    }

    public void show(){
        System.out.println(str1 + str1 == "HelloHello");//true
        System.out.println(str1 + str11 == "HelloHello");
        System.out.println(str2 + str2 == "HelloHello");//false
        System.out.println(str3 + str3 == "HelloHello");//false
    }
    public static void main(String[] args) {
        new StringTest().show();
    }
}
