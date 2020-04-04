package 算法.查找算法;

public class 插值查找 {
    public static void main(String args[]) {
        int a[] = new int[100];
        // 要求数组排序且有序
        for (int i = 0; i < 100; i++) {
            a[i] = i + 1;
        }
        int i = insertValueSearch(a, 0, a.length - 1, 100);
        if (i == -1) {
            System.out.println("没有找到!");
        } else {
            System.out.println("下标:" + i);
        }
    }

    // 找到一个满足条件的值就返回
    private static int insertValueSearch(int[] a, int left, int right, int value) {
        // 防止越界
        if (left > right || value < a[0] || value > a[a.length - 1]) {
            return -1;
        }
        // 自适应
        int mid = left + (right - left) * (value - a[left]) / (a[right] - a[left]);
        if (a[mid] < value) {
            // value在mid右边
            return insertValueSearch(a, mid + 1, right, value);
        } else if (a[mid] > value) {
            // value在mid左边
            return insertValueSearch(a, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
