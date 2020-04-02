package 算法.排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 基数排序 {
    public static void main(String args[]) {
        int a[] = {6, 12, 32, 43, 8, 116, 221, 19, 77, 20};
        basicSort(a);
        System.out.println(Arrays.toString(a));

    }

    private static void basicSort(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        int times = 0;
        while (max > 0) {
            max = max / 10;
            times++;        // 获取最大值的位数,得到基数排序的次数
        }
        List<ArrayList> queue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList q = new ArrayList();
            queue.add(q);
            q = null;
        }
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < a.length; j++) {
                // 获取对应位的值(i为0是个位,1是十位,2是百位)
                int x = a[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList q = queue.get(x); // {[0],[1],[2]...}
                q.add(a[j]);    // 添加进对应下标数组
                //queue.set(x,q);
            }
            // 开始收集
            int count = 0;
            for (int j = 0; j < 10; j++) {
                while (queue.get(j).size() > 0) {
                    ArrayList<Integer> q = queue.get(j);    //拿到每一个数组
                    a[count] = q.get(0);      //取出各个小数组中的元素,取一个删一个
                    q.remove(0);
                    count++;
                }
            }
        }
    }
}
