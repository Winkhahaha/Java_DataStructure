package 栈;

public class Calculator {
    public static void main(String args[]) {

        String exp = "30+2*6-5";
        ArrayStack numStack = new ArrayStack(10);   // 存放数字
        ArrayStack operStack = new ArrayStack(10);      // 存放运算符

        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';  //将每次扫描到的char保存到ch
        String keep = "";
        for (int i = 0; i < exp.length(); i++) {
            ch = exp.charAt(i);
            // 根据ch的类型做处理
            if (operStack.isOper(ch)) {
                // 如果是运算符,判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    // 如果栈不为空,进行比较当前操作符和栈中操作符的优先级
                    // 如果当前的操作符优先级小于栈中操作符
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);     // 进行运算
                        numStack.push(res);         // 运算结果入数栈
                        operStack.push(ch);         // 将当前操作符入符号栈
                    } else {
                        // 如果当前的操作符优先级大于栈中操作符,直接入栈
                        operStack.push(ch);
                    }
                } else {
                    // 如果栈为空直接入栈
                    operStack.push(ch);
                }
            } else {
                // 如果是数字,执行工序:
                keep += ch;
                // 如果游标到末尾,则直接将数字入栈
                if (i == exp.length() - 1) {
                    numStack.push(Integer.parseInt(keep));
                } else {
                    // 若是多位数,在处理数时需要往字符串多看一位,若还是数则拼接成多位数,直到下一位是操作符
                    // 判断下一位是操作符还是数
                    if (operStack.isOper(exp.charAt(i + 1))) {
                        // 如果是运算符,该数直接入栈
                        numStack.push(Integer.parseInt(keep));
                        keep = "";
                    }
                }
            }
        }

        // 扫描完毕,顺序从栈中得到相应的数和操作符,运行
        while (true) {
            // 如果符号栈为空,则计算到最后的结果,数栈只有一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);     // 进行运算
            numStack.push(res);
        }
        System.out.println("运算结果:" + numStack.pop());
//        numStack.print();
//        System.out.println("----------------");
//        operStack.print();
    }
}
