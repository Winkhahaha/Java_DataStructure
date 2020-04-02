package 算法.字符串匹配算法;

public class 暴力匹配 {
    public static void main(String args[]) {
        String s1 = "a$%dsg$%^fhjkl[]-";
        String s2 = "$%^";
        int index = violenceMatch(s1, s2);
        System.out.println(index);
    }

    public static int violenceMatch(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        int i = 0;  // 指向chars1
        int j = 0;  // 指向chars2
        while (i < s1.length() && j < s2.length()) {
            if (chars1[i] == chars2[j]) {
                i++;
                j++;
            } else {
                // 如果没有匹配成功
                i = i - j + 1;  // 移到后一个字符准备重新匹配
                j = 0;          // 移到头
            }
        }
        // 判断是否匹配成功
        if (j == s2.length()) {
            return i - j;
        }
        return -1;

    }
}
