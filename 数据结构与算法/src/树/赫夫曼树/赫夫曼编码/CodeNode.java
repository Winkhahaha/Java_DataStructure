package 树.赫夫曼树.赫夫曼编码;

// Node需要排序
public class CodeNode implements Comparable<CodeNode> {
    Character data;      // 存放数据 'a'
    int weight;     // 权值,某字符的个数
    CodeNode left;   // 左子节点
    CodeNode right;  // 右子节点

    public CodeNode(Character data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "CodeNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(CodeNode o) {
        // 从小到大排
        return this.weight - o.weight;
    }

    // 前序遍历
    public void preOrder() {
        System.out.print(data + ":" + this.weight + " ");
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
