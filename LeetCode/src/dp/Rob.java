package dp;

/**
 *线性排列情况
 * @author QinE
 * @create 2022-06-08 18:35
 */
public class Rob {

    public static int rob(int[] nums) {
        int dp_i_1 = 0, dp_i_2 = 0;
        int dp_i = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            dp_i = Math.max(dp_i_1, dp_i_2 + nums[i]);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }

        return dp_i;
    }
}
