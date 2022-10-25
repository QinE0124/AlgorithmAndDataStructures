package daily_practice;

import java.util.HashSet;

/**
 * @author QinE
 * @create 2022-09-18 19:00
 */
public class LargestIsLand {

    static int N = 500;
    static int[] p = new int[N * N], sz = new int[N * N];
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    int find (int x) {
        if (p[x] != x)
            return p[x] = find(p[x]);
        return x;
    }

    void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb)
            return;

        if (sz[ra] > sz[rb]) {
            union(b, a);
        } else {
            sz[rb] += sz[ra];
            p[ra] = p[rb];
        }
    }

    public int largestIsLan(int[][] g) {
        int n = g.length;
        for (int i = 1; i <= n * n; i++) {
            p[i] = i;
            sz[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 0)
                    continue;
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x < 0 || x >= n || y < 0 || y >= n || g[x][y] ==0)
                        continue;
                    union(i * n + j + 1, x * n + y + 1);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    ans = Math.max(ans, sz[find(i * n + j + 1)]);
                } else {
                    int tot = 1;
                    HashSet<Integer> set = new HashSet<>();
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x < 0 || x > n || y < 0 || y >= n || g[x][y] == 0)
                            continue;
                        int root = find(x * n + y + 1);
                        if (set.contains(root))
                            continue;
                        tot += sz[root];
                        set.add(root);
                    }

                    ans = Math.max(ans, tot);
                }
            }
        }

        return ans;
    }
}
