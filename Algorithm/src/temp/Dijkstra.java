package temp;

import java.io.*;
import java.util.Arrays;

/**
 * 朴素迪杰斯特拉（适用于稠密图）
 * @author QinE
 * @create 2022-06-17 14:20
 */
public class Dijkstra {

    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final int N = 505;
    static int[][] w = new int[N][N];
    static int[] dis = new int[N];//每个点距离远点的距离
    static int[] st = new int[N];//最短路径集合
    static int n, m;

    public static int Integer(String s) {
        return Integer.parseInt(s);
    }

    public static int dijkstra() {
        Arrays.fill(dis, 0x3f3f3f3f);
        dis[1] = 0;
        for (int i = 0; i < n; i++) {//循环n次，每次找出一个距离起点最近的点
            int t = -1;
            for (int j = 1; j <= n; j++) {
                if (st[j] == 0 && (t == -1 || dis[t] > dis[j]))//找到一个距离最近的加入ts集合
                    t = j;
            }

            st[t] = 1;//然后通过这个点来缩短起点到达其他点的距离
            for (int j = 2; j <= n; j++)
                dis[j] = Math.min(dis[j], dis[t] + w[t][j]);

        }

        return dis[n] != 0x3f3f3f3f ? dis[n] : -1;

    }

    public static void main(String[] args) throws IOException {
        String[] s = in.readLine().split(" ");
        n = Integer(s[0]);
        m = Integer(s[1]);

        for (int i = 0; i < n; i++)
            Arrays.fill(w[i], 0x3f3f3f3f);

       for (int i = 0; i < m; i++) {
           String[] s1 = in.readLine().split(" ");
           int a = Integer(s1[0]);
           int b = Integer(s1[1]);
           int c = Integer(s1[2]);
           w[a][b] = Math.min(w[a][b], c);

       }

       out.write(dijkstra() + "\n");
       out.flush();
    }
}
