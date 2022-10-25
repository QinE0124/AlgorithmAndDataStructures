package daily_practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author QinE
 * @create 2022-10-16 22:04
 */
public class PossibleBipartition {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        int[] prev = new int[n + 1];
        Arrays.fill(prev, -1);
        List<Integer>[] g = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int[] p : dislikes) {
            g[p[0]].add(p[1]);
            g[p[1]].add(p[0]);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < g[i].size(); j++) {
                unit(g[i].get(0), g[i].get(j), prev);
                if (isConnect(i, g[i].get(j), prev))
                    return false;
            }
        }

        return true;
    }

    private void unit(int x, int y, int[] prev) {
        x = findRoot(x, prev);
        y = findRoot(y, prev);
        if (x == y)
            return;
        if (prev[x] < prev[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        prev[x] += prev[y];
        prev[y] = x;
    }

    private int findRoot(int x, int[] prev) {
        return prev[x] < 0 ? x : (prev[x] = findRoot(prev[x], prev));
    }

    private boolean isConnect(int x, int y, int[] prev) {
        x = findRoot(x, prev);
        y = findRoot(y, prev);
        return x == y;
    }
}
