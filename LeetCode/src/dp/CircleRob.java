package dp;

/**
 *环形排列
 * 首尾不能同时选择
 * @author QinE
 * @create 2022-06-08 18:40
 */
public class CircleRob {

  public static int rob(int[] nums) {//环形数组
      int n = nums.length;
      if (n == 1) return nums[0];
      return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
  }

  private static int robRange(int[] nums, int start, int end) {
      int dp_i_1 = 0, dp_i_2 = 0;
      int dp_i = 0;
      for (int i = end; i >= start; i--) {
          dp_i = Math.max(dp_i_1, dp_i_2 + nums[i]);
          dp_i_2 = dp_i_1;
          dp_i_1 = dp_i;
      }

      return dp_i;
  }
}
