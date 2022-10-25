package dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法练习
 * 迪杰斯特拉算法用于求出由某点到其他各点的最短距离
 *
 * @author QinE
 * @create 2021-12-12 1:58
 */
public class DijkstraAlgorithm {

    public static char[] vertex;//顶点集合
    public static int[][] matrix;//邻接矩阵
    public static int[] pre;//前驱节点
    public static int[] vis;//记录各顶点是否已访问
    public static int[] dis;//记录访问顶点到各顶点的最短距离
    public static final int N = Integer.MAX_VALUE >> 2;

    public static void main(String[] args) {

        vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        matrix = new int[][]{
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };

        init(vertex.length);
        dijkstra(2);
        printResult();



    }

    public static void init(int length) {
        pre = new int[length];
        vis = new int[length];
        dis = new int[length];
        Arrays.fill(dis, N);
    }

    public static void dijkstra(int index) {
        //设置出发节点的距离为0
        dis[index] = 0;
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            index = updateIndex();
            update(index);
        }
    }

    /**
     * 更新index顶点和周围顶点的最短距离，以及周围顶点的前驱顶点
     *
     * @param index
     */
    private static void update(int index) {

        for (int i = 0; i < matrix[index].length; i++) {
            int len = dis[index] + matrix[index][i];
            if (len < dis[i] && vis[i] == 0) {
                dis[i] = len;
                pre[i] = index;
            }
        }
    }

    /**
     * 查询下个访问节点
     *
     * @return
     */
    private static int updateIndex() {
        int index = 0;
        int min = N;
        for (int i = 0; i < vertex.length; i++) {
            if (dis[i] < min && vis[i] == 0) {
                min = dis[i];
                index = i;
            }
        }
        vis[index] = 1;
        return index;
    }

    public static void printResult() {
        int count = 0;
        for (int i : dis) {
            if (dis[count] == N) System.out.print('N' +  "\t");
            else System.out.print(vertex[count] + "(" + i + ")" + " ");
            count++;
        }
    }

}
