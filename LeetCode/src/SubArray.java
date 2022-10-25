/**
 * 长度最小的子数组
 * @author QinE
 * @create 2022-05-05 13:00
 */
public class SubArray {

    public static void main(String[] args) {
        int nums[] = {2,3,1,2,4,3};
        int ret = minSubArrayLen(7, nums);
        System.out.println(ret);
    }

    //滑动窗口
    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0;
        int sum = 0, ret = Integer.MAX_VALUE;
        int len = nums.length;
        while (r < nums.length) {
            sum += nums[r++];
            //如若符合要求，移动l缩小窗口
            while (sum >= target) {
                //如果此窗口的子数组长度更短，则更新答案
                ret = Math.min(ret, r - l);
                sum -= nums[l++];
            }
        }

        return ret == Integer.MAX_VALUE ? 0 : ret;
    }
}
