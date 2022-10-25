import org.junit.Test;

import java.util.TreeSet;

/**
 * Given an integer array nums, return the third distinct maximum number in this array.
 * If the third maximum does not exist, return the maximum number.
 *
 * @author QinE
 * @create 2021-10-06 21:50
 */
public class Maximum {

    @Test
    public void solution() {

        int[] nums = new int[]{0, 1, 2, 4, -1, -2, -3, 0, 4, -2, -1};
        System.out.println(thirdMax(nums));
    }

    /**
     * 使用有序树列解决
     * @param nums
     * @return
     */
    public int thirdMax(int nums[]) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int num : nums) {
            set.add(num);

            if (set.size() > 3) {
                set.remove(set.first());
            }
        }

        return set.size() == 3 ? set.first() : set.last();
    }
}
