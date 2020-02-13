package 链表;

public class DoubleLinkedList {
    public static void main(String args[]) {
        DoubleNode n1 = new DoubleNode(1, "a");
        DoubleNode n2 = new DoubleNode(2, "b");
        DoubleNode n3 = new DoubleNode(3, "c");
        DoubleNode n4 = new DoubleNode(4, "d");
        DoubleNode n5 = new DoubleNode(5, "e");
        DoubleNode n6 = new DoubleNode(5, "f");

        // 创建双向链表
        MyDoubleLinkedList doubleLinkedList = new MyDoubleLinkedList();
//        doubleLinkedList.add(n1);
//        doubleLinkedList.add(n2);
//        doubleLinkedList.add(n3);
//        doubleLinkedList.add(n4);
//        doubleLinkedList.add(n5);

        // 测试有序链表
        doubleLinkedList.addByOrder(n2);
        doubleLinkedList.addByOrder(n1);
        doubleLinkedList.addByOrder(n3);
        doubleLinkedList.addByOrder(n5);
        doubleLinkedList.addByOrder(n4);
        doubleLinkedList.print();
        System.out.println(n3.pre);
        // 修改测试
//        doubleLinkedList.update(n6);
//        System.out.println("修改后:");
//        doubleLinkedList.print();
//        //删除修改
//        doubleLinkedList.delete(n3);
//        System.out.println("删除后:");
//        doubleLinkedList.print();

    }
}

// 定义Node
class DoubleNode {
    public int id;
    public String name;     // 这都是数据域信息
    public DoubleNode next;   // 指向下一个节点
    public DoubleNode pre;      // 指向上一个节点

    public DoubleNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

// 定义双向链表
class MyDoubleLinkedList {

    private DoubleNode head = new DoubleNode(0, "");

    public DoubleNode getHead() {
        return head;
    }

    public void setHead(DoubleNode head) {
        this.head = head;
    }

    // 添加,尾插法
    public void add(DoubleNode node) {
        DoubleNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        // 当退出循环时,temp就是链表末尾的节点
        temp.next = node;   //将要插入的节点进行尾插
        node.pre = temp;    //形成双向链表
    }

    // 有序添加,正序
    public void addByOrder(DoubleNode node) {
        // temp是位于添加位置的前一个节点
        DoubleNode temp = head;
        while (temp.next != null) {
            if (temp.next.id == node.id) {
                System.out.println("要插入的id已经存在!");
                return;
            }
            if (temp.next.id > node.id && temp.id < node.id) {        //找到了位置,直接添加到temp后面
                break;
            }
            temp = temp.next;       // 没有找到位置,next
        }
        if (temp.next != null) {
            // 执行断开,插入一系列步骤
            temp.next.pre = node;
            node.pre = temp;
            node.next = temp.next;
            temp.next = node;
        } else {
            // 当temp为尾节点即temp.next == null时,直接尾插
            temp.next = node;
            node.pre = temp;
        }
    }

    // 修改一个节点
    public void update(DoubleNode newNode) {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        // 根据传入的id找到要修改的node
        DoubleNode temp = head.next;
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

    // 删除某个节点
    public void delete(DoubleNode node) {
        if (head.next == null) {
            System.out.println("链表为空!");
            return;
        }
        DoubleNode temp = head.next;
        boolean flag = false;   // 判断是否找到的标志
        while (temp != null) {
            if (temp.id == node.id) {   // 找到要删除的节点
                flag = true;
                break;      // 找到了即退出
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                // 如果是最后一个节点,不该执行下面的语句,因为temp.next = null
                temp.next.pre = temp.pre;
            }
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
        DoubleNode temp = head.next;
        // 只要节点不为空
        while (temp != null) {
            System.out.print(temp + "-->");
            temp = temp.next;   // 后移
        }
        System.out.println();
    }
}

