package monotonous_queue;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 实现单调队列
 * @author QinE
 * @create 2022-06-12 14:53
 */
public class MonotonicQueue {
    LinkedList<Integer> q = new LinkedList<>();

    public void push(int n) {
        while (!q.isEmpty() && q.getLast() < n)
            q.pollLast();
        q.addLast(n);
    }

    public int max() {
        return q.getFirst();
    }

    public void pop(int n) {
        if (n == q.getFirst())
            q.pollFirst();
    }

    //解题函数的实现
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                //先填满窗口的前k-1
                window.push(nums[i]);
            } else {
                //窗口向前滑动，加入新数字
                window.push(nums[i]);
                //记录当前窗口的最大值
                res.add(window.max());
                //移出旧数字
                window.pop(nums[i - k + 1]);
            }
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++)
            arr[i] = res.get(i);
        return arr;
    }
}


