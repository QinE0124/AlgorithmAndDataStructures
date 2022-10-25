package temp;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 判断有无负环的算法
 * @author QinE
 * @create 2022-06-17 15:13
 */
public class SPFA {

    static final int N =  100010;
    static final int INF = 0x3f3f3f3f;
    static int n,  m;
    static int[] w = new int[N];
    static int[] dis = new int[N];
    static int[] e = new int[N];
    static int[] ne = new int[N];
    static int[] h = new int[N];
    static int[] st = new int[N];
    static int[] cnt = new int[N];
    static int idx = 1;


    static PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }

    });

    public static int spfa() {
        //因为目的是判断有无负环，所以要将所有点都当做起点加入队列
        //因为一个图可能分成不连通的几部分，而负环一定存在其中某一个连通块
        for (int i = 1; i <= n; i++)
            q.add(i);

        while (!q.isEmpty()) {//因为存在负环的化距离会小于0，所以不需要对dis数组初始化
            Integer t = q.poll();
            for (int i = h[t]; i != 0; i++) {
                int j = e[i];
                if (dis[j] > dis[t] + w[i]) {
                    dis[j] = dis[t] + w[i];
                    cnt[j] = cnt[t] + 1;
                    if (cnt[j] >= n)
                        return n;

                    if (st[j] == 0) {
                        q.add(j);
                    }
                }


            }

        }
        return 0;
    }
}
