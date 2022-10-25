package temp;

import java.io.*;
import java.util.ArrayDeque;

/**
 * 拓扑序列
 * @author QinE
 * @create 2022-06-17 16:30
 */
public class TopologicalOrder {

    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    static final int N = 100010;
    static int n, m, idx;
    static int[] e = new int[N];
    static int[] ne = new int[N];
    static int[] h = new int[N];
    static int[] d = new int[N];
    static ArrayDeque<Integer> q = new ArrayDeque<>();
    static ArrayDeque<Integer> ans = new ArrayDeque<>();

    public static int integer(String s) {
        return Integer.parseInt(s);
    }

    public static void add(int u, int v) {
        e[idx] = v;
        ne[idx] = h[u];
        h[u] = idx++;
    }

    public static boolean bfs() {
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i = h[x]; i != 0; i = ne[i]) {
                //删除当前节点与后继节点的边，如果删除后其后继节点的入度变为0，则入队
                if (--d[e[i]] == 0) {
                    q.add(e[i]);
                    ans.add(e[i]);
                }
            }
        }

        return ans.size() == n;
    }

    public static void main(String[] args) throws IOException {
        String[] s = in.readLine().split(" ");
        n = integer(s[0]);
        m = integer(s[1]);

        for (int i = 0; i < m; i++) {
            String[] s1 = in.readLine().split(" ");
            add(integer(s1[0]) ,integer(s1[1]));
            //入度加一
            d[integer(s1[1])]++;

        }

        int flag = 0;
        //找到入度为0的点
        for (int i = 1; i <= n; i++) {
            if (d[i] == 0) {
                q.add(i);
                ans.add(i);
                flag = 1;
            }
        }

        if (flag == 0) {
            out.write(-1 + "\n");
        } else {
            if (bfs()) {
                while (!ans.isEmpty())
                    out.write(ans.poll() + " ");
            } else {
                //不存在拓扑排序
                out.write(-1 + "\n");
            }
        }
        out.flush();


    }
}
