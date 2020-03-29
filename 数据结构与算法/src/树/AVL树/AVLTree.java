package 树.AVL树;


public class AVLTree {
    private AVLNode root;

    public AVLNode getRoot() {
        return root;
    }

    public void setRoot(AVLNode root) {
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

    // 递归添加
    public void add(AVLNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
        // 添加完成后 右子树高度 - 左子树高度 > 1
        // 进行左旋转
        if (root.rightHight() - root.leftHight() > 1) {
            // 如果当前根节点的右子树的左子树高度大于它的右子树的右子树高度
            if (root.right != null && root.right.rightHight() > root.right.leftHight()) {
                System.out.println("双旋转处理...");
                // 先对当前根节点的右子树进行右旋
                root.right.rightRotate();
                // 再对当前根节点进行左旋转
                root.leftRotate();
            } else {
                System.out.println("直接进行左旋转...");
                root.leftRotate();
            }
            return;
        }
        // 右旋转
        else if (root.leftHight() - root.rightHight() > 1) {
            // 如果当前根节点的左子树的右子树高度大于它的左子树高度
            if (root.left != null && root.left.rightHight() > root.left.leftHight()) {
                System.out.println("双旋转处理...");
                // 先对当前根节点的左节点进行左旋
                root.left.leftRotate();
                // 再对当前根节点进行右旋转
                root.rightRotate();
            } else {
                // 直接进行右旋转
                System.out.println("直接进行右旋转...");
                root.rightRotate();
            }
        }
    }

    // 查找指定节点
    public AVLNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找指定节点的父节点
    public AVLNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 删除节点
    public void delete(int value) {
        if (root == null) {
            return;
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else {
                root.delete(value);
            }
        }
    }
}
