package kmp;

/**
 * @author Qine
 * @create 2021-08-14 10:04
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        int index = kmpSearch(str1, str2, next);
        System.out.println(index);
    }

    /**
     *  kmp搜索引擎算法
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表，是子串对应的部分匹配表
     * @return 如果返回-1则表示没有匹配到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        for (int i = 0, j = 0; i < str1.length(); i++) {

            //需要处理str.charAt(i) != str2.charAt(j),调整j的大小
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
    //获取到一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串是长度为1则部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i) != dest.char(j),则需要从next[j - 1]获取新的j
            //直到发现有dest(i) == dest(j)成立才退出
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
                //当dest.charAt(i) == dest.charAt(j)满足时，部分匹配值+1
                if (dest.charAt(i) == dest.charAt(j)) {
                    j++;
                }
                next[i] = j;
            }
        return next;

    }
}
