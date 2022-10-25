package daily_practice;

import java.util.Arrays;

/**
 * @author QinE
 * @create 2022-04-14 9:24
 */
public class MaximumWealth {

    public static void main(String[] args) {
        
    }

    public static int soulution(int[][] accounts) {
        int maxWealth = Integer.MIN_VALUE;
        for (int[] a : accounts) {
            maxWealth = Math.max(maxWealth, Arrays.stream(a).sum());
        }
        return maxWealth;
    }
}
