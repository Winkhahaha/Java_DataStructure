package 树.赫夫曼树;

// Node需要排序
public class HuffmanNode implements Comparable<HuffmanNode> {
    int value;          // 权值
    HuffmanNode left;   // 左子节点
    HuffmanNode right;  // 右子节点

    public HuffmanNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(HuffmanNode o) {
        // 从小到大排
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder() {
        System.out.print(this.value + " ");
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
