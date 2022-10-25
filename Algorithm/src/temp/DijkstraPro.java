package temp;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆优化迪杰斯特拉（适用稀疏图）
 * 堆优化其实就是使用优先队列对图BFS一遍，在搜索得同时更新点的距离
 * 朴素算法需要遍历所有点找出距离最近的点，使用优先队列使得这一步的时间复杂度从n变为对数级
 * @author QinE
 * @create 2022-06-17 14:46
 */
public class DijkstraPro {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int N =  100010;
    static final int INF = 0x3f3f3f3f;
    static int n,  m;
    static int[] w = new int[N];
    static int[] dis = new int[N];
    static int[] e = new int[N];
    static int[] ne = new int[N];
    static int[] h = new int[N];
    static int[] st = new int[N];
    static int idx = 1;

    static PriorityQueue<Pair> q = new PriorityQueue<>(new Comparator<Pair>() {
        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.w - o2.w;
        }
    });


    public static void add(int a, int b, int c) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
        w[idx] = c;
    }

    public static int integer(String s) {
        return Integer.parseInt(s);
    }

    public static int bfs() {
        Arrays.fill(dis, INF);
        q.add(new Pair(0, 1));
        dis[1] = 0;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            if (st[pair.x] == 1)
                continue;
            st[pair.x] = 1;

            for (int i = h[pair.x]; i != 0; i = ne[i]) {
                if (dis[e[i]] > pair.w + w[i]) {
                    dis[e[i]] = pair.w + w[i];
                    q.add(new Pair(dis[e[i]], e[i]));
                }
            }
        }

        return dis[n] != INF ? dis[n] : -1;
    }

    public static void main(String[] args) throws IOException {
        String[] s = in.readLine().split(" ");
        n = integer(s[0]);
        m = integer(s[1]);
        for (int i = 0; i < m; i++) {
            String[] s1 = in.readLine().split(" ");
            add(integer(s1[0]), integer(s1[1]), integer(s1[2]));
        }

        out.write(bfs() + "\n");
        out.flush();
    }
}

class Pair {
    int w;
    int x;
    Pair(int a, int b) {
        w = a;
        x = b;
    }
}