package daily_practice;

import java.util.Arrays;

/**
 * @author QinE
 * @create 2022-09-12 19:43
 */
public class SpecialArray {

        public int specialArray(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            for (int i = 0, j = n - 1; i < j; i++, j--) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            for (int i = 1; i <= n; ++i) {
                if (nums[i - 1] >= i && (i == n || nums[i] < i)) {
                    return i;
                }
            }
            return -1;
        }
}
