package daily_practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到所有数组中消失的数字
 * @author QinE
 * @create 2022-05-08 16:07
 */
public class DisappearedNumbers {

    public static List<Integer> findDisapearedNumbers(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            while (nums[nums[i] - 1] != nums[i])
                swap(nums, nums[i] - 1, i);
        }

        for (int i = 0; i < n; i++) {
            if (i != nums[i] - 1)
                ans.add(i + 1);
        }

        return ans;
    }

    private static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
