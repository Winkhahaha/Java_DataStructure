package 哈希表;

public class HashTableDemo {
    public static void main(String args[]) {
        MyHashTable myHashTable = new MyHashTable(7);
        myHashTable.put(new Employee(11, "张三"));
        myHashTable.put(new Employee(43, "李四"));
        myHashTable.put(new Employee(28, "哈哈哈"));
        myHashTable.put(new Employee(21, "王麻子"));
        myHashTable.put(new Employee(42, "嘿嘿嘿"));

        myHashTable.list();
        myHashTable.get(43);
        System.out.println("---------------");
        myHashTable.remove(42);
        myHashTable.list();
    }

}

