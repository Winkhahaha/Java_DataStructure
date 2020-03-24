package 查找算法;

public class 线性查找 {
    public static void main(String args[]) {
        int a[] = {6, 2, 32, 43, 8, 16, 21, 9, 77, -2};
        int i = seqSearch(a, -2);
        if (i == -1) {
            System.out.println("没有找到!");
        } else {
            System.out.println("下标:" + i);
        }
    }

    // 找到一个满足条件的值就返回
    private static int seqSearch(int[] a, int value) {
        // 线性查找是逐一比对
        for (int i = 0; i < a.length; i++) {
            if (a[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
