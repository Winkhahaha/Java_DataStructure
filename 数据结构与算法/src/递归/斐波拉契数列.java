package 递归;

public class 斐波拉契数列 {

    public static void main(String[] args) {
        System.out.println(Fibonacci(3));
    }

    /*
     * 获取斐波拉契数列的某一项
     */
    public static int Fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        if (n > 2) {
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
        return 0;

    }
}
