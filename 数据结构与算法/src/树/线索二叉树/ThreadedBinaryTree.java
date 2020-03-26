package 树.线索二叉树;

public class ThreadedBinaryTree {
    private TreeNode root;
    private TreeNode pre = null;    // 线索化的前驱节点

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    // 对二叉树中序线索化
    // node即为当前需要线索化的节点
    public void threadedNodes(TreeNode node) {
        if (node == null) {
            return;
        }
        // 1.线索化左子树
        threadedNodes(node.getLeft());
        // 2.线索化当前节点
        // 处理当前节点的前驱节点
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前左指针的指向类型
            node.setLeftType(1);
        }
        // 处理后继节点
        if (pre != null && pre.getRight() == null) {
            // 前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        // 每处理一个节点,让当前节点是下一个节点的前驱节点
        pre = node;
        // 3.线索化右子树
        threadedNodes(node.getRight());
    }

    // 遍历中序线索化二叉树
    public void threadedList(){
        // 定义一个变量,存储当前遍历到的节点
        TreeNode node = root;
        while(node!=null){
            // 循环找到leftType == 1的节点
            // 当leftType == 1,说明该节点是线索化处理后的
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.print(node.getValue()+" ");
            // 如果当前节点的右节点指向后继节点,就一直输出
            while (node.getRightType() == 1){
               // 获取到当前节点的后继节点
                node = node.getRight();
                System.out.print(node.getValue()+" ");
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }
    }

    // 前序遍历
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空!");
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空!");
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空!");
        }
    }


}
