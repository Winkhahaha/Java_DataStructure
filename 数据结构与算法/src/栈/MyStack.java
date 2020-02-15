package 栈;

import java.lang.reflect.Array;
import java.util.Scanner;

public class MyStack {

    public static void main(String args[]) {
        ArrayStack stack = new ArrayStack(5);
        Scanner sc = new Scanner(System.in);
        char key = ' ';
        boolean loop = true;
        while (loop) {
            System.out.println("s---->显示栈");
            System.out.println("e---->退出程序");
            System.out.println("a---->添加数据到栈");
            System.out.println("g---->从栈中取数据");
            key = sc.next().charAt(0);      /// 接收一个字符
            switch (key) {
                case 's':
                    stack.print();
                    break;
                case 'a':
                    System.out.println("请输入数据:");
                    int value = sc.nextInt();
                    stack.push(value);

                    break;
                case 'g':
                    try {
                        System.out.println("取出的数据为:" + stack.pop());
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

class ArrayStack {
    private int maxSize;    //栈的大小
    private int[] arr;      //数组,存放数据
    private int top = -1;   //表示栈游标,初始-1,放一个数据+1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int data) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈满!");
            return;
        }
        top++;
        arr[top] = data;
    }

    // 出栈
    public int pop() {
        // 判断栈是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空!");
        }
        int data = arr[top];    // 取出栈上方的元素,top向下移
        top--;
        return data;
    }

    // 遍历栈
    public void print() {
        // 判断栈是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空!");
        }
        // 从栈顶开始
        for (int i = top; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }

    /*
    计算器功能
     */
    // 判断运算符优先级
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;  // 假定运算符只有加减乘除
        }
    }

    // 判断是不是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;    // 存放运算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

    // 只看栈顶,不弹栈
    public int peek(){
        return arr[top];
    }

}
