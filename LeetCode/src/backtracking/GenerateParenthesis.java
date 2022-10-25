package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 回溯算法最佳实践：括号生成
 * @author QinE
 * @create 2022-06-13 18:00
 */
public class GenerateParenthesis {

    //记录所有合法的括号选择
    static ArrayList<LinkedList<Character>> res = new ArrayList<>();

    //主函数
    public static ArrayList<LinkedList<Character>> generateParenthesis(int n) {
        if (n == 0)
            return res;
        //回溯过程中的路径
        LinkedList<Character> track = new LinkedList<>();
        //可用的左括号和右括号初始化为n
        backtrack(n, n, track);
        return res;

    }

    private static void backtrack(int left, int right, LinkedList<Character> track) {
        //数量小于0，不合格
        if (left < 0 || right < 0)
            return;
        //若左括号剩余多于右括号,不合格
        if (left > right)
            return;
        //当所有的括号用完时，得到一个合法的括号组合
        if (left == 0 && right == 0) {
            res.add(new LinkedList<>(track));
            return;
        }

        //尝试添加一个左括号
        //选择
        track.addLast('(');
        //进入下层决策树
        backtrack(left - 1, right, track);
        //撤销选择
        track.removeLast();

        //尝试添加一个右括号
        //选择
        track.addLast(')');
        //进入下层决策树
        backtrack(left, right - 1, track);
        //撤销选择
        track.removeLast();
    }
}
