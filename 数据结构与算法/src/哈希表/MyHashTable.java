package 哈希表;

public class MyHashTable {
    private EmpLinkedList[] table;

    public MyHashTable(int size) {
        this.table = new EmpLinkedList[size];
        // 存储引用类型的数组,数组初始化后,也要为每个引用元素初始化
        for (int i = 0; i < size; i++) {
            table[i] = new EmpLinkedList();
        }
    }

    // 添加
    public void put(Employee employee) {
        // 根据员工id得到员工该添加到哪条链表
        // 散列,得到该去的table[i]
        int tableId = hash(employee.id);
        // 将员工添加到对应的链表中
        table[tableId].add(employee);
    }

    // 遍历table
    public void list() {
        for (int i = 0; i < table.length; i++) {
            table[i].list(i);
        }
    }

    // 编写散列函数(简单取模)
    public int hash(int id) {
        return id % table.length;
    }

    // 根据员工id查找
    public void get(int id) {
        // 使用散列函数确定到哪条链表查找
        int tableId = hash(id);
        Employee emp = table[tableId].getEmpById(id);
        if (emp != null) {
            System.out.print("查找结果 --> 该员工所属链表(" + tableId + "):");
            System.out.println(emp);
        } else {
            System.out.println("查不到该员工!");
        }
    }

    // 根据员工id删除
    public void remove(int id) {
        // 使用散列函数确定到哪条链表查找
        int tableId = hash(id);
        table[tableId].deleteEmpById(id);
    }

}
