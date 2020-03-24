package 递归;

public class Queen8 {
    // 定义一个max数组,表示共有多少皇后
    static int max = 8;
    // 定义数组arr,保存皇后放置的位置,arr = {0(第一行第一列),5(第二行第六列)}.一行一个皇后,所以存列就行
    static int[] arr = new int[max];
    // 定义解法计数器
    static int count = 0;

    public static void main(String args[]) {
        check(0);
        System.out.println("共有:"+count);
    }

    // 放置第n个皇后
    // 注意:每一次进入check方法,都有for循环,因此产生回溯
    private static void check(int n){
        if (n == max){
            print();
            count++;    // 计一个解法
            return;
        }
        for (int i = 0; i < max; i++) {
            // 先把当前皇后放到该行的第1列
            arr[n] = i;
            // 判断放到i列时是否冲突
            if (judge(n)){      // 不冲突
                check(n+1);
            }
            // 如果冲突,就回到arr[n] = i+1;
        }
    }

    // 当我们放置第n个皇后时,就去检测该皇后是否和已经摆放的皇后冲突(回溯)
    private static boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // arr[i] == arr[n]判断是否和前面皇后在同一列
            // Math.abs(n - i) == Math.abs(arr[n] - arr[i])判断是否和前面皇后在同一斜线
            // 二维坐标系中,处于同一斜线上的坐标:(x1,y1),(x2,y2),x1-x2等于y1-y2(绝对值)
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    private static void print() {
        for (int i : arr) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }
}

