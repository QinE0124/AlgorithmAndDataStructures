package n_sum;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author QinE
 * @create 2022-06-14 9:56
 */
public class ThreeSum {

    public static ArrayList<ArrayList<Integer>> threeSum(int[] nums) {

        return threeSumTarget(nums, 0);
    }

    //输入数组nums，返回所有和为target的三元组
    private static ArrayList<ArrayList<Integer>> threeSumTarget(int[] nums, int target) {

        Arrays.sort(nums);
        int n = nums.length;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        //穷举threeSum中的第一个数
        for (int i = 0; i < n; i++) {
            //对target - nums[i]计算twoSum
            ArrayList<ArrayList<Integer>> tuples = twoSumTarget(nums, i + 1, target - nums[i]);
            //如果存在满足条件的三元组，加上nums[i]就是结果三元组
            for (ArrayList<Integer> tuple : tuples) {
                tuple.add(nums[i]);
                res.add(tuple);
            }
            //跳过第一个数重复的情况，否则会出现重复结果
            while (i < n - 1 && nums[i] == nums[i + 1])
                i++;
        }

        return res;
    }

    /*从nums[start]开始，计算有序数组nums中所有和为target的二元组*/
    private static ArrayList<ArrayList<Integer>> twoSumTarget(int[] nums, int start, int target) {
        //左指针改为start，其它不变
        int lo = start, hi = nums.length - 1;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
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
                ArrayList<Integer> ans = new ArrayList<>();
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
