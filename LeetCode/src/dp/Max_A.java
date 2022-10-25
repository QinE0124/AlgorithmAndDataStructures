package dp;

/**
 *假设有一个特殊的键盘，上面只有四个键
 * A键：在屏幕上显示一个A
 * Ctrl-A:选中整个屏幕
 * Ctrl-C：将选中的区域复制到缓冲区
 * Ctrl-V： 将缓冲区的内容输出到光标所在的屏幕位置
 * @author QinE
 * @create 2022-06-05 20:18
 */
public class Max_A {

    //思路一：定义三个状态
    //状态一：剩余的按键数量，用n表示
    //状态二：当前屏幕上A的数量，用a_nums表示
    //状态三：剪贴板上字符A的数量，用copy表示
//    public static int max_A(int N) {
//        //备忘录
//        HashMap<int[], Integer> memo = new HashMap<>();
//        return dp(N, 0, 0, memo);
//    }
//
//    private static int dp(int n, int a_nums, int copy, HashMap<int[], Integer> memo) {
//        if (n <= 0) //base case
//            return a_nums;
//        int[] key = new int[]{n, a_nums, copy};
//        if (memo.containsKey(key))
//            return memo.get(key);
//        memo.put(key, Math.max(
//                Math.max(dp(n - 1, a_nums + 1, copy, memo),
//                        dp(n - 2, a_nums, a_nums, memo)),
//                dp(n - 1, a_nums + copy, copy, memo)
//        ));
//
//        return memo.get(key);
//    }

    //思路二
    public static int max_A(int N) {
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i + 1];//输入一个A
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }

        return dp[N];
    }

    public static void main(String[] args) {
        System.out.println(max_A(5));
    }
}
