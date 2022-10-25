package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 回溯算法解决组合问题
 * @author QinE
 * @create 2022-06-13 11:44
 */
public class Combine {

    static ArrayList<LinkedList<Integer>> res = new ArrayList<>();

    public static ArrayList<LinkedList<Integer>> chmbine(int n, int k) {
        if (k <= 0 || n <= 0)
            return res;
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(n, k, 1, track);
        return res;
    }

    //套回溯算法模板
    private static void backtrack(int n, int k, int start, LinkedList<Integer> track) {
      //到达叶子结点才能更新res
        if (track.size() == k) {
            res.add(new LinkedList<>(track));
            return;
        }

        //从start开始递增
        for (int i = start; i <= n; i++) {
            //做选择
            track.addLast(i);
            //递归回溯
            backtrack(n, k, i + 1, track);
            //撤销选择
            track.removeLast();
        }
    }
}
