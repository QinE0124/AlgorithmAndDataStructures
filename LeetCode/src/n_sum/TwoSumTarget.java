package n_sum;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2sum泛用优化
 * @author QinE
 * @create 2022-06-14 9:28
 */
public class TwoSumTarget {

    public static ArrayList<int[]> twoSumTarget(int[] nums, int target) {
         //nums数组必须有序
        Arrays.sort(nums);
        int lo = 0, hi = nums.length - 1;
        ArrayList<int[]> res = new ArrayList<>();
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            //记录lo和hi最初对应的值
            int left = nums[lo], right = nums[hi];
            if (sum < target) {
                while (lo < hi && nums[lo] == left)
                    lo++;
            } else if (sum > target) {
                while (lo < hi && nums[hi] == right)
                    hi--;
            } else {
                res.add(new int[]{left, right});
                while (lo < hi && nums[lo] == left)
                    lo++;
                while (lo < hi && nums[hi] == right)
                    hi--;
            }
        }

        return res;
    }
}
