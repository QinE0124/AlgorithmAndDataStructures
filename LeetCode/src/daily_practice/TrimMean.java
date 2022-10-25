package daily_practice;

import java.util.Arrays;

/**
 * @author QinE
 * @create 2022-09-14 20:45
 */
public class TrimMean {

    public double trimMean(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int partialSum = 0;
        for (int i = n / 20; i < 19 * n / 20; i++) {
            partialSum += arr[i];
        }
        return partialSum / (n * 0.9);
    }

}
