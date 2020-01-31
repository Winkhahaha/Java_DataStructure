package 队列;


import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String args[]) {

        ArrayQueue<Object> queue = new ArrayQueue(3);
        Scanner sc = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        while (loop) {
            System.out.println("s---->显示队列");
            System.out.println("e---->退出程序");
            System.out.println("a---->添加数据到队列");
            System.out.println("g---->从队列中取数据");
            System.out.println("h---->查看队列头数据");
            key = sc.next().charAt(0);      /// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请任意输入:");
                    Object value = sc.next();
                    queue.add(value);
                    break;
                case 'g':
                    try {
                        System.out.println("取出的数据为:" + queue.get());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        System.out.println("队列头数据为:" + queue.headQueue());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    sc.close();
                    loop = false;   //退出循环
                    System.out.println("程序退出");
                    break;
                default:
                    break;

            }
        }

    }
}


/**
 * 数组实现队列
 */
class ArrayQueue<T> {
    private int maxSize;    // 表示数组最大容量
    private int front;  // 队列头
    private int rear;   // 队列尾
    private Object[] arr;  // 该数组用于存放数据,模拟队列

    // 创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new Object[this.maxSize];
        this.front = -1;    // 指向队列头部(前一个位置)
        this.rear = -1;     // 指向队列尾(就是队列的最后一个数据)
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == this.maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    public void add(T n) {
        // 要判断队列是否满
        if (isFull()) {
            System.out.println("队列满!不能加入!");
            return;
        }
        rear++; //加入之后,尾向后指
        arr[rear] = n;
    }

    // 获取队列数据,出队列
    public Object get() {
        // 判断队列是否空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列空!无法获取!");
        }
        front++;    // front后移
        return arr[front];
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空!无法获取!");
        }
        for (int i = front + 1; i < maxSize; i++) {
            System.out.println(arr[i]);
        }

    }

    // 显示队列的头数据
    public Object headQueue() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列空!无法获取!");
        }
        return arr[front + 1];
    }
}
