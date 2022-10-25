package backtracking;

import java.util.ArrayDeque;
import java.util.HashSet;

/**
 * BFS算法暴力破解各种智力题
 * 滑动拼图
 * @author QinE
 * @create 2022-06-13 19:25
 */
public class SlidingPuzzle {

    public static int slidingPuzzle(int[][] board) {
        int m = 2, n = 3;
        StringBuilder start = new StringBuilder();
        StringBuilder target = new StringBuilder("123450");
        //将 2 * 3 的数组转换为字符串
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                start.append(board[i][j] + '0');
            }
        }

        //记录一维数组的相邻索引
        int[][] neighbor = {
                {1, 3}, {0, 4, 2}, {1, 5}, {0, 4}, {3, 1, 5}, {4, 2}
        };

        ArrayDeque<StringBuilder> queue = new ArrayDeque<>();
        HashSet<StringBuilder> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                StringBuilder cur = queue.poll();
                //判断是否到达目标局面
                if (target.equals(cur))
                    return step;
                //找到数字0的索引
                int idx = 0;
                while (cur.charAt(idx) != '0')
                    idx++;
                //将数字0和相邻的数字交换位置
                for (int adj : neighbor[idx]) {
                    StringBuilder new_board = cur;
                    char[] chars = new_board.toString().toCharArray();
                    char temp = chars[adj];
                    chars[adj] = chars[idx];
                    chars[idx] = temp;
                    cur = new StringBuilder(chars.toString());

                    if (!visited.contains(cur)) {
                        queue.offer(cur);
                        visited.add(cur);
                    }
                }
            }
            step++;
        }

        return -1;
    }
}
