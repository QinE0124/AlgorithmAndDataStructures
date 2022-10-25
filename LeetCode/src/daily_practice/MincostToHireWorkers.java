package daily_practice;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author QinE
 * @create 2022-09-11 20:00
 */
public class MincostToHireWorkers {

    public double mincostToHireWrorks(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Integer[] h = new Integer[n];
        for (int i = 0; i < n; i++)
            h[i] = i;

        Arrays.sort(h, (a, b) -> {
            return quality[b] * wage[a] - quality[a] * wage[b];
        });

        double res = 1e9;
        double totalq = 0.0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k - 1; i++) {
            totalq += quality[h[i]];
            pq.offer(quality[h[i]]);
        }

        for (int i = k - 1; i < n; i++) {
            int idx = h[i];
            totalq += quality[idx];
            pq.offer(quality[idx]);
            double totlac = ((double)wage[idx] / quality[idx]) * totalq;
            res = Math.min(res, totlac);
            totalq -= pq.poll();

        }
        return res;
    }
}
