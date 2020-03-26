package 树.顺序存储二叉树;

public class ArrayBinaryTreeDemo {
    public static void main(String args[]) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        /*
                 1
             2       3
           4   5   6   7
         */
        tree.preOrder(0);   // 首元素相当于根节点
        System.out.println("前序遍历");
        tree.infixOrder(0);
        System.out.println("中序遍历");
        tree.postOrder(0);
        System.out.println("后序遍历");
    }
}
