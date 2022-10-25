package dp;

/**
 * 动态规划解决最大子数组和问题
 * @author QinE
 * @create 2022-06-02 9:05
 */
public class MaxSubArray {

    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        //base case
        int dp_0 = nums[0];
        int dp_1 = 0, res = 0;
        for (int i = 1; i < n; i++) {
            dp_1 = Math.max(nums[i], nums[i] + dp_0);
            dp_0 = dp_1;
            res += dp_1;
        }

        return res;
    }
}
