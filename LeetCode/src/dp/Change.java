package dp;

/**
 * 经典动态规划：完全背包
 * @author QinE
 * @create 2022-06-07 19:46
 */
public class Change {

//    public static int change(int amount, int[] coins) {
//        int n = coins.length;
//        //dp数组定义：若只使用coins中的前i个硬币的面值，想凑出金额j。有dp[i][j]种解法
//        int[][] dp = new int[n + 1][amount + 1];
//        //base case dp[0][..] = 0, dp[...][0] = 1。因为若不使用任何硬币面值，则无法凑出任何金额；如凑出的金额为0，则“无为而治”，就是唯一一种解法
//        for (int i = 0; i <= n; i++)
//            dp[i][0] = 1;
//        //开始状态转移
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= amount; j++) {
//                if (j - coins[i - 1] >= 0)
//                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j -coins[i - 1]];
//                else
//                    dp[i][j] = dp[i - 1][j];
//            }
//        }
//
//        return dp[n][amount];
//    }

    public static int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;//base case;
        //开始状态转移
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i] >= 0)
                  dp[j] = dp[j] + dp[j - coins[i]];
            }
        }

        return dp[amount];
    }
}
