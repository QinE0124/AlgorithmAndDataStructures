

/**
 * 克鲁斯卡尔(邻接矩阵)解决奶牛安慰
 *
 * @author QinE
 * @create 2021-11-11 17:59
 */
public class KruskalCase {

    private int edgeNum;//边的个数
    private int[] vertex;//顶点集
    private int[] values;//存储点值
    private int[][] matrix;//邻接矩阵
    //使用INF常量表示两点不联通
    private static final int INF = Integer.MAX_VALUE;


    public static void main(String[] args) {
        int[] vertex = new int[]{1, 2, 3, 4, 5};
        int[] values = new int[]{10, 10, 20, 6 , 30};
        /*
        1 2 5
        2 3 5
        2 4 12
        3 4 17
        2 5 15
        3 5 6
         */
        int[][] matrix = new int[][]{
                {0, 5, INF, INF, INF},
                {5, 0, 5, 12, 15},
                {INF, 5, 0, 17, 6},
                {INF, 12, 17, 0, INF},
                {INF, 15, 6, INF, 0}};

        KruskalCase kruskalCase = new KruskalCase(vertex, values, matrix);
//        kruskalCase.print();
        EData[] rets = kruskalCase.kruskal();
        System.out.println(kruskalCase.getSum(rets, values));

    }

    public KruskalCase(int[] vertex, int[] values, int[][] matrix) {
        //初始化顶点数，和边的个数
//        int vLen = vertex.length;
        //为了保护数据，使用复制拷贝
        /*
        this.vertex = new int[vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }

        this.values = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            this.values[i] = values[i];
        }

        this.matrix = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }*/
        //为了提升效率，直接引用地址值拷贝
        this.vertex = vertex;
        this.matrix = matrix;
        this.values = values;

        //统计边的个数
        for (int i = 0; i <matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }


    }

    /**
     * 求出结果
     * @param rets 最小生成树集合
     * @param values 每个顶点对应的值
     * @return 总和
     */
    public int getSum(EData[] rets, int[] values) {
        int sum = 0;
        for (int i = 0; i < rets.length; i++) {
            sum += rets[i].weight;
        }
        sum *= 2;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum;
    }

    /**
     * 功能：获取最小生成树集合
     * @return
     */
    public EData[] kruskal() {
        int index = 0;//保存索引
        int sum = 0;
        //用于保存已有最小生成树中每个顶点在最小生成树中的终点
        int[] ends = new int[vertex.length];
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[vertex.length - 1];
        //获取图中所有边的集合
        EData[] edges = getEdges();
//        System.out.println("图中边个数为" + edges.length);
        //按照边的权值大小进行排序(从小到大)
        sortEdges(edges);
        //遍历edges数组，将边添加到最小生成树中，判断是否形成了回路
        for (int i = 0; i < edges.length; i++) {
            //获取第i条边的第一个顶点下标
            int p1 = getPosition(edges[i].start);
            //获取第i条边的第二个顶点下标
            int p2 = getPosition(edges[i].end);
            //获取p1的终点
            int m = getEnd(ends, p1);
            //获取p2的终点
            int n = getEnd(ends, p2);
            //判断是否形成回路
            if (m != n) {
                ends[m] = n;//设置m在最小生成树中的终点为n
                rets[index++] = edges[i];//将此边加入到结果集中

            }

        }

        //统计并打印最小生成树
        System.out.println("最小生成树为：");
        for (int i = 0; i < rets.length; i++) {
            System.out.println(rets[i]);
        }
        return rets;

    }
   //打印邻接矩阵
    /*
    public void print() {
        System.out.println("输出邻接矩阵");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%-12d",matrix[i][j]);
            }
            System.out.println();
        }
    }*/

    /**
     *功能：获取顶点对应下标
     * @param point 顶点
     * @return 顶点对应下标,若找到则直接返回下标，否则返回-1
     */
    public int getPosition(int point) {
        for (int i = 0; i < vertex.length; i++) {
            if (point == vertex[i]) return i;
        }
        return -1;
    }

    /**
     * 功能：获取图中的边，放到数组中
     * @return
     */
    public EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertex[i], vertex[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    //对边利用权值进行排序
    public void sortEdges(EData[] edges) {
        //冒泡排序
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;

                }
            }
        }
    }

    /**
     * 功能：获取下标为i的顶点终点下标，用于判断两个顶点的终点是否相同
     *
     * @param ends 记录各个顶点对应的终点，ends数组在遍历过程中逐步形成
     * @param i 传入的顶点的下标
     * @return 返回下标为i的顶点的终点
     */
    public int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

//创建类，它的实例对象代表一条边
class EData{
    int start;
    int end;
    int weight;//两点距离，边的权值

    public EData(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public EData() {}

    @Override
    public String toString() {
            return "EData [<" + start + ", " + end + ">= " + weight + "]";
        }
}
