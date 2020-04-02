package 算法.排序;

import java.util.Arrays;

public class 冒泡排序 {
    public static void main(String args[]) {

        int a[] = {6, 2, 32, 43, 8, 16, 21, 9, 77,-2};
        //bubbSort(a);
        hyperBubbSort(a);
        System.out.println(Arrays.toString(a));

    }

    private static void bubbSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    private static void hyperBubbSort(int[] a) {
        boolean flag = false;   // 标识是否进行过交换
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    flag = true;    // 进行交换
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            if (!flag) {
                break;      // 在一趟排序中一次交换都没有发生
            } else {
                flag = false;   // 重置便于下次判断
            }
        }
    }

}
