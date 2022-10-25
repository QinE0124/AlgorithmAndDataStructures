package dp;

/**
 *经典动态规划：高楼扔鸡蛋（进阶版）
 * @author QinE
 * @create 2022-06-07 11:24
 */
public class SupperEggDrop {

    public static int superEggDrop(int K, int N) {
        //修改dp数组的具体定义，确定当前的鸡蛋个数和最多允许的扔鸡蛋次数，就能确定F的最高楼层数
        //m最多不会超过N次，（线性扫描）
        int[][] dp = new int[K + 1][N + 1];
        //base case
        //dp[0][...] = 0;
        //dp[...][0] = 0;
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++)
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
            //无论上楼还是下楼，总的楼层数=楼上的楼层数+楼下的楼层数+1（当前这层楼）
        }
        return m;
    }

}
