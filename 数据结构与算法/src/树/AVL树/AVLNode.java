package 树.AVL树;

public class AVLNode {
    int value;
    AVLNode left;
    AVLNode right;

    public AVLNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }

    // 返回以该节点为根节点的树的高度
    public int height() {
        return Math.max(this.left == null ? 0 : this.left.height(), this.right == null ? 0 : this.right.height()) + 1;
    }

    // 返回左子树的高度
    public int leftHight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    // 返回右子树的高度
    public int rightHight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    // 左旋转:
    // 右子树高度 - 左子树高度 > 1
    public void leftRotate() {
        // 创建新节点,value为当前根节点的值
        AVLNode node = new AVLNode(this.value);
        // 把新节点的左子树设置为当前根节点的左子树
        node.left = this.left;
        // 把新节点的右子树设置为当前根节点的右子树的左子树
        node.right = this.right.left;
        // 把当前根节点的值替换为它原先右子树的值
        this.value = this.right.value;
        // 把当前根节点的右子树设置成当前根节点右子树的右子树
        this.right = this.right.right;
        // 把当前根节点的左子树设置为新的节点(node)
        this.left = node;
    }

    // 右旋转
    // 左子树高度 - 右子树高度 > 1
    public void rightRotate() {
        // 创建新节点,value为当前根节点的值
        AVLNode node = new AVLNode(this.value);
        // 把新节点的右子节点设置为当前根节点的右子节点
        node.right = this.right;
        // 把新节点的左子节点设置为当前根节点的左子节点的右节点
        node.left = this.left.right;
        // 把当前根节点的value设置为它左节点的value
        this.value = this.left.value;
        // 把当前根节点的左子节点设置为当前根节点的左节点的左节点
        this.left = this.left.left;
        // 把当前根节点的右节点指向新的节点(node)
        this.right = node;
    }

    // 前序遍历
    public void preOrder() {
        System.out.print(this.value + " ");
        if (this.left != null) {
            this.left.preOrder();
        }
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

    // 添加节点
    public void add(AVLNode node) {
        if (node == null) {
            return;
        }
        // 判断传入的节点的值与当前子树根节点的值的关系
        // 小于,往左添加
        if (node.value < this.value) {
            if (this.left == null) {
                // 左子树为空直接添加
                this.left = node;
            } else {
                // 向左子树递归则添加
                this.left.add(node);
            }
        } else {
            // 大于等于往右添加
            if (this.right == null) {
                // 右子树为空直接则添加
                this.right = node;
            } else {
                // 向右子树递归添加
                this.right.add(node);
            }
        }

    }

    // 查找指定节点
    public AVLNode search(int value) {
        if (value == this.value) {
            return this;
        } else if (this.left != null && value < this.value) {
            return this.left.search(value);
        } else if (this.right != null && value >= this.value) {
            return this.right.search(value);
        } else {
            return null;
        }
    }

    // 查找要删除节点的父节点
    public AVLNode searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.left != null && value < this.value) {
                // 如果查找的值小于当前节点的值
                return this.left.searchParent(value);
            } else if (this.right != null && value >= this.value) {
                // 如果查找的值大于等于当前节点的值
                return this.right.searchParent(value);
            } else {
                return null;    // 找不到
            }
        }
    }

    // 返回某右节点的左分叉中的最小值(即最底部的左叶子节点)
    public int deleteRightTree(AVLNode node) {
        AVLNode treeNode = node;
        // 循环查找左节点,找到最小值
        while (treeNode.left != null) {
            treeNode = treeNode.left;
        }
        // 删除最小节点
        delete(treeNode.value);
        // 返回最小值
        return treeNode.value;
    }

    // 删除节点
    public void delete(int value) {
        // 获取要删除的节点
        AVLNode treeNode = search(value);
        if (treeNode == null) {
            System.out.println("找不到要删除的节点");
            return;
        }
        // 找要删除节点的父节点
        AVLNode parent = searchParent(value);
        // 1.待删除的节点是叶子节点
        if (treeNode.left == null && treeNode.right == null) {
            // 判断要删除的叶子节点是它父节点的左还是右节点
            // 此处必须判断value,如果父节点的左右子树都存在,就得依靠value来判断,否则会误删
            //   3
            // 1   5
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (treeNode.left != null && treeNode.right != null) {
            // 2.待删除的节点存在左右子树
            // 遍历待删节点的右子树,在左分支中找到最小value替换待删节点的value
            // 替换后,仍满足二叉排序树
            int minVal = deleteRightTree(treeNode.right);
            treeNode.value = minVal;
        } else {
            // 3.待删除的节点存在一颗子树
            // 判断待删除节点是其父节点的左还是右节点
            if (parent.left.value == value) {
                parent.left = (treeNode.left == null ? treeNode.right : treeNode.left);
            } else {
                parent.right = (treeNode.left == null ? treeNode.right : treeNode.left);
            }
        }
    }
}