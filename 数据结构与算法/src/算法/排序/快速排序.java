package 算法.排序;

import java.util.Arrays;

public class 快速排序 {
    public static void main(String args[]) {

        int a[] = {6, 2, 32, 43, 8, 16, 21, 9, 77, -2};
        quick(a);
        System.out.println(Arrays.toString(a));

    }

    private static void quick(int[] a) {
        if (a.length > 0) {
            quickSort(a, 0, a.length - 1);
        }
    }


    //左下标,右下标
    private static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = getMiddle(a, left, right);
            quickSort(a,0,middle-1);
            quickSort(a,middle+1,right);
        }
    }

    // 快排核心:找出一个位置的元素满足:所有左元素<=该元素<=所有右元素
    private static int getMiddle(int[] a, int left, int right) {
        int temp = a[left];     // 基准元素
        while (left < right) {
            while (left < right && a[right] >= temp) {
                right--;
            }
            a[left] = a[right];
            while (left < right && a[left] <= temp) {
                left++;
            }
            a[right] = a[left];
        }
        a[left] = temp;     // 将基数插入到排序后的位置
        return left;
    }

}

