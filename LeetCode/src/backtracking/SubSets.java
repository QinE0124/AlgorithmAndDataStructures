package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 回溯算法框架解决子集问题
 * @author QinE
 * @create 2022-06-13 11:22
 */
public class SubSets {

    static ArrayList<LinkedList<Integer>> res = new ArrayList<>();

    public static ArrayList<LinkedList<Integer>> subSets(int[] nums) {
        //记录走过的路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, track);
        return res;
    }

    private static void backtrack(int[] nums, int start, LinkedList<Integer> track) {
        //前序遍历的位置
        res.add(new LinkedList<>(track));
        //从start开始，防止产生重复的子集
        for (int i = start; i < nums.length; i++) {
            //做选择
            track.addLast(nums[i]);
            //递归回溯
            backtrack(nums, i + 1, track);
            //撤销选择;
            track.removeLast();
        }
    }
}
