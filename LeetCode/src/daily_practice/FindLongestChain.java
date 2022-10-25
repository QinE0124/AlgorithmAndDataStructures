package daily_practice;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最长数对链 dp
 *
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 *
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 *
 * Return the length longest chain which can be formed.
 *
 * You do not need to use up all the given intervals. You can select pairs in any order.
 *
 * @author QinE
 * @create 2022-09-03 13:06
 */
public class FindLongestChain {

    public int findLongestChain(int[][] pairs) {

        int n = pairs.length;
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        return dp[n -  1];
    }

    @Test
    public void test() {
        findLongestChain(new int[][]{{1, 2}, {2, 3}, {3, 4}});
    }
}
