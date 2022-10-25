package greedy;

/**
 * 跳跃游戏I
 * @author QinE
 * @create 2022-06-15 11:44
 */
public class CanJump {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0;
        for (int i = 0; i < n; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest <= i)
                return false;
        }
        return farthest >= n - 1;
    }
}
