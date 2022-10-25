package dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 动态规划解决信封嵌套问题
 *
 * @author QinE
 * @create 2022-06-02 8:23
 */
public class MaxEnvelopes {

    public static int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        //对二维数组排序，先按w升序排序，若w相同，则按h降序排序
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });

        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }

        return lengthOfLIS(height);
    }

    //求最大递增子序列
    //子序列不连续，子数组连续
    private static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        //base case
        Arrays.fill(dp, 1);
        //状态转移方程
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int res = 0;
        for (int d : dp)
            res = Math.max(res, d);

        return res;
    }
}
