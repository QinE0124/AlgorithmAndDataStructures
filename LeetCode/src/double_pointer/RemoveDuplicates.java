package double_pointer;

/**
 * 快慢指针去除有序数组的重复元素
 * @author QinE
 * @create 2022-06-15 11:12
 */
public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0)
            return 0;
        int slow = 0, fast = 1;
        while (fast < n) {
            if (nums[fast] != nums[slow]) {
                slow++;
                //维护nums[0...slow]无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        //长度为索引+1
        return slow + 1;
    }
}
