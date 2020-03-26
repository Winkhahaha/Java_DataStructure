package 树.线索二叉树;

public class ThreadedBinaryTreeDemo {
    public static void main(String args[]) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(10);
        TreeNode node5 = new TreeNode(14);
        // 构建树
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        // 创建根节点
        tree.setRoot(root);
        /*
                        1
                   3         6
                 8   10   14
         */
        tree.infixOrder();
        System.out.println("中序遍历(线索化前)");
        // 线索化二叉树
        System.out.println("中序线索化:");
        tree.threadedNodes(root);
        // 测试,10号节点
        System.out.println("10的前驱节点:" + node4.getLeft());
        System.out.println("10的后继节点:" + node4.getRight());
        // 当线索化二叉树后,不能使用原先的遍历方式
        tree.threadedList();
        System.out.println("中序遍历(线索化后)");
    }
}
