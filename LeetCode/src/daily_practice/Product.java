package daily_practice;

/** 乘积小于k的数组
 * @author QinE
 * @create 2022-05-05 10:14
 */
public class Product {

    //滑动窗口
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ret = 0;
        int n = nums.length;
        int pros = 1, j = 0;
        for (int i = 0; i < n; i++) {
            pros *= nums[i];
            while (j < i && pros >= k) {
                pros /= nums[j++];
            }
            ret += i - j + 1;
        }

        return ret;
    }
}
