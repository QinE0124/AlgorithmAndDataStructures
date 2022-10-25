package dp;

/**
 *打印最小编辑距离的具体操作
 *
 * @author QinE
 * @create 2022-06-03 16:51
 */
public class PrintMinDistatnce {

    public static int minDistance(String s1, String s2) {
        //修改代码，将int[][] dp 升级成 Node[][] dp
         int m = s1.length();
         int n = s2.length();
        Node[][] dp = new Node[m + 1][n + 1];
        //base case
        for (int i = 1; i <= m; i++)
            //s1转换为s2需要删除一个字符
            dp[i][0] = new Node(i, 2);
        for (int j = 1; j <= n; j++)
            //s1转换为s2需要添加一个字符
            dp[0][j] = new Node(j, 1);

        //状态转移方程
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    //如果两个字符相同，则skip
                    Node node = dp[i - 1][j - 1];
                    dp[i][j] = new Node(node.val, 0);
                } else {
                    //否则，记录代价最小的操作
                    dp[i][j] = minNode(
                            dp[i - 1][j],
                            dp[i][j - 1],
                            dp[i - 1][j - 1]
                    );

                    //将编辑距离+1
                    dp[i][j].val++;
                }

            }
        }

        printResult(dp, s1, s2);
        return dp[m][n].val;
    }

    //计算delete, insert, replace中代价最小的操作
    private static Node minNode(Node a, Node b, Node c) {
        Node res = new Node(a.val, 2);

        if (res.val > b.val) {
            res.val = b.val;
            res.choice = 1;
        }

        if (res.val > c.val) {
            res.val = b.val;
            res.choice = 3;
        }

        return res;
    }

    //printResult()反推结果并把具体的操作打印出来
    private static void printResult(Node[][] dp, String s1, String s2) {
        int rows = dp.length;
        int cols = dp[0].length;
        int i = rows - 1, j = cols - 1;
        System.out.println("Change s1=" + s1 + "to s2=" + s2);
        while (i != 0 && j != 0) {
            char c1= s1.charAt(i - 1);
            char c2 = s2.charAt(j - 1);
            int choice = dp[i][j].choice;
            System.out.print("s1[" + (i - 1) + "]:");
            switch (choice) {
                case 0:
                    //skip, 两个指针同时前进
                    System.out.println("skip '" + c1 + "'");
                    i--;
                    j--;
                    break;
                case 1:
                    //将s2[j]插入s1[i]，s2指针前进
                    System.out.println("insert '" + c2 + "'");
                    j--;
                    break;
                case 2:
                    //将s1[i]删除,s1指针前进
                    System.out.println("delete '" + c1 + "'");
                    i--;
                    break;
                case 3:
                    //将s1[i]替换成s2[j],则两个指针同时前进
                    System.out.println(
                            "replace '" + c1 + "'" + "with '" + c2 + "'");
                    i--;
                    j--;
                    break;

            }
        }

        //如果s1还没有走完，则剩下的都是需要删除的
        while (i > 0) {
            System.out.print("s1[" + (i - 1) + "]:");
            System.out.println("delete '" + s1.charAt(i - 1) + "'");
            i--;
        }

        //如果s2还没有走完，则剩下的全部插入到s1
        while (j > 0) {
            System.out.print("s1[0]:" );
            System.out.println("insert '" + s2.charAt(j - 1) + "'");
            j--;
        }

    }
}


class Node {
    int val;//val数值就是之前的dp数组的数值
    int choice;//具体操作
    //0 skip
    //1 insert
    //2 delete
    //3 replace

    Node(int val, int choice) {
        this.val = val;
        this.choice = choice;
    }

}