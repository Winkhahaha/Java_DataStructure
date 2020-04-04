package 算法.普利姆算法;

public class PrimAlgorithm {
    public static void main(String args[]) {
        String s = "ABCDEFG";
        char[] chars = s.toCharArray();

        // 邻接矩阵
        // 10000表示两个点不连通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};

        MGraph graph = new MGraph(chars.length);
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, chars.length, chars, weight);
        System.out.println("图的邻接矩阵:");
        minTree.print(graph);
        // 测试普利姆算法
        minTree.prim(graph, 0);
    }
}

class MGraph {
    int verx;   // 表示图节点个数
    char[] data;    // 存放节点数据
    int[][] weight; // 存放边,邻接矩阵

    public MGraph(int verx) {
        this.verx = verx;
        data = new char[verx];
        weight = new int[verx][verx];
    }
}

// 创建最小生成树 -> 村庄的图
class MinTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param graph  图对象
     * @param verxs  图对应的顶点个数
     * @param data   图的各顶点值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void print(MGraph graph) {
        for (int[] ints : graph.weight) {
            for (int i : ints) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    // v:从图的第几个顶点开始
    public void prim(MGraph graph, int v) {
        // 该数组标记顶点是否被访问过
        // 默认元素全部为0
        int[] visited = new int[graph.verx];
        // 把当前节点v标记为已访问
        visited[v] = 1;
        // h1,和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        // 记录最短路径权值,若找到,则替换之
        int minWeight = 10000;
        // 计算权值总和
        int sum = 0;
        /**
         * i 需要找到的边总数(顶点数-1)
         * j和k理解为在二维数组中寻找<构成一条边且权值最小的两顶点>
         */
        // 因为有verx个顶点,普利姆算法结束后,有verx - 1条边
        for (int i = 1; i < graph.verx; i++) {
            // 确定每一次生成的子图(<A,G,B>...)和哪个节点的距离最近
            for (int j = 0; j < graph.verx; j++) {  // j表示被访问过的节点
                for (int k = 0; k < graph.verx; k++) {  // k表示还没有被访问过的节点
                    if (visited[j] == 1 && visited[k] == 0 && graph.weight[j][k] < minWeight) {
                        minWeight = graph.weight[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            // 找到一条边是最小的
            System.out.println("边:" + graph.data[h1] + "--" + graph.data[h2] + ",权值:" + minWeight);
            sum += minWeight;
            // 将当前找到的节点标记为已经访问
            visited[h2] = 1;
            minWeight = 10000;
        }
        System.out.println("权值和:" + sum);
    }
}