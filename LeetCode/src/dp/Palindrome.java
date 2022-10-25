package dp;

import java.util.Arrays;

/**
 *动态规划解决最长回文子串
 *
 * @author QinE
 * @create 2022-06-04 13:30
 */
public class Palindrome {

//    public static int longestPalindromeSubseq(String s) {
//        int n = s.length();
//        //dp数组全部初始化为0
//        int[][] dp = new int[n][n];
//        //base case
//        for (int i = 0; i < n; i++)
//            dp[i][i] = 1;
//        //反向遍历，保证状态转移的正确性
//        for (int i = n - 2; i >= 0; i--) {
//            for (int j = i + 1; j < n; j++) {
//                //状态转移方程
//                if (s.charAt(i) == s.charAt(j))
//                    dp[i][j] = dp[i + 1][j] + 2;
//                else
//                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
//            }
//        }
//
//        return dp[0][n - 1];//整个s的最长回文子序列长度
//    }

    //状态压缩
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        //base case： 一维数组全部初始化为1
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = n - 2; i >= 0; i--) {
            int pre = 0;
            for (int j = i + 1; j < n; j++) {
                int temp = dp[j];
                if (s.charAt(i) == s.charAt(j))
                    dp[j] = pre + 2;
                else
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                pre = temp;
            }
        }

        return dp[n - 1];
    }
}