package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 利用回溯框架解决全排列问题
 * @author QinE
 * @create 2022-06-13 14:01
 */
public class Permute {

    static ArrayList<LinkedList<Integer>> res = new ArrayList<>();

    //主函数，输入一组不重复的数字，返回它们的全排列
    public static ArrayList<LinkedList<Integer>> permute(int[] nums) {
        //记录路径
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    private static void backtrack(int[] nums, LinkedList<Integer> track) {
        //打到叶子结点
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //排除不合法的选择
            if (track.contains(nums[i]))
                continue;
            //做选择
            track.addLast(nums[i]);
            //进入下层决策树
            backtrack(nums, track);
            //取消选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3};
        permute(nums);
        for (LinkedList<Integer> arr : res) {
            for (int n : arr)
                System.out.print(n + " ");
            System.out.println();
        }
    }
}
