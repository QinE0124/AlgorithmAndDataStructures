package union_find;

/**
 * @author QinE
 * @create 2022-06-16 21:09
 */
public class Solve {

    //从board[i][j] 开始DFS，将字符O替换成字符#
    public void dfs(char[][] board, int i, int j) {
        int n = board.length;
        int m = board[0].length;
        //越界则直接返回
        if (i < 0 || i >= n || j < 0 || j >= m)
            return;
        if (board[i][j] != 'O')
            return;
        //进行替换
        board[i][j] = '#';
        //向四周搜索
        dfs(board, i + 1, j);
        dfs(board, i, j + 1);
        dfs(board, i - 1, j);
        dfs(board, i, j - 1);
    }

    public void dfs(char[][] board) {
        if (board.length == 0)
            return;
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }

        for (int j = 0; j < m; j++) {
            dfs(board, 0, j);
            dfs(board, n - 1, j);
        }

        //剩下的O就是应该被替换的
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

        //把所有字符#恢复成O
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }

    }

    public void solve(int[][] board) {
        if (board.length == 0)
            return;

        int n = board.length;
        int m = board[0].length;
        //给dummy留一个额外位置
        UnionFind uf = new UnionFind(n * m + 1);
        int dummy = m * n;
        //将首列和末列的0于dummy相连
        for (int i = 0; i < n; i++) {
            if (board[i][0] == '0')
                uf.union(i * m, dummy);
            if (board[i][m - 1] == '0')
                uf.union(i * m + m - 1, dummy);
        }

        //将首行和末行的0余dummy连通
        for (int j = 0; j < m; j++) {
            if (board[0][j] == '0')
                uf.union(j, dummy);
            if (board[n - 1][j] == '0')
                uf.union(m * (n - 1) + j, dummy);
        }

        //方向数组d是搜索上下左右四个方向的常用手法
        int[][] d = new int[][] {{1, 0}, {0, 1}, {1, -1}, {-1, 1}};
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (board[i][j] == '0') {
                    //将此0与上下左右的0连通
                    for (int k = 0; k < 4; k++) {
                        int x = d[k][0] + i;
                        int y = d[k][1] + j;
                        if (board[x][y] == '0')
                            uf.union(x * m + y, i * m + j);
                    }
                }
            }
        }
        //现在，没有被x包围的0都和dummy连通了
        //所有不和dummy连通的0都要被替换
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (!uf.connected(dummy, i * m + j))
                    board[i][j] = 'X';
            }
        }
    }
}
