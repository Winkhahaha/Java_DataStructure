package 递归;

public class MiGong {
    static int count = 0;
    public static void main(String args[]) {

        // 二维数组模拟地图
        int[][] map = new int[8][7];

        // 使用1表示墙
        // 上下置1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右置1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置迷宫挡板
        map[3][1] = 1;
        map[3][2] = 1;

        // 查看地图
        // print(map);
        //setWay(map, 1, 1);
        setWay2(map, 1, 1);
        System.out.println("标识过的地图:");
        print(map);
        System.out.println("总步数:"+count);


    }

    /**
     * @param map 地图
     * @param i   从哪个位置开始找,比如(1,1)
     * @param j
     * @return 找到通路返回true
     * 如果找到map[6][5]位置,说明通路找到
     * 当map[i][j]为0表示该点未被走过,2表示可以走,3表示该点已经走过,但是走不通
     * 定制策略:
     * 下-右-上-左
     */
    private static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 通路找到
            return true;
        } else {
            if (map[i][j] == 0) {
                // 如果当前这个点没有走过
                // 按照策略
                map[i][j] = 2;  // 假定该点可以走通

                if (setWay(map, i + 1, j)) {         // 向下走,所以行+1
                    return true;
                } else if (setWay(map, i, j + 1)) {   // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {   // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {   // 向左走
                    return true;
                } else {
                    // 该点走不通,死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j]!=0,可能是1,2,3
                return false;
            }
        }
    }

    // 改变策略
    private static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 通路找到
            return true;
        } else {
            if (map[i][j] == 0) {
                // 如果当前这个点没有走过
                // 按照策略
                map[i][j] = 2;  // 假定该点可以走通
                count++;
                if (setWay2(map, i - 1, j)) {          // 向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) {   // 向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {   // 向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) {   // 向左走
                    return true;
                } else {
                    // 该点走不通,死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j]!=0,可能是1,2,3
                return false;
            }
        }
    }

    private static void print(int[][] map) {
        // 查看地图
        for (int[] datas : map) {
            for (int data : datas) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
    }
}
