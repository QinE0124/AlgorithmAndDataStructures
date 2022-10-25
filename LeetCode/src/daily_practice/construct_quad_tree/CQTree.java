package daily_practice.construct_quad_tree;

/**
 * 建立四叉树
 * 递归 + 二维前缀和优化
 * 某一部分均为0时，它的和为0；某一部分均为1时，它的和为这一部分的面积大小
 * @author QinE
 * @create 2022-05-02 14:45
 */
public class CQTree {

    public static void main(String[] args) {
        int[][] grid = {{0,1},{1,0}};
        construct(grid);
    }

    public static Node construct(int[][] grid) {
        int n = grid.length;
        int[][] pre = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i][j - 1] + pre[i - 1][j] - pre[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        return dfs(pre, 0, 0, n, n);
    }

    public static Node dfs(int[][] pre, int r0, int c0, int r1, int c1) {
        int total = getSum(pre, r0, c0, r1, c1);
        if (total == 0) {
            return new Node(false, true);
        } else if (total == (r1 - r0) * (c1 - c0)) {
            return new Node(true, true);
        }

        Node res = new Node(
                true, false,
                dfs(pre, r0, c0, r0 + r1 >> 1, c0 + c1 >> 1),
                dfs(pre, r0, c0 + c1 >> 1, r0 + r1 >> 1, c1),
                dfs(pre, r0 + r1 >> 1, c0, r1, c0 + c1 >> 1),
                dfs(pre, r0 + r1 >> 1, c0 + c1 >> 1, r1, c1)

        );

        return res;
    }

    public static int getSum(int[][] pre, int r0, int c0, int r1, int c1) {
        return pre[r1][c1] - pre[r0][c1] - pre[r1][c0] + pre[r0][c0];
    }
}
