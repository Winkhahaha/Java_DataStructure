package 排序;

import java.util.Arrays;

public class 选择排序 {
    public static void main(String args[]) {

        int a[] = {6, 2, 32, 43, 8, 16, 21, 9, 77,-2};
        choseSort(a);
        System.out.println(Arrays.toString(a));

    }

    private static void choseSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 1 + i; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

}
