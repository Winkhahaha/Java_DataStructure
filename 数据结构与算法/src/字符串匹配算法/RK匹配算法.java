package 字符串匹配算法;

public class RK匹配算法 {
    public static void main(String args[]) {

        String str = "asadsg$%^fhjkl[]-";
        String pattern = "$%^";
        System.out.println("第一次出现的位置"+rabinKarp(str,pattern));
    }

    /**
     * 采用RK算法,复杂度O(n)
     * @param str:主串
     * @param pattern:要匹配的模式串
     * @return
     */
    private static int rabinKarp(String str, String pattern) {
        int m = str.length();
        int n = pattern.length();

        // 计算模式串的自定义Hash值
        int patternCode = MyHash(pattern);

        // 计算主串中与模式串等长的自定义Hash值
        int strCode = MyHash(str.substring(0, n));

        // 比较等长主串和模式串的hash值
        // 主串可以移位m-n次形成等长串
        for (int i = 0; i <= m - n; i++) {
            // 取主串中的与匹配串等长串
            String sub = str.substring(i, i + n);
            // 如果hash值相等,并且等长串与模式串内容相同
            if (strCode == patternCode && sub.equals(pattern)) {
                // 那么则返回该等长串第一次出现在主串中的索引位置
                return i;
            }
            // 如果无法达到上述两条件
            // 则等长串后移,重新计算hash,下轮循环重新匹配
            if (i < m - n) {
                strCode = reHash(str,strCode,i,pattern.length());
            }
        }
        return -1;      //若匹配不到返回-1
    }

    //  简单的增量计算新hash降低时间复杂度
    private static int reHash(String str, int strCode, int i, int length) {
        strCode -= str.charAt(i) - 'a';     // 减去原等长串的第一个字符对应的数值
        strCode += str.charAt(i + length) - 'a';    // 加上原等长串后一位字符的数值,得到新hashcode
        return strCode;
    }


    private static int MyHash(String str) {

        int hashcode = 0;
        for (int i = 0; i < str.length(); i++) {
            // 将字符串中每个字符化为一个整数,取它们的和即为自定义hashcode
            hashcode += str.charAt(i) - 'a';
        }
        return hashcode;
    }
}
