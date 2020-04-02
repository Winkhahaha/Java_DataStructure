package 算法.分治算法;

public class 汉诺塔 {
    private static int count = 0;   // 记录移动次数

    public static void main(String args[]) {
        hanoTower(5, 'A', 'B', 'C');
    }

    /**
     *
     * @param num 个数
     * @param a from
     * @param b mid
     * @param c to
     */
    public static void hanoTower(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第" + num + "个盘从" + a + "--->" + c + " :第" + (++count) + "步");
        } else {
            // 当num >= 2,总是看成两个盘:
            // a.最下边的一个盘
            // b.上面的所有盘
            // 1.先把最上面的盘A-->B,移动过程用到C
            hanoTower(num - 1, a, c, b);
            // 2.把最下面的盘A-->C
            System.out.println("第" + num + "个盘从" + a + "--->" + c + " :第" + (++count) + "步");
            // 3.把B塔的所有盘B-->C,移动过程用到A
            hanoTower(num - 1, b, a, c);
        }
    }
}
