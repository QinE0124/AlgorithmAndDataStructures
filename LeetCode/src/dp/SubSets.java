package dp;

/**
 * 动态规划：子集划分
 * 如果把nums划分为两个子集A和B，分别代表分配+的数和分配-的数，
 * 那么他们和target存在如下关系
 * sum(A) - sum(B) = target
 * sum(A) = target + sum(B)
 * 2 * sum(A) = target + sum(nums)
 *
 * 推出 sum(A) = (target + sum(nums) / 2
 * 将原问题转化为：nums中存在几个子集A,使得A中元素的和为（target + sum(nums))
 * @author QinE
 * @create 2022-06-08 18:58
 */
public class SubSets {

    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        if (target < sum || (sum + target) % 2 == 1)
            return 0;

        return subsSets(nums, (sum + target) / 2);
    }

//    private static int subsSets(int[] nums, int sum) {
//        int n = nums.length;
//        int[][] dp = new int[n + 1][sum + 1];
//        //base case dp[0][...] = 0, dp[...][0] = 1;
//        for (int i = 0; i <= n; i++)
//            dp[i][0] = 1;
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= sum; j++) {
//                if (j >= nums[i - 1])
//                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
//            }
//        }
//
//        return dp[n][sum];
//    }

    //状态压缩
    private static int subsSets(int[] nums, int sum) {
        int n = nums.length;
        int[] dp = new int[sum + 1];
        //base case
        dp[0] = 1;
        for (int num : nums) {
            for (int j = 1; j <= sum; j++) {
                if (j >= num)
                    dp[j] = dp[j] + dp[j - num];
            }
        }

        return dp[sum];
    }
}
