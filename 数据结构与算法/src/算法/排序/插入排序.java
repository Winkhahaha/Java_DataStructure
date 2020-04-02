package 算法.排序;

import java.util.Arrays;

public class 插入排序 {
    public static void main(String args[]) {

        int a[] = {6, 2, 32, 43, 8, 16, 21, 9, 77, -2};
        insertSort(a);
        System.out.println(Arrays.toString(a));

    }

    // 插入排序
    private static void insertSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            // 定义待插入的数
            int insertVal = a[i + 1];
            // 待插入数的前一个下标
            int beforeIndex = i;
            // 找到要插入的位置
            while (beforeIndex >= 0 && insertVal < a[beforeIndex]) {
                a[beforeIndex + 1] = a[beforeIndex];
                beforeIndex--;
            }
            // 退出while,说明已找到要插入位置的前一个下标(因为要判断前一个是否小于插入,所以找到的是前一个)
            a[beforeIndex + 1] = insertVal;
        }
    }

}

