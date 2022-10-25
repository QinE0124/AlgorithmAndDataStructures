package dp;

/**
 * 子集背包问题
 *
 * @author QinE
 * @create 2022-06-07 15:36
 */
public class CanPartition {

//    public static boolean canPartition(int[] nums) {
//        int sum = 0;
//        for (int num : nums)
//            sum += num;
//        //和为奇数时，不可能划分为两个相等的集合
//        if (sum % 2 == 1)
//            return false;
//        int n = nums.length;
//        sum = sum / 2;
//        //构建dp数组
//        boolean[][] dp = new boolean[n + 1][sum + 1];
//        //base case dp[0][...] = false, dp[...][0] = true
//        for (int i = 0; i <= n; i++)
//            dp[i][0] = true;
//
//        //开始状态转移
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= sum; j++) {
//                if (j - nums[i - 1] < 0)
//                    //背包容量不足，肯定不能装满
//                    dp[i][j] = dp[i - 1][j];
//                else
//                    //装入或不装入背包，看看是否存在一种状态能装满
//                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
//            }
//        }
//
//        return dp[n][sum];
//    }

    //状态压缩
    public static boolean canPartition(int[] nums) {
        int sum = 0, n = nums.length;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0)
            return false;
        boolean[] dp = new boolean[sum + 1];
        //base case
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0)
                    dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[sum];
    }
}
