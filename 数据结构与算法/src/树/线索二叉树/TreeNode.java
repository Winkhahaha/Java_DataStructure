package 树.线索二叉树;

public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;
    // 如果leftType为0,表示指向左子树,如果为1表示指向前驱节点(rightType同)
    private int leftType;
    private int rightType;

    public TreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    // 前序遍历
    public void preOrder() {
        // 输出父节点
        System.out.print(this.value + " ");
        // 递归左子树
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归右子树
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.print(this.value + " ");
        // 递归右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        // 递归左子树
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归右子树
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出父节点
        System.out.print(this.value + " ");
    }


    }

