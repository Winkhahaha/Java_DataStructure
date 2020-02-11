package 链表;

import java.util.Scanner;
import java.util.Stack;

public class SingleLinkedList {
    public static void main(String args[]) {
        Node n1 = new Node(1, "a");
        Node n2 = new Node(2, "b");
        Node n3 = new Node(3, "c");
        Node n4 = new Node(4, "d");
        Node n5 = new Node(5, "e");
        Node n6 = new Node(6, "f");

        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addByOrder(n1);
        linkedList.addByOrder(n4);
        linkedList.addByOrder(n3);
        linkedList.addByOrder(n2);
        linkedList.addByOrder(n5);

//        linkedList.add(n1);
//        linkedList.add(n4);
//        linkedList.add(n3);
//        linkedList.add(n2);
//        linkedList.add(n5);
//        linkedList.print();
//        System.out.println("修改后:");
//        // 测试修改
//        linkedList.update(new Node(5, "eee"));
        //测试删除
//        System.out.println("删除后");
//        linkedList.delete(n4);
        System.out.println("正常输出:");
        linkedList.print();
        System.out.println("有效节点个数:" + getNodeLength(linkedList.getHead()));
//        Scanner sc = new Scanner(System.in);
//        int index = sc.nextInt();
//        System.out.println("倒数第"+index+"个节点为:"+findLastIndexNode(linkedList.getHead(),index));
        System.out.println("反转后:");
        //reverse(linkedList.getHead());
        //linkedList.print();
        reverseByStack(linkedList.getHead());

    }

    // 获取单链表有效节点个数(不算头节点)
    public static int getNodeLength(Node head) {
        if (head.next == null) {
            return 0;   //空链表
        }
        int count = 0;
        Node temp = head.next;

        while (temp != null) {
            count++;
            temp = temp.next;   // 遍历
        }
        return count;
    }

    // 查找单链表中的倒数第k个节点
    // length-index(倒数)即得到正数下标(0为开始)
    public static Node findLastIndexNode(Node head, int index) {
        if (head.next == null) {
            System.out.println("链表为空!");
            return null;   //空链表
        }
        // 得到有效节点个数
        int size = getNodeLength(head);
        // 遍历到size-index位置
        if (index <= 0 || index > size) {
            System.out.println("请重新输入要查找的倒数序号!");
            return null;
        }
        Node temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // 链表反转,改变了单链表结构
    public static void reverse(Node head) {
        if (head.next == null || head.next.next == null) {
            System.out.println("链表为空或者只有一个节点!");
            return;
        }
        Node temp = head.next;
        Node next = null;       // 指向temp的下一个节点
        Node reverseHead = new Node(0, "");
        // 遍历原来的链表
        while (temp != null) {
            next = temp.next;   // 暂时保存当前节点的下一个节点
            temp.next = reverseHead.next;   // 将temp的下一个节点指向新链表的最前端
            reverseHead.next = temp;
            temp = next;
        }
        // 将head.next指向reverseHead.next
        head.next = reverseHead.next;

    }

    // 逆序打印单链表(不改变链表结构),运用栈
    public static void reverseByStack(Node head) {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node temp = head.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "-->");
        }
        System.out.println();
    }


}

// 定义Node
class Node {
    public int id;
    public String name;     // 这都是数据域信息
    public Node next;   // 指向下一个节点

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

// 创建链表
class MyLinkedList {
    private Node head = new Node(0, ""); //头结点,不存放数据

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    // 尾插法
    // 找到当前链表的最后节点,将最后这个节点的next指向新的节点
    public void add(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 当退出循环时,temp就指向了链表的最后
        temp.next = node;   //将要插入的节点进行尾插
    }

    // 进行有序无重复插入
    public void addByOrder(Node node) {

        // temp是位于添加位置的前一个节点
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.id == node.id) {
                System.out.println("要插入的id已经存在!");
                break;
            }
            if (temp.next.id > node.id) {        //找到了位置,直接添加到temp后面
                break;
            }
            temp = temp.next;
        }
        // 断开,插入
        node.next = temp.next;
        temp.next = node;

    }

    // 修改某节点,根据id修改
    public void update(Node newNode) {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        // 根据传入的id找到要修改的node
        Node temp = head.next;
        boolean flag = false;   // 判断是否找到的标志
        while (temp != null) {
            if (temp.id == newNode.id) {
                flag = true;
                break;      // 找到了即退出
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newNode.name;
        } else {
            System.out.println("不存在指定id的节点!");
        }
    }

    // 删除指定节点
    public void delete(Node node) {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        Node temp = head.next;
        boolean flag = false;   // 判断是否找到的标志
        while (temp != null) {
            if (temp.next == node) {   // 找到要删除节点的前一个节点
                flag = true;
                break;      // 找到了即退出
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = node.next;
        } else {
            System.out.println("该节点不存在!");
        }
    }


    // 遍历链表
    public void print() {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        Node temp = head.next;
        // 只要节点不为空
        while (temp != null) {
            System.out.print(temp + "-->");
            temp = temp.next;   // 后移
        }
        System.out.println();
    }
}
