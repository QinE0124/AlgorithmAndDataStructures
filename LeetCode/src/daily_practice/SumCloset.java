package daily_practice;

import java.util.Arrays;
import java.util.Scanner;

/**排序+双指针
 * @author QinE
 * @create 2022-04-12 9:37
 */
public class SumCloset {

    static int[] nums = new int[1010];
    static int n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        for (int i = 0; i < n; i++)
            nums[i] = in.nextInt();
        int target = in.nextInt();
        int sum = sumClosetThree(nums, target);
        System.out.println(sum);
    }

    public static int sumClosetThree (int[] nums, int target) {
//        int len = nums.length;
        int min = Integer.MAX_VALUE >> 1;
        Arrays.sort(nums, 0, n);
        //先寻找a
        for (int i = 0; i  < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1])//保证和上次的元素不同
                continue;
            //使用双指针寻找b和c
            int b = i + 1, c = n - 1;
            while (b < c) {
                int sum = nums[i] + nums[b] + nums[c];
                if (sum == target)
                    return target;
                //根据差的绝对值更新答案
                if (Math.abs(sum - target) < Math.abs(min - target))
                    min = sum;
                if (sum > target) {
                    int k = c - 1;
                    while (k > b && nums[k] == nums[c])
                        k--;
                    c = k;
                } else {
                    int k = b + 1;
                    while (k < c && nums[k] == nums[b])
                        k++;
                    b = k;
                }
            }
        }
        return min;
    }
}
