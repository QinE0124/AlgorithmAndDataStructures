package temp;

import java.io.*;
import java.util.Arrays;

/**
 * 稠密图求最小生成树
 * @author QinE
 * @create 2022-06-17 15:34
 */
public class Prim {

    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    static final int N = 1005, INF = 0x3f3f3f3f;
    static int[][] g = new int[N][N];
    static int n, m;
    static int[] dis = new int[N];
    static int[] st = new int[N];

    public static int prim() {
        Arrays.fill(dis, INF);
        int res = 0;
        dis[1] = 0;
        for (int i = 0; i < n; i++) {
            int t = -1;
            int minV = INF;
            for (int j = 1; j <= n; j++) {
                if (st[j] == 0 && minV > dis[j]) {
                    t = j;
                    minV = dis[j];
                }
            }

            if (minV == INF)
                return INF;
            res += dis[t];
            st[t] = 1;

            for (int j = 1; j <= n; j++)
                dis[j] = Math.min(dis[j], g[t][j]);
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        String[] s = in.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        for(int i = 1; i <= n; i++){
            Arrays.fill(g[i], 0x3f3f3f3f);
        }

        for(int i = 0; i < m; i++){
            int a, b, w;
            String[] s1 = in.readLine().split(" ");
            a = Integer.parseInt(s1[0]);
            b = Integer.parseInt(s1[1]);
            w = Integer.parseInt(s1[2]);
            g[a][b] = g[b][a] = Math.min(g[a][b], w);
        }

        int res = prim();
        if (res == INF)
            out.write("impossible" + "\n");
        else
            out.write(res + "\n");
        out.flush();
    }
}
