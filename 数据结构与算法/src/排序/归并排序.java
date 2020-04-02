package 排序;

import java.util.Arrays;

public class 归并排序 {
    public static void main(String args[]) {
        int a[] = {6, 2, 32, 43, 8, 16, 21, 9, 77, -2};
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    private static void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(a, left, middle);       // 分成左右数组各自排序
            mergeSort(a, middle + 1, right);
            merge(a, left, middle, right);     // 最后合并
        }
    }

    private static void merge(int[] a, int left, int middle, int right) {
        int[] newArray = new int[a.length];
        int rightStart = middle + 1;  // 右数组起始位置
        int temp = left;
        int index = left;
        // 左右小数组元素开始比较,谁小先放谁进新数组,之后将索引位置+1
        while (left <= middle && rightStart <= right) {
            if (a[left] <= a[rightStart]) {
                newArray[index++] = a[left++];
//                index++;
//                left++;
            } else {
                newArray[index++] = a[rightStart++];
            }
        }
        // 右边小数组已经全放进去,左边还剩下的直接拷贝
        while (left <= middle) {
            newArray[index++] = a[left++];
        }
        // 左边小数组已经全放进去,右边还剩下的直接拷贝
        while (rightStart <= right) {
            newArray[index++] = a[rightStart++];
        }
        // 将每一轮新数组赋给原数组a
        while (temp <= right) {
            a[temp] = newArray[temp];
            temp++;
        }

    }

}
