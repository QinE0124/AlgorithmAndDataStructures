package dp;

/**
 *动态规划解决最小插入使字符串变成回文串
 * @author QinE
 * @create 2022-06-04 16:45
 */
public class MinInsetions {

 /*   public static int minInsertions(String s) {
        int n = s.length();
        //定义：对s[i...j]，最少需要插入dp[i][j]次才能变成回文串
        int[][] dp = new int[n][n];
        //base case : i == j 时， dp[i][j] = 1, 单个字符已经是回文串
        //从下向上遍历
        for (int i = n - 2; i >= 0; i--) {
            //从左向右遍历
            for (int j = i + 1; j < n; j++) {
                //根据s[i]和s[j]进行状态转移
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
            }
        }

        //根据dp数组的定义，答案为dp[0][n - 1]
        return dp[0][n - 1];
    }*/

    //状态压缩
    public static int minInsertions(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int temp = 0;
        for (int i = n - 2; i >= 0; i--) {
            int pre = 0;
            for (int j = i + 1; j < n; j++) {
                temp = dp[j];
                if (s.charAt(j) == s.charAt(i))
                    dp[j] = pre;
                else
                    dp[j] = Math.min(dp[j - 1], dp[j]) + 1;

                pre = temp;
            }
        }

        return dp[n - 1];
    }
}
