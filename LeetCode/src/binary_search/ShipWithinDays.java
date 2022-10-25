package binary_search;


/**
 * @author QinE
 * @create 2022-06-15 10:37
 */
public class ShipWithinDays {

    public int shipWhithinDays(int[] weights, int D) {
        int left = getMax(weights);
        int right = getSum(weights) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(weights, mid, D))
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    private boolean canFinish(int[] weights, int cap, int d) {
        int i = 0;
        for (int k = 0; k < d; k++) {
            int maxCap = cap;
            while ((maxCap -= weights[i]) >= 0) {
                i++;
                if (i == weights.length)
                    return true;
            }

        }

        return false;
    }

    private int getSum(int[] weights) {
        int sum = 0;
        for (int weight : weights)
            sum += weight;
        return sum;
    }

    private int getMax(int[] weights) {
        int max = 0;
        for (int weight : weights)
            max = Math.max(max, weight);
        return max;
    }
}
