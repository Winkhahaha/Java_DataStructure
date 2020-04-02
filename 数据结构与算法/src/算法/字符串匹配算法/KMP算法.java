package 算法.字符串匹配算法;

import java.util.Arrays;

public class KMP算法 {
    public static void main(String args[]) {
        String s1 = "BBC ABCDAB ABCDABCDABDE";
        String s2 = "ABCDABD";
        int[] next = kmpNext(s2);
        int kmp = KMP(s1, s2, next);
        System.out.println("部分匹配值:" + Arrays.toString(next));
        System.out.println("位置:" + kmp);
    }

    // KMP搜索算法
    public static int KMP(String s1, String s2, int[] next) {
        for (int i = 0, j = 0; i < s1.length(); i++) {
            // 当两个不相等的时候
            while (j > 0 && s1.charAt(i) != s2.charAt(j)) {
                j = next[j - 1];
            }
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
            if (j == s2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    // 获取子串的部分匹配值
    public static int[] kmpNext(String dest) {
        // 创建一个数组,保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;    // 如果字符串长度为1 部分匹配值为0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 当满足该条件时,部分匹配值就是要加一
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
