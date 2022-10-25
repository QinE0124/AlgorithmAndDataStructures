import java.util.Arrays;
import java.util.Scanner;

/**
 * 解决安慰奶牛（克鲁斯卡尔算法）
 *
 * @author QinE
 * @create 2021-11-14 16:19
 */
public class ComfortCow {

    public static int[] c = new int[10000];//存储每个牧场里谈话时间
    public static int[] ends = new int[10000];//存储每个顶点对应的终点
    public static Edge[] e = new Edge[100000];
    public static int n;
    public static int m;
    public static int min = 1000;//最小谈话时间

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        //输入N个奶牛需要的谈话时间
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt();
            if (c[i] < min) {//寻找睡觉的地方
                min = c[i];
            }
        }

        //输人P条道路所需要的行走时间
        for (int i = 0; i < m; i++) {
            e[i] = new Edge();
            e[i].s = sc.nextInt() - 1;
            e[i].e = sc.nextInt() - 1;
            //此处的l需要考虑所有的时间来回的路程和两处的谈话时间之和
            e[i].l = sc.nextInt() * 2 + c[e[i].s] + c[e[i].e];
        }

        System.out.println(min + kruskal());

    }

    //因为数组没有填充完成，使用此方法会造成空指针
   /* public static void sort(Edge[] e) {
        for (int i = 0; i < e.length; i++) {
            for (int j = 0; j < e.length - i - 1; j++) {
                if (e[j].l > e[j + 1].l) {
                    Edge temp = e[j];
                    e[j] = e[j + 1];
                    e[j + 1] = temp;
                }

            }

        }
    }*/

    /**
     * 获取顶点所对应的终点
     * @param k 需要求终点的顶点
     * @return 返回顶点对应的终点
     */
    public static int getEnd(int k) {
        while (ends[k] != 0) {
            k = ends[k];
        }
        return k;
    }

    public static int kruskal() {
        ends = new int[10000];//存储每个顶点对应的终点
        int sumWeight = 0;
        Arrays.sort(e, 0, m);
        for (int i = 0; i < m; i++) {
            int des1 = getEnd(e[i].s);
            int des2 = getEnd(e[i].e);
            //判断是否形成回路
            if (des1 != des2) {//没有形成回路
                ends[des1] = des2;
                sumWeight += e[i].l;
            }

        }
        return sumWeight;
    }




}

//将边封装成一个类，并进行排序，每个实例对象表示一条边。
class Edge implements Comparable<Edge> {
    int s;
    int e;
    int l;

    public Edge() {}

    public Edge(int s, int e, int l) {
        this.s  = s;
        this.e = e;
        this.l = l;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.l > o.l) {
            return 1;
        } else if (this.l < o.l) {
            return -1;
        }
        return 0;
    }


}