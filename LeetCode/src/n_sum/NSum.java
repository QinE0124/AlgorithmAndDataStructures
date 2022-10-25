package n_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author QinE
 * @create 2022-06-14 10:58
 */
public class NSum {

    public static List<List<Integer>> nSum(int[] nums, int n) {
        Arrays.sort(nums);
        return nSumTarget(nums, n, 0, 0);
    }

    public static List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        //至少是2sum，且数组大小不应该小于n
        if (n < 2 || len < n)
            return res;
        //base case 2sum
        if (n == 2) {
            int lo = start, hi = len - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];
                if (sum < target) {
                    while (lo < hi && nums[lo] == left)
                        lo++;
                } else if (sum > target) {
                    while (lo < hi && nums[hi] == right)
                        hi--;
                } else {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(left);
                    ans.add(right);
                    while (lo < hi && nums[lo] == left)
                        lo++;
                    while (lo < hi && nums[hi] == right)
                        hi--;
                }
            }
        } else {
            //n > 2时，递归计算(n - 1)sum的值
            for (int i = start; i < len; i++) {
                List<List<Integer>> ans = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                for (List<Integer> arr : ans) {
                    arr.add(nums[i]);
                    res.add(arr);
                }
            }
        }

        return res;
    }
}
