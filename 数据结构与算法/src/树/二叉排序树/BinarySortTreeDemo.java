package 树.二叉排序树;

public class BinarySortTreeDemo {
    public static void main(String args[]) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree sortTree = new BinarySortTree();
        // 循环添加节点到树
        // 按照规则构成二叉排序树
        for (int i = 0; i < arr.length; i++) {
            sortTree.add(new TreeNode(arr[i]));
        }
        sortTree.add(new TreeNode(2));
        /*
                7
             3       10
          1    5   9   12
           2
         */

        sortTree.preOrder();
        System.out.println("前序遍历");
        System.out.println("测试查找某节点:" + sortTree.search(5));
        System.out.println("测试查找某节点的父节点:" + sortTree.searchParent(5));
        System.out.println("------测试删除------");
        sortTree.delete(3);
        System.out.println("删除后:");
        sortTree.preOrder();
    }
}
