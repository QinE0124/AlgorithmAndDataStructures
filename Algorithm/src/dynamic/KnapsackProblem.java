package dynamic;

/**动态规划算法解决01背包问题
 * @author Qine
 * @create 2021-07-30 22:02
 */
public class KnapsackProblem {
    public static void main(String[] args) {

        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000};
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组
        //v[i][j]表示在前i个物品中能装入容量为j的的背包中的最大价值
        int[][] v = new int[n + 1][m + 1];

        //为了记录放入商品的情况，初始化一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //根据前面得到的公式来动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[1].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] +v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        //将当前情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }

        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.err.print(v[i][j] + "\t");
            }
            System.err.println();
        }

        //输出最后放入的商品
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }

}
