package 查找算法;

import java.util.ArrayList;

public class 二分查找 {
    public static void main(String args[]) {
        // 二分查找需要数组排序
        int a[] = {-2, 2, 6, 8, 9, 16, 16, 16, 16, 21, 32, 43, 77};
//        int i = binarySearch(a, 0, a.length - 1, 16);
//        if (i == -1) {
//            System.out.println("没有找到!");
//        } else {
//            System.out.println("下标:" + i);
//        }
//        ArrayList<Integer> list = binarySearchAll(a, 0, a.length - 1, 16);
//        System.out.println("下标集合:" + list);
        System.out.println(binarySearch2(a, 6));
    }

    // 找到一个满足条件的值就返回
    // 递归
    private static int binarySearch(int[] a, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        // value在mid右边
        if (a[mid] < value) {
            return binarySearch(a, mid + 1, right, value);
        } else if (a[mid] > value) {
            // value在mid左边
            return binarySearch(a, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    // 二分查找(非递归)
    private static int binarySearch2(int[] a, int value) {
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] > value) {
                // value在mid左边
                right = mid - 1;
            } else if (a[mid] < value) {
                // value在mid右边
                left = mid + 1;
            }
        }
        return -1;
    }

    // 找出数组中所有的和value一样的值的下标
    // 找到mid时不返回,继续向mid索引值的左边扫描,将所有满足的加入集合
    // 向mid索引值的右边扫描,将所有满足的加入集合
    private static ArrayList<Integer> binarySearchAll(int[] a, int left, int right, int value) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        // value在mid右边
        if (a[mid] < value) {
            return binarySearchAll(a, mid + 1, right, value);
        } else if (a[mid] > value) {
            // value在mid左边
            return binarySearchAll(a, left, mid - 1, value);
        } else {
            ArrayList<Integer> indexList = new ArrayList<>();
            // 向左边搜索
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || a[temp] != value) {
                    break;
                }
                indexList.add(temp);
                temp -= 1;
            }
            indexList.add(mid);
            // 向右边搜索
            temp = mid + 1;
            while (true) {
                if (temp > a.length - 1 || a[temp] != value) {
                    break;
                }
                indexList.add(temp);
                temp += 1;
            }
            return indexList;
        }
    }
}
