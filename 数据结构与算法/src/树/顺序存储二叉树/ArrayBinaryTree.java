package 树.顺序存储二叉树;

public class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 前序遍历
    // index:数组下标
    public void preOrder(int index) {
        // 如果数组为空,或者length为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空!");
        }
        System.out.print(arr[index] + "  ");
        // 向左递归
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    // 中序遍历
    // index:数组下标
    public void infixOrder(int index) {
        // 如果数组为空,或者length为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空!");
        }
        // 向左递归
        if ((index * 2 + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.print(arr[index] + "  ");
        // 向右递归
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    // 后序遍历
    // index:数组下标
    public void postOrder(int index) {
        // 如果数组为空,或者length为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空!");
        }
        // 向左递归
        if ((index * 2 + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        // 向右递归
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.print(arr[index] + "  ");
    }
}
