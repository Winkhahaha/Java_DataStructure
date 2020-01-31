package 队列;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String args[]) {
    CircleArrayQueue<Object> queue = new CircleArrayQueue<>(4);     //设置4,但最大存取为3
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

class CircleArrayQueue<T> {
    private int maxSize;    // 表示数组最大容量
    private int front;  // 队列头,指向队列头元素,默认0
    private int rear;   // 队列尾,指向队列最后一个元素的下一个位置,默认0
    private Object[] arr;  // 该数组用于存放数据,模拟队列


    // 创建队列的构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new Object[this.maxSize];
    }

    // 判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据
    public void add(T n) {
        // 要判断队列是否满
        if (isFull()) {
            System.out.println("队列满!不能加入!");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;     // rear后移,环形,所以取模
    }

    // 获取队列数据,出队列
    public Object get() {
        // 判断队列是否空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列空!无法获取!");
        }
        // 这里的front是指向队列的第一个元素
        Object temp = arr[front];
        front = (front + 1) % maxSize;  // 环形,所以取模
        return temp;
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空!无法获取!");
        }
        // 从front下标开始遍历
        for (int i = front; i < front + size(); i++) {
            System.out.println(arr[i % maxSize]);       // 环形,所以取模
        }
    }

    // 求出当前环形里面的有效数据个数
    public int size() {
        //设当前只加不出
        //rear=0,max=3,front=0,有效个数0
        //rear=1,max=3,front=0,有效1
        //rear=2,max=3,front=0,有效2...
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据
    public Object headQueue() {
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("队列空!无法获取!");
        }
        return arr[front];
    }
}
