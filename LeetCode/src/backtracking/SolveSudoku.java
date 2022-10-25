package backtracking;

/**
 * 回溯算法最佳实现：解数独
 * @author QinE
 * @create 2022-06-13 14:36
 */
public class SolveSudoku {

    public static void solveSudoku() {

    }

    private static boolean backtrack(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == m)
            //穷举到最后一列的时候就换到下一行重新开始
            return backtrack(board, i + 1,0);

        if (i == n)
            //找到一个可行解，触发base case
            return true;

        if (board[i][j] != '.')
            //如果有预设数字，不用穷举
            return backtrack(board, i, j + 1);

        for (char ch = '1'; ch <= '9'; ch++) {
            //如果遇到不合法的数字，跳过
            if (!isValid(board, i, j, ch))
                continue;
            //做选择
            board[i][j] = ch;
            //进入下层决策树
            //如果找到一个可行解，立即结束
            if (backtrack(board, i, j + 1))
                return true;
            //撤销选择
            board[i][j] = '.';
        }
        //穷举完前面1-9，依然没有找到可行解，需要前面的格子换个数字穷举
        return false;
    }

    private static boolean isValid(char[][] board, int r, int c, int n) {
        for (int i = 0; i < 9; i++) {
            //判断行是否存在重复
            if (board[r][i] == n)
                return false;
            //判断列是否存在重复
            if (board[i][c] == n)
                return false;
            //判断3 * 3 方框是否存在重复
            if (board[(r / 3) * 3 + i / 3][(c / 3) * 3 + i % 3] == n)
                return false;
        }

        return true;
    }
}
