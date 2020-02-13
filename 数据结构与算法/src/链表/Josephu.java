package 链表;

public class Josephu {
    public static void main(String args[]) {
        CircleSingleLinkedList josephu = new CircleSingleLinkedList(5);
        josephu.addNode();      // 创建环形链表
        josephu.print();
        josephu.out(1, 2);
    }
}

// 创建环形单向列表
class CircleSingleLinkedList {
    // 创建first节点
    private NNode first = null;
    private int nums;

    public CircleSingleLinkedList(int nums) {
        this.nums = nums;
    }

    // 添加节点,构建环形链表
    public void addNode() {      // nums为传入的节点数
        if (nums < 1) {
            System.out.println("节点数不能少于1!");
            return;
        }
        // 辅助指针
        NNode cur = null;
        // 循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            NNode node = new NNode(i);
            // 如果是第一个节点(环的起始)
            if (i == 1) {
                first = node;           // 起始为1
                first.setNext(first);   // 自成环
                cur = first;            // 让cur指向第一个节点
                node = null;            // 避免内存泄漏
            } else {
                cur.setNext(node);
                node.setNext(first);
                cur = node;
                node = null;            // 避免内存泄漏
            }
        }

    }

    // 遍历环形链表
    public void print() {
        if (first == null) {
            System.out.println("链表为空!");
            return;
        }
        // 定义辅助指针完成遍历
        NNode temp = first;
        while (temp.getNext() != first) {
            System.out.print(temp.getId() + "-->");
            temp = temp.getNext();      // 后移
        }
        System.out.print(temp.getId());     // 尾节点
        System.out.println();
    }


    /**
     * 根据用户输入,得到出圈顺序
     *
     * @param startId 表示从哪个id节点开始
     * @param count   表示数几下
     * @param nums    表示节点数
     */
    public void out(int startId, int count) {
        if (first == null || startId < 1 || startId > nums) {
            System.out.println("输入有误!");
            return;
        }
        // 创建辅助指针,帮助完成出圈
        NNode helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();      // 说明helper指向了最后的节点
        }

        // 根据startId确定first和helper的起始位置
        // 报数前,先移动到指定的开始位置(first到指定的id,helper到id后面)
        for (int i = 0; i < startId - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 开始出圈
        while (true) {
            if (helper == first) {      // 仅剩一个
                break;
            }
            // 根据指定的所报数字,每次进行移动
            for (int j = 0; j < count - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.println("出:" + first.getId());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后活着的:" + first.getId());
    }
}

// 定义节点
class NNode {
    private int id;
    private NNode next;     // 指向下一个节点

    public NNode(int id) {
        this.id = id;
    }

    public NNode getNext() {
        return next;
    }

    public void setNext(NNode next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
