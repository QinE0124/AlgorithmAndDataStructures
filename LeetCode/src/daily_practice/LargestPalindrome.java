package daily_practice;

/**
 * 最大回文数乘积
 * 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。
 * 因为答案可能非常大，所以返回它对 1337 取余 。
 * 1 <= n <= 8
 * @author QinE
 * @create 2022-04-16 18:53
 */
public class LargestPalindrome {

    public static void main(String[] args) {

        System.out.println(largestPalindrome(2));
    }

    public static int largestPalindrome(int n) {
        if (n == 1)
            return 9;
        int left = (int) Math.pow(10, n) - 1;
        int ans = 0;
        for (int i = left; ans == 0; i--) {  //枚举回文数左半部分
            long p = i;
            for (int x = i; x > 0; x /= 10)//翻转左半部分到结尾构造回文数
                p = p * 10 + x % 10;
            for (int y = left; y >= p / y; y--) {
                if (p % y == 0) {
                    ans = (int) (p % 1337);
                    break;
                }
            }
        }
        return ans;
    }
}
