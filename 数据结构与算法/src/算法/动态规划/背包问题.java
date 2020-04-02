package 算法.动态规划;

public class 背包问题 {
    public static void main(String args[]) {
        int w[] = {1, 4, 3};               // 保存物品重量
        int val[] = {1500, 3000, 2000};   // 物品的价值
        int capacity = 4;                 // 背包容量
        int n = val.length;               // 物品个数


        // 二维数组,表
        // v[i][j]表示在前i个物品中能够放入容量为j的背包中的最大价值
        int[][] v = new int[n + 1][capacity + 1];
        // 记录放入商品的情况
        int[][] path = new int[n + 1][capacity + 1];

        // 初始化第一行,第一列
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; // 第一行设置为0
        }
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;    // 第一列设置为0
        }

        // 根据公式,算法.动态规划
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                // 公式
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    // 因为i从1开始
                    // 因此需要:w[i-1],val[i-1]
                    //v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    // 为了记录商品存放到背包的情况,不能直接使用上述公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 把当前情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int[] ints : v) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("取了哪些商品?");
        // 遍历最后的放入情况
        int i = path.length - 1;  // 行最大下标
        int j = path[0].length - 1;   // 列最大下标
        // 从最后开始找
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入背包\n", i);
                j -= w[i - 1];  // 指向前一个放到背包的商品,-1是因为i比w大1
            }
            i--;
        }
    }
}
