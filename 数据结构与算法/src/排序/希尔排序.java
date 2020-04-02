package 排序;

import java.util.Arrays;

public class 希尔排序 {
    public static void main(String args[]) {

        int a[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        hellSort2(a);
        System.out.println(Arrays.toString(a));

    }

    // 希尔排序(交换法)
    private static void hellSort(int[] a) {
        // 逐渐缩小步长
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            // 步长为5,即a[j]和a[j+5]一组进行交换
            for (int i = gap; i < a.length; i++) {
                // 如果当前元素大于加上步长之后的元素,交换
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (a[j] > a[j + gap]) {
                        int temp = a[j];
                        a[j] = a[j + gap];
                        a[j + gap] = temp;
                    }
                }
            }
        }
    }

    // 改进希尔排序为移位法
    private static void hellSort2(int[] a) {
        // 逐渐缩小步长
        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素,逐个对其所在的组进行直接插入排序
            for (int i = gap; i < a.length; i++) {
                int j = i;      // 插入下标
                int temp = a[j];    // 插入元素
                if (a[j] < a[j - gap]) {
                    while (j - gap >= 0 && temp < a[j - gap]) {
                        // 移动
                        a[j] = a[j - gap];
                        j -= gap;
                    }
                    //退出循环,找到了插入位置
                    a[j] = temp;
                }
            }
        }
    }
}

