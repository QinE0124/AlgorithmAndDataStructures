package daily_practice;

/**
 * @author QinE
 * @create 2022-09-08 19:49
 */
public class ConstructArray {

    /**
     * 从特殊情况到一般情况
     * 特殊情况 ：K = 1， 以及 k = n - 1
     * 对于其他一般情况，将这两种特殊情况合并，即列表的前半部分相邻差差均为1，后半部分相邻差从k开始逐渐减到1
     *
     */
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int idx = 0;

        for (int i = 1; i < n - k; i++)
            ans[idx++] = i;

        for (int i = n - k, j = n; i <= j; i++, j--) {
            ans[idx++] = i;

            if (i != j)
                ans[idx++] = j;
        }

        return ans;
    }
}
