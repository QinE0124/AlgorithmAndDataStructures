package temp;

import java.io.*;
import java.util.Arrays;

/**
 * @author QinE
 * @create 2022-06-17 15:52
 */
public class Kruskal {

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static final int N = 200010, INF = 0x3f3f3f3f;
    static int n, m;
    static int[] par = new int[N];
    static Edge[] g = new Edge[N];

    public static int integer(String s) {
        return Integer.parseInt(s);
    }

    public static int find(int x) {
        while (x != par[x])
            par[x] = par[par[x]];
        return x;
    }

    public static int kruskal() {
        Arrays.sort(g, 0, m - 1);
        int cnt = 0, res = 0;
        for (int i = 1; i <= n; i++)
            par[i] = i;

        for (int i = 1; i <= m; i++) {
            Edge e = g[i];
            int a = find(e.u);
            int b = find(e.v);
            if (a != b) {
                par[a] = b;
                cnt++;
                res += e.w;
            }
        }
        return cnt != n - 1 ? INF : res;
    }

    public static void main(String[] args) throws IOException {
        String[] s = in.readLine().split(" ");
        n = integer(s[0]);
        m = integer(s[1]);
        for (int i = 0, j = 0; i < m; i++) {
            String[] split = in.readLine().split(" ");
            g[j++] = new Edge(integer(s[0]), integer(s[1]), integer(s[2]));
        }

        int res = kruskal();
        if (res == INF)
            out.write("impossible" + "\n");
        else
            out.write(res);
        out.flush();
    }
}

class Edge implements Comparable<Edge>{
    int u, v, w;
    public Edge(int a, int b, int c) {
        u = a;
        v = b;
        w = c;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w;
    }
}
