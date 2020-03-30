package 图;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexs;   // 存储图顶点集合
    private int vertexSize;             // 顶点个数
    private int[][] edges;               // 存储图的连接矩阵
    private int numOfEdges;             // 边的个数
    // 定义数组,记录某个顶点是否被访问过
    private boolean[] isVisited;

    public Graph(int n) {
        // 初始化矩阵和vertex
        this.vertexSize = n;
        edges = new int[vertexSize][vertexSize];
        vertexs = new ArrayList<>();
    }

    // 得到第一个邻接节点的下标
    // 如果存在就返回对应的下标
    public int getFirst(int v1) {
        for (int i = 0; i < vertexs.size(); i++) {
            if (edges[i][v1] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNext(int v1, int v2) {
        for (int i = v2 + 1; i < vertexs.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    // 深度优先遍历
    private void dfs(boolean[] is, int i) {
        // 首先访问该节点
        System.out.print(getData(i) + "-->");
        // 将该节点设置为已访问
        is[i] = true;
        // 查找节点i的第一个邻接节点w
        int w = getFirst(i);
        while (w != -1) {
            // 判断w是否被访问过
            if (!is[w]) {
                dfs(is, w);
            }
            // 如果已经被访问过
            w = getNext(i, w);
        }
    }

    // 对dfs进行重载,遍历所有节点进行dfs
    // 回溯
    public void dfs() {
        isVisited = new boolean[vertexSize];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!this.isVisited[i]) {
                dfs(this.isVisited, i);
            }
        }
    }

    // 广度优先遍历
    private void bfs(boolean[] is, int i) {
        int u;  // 表示队列头结点的下标 A
        int w;  // 表示邻接节点
        // 队列:记录节点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        System.out.print(getData(i) + "-->");
        // 标记为已访问
        is[i] = true;
        // 将该节点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            // 取出队列的头结点下标
            u = queue.removeFirst();
            // 得到第一个邻接节点的下标w
            w = getFirst(u);
            while (w != -1) {  // 找到
                if (!is[w]) {
                    System.out.print(getData(w) + "-->");
                    // 标记已经访问
                    is[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 如果已经访问过
                // 以u为起点的那一行继续找w的下一个邻接点
                w = getNext(u, w);
            }
        }
    }

    // 广度优先搜索,遍历所有节点
    public void bfs() {
        isVisited = new boolean[vertexSize];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!this.isVisited[i]) {
                bfs(this.isVisited, i);
            }
        }
    }


    // 插入顶点
    public void insert(String vertex) {
        vertexs.add(vertex);
    }

    // 添加边
    // v1和v2表示第几个顶点
    // weight为1表示点与点连接
    public void insertEdge(int v1, int v2, int weight) {
        // 无向图
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;   // 边长加一个
    }

    // 返回节点的个数
    public int getNumOfVertex() {
        return vertexs.size();
    }

    // 返回边的数目
    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    // 返回节点i对应的数据
    public String getData(int i) {
        return vertexs.get(i);
    }

    // 返回点和点直接是否连接的标志(1)
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 显示图的矩阵
    public void print() {
        for (int[] edge : edges) {
            for (int i : edge) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        // 创建顶点
        String vertex = "ABCDEFGH";
        Graph graph = new Graph(vertex.length());
        for (int i = 0; i < vertex.length(); i++) {
            graph.insert(String.valueOf(vertex.charAt(i)));
        }

        // 创建边
        // A-B
        graph.insertEdge(0, 1, 1);
        // A-C
        graph.insertEdge(0, 2, 1);
        // B-D
        graph.insertEdge(1, 3, 1);
        // B-E
        graph.insertEdge(1, 4, 1);
        // D-H
        graph.insertEdge(3, 7, 1);
        // E-H
        graph.insertEdge(4, 7, 1);
        // C-F
        graph.insertEdge(2, 5, 1);
        // C-G
        graph.insertEdge(2, 6, 1);
        // F-G
        graph.insertEdge(5, 6, 1);
        // 遍历图矩阵
        graph.print();
        System.out.println("顶点数:" + graph.vertexSize);
        System.out.println("边数:" + graph.numOfEdges);

        // 测试dfs
        System.out.println("深度优先遍历:");
        graph.dfs();
        System.out.println();

        // 测试bfs
        System.out.println("广度优先遍历:");
        graph.bfs();
    }
}
