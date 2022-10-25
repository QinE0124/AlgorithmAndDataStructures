package dp;

/**
 *动态规划解决01背包
 * @author QinE
 * @create 2022-06-07 14:53
 */
public class Knapsack {

    public static int knaspack(int W, int N, int[] wt, int[] val) {
        //base case dp[0][...] = dp[...][0] == 0
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                //若背包容量不够，则只能不装入背包
                if (w - wt[i - 1] < 0)
                    dp[i][w] = dp[i - 1][w];
                else
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - wt[i - 1]] + val[i - 1]);
            }
        }

        return dp[N][W];
    }
}
