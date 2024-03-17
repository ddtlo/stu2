package com.chl.test.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/3/7
 */
public class Sort {

    public static void main(String[] args) {
//        final int[] a = {1, 2, 3, 5, 7, 11};
        final int[] a = {11, 3, 15, 8, 18, 14, 6, 9, 2, 17, 13, 4, 10, 19, 16, 5, 7, 12, 20, 1, 11, 15, 18, 3, 4, 14, 8, 9};
//        bubbleSort(a);
//        int[] b = quickSort(a, 0, a.length - 1);
//        quickSort2(a, 0, a.length - 1);
        insertSort(a);
        System.out.println(Arrays.stream(a).boxed()
                .map(Object::toString) // 将Integer对象转换为字符串
                .collect(Collectors.joining(",")));
        System.out.println(BinarySearch(a, 7));
    }

    /**
     * 插入排序
     * 3 11
     * 3 11 15
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int n = a[i];
            int index = i - 1;
            while (index >= 0 && n < a[index]) {
                a[index + 1] = a[index];
                index--;
            }
            a[index + 1] = n;
        }
    }

    /**
     * 二分查找
     *
     * @param a
     * @param b
     * @return
     */
    static private int BinarySearch(int[] a, int b) {
        int start = 0;
        int end = a.length - 1;
        while (start <= end) {
            int mid = (start + end) >>> 1;
            if (b == a[mid]) {
                return mid;
            }
            if (b < a[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序
     *
     * @param a
     * @return
     */
    static private int[] bubbleSort(int[] a) {
        boolean change = true;
        while (change) {
            change = false;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i] > a[i + 1]) {
                    int tmp = a[i + 1];
                    a[i + 1] = a[i];
                    a[i] = tmp;
                    change = true;
                }
            }
        }
        return a;
    }

    static int[] quickSort(int[] a, int start, int end) {
        int i = start;
        int j = end;
        int p = a[start];
        while (i < j) {
            while ((i < j) && a[j] >= p) {
                j--;
            }
            if (i < j && a[j] <= p) {
                change(a, i, j);
            }

            while ((i < j) && a[i] >= p) {
                i++;
            }
            if (i < j && a[i] >= p) {
                change(a, i, j);
            }
        }
        if (start < i) {
            quickSort(a, start, i - 1);
        }
        if (end > j) {
            quickSort(a, j + 1, end);
        }
        return a;
    }

    static void change(int[] a, int l, int r) {
        int tmp = a[l];
        a[l] = a[r];
        a[r] = tmp;
    }

    /**
     * 快速排序（无返回值）
     *
     * @param a    需要排序的数组
     * @param low  数组的最小索引： 0
     * @param high 数组的最大索引： arr.length - 1
     */
    public static void quickSort2(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int p = a[start];
        while (start < end) {
            while ((start < end) && p < a[end]) {
                end--;
            }
            if (start < end && p > a[end]) {
                change(a, start, end);
            }
            while ((start < end) && p > a[start]) {
                start++;
            }
            if (start < end && p < a[start]) {
                change(a, start, end);
            }
        }
        if (start > low) {
            quickSort2(a, low, start - 1);
        }
        if (end < high) {
            quickSort2(a, end + 1, high);
        }
    }
}
