package dp;

/**
 * 动态规划解决最长公共子序列
 * 第一步：明确dp数组的含义
 * 第二步：定义base case
 * 第三步：找状态转移方程
 *
 * @author QinE
 * @create 2022-06-02 16:34
 */
public class LCS {

    //找到状态转移方程，直接暴力破解
//    public static int longestCommonSubsequence(String str1, String str2) {
//        return dp(str1, str2, str1.length() - 1, str2.length());
//    }
//
//    private static int dp(String str1, String str2, int i, int j) {
//        //空串的base case
//        if (i == - 1 || j == -1)
//            return 0;
//        if (str1.charAt(i) == str2.charAt(j))
//            //说明这边找到了一个lcs中的元素
//            return dp(str1, str2, i - 1, j - 1) + 1;
//        else
//            //至少有一个字符不在lcs中，都试一下，谁能让lcs最长，选谁
//            return Math.max(dp(str1, str2, i, j - 1), dp(str1, str2, i - 1, j));
//    }

    //使用dp数组
    public static int longestCommonsubSequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        //定义：对于s1[0...i - 1]和s2[0...j - 1]，它们的lcs长度是dp[i, j]
        int[][] dp = new int[m + 1][n + 1];
        //base case: dp[0][...] = dp[...][0] = 0，已初始化
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //状态转移逻辑
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j -  1], dp[i - 1][j]);
            }
        }
        return dp[m][n];
    }
}
