package binary_search;

/**
 * 输入一个长度为n的正整数数组piles代表n堆香蕉，piles[i]代表第i堆香蕉的数量，
 * 现在，要在h小时吃完这些香蕉
 *
 * 二分搜索
 * @author QinE
 * @create 2022-06-15 9:53
 */
public class MinEatingSpeed {

    public int minEatingSpeed(int[] piles, int H) {
        //套用搜索左侧边界的算法框架
        int left = 1, right = getMax(piles) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, H))
                right = mid;
            else
                left = mid + 1;
        }

        return left;

    }

    private boolean canFinish(int[] piles, int speed, int H) {
        int time = 0;
        for (int n : piles)
            time += timeOf(n, speed);
        return time <= H;
    }

    private int timeOf(int n, int speed) {
        //每小时最多吃一堆，如果吃不下的话留到下小时再吃
        return (n / speed) + ((n % speed > 0) ? 1 : 0);
    }

    private int getMax(int[] piles) {
        int max = 0;
        for (int n : piles)
            max = Math.max(n, max);
        return max;
    }

}
