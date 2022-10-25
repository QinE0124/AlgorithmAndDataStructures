package pirm;

import java.util.Arrays;

/**
 * @author QinE
 * @create 2021-08-26 11:29
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图是否成功创建
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //邻接矩阵的关系使用二维数组表示，10000表示两点不联通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};

        //创建MGraph对象
        MGraph graph = new MGraph(verxs);
        //创建一个MinTree对象
        MinTreee minTreee = new MinTreee();
        minTreee.createGraph(graph, verxs, data, weight);
        //输出
        minTreee.showGraph(graph);
        //测试普利姆算法
        minTreee.prim(graph, 1);

    }
}

//创建最小生成树->村庄的图
class MinTreee {
    /**
     *
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prim算法，得到最小生成树
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph, int v) {
        //visited[]标记结点是否被访问过
        int visited[] = new int[graph.verxs];
        //visited[]默认元素的值都是0，表示没有访问过
        //把当前结点标记为已访问
        visited[v] = 1;
        //h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;//将minWeight初始成一个大数，后面在遍历过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) {//因为有graph.verxs个顶点，普利姆算法结束后，有graph.verxs-1个边

            //确定每次生成的子图，和哪个结点的距离最近
            for (int i = 0; i < graph.verxs; i++) {//i结点表示被访问过的结点
                for (int j = 0; j < graph.verxs; j++) {//j结点表示没有被访问的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight（寻找已经访问过的结点和未访问过的结点间的权值最小的边
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到最小边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">权值：" + minWeight);
            //将当前这个结点标记为已经访问
            visited[h2] = 1;
            //minWeight重置为最大值10000
            minWeight = 10000;
        }
    }
}
class MGraph {
    int verxs;//表示图的结点个数
    char[] data;//存放结点数据
    int[][] weight;//存放边，邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}