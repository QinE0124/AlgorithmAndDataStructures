package daily_practice;

/**
 * 盛最多水的容器
 * @author QinE
 * @create 2022-05-09 9:42
 */
public class MaxArea {

    //贪心+双指针
    public int maxArea(int[] height) {
        int r = height.length - 1;
        int l = 0;
        int ans = Integer.MIN_VALUE;
        while (l < r) {
            //选择较低的指针移动
            ans = Math.max(ans, Math.min(height[l], height[r]) * (r - l));
            if (height[l] > height[r])
                r--;
            else
                l++;

        }
        return ans;
    }
}
