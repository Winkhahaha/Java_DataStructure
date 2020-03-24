package 查找算法;

import java.util.Arrays;

public class 斐波拉契查找 {
    final static int maxSize = 20;

    public static void main(String args[]) {
        // 数组需要排序
        int a[] = {1, 8, 10, 89, 1000, 1234};
        int i = fibSearch(a, 10);
        if (i == -1) {
            System.out.println("没有找到!");
        } else {
            System.out.println("下标:" + i);
        }
    }

    // 得到一个斐波拉契数列数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int fibSearch(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;
        int mid = 0;
        int k = 0;  // 表示斐波拉契分割数值的下标
        int f[] = fib();    // 获取斐波拉契数列
        // 获取到斐波拉契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        // 因为f[k]可能大于数组a长度,因此需要使用Arrays类,扩容
        // 扩加部分用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        // 但我们使用a[a.length-1]填充:
        // a[] = {1, 8, 10, 89, 1000, 1234,0,0,0} -> {1, 8, 10, 89, 1000, 1234,1234,1234,1234}
        // 从a.length索引开始填充
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        // 循环处理,找到数value
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (value < temp[mid]) {
                // 应该继续向左边查找
                high = mid - 1;
                // 说明k--
                // f[k] = f[k-1] + f[k-2]
                // 因为前面有f[k-1],所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                // 即下次循环mid = f[k-1-1] - 1
                k--;
            } else if (value > temp[mid]) {
                // 应该继续向右边查找
                low = mid + 1;
                // 说明k -= 2
                // f[k] = f[k-1] + f[k-2]
                // 因为后面有f[k-2] 所以继续拆分f[k-1] = f[k-3] + f[k-4]
                // 即在f[k-2]前面继续查找k-=2
                // 即下次循环mid = f[k-1-2] - 1
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
