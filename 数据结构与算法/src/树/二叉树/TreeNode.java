package 树.二叉树;

public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

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
        System.out.print(this + "   ");
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
        System.out.print(this + "   ");
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
        System.out.print(this + "   ");
    }

    // 前序遍历查找指定节点
    public TreeNode preSearch(int value) {
        // 先和根节点比较
        if (this.value == value) {
            return this;
        }
        // 从左子树开始递归查找
        TreeNode tree = null;
        if (this.left != null) {
            tree = this.left.preSearch(value);
        }
        if (tree != null) {
            // 说明左子树递归中找到了
            return tree;
        }
        // 从右子树开始递归查找
        if (this.right != null) {
            tree = this.right.preSearch(value);
        }
        return tree;
    }

    // 中序遍历查找指定节点
    public TreeNode infixSearch(int value) {
        // 从左子树开始递归查找
        TreeNode tree = null;
        if (this.left != null) {
            tree = this.left.infixSearch(value);
        }
        if (tree != null) {
            // 说明左子树递归中找到了
            return tree;
        }
        // 和根节点比较
        if (this.value == value) {
            return this;
        }
        // 从右子树开始递归查找
        if (this.right != null) {
            tree = this.right.infixSearch(value);
        }
        return tree;
    }

    // 后序遍历查找指定节点
    public TreeNode postSearch(int value) {
        // 从左子树开始递归查找
        TreeNode tree = null;
        if (this.left != null) {
            tree = this.left.postSearch(value);
        }
        if (tree != null) {
            // 说明左子树递归中找到了
            return tree;
        }
        // 从右子树开始递归查找
        if (this.right != null) {
            tree = this.right.postSearch(value);
        }
        if (tree != null) {
            // 说明右子树递归中找到了
            return tree;
        }
        // 和根节点比较
        if (this.value == value) {
            return this;
        }
        return tree;
    }

    // 递归删除节点
    // 如果是叶子节点,则删除该节点
    // 若非叶子节点,删除该子树
    // 若是根节点,将树置空
    public void delete(int value) {
        // 如果当前节点的左子节点不为空,并且就是要删除的
        if (this.left != null && this.left.value == value) {
            this.left = null;
            return;
        }
        // 如果当前节点的右子节点不为空,并且就是要删除的
        if (this.right != null && this.right.value == value) {
            this.right = null;
            return;
        }
        // 向左子树继续递归
        if (this.left != null) {
            this.left.delete(value);
        }
        // 向右子树继续递归
        if (this.right != null) {
            this.right.delete(value);
        }
    }

    // 递归删除节点
    // 如果是叶子节点,则删除该节点
    // 若非叶子节点,1.子节点只有一个,则该子节点替换之;
    // 2.有左右子节点,则左节点替换之(即右子节点成为该左节点的子节点)
    // 若是根节点,将树置空
    public void delete2(int value) {
        // 如果当前节点的左子节点不为空,并且就是要删除的
        if (this.left != null && this.left.value == value) {
            // 判断该左子节点(它)下面是否还包含左右子节点
            // 1.如果它的左右子节点均不为空,则让它的左子节点取代之,并且它的右子节点成为左子节点的新右子节点
            // (bug-->如果它的左子节点本身有右子节点,导致强行覆盖)
            if (this.left.left != null && this.left.right != null) {
                this.left.left.right = this.left.right; // 会出现覆盖问题
                this.left = this.left.left;
            } else if (this.left.left != null || this.left.right != null) {
                // 3.当前节点的子节点下再无任何子树(即叶子节点)
                this.left = null;
            } else {
                // 2.若它下面只有一个子节点,就进行该判断:有左加左,无左加右
                this.left = (this.left.left == null ? this.left.right : this.left.left);
            }
            return;
        }
        // 如果当前节点的右子节点不为空,并且就是要删除的
        if (this.right != null && this.right.value == value) {
            // 判断该左子节点(它)下面是否还包含左右子节点
            // 如果它的左右子节点均不为空,则让它的左子节点取代之,并且它的右子节点成为左子节点的右子节点
            if (this.right.left != null && this.right.right != null) {
                this.right.left.right = this.right.right;   // 出现覆盖问题
                this.right = this.right.left;
            } else if (this.right.left == null || this.right.right == null) {
                // 若它下面只有一个子节点,就进行该判断:有左加加左,无左加右
                this.right = (this.right.left == null ? this.right.right : this.right.left);
            } else {
                this.right = null;
            }
            return;
        }
        // 向左子树继续递归
        if (this.left != null) {
            this.left.delete2(value);
        }
        // 向右子树继续递归
        if (this.right != null) {
            this.right.delete2(value);
        }
    }
}
