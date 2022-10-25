package kurskal;

import java.util.Arrays;

/**
 * @author QinE
 * @create 2021-08-28 14:21
 */
public class KruskalCase {

    private  int edgeNum;//边的个数
    private int[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    //使用INF表示两个顶点不能连通
    private static final int INF = Integer.MIN_VALUE;

    public static void main(String[] args) {
//        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
//        int matrix[][] = {
//                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
//                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
//                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
//                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
//                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
//                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
//                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
//                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        int[] vertexs = new int[]{1, 2, 3, 4, 5};
        int[][] matrix = new int[][]{
                {0, 5, INF, INF, INF},
                {5, 0, 5, 12, 15},
                {INF, 5, 0, 17, 6},
                {INF, 12, 17, 0, INF},
                {INF, 15, 6, INF, 0}};

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        kruskalCase.kruskal();
    }

    public KruskalCase(int[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;

        //初始化顶点，复制拷贝的方式
        this.vertexs = new int[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边，使用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        //统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%-12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 功能：对边进行排序处理，冒泡排序
     * @param edges 边的集合
     */
    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - i - 1; j++) {
              if (edges[j].weight > edges[j + 1].weight) {
                  EData temp = edges[j];
                  edges[j] = edges[j + 1];
                  edges[j + 1] = temp;
              }
            }
        }
    }

    /**
     *
     * @param ch 顶点的值
     * @return 返回ch顶点对应的下标，如果找不到，返回-1
     */
    private int getPosition(int ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 功能：获取图中边，放到EData[]数组中，后面需要遍历该数组
     * 通过matrix邻接矩阵来获取
     * EData[]形式  [['A','B', 12], ['B','F',7], .....]
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
         for (int i = 0; i < vertexs.length; i++) {
             for (int j = i + 1; j < vertexs.length; j++) {
                 if (matrix[i][j] != INF) {
                     edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j] );
                 }
             }
         }
         return edges;
    }

    /**
     * 功能：获取下标为i的顶点的终点，用于判断后面俩哪个顶点的终点是否相同
     * @param ends 记录各个顶点对应的终点是哪个，ends数组在遍历过程中逐步形成
     * @param i 表示传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNum];//用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[vertexs.length];

        //获取图中所有的边的集合
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + " 共"+ edges.length);

        //按照边的权值大小进行排序
        sortEdges(edges);

        //遍历edges数组，将边添加到最小生成树中时，判断准备加入的边是否形成了回路，如果没有，就加入rets，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);
            //获取到第i条边的di二个顶点（终点）
            int p2 = getPosition(edges[i].end);

            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //获取p2这个顶点在已有生成树中的终点
            int n = getEnd(ends, p2);

            //判断是否构成回路
            if (m != n) {
                ends[m] = n;//设置m在“已有最小生成树”中的终点
                rets[index++] = edges[i];//将边加入到rets数组
            }
        }
        //统计并打印“最小生成树”，输出rets
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }
}

//创建类EDate，它的对象实例表示一条边
class EData {
    int start;//边的一个点
    int end;//边的另外一个点
    int weight;//边的权值

    public EData(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData [<" + start + ", " + end + ">= " + weight + "]";
    }
}