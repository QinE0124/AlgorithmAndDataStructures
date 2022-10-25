package dp;

import java.util.HashMap;

/**
 *动态规划模拟正则表达式
 * @author QinE
 * @create 2022-06-04 20:41
 */
public class RegularExpression {

    public static boolean isMatch(String s, String p) {
        HashMap<int[], Boolean> memo = new HashMap<>();//备忘录
        return dp(s, 0, p, 0, memo);
    }

    //计算p[j...]是否匹配s[i...]
    private static boolean dp(String s, int i, String p, int j, HashMap<int[], Boolean> memo) {
        int n = s.length();
        int m = p.length();
        //base case
        if (j == m)
            return i == n;
        if (i == n) {
            if ((m - j) % 2 == 1)
                return false;
            for (; j + 1 < m; j += 2) {
                if (p.charAt(j + 1) != '*')
                    return false;
            }

            return true;
        }

        //查询备忘录，有就返回
        int[] key = new int[]{i, j};
        if (memo.containsKey(key))
            return memo.get(key);

        boolean res = false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            //匹配0次或多次
            if (j < n - 1 && p.charAt(j + 1) == '*')
                res = dp(s, i, p, j + 2, memo) || dp(s, i + 1, p, j, memo);
            else
                //常规匹配一次
                res = dp(s, i + 1, p, j + 1, memo);
        } else {
            if (j < n - 1 && p.charAt(j + 1) == '*')
                res = dp(s, i, p, j + 2, memo);
            else
                return false;
        }

        //将结果添加到备忘录中
        memo.put(key, res);
        return res;
    }
}
