package dp;

/**
 * @author QinE
 * @create 2022-06-07 11:42
 */
public class MaxCoins {

    public static int maxCoins(int[] nums) {
        int n = nums.length;
        //添加两侧的虚拟气球
        int[] points = new int[n + 1];
        points[0] = points[n] = 1;
        System.arraycopy(nums, 0, points, 1, n);
        //base case dp[i][j] = 0 (i == j) 开区间
        int[][] dp = new int[n + 2][n  + 2];
        //开始状态转移
        //i 从下到上
        for (int i = n; i >= 0; i--) {
            //j 从左到右
            for (int j = i + 1; j < n + 2; j++) {
                //选择最后戳破的气球
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j],
                            dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]);
                }
            }
        }

        return dp[0][n + 1];
    }
}
