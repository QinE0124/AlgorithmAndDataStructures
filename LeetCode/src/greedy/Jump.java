package greedy;

/**
 * @author QinE
 * @create 2022-06-15 11:58
 */
public class Jump {

    /* dp 动态规划解决
    //备忘录
    int[] memo;

    public int jump(int[] nums) {
        int n = nums.length;
        memo = new int[n];
        //备忘录都优化为n,相当于INT_MAX
        //因为从0跳到n - 1,不会超过n - 1步
        Arrays.fill(memo, n);
        return dp(nums, 0);

    }

    //返回从索引p跳到最后一格需要的最少步数
    private int dp(int[] nums, int p) {
       int n = nums.length;
       //base case
        if (p >= n - 1)
            return 0;

        //子问题已经计算过
        if (memo[p] != n)
            return memo[p];
        int step = nums[p];
        //穷举每一个选择
        for (int i = 1; i <= step; i++) {
            //计算每个子问题的结果
            int subProblem = dp(nums, p + i);
            //取其中最小的作为答案
            memo[p] = Math.min(memo[p], subProblem + 1);
        }

        return memo[p];
    }*/

    //贪心算法解决
    public int jump(int[] nums) {
        int n = nums.length;
        //站在索引i，最多能跳到索引end
        int end = 0;
        //从索引[i...end]起跳，最远能到的距离
        int farthest = 0;
        //记录跳跃次数
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, nums[i] + i);
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }

        return jumps;

    }
}
