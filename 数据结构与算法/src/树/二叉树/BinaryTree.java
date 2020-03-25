package 树.二叉树;

public class BinaryTree {
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
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

    // 前中后查找
    public TreeNode preSearch(int value) {
        if (root != null) {
            return root.preSearch(value);
        }
        return null;
    }

    public TreeNode infixSearch(int value) {
        if (root != null) {
            return root.infixSearch(value);
        }
        return null;
    }

    public TreeNode postSearch(int value) {
        if (root != null) {
            return root.postSearch(value);
        }
        return null;
    }

    // 删除节点
    public void delete(int value) {
        if (root != null) {
            // 判断root是否为要删除节点
            if (root.getValue() == value) {
                root = null;    // 置空
            } else {
                root.delete(value);
                System.out.println("删除成功!");
            }
        } else {
            System.out.println("树为空!");
        }
    }

    // 删除节点
    // 改进了对子树的删除
    public void delete2(int value) {
        if (root != null) {
            // 判断root是否为要删除节点
            if (root.getValue() == value) {
                root = null;    // 置空
            } else {
                root.delete2(value);
                System.out.println("删除成功!");
            }
        } else {
            System.out.println("树为空!");
        }
    }

}
