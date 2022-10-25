package dp;

/**
 * 动态规划解决最小编辑距离
 * @author QinE
 * @create 2022-06-02 17:29
 */
public class MinDistance {

    //dp方法解决
//    public static int minDistance(String s1, String s2) {
//
//        //i, j初始化指向最后一个索引
//        return dp(s1, s2, s1.length() - 1, s2.length() - 1);
//    }
//
//    //dp函数的定义
//    //s1[0...i]和s2[0...j]的最小编辑距离是dp(i,j)
//    private static int dp(String s1, String s2, int i, int j) {
//        //base case
//        if (i == -1)
//            return j + 1;
//        if (j == -1)
//            return i + 1;
//        //做选择
//        if (s1.charAt(i) == s2.charAt(j))
//            return dp(s1, s2, i - 1, j - 1);//skip
//        else
//            return Math.min(
//                    Math.min(
//                            dp(s1, s2, i, j - 1) + 1, //insert
//                            dp(s1, s2, i - 1, j) + 1//delete
//                    ), dp(s1, s2, i - 1, j - 1)//replace
//            );
//    }

    //备忘录优化
//    public static int minDistance(String s1, String s2) {
//        HashMap<int[], Integer> memo = new HashMap<>();//备忘录
//        return dp(s1, s2, memo, new int[]{s1.length() - 1, s2.length() - 1});
//    }
//
//    private static int dp(String s1, String s2, HashMap<int[], Integer> memo, int[] index) {
//        //先查备忘录，避免重复计算
//        if (memo.containsKey(index))
//            return memo.get(index);
//        //base case
//        if (index[0] == -1)
//            return index[1] + 1;
//        if (index[1] == -1)
//            return index[0] + 1;
//
//        if (s1.charAt(index[0]) == s2.charAt(index[1]))
//            memo.put(index, dp(s1, s2, memo, new int[]{index[0] - 1, index[1] - 1}));
//        else
//            memo.put(index, Math.min
//                    (Math.min(
//                    dp(s1, s2, memo, new int[]{index[0], index[1] - 1}) + 1,//insert
//                    dp(s1, s2, memo, new int[]{index[0] - 1, index[1]}) + 1),//delete
//                    dp(s1, s2, memo, new int[]{index[0] - 1, index[1] - 1})//replace
//                    ));
//        return memo.get(index);
//    }

    //DP Table 优化
    public static int minDistance(String s1, String s2) {
        //dp[i][j]存储s1[0...i - 1]和s2[0...j - 1]的最小编辑距离
        //dp函数的base case 是 i, j 等于 -1， 而数组索引至少是0，所以dp数组会便宜一位
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        //base case
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;

        for (int j = 1; j <= n; j++)
            dp[0][j] = j;

        //自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(
                            Math.min(
                                    dp[i - 1][j] + 1,
                                    dp[i][j - 1] + 1
                            ),  dp[i - 1][j - 1] + 1
                    );

            }
        }
        //存储整个s1和s2的最小编辑距离
        return dp[n][m];
    }
}

