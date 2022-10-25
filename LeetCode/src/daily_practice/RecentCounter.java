package daily_practice;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 最近的请求次数
 * @author QinE
 * @create 2022-05-06 8:34
 */
public class RecentCounter {

    Queue<Integer> queue;

    public RecentCounter() {
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }
}
