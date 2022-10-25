/**
 * 最大子序列交替和
 *
 * 动态规划(DP)
 * 使用状态转移方程
 *
 * @author QinE
 * @create 2021-11-02 11:57
 */
public class MaximumAlternatingSubsequenceSum {

    public long maxAlternatingSum(int[] nums) {

        int n = nums.length;
//        记odd[i] 表示我们在数组nums的前缀nums[0..i] 中选择元素组成子序列，
//        且最后一个选择的元素的下标是奇数时，可以得到的最大交替和。
        long odd = 0;
//        记even[i] 表示在nums[0..i] 中选择元素组成子序列，且最后一个选择的元素的下标是偶数时，
//        可以得到的最大交替和。
        long even = nums[0];

        for(int i = 0; i < n; i++) {

        }
        return 0;
    }
}
