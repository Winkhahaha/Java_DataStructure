package 哈希表;

public class EmpLinkedList {
    private Employee head;  // 默认空

    // 添加
    // 尾插
    public void add(Employee employee) {
        // 头结点空,直接添加
        if (head == null) {
            head = employee;
            return;
        }
        Employee curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        // 尾插
        curEmp.next = employee;
    }

    // 遍历
    public void list(int tableId) {
        if (head == null) {
            System.out.println("链表编号(" + tableId + "):空!");
            return;
        }
        Employee curEmp = head;
        while (curEmp != null) {
            System.out.print("链表编号(" + tableId + "):" + curEmp + "--->");
            curEmp = curEmp.next;   // 后移
        }
        System.out.println();
    }

    // 根据员工id获取实例
    public Employee getEmpById(int id) {
        if (head == null) {
            System.out.println("链表空!");
            return null;
        }
        Employee curEmp = head;
        while (curEmp.id != id && curEmp != null) {
            curEmp = curEmp.next;
            if (curEmp.id == id) {
                break;
            }
        }
        return curEmp;
    }

    // 根据id删除员工
    public void deleteEmpById(int id) {
        if (head == null) {
            System.out.println("链表空!");
            return;
        }
        // 如果要删除的节点刚好为头结点
        if (head.id == id) {
            head = head.next;
            System.out.println("删除成功");
        } else {
            Employee temp = head;
            boolean flag = false;   // 判断是否找到的标志
            while (temp.next != null) {
                if (temp.next.id == id) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.next = temp.next.next;
                System.out.println("删除成功");
            } else {
                System.out.println("该节点不存在!");
            }
        }

    }
}
