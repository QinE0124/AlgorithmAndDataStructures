package n_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author QinE
 * @create 2022-06-14 10:30
 */
public class ForSum {

    public static List<List<Integer>> fourSum(int[] nums) {
        return fourSumTarget(nums, 0);
    }

    private static List<List<Integer>> fourSumTarget(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            List<List<Integer>> ans = treeSumTarget(nums, i + 1, target - nums[i]);
            for (List<Integer> arr : ans) {
                arr.add(nums[i]);
                res.add(arr);
            }

            while (i < n - 1 && nums[i] == nums[i + 1])
                i++;
        }

        return res;
    }

    private static List<List<Integer>> treeSumTarget(int[] nums, int start, int target) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = start; i < n; i++) {
            List<List<Integer>> ans = twoSumTarget(nums, start + 1, target - nums[i]);
            for (List<Integer> arr : ans) {
               arr.add(nums[i]);
               res.add(arr);
            }

            while (i < n - 1 && nums[i] == nums[i + 1])
                i++;
        }

        return res;
    }

    private static List<List<Integer>> twoSumTarget(int[] nums, int start, int target) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = n - 1;
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
                res.add(ans);
                while (lo < hi && nums[lo] == left)
                    lo++;
                while (lo < hi && nums[hi] == right)
                    hi--;
            }
        }
        return res;
    }
}
