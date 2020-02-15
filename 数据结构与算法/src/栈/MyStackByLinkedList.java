package 栈;

public class MyStackByLinkedList {
    public static void main(String args[]) {
        LinkedlistStack stack = new LinkedlistStack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("遍历:");
        stack.print();
        System.out.println("出栈:");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("遍历:");
        stack.print();
    }
}

// 使用单向链表模拟栈
// 定义Node
class Node {
    public int data;
    public Node next;   // 指向下一个节点

    public Node(int id) {
        this.data = id;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}

// 定义链表栈
class LinkedlistStack {
    private int maxSize;
    private Node head = new Node(0);
    int count = 0;      // 记录插入的节点数量

    public LinkedlistStack(int maxSize) {
        this.maxSize = maxSize;
    }

    // 栈空
    public boolean isEmpty() {
        return count == 0;
    }

    // 栈满
    public boolean isFull() {
        return count == maxSize;
    }

    // 入栈
    public void push(int data) {
        if (isFull()) {
            System.out.println("栈满!");
            return;
        }
        Node node = new Node(data); //创建入栈的节点
        Node temp = head;           //创建辅助指针
        while (temp.next != null) {
            temp = temp.next;
        }
        // 当退出循环时,temp就指向了链表的最后
        temp.next = node;   //将要插入的节点进行尾插
        count++;
        node = null;        //防止内存泄漏
    }

    // 出栈
    public int pop() {
        // 判断栈是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空!");
        }
        Node temp = head;           //创建辅助指针
        while (temp.next.next != null) {
            temp = temp.next;
        }
        // 当退出循环时,temp就指向了链表最后节点的前一个节点
        int data = temp.next.data;      // 利用temp获取栈最后一个节点的数据
        temp.next = temp.next.next;   // 相当于搞了一步删除,利用找到的前一个节点把栈尾节点删掉了
        count--;
        return data;
    }

    // 遍历栈
    public void print() {
        // 判断栈是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空!");
        }
        Node temp = head.next;
        int datas[] = new int[count];       // 保存栈中每个节点的数据
        for (int i = 0; i < count; i++) {
            if (temp != null) {
                datas[i] = temp.data;
            } else {
                return;
            }
            temp = temp.next;
        }
        // 倒序遍历一下该数组,模拟先进后出
        for (int i = datas.length - 1; i >= 0; i--) {
            System.out.println(datas[i]);
        }

    }
}
