package 树.AVL树;

public class AVLTreeDemo {
    public static void main(String args[]) {
        //int[] arr = {4, 3, 6, 5, 7, 8};       // 测试左旋
        //int[] arr = {10, 12, 8, 9, 7, 6};       // 测试右旋
        int[] arr = {10, 11, 7, 6, 8, 9};       // 测试双旋
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new AVLNode(arr[i]));
        }
     /*
         测试左旋                        测试右旋
            4                             10
         3      6                     8       12
              5   7                 7   9
                    8             6
      */
        avlTree.infixOrder();
        System.out.println("中序遍历");
        System.out.println("树高度:" + avlTree.getRoot().height());
        System.out.println("左子树高度:" + avlTree.getRoot().leftHight());
        System.out.println("右子树高度:" + avlTree.getRoot().rightHight());
        System.out.println("当前根节点:" + avlTree.getRoot());
    }
}
