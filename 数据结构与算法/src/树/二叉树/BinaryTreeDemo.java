package 树.二叉树;

public class BinaryTreeDemo {
    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        TreeNode root = new TreeNode(33);
        TreeNode node1 = new TreeNode(18);
        TreeNode node2 = new TreeNode(63);
        TreeNode node3 = new TreeNode(77);
        TreeNode node4 = new TreeNode(51);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(24);
        TreeNode node7 = new TreeNode(100);
        // 构建树
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node5);
        node1.setRight(node6);
        node2.setLeft(node4);
        node2.setRight(node3);
        node3.setRight(node7);
        // 创建根节点
        tree.setRoot(root);

        /*
                 33
           18           63
         9   24       51    77
                                100
         */
        System.out.println("前序遍历:");
        tree.preOrder();
        System.out.println();
        System.out.println("中序遍历:");
        tree.infixOrder();
        System.out.println();
        System.out.println("后序遍历:");
        tree.postOrder();
        System.out.println();

        System.out.println("前序查找:" + tree.preSearch(18));
        System.out.println("前序查找:" + tree.preSearch(180));
        System.out.println("中序查找:" + tree.infixSearch(63));
        System.out.println("后序查找:" + tree.postSearch(51));

        System.out.println("删除前(前序遍历):");
        tree.preOrder();
        System.out.println();
        //tree.delete(18);
        tree.delete2(18);
        System.out.println("删除后(前序遍历):");
        tree.preOrder();
        System.out.println(node5 + "的右节点为:" + node5.getRight());
    }
}
