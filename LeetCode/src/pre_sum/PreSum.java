package pre_sum;

import java.util.HashMap;

/**
 * 前缀和算法
 *
 * @author QinE
 * @create 2022-06-14 14:50
 */
public class PreSum {

//    public int subArraySum(int[] nums, int k) {
//        int n = nums.length;
//        //构造前缀和
//        int[] sum = new int[n];
//        sum[0] = 0;
//        for (int i = 1; i <= n; i++) {
//            sum[i] = sum[i - 1] + sum[i - 1];
//        }
//
//        int ans = 0;
//        //穷举所有子数组
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j < i; j++) {
//                //sum of nums[j ...i - 1]
//                if (sum[i] - sum[j] == k)
//                    ans++;
//            }
//        }
//
//        return ans;
//    }

    //优化
    public int subArraySum(int[] nums, int k) {
        int n = nums.length;
        //map:前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer> preSum = new HashMap<>();
        //base case
        preSum.put(0, 1);

        int ans = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            //需要找的前缀和nums[0...j];
            int sum0_j = sum0_i - k;
            //如果前面有这个前缀和，则直接更新答案
            if (preSum.containsKey(sum0_j))
                ans += preSum.get(sum0_j);
            //把前缀和nums[0...i]加入并记录出现次数
            preSum.put(sum0_i, preSum.getOrDefault(sum0_i, 0) + 1);
        }

        return ans;

    }
}
