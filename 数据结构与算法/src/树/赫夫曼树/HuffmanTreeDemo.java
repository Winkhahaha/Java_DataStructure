package 树.赫夫曼树;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HuffmanTreeDemo {
    public static void main(String args[]) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Arrays.sort(arr);
        HuffmanNode root = createHuffmanTree(arr);
        System.out.println("前序遍历:");
        root.preOrder();
    }

    public static HuffmanNode createHuffmanTree(int[] arr) {
        ArrayList<HuffmanNode> nodes = new ArrayList<>();
        for (int i : arr) {
            // 每个节点放入集合中
            nodes.add(new HuffmanNode(i));
        }
        // 以下六步骤应为循环
        while (nodes.size() > 1) {
            // 1.取出根节点权值最小的二叉树
            // 单个节点视为小型二叉树
            HuffmanNode leftNode = nodes.get(0);
            // 2.取出第二小的节点
            HuffmanNode rightNode = nodes.get(1);
            // 3.构建新的二叉树
            // 新二叉树权值 = 左 + 右
            HuffmanNode parent = new HuffmanNode(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 4.构建新二叉树后,删除掉用过的左右小树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 5.将新树加入进集合
            nodes.add(parent);
            // 6.根据权值重新排序
            Collections.sort(nodes);
        }
        // 返回赫夫曼树的头结点
        return nodes.get(0);
    }
}
