package com.chl.test.观察者;
import java.util.Observable;
/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/8
 */
public class ObserverTest extends Observable {
    /**
     * @param args
     */
    public static void main(String[] args) {
        ObserverTest observer = new ObserverTest();
        // 添加观察者
        observer.addObserver((o, arg) -> {
            System.out.println("通知数据变化---1");
        });
        observer.addObserver((o, arg) -> {
            System.out.println("通知数据变化---2");
        });
        observer.addObserver((o, arg) -> {
            System.out.println("通知数据变化---3");
        });
        // 数据变化
        observer.setChanged();
        // 通知
        observer.notifyObservers();
        System.out.println("代码执行完成.....4");
    }
}
