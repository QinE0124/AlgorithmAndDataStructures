package math;

import java.util.LinkedList;

/**
 * @author QinE
 * @create 2022-06-09 21:17
 */
public class SuperPow {

    static int base = 1337;

    public static int superPow(int a, LinkedList<Integer> b) {
        //递归的base case
        if (b.isEmpty())
            return 1;
        //取出最后一个数
        int last = b.removeLast();
        //将原问题化简，缩小规模看，递归求解
        int part_1 = myPow(a, last);
        int part_2 = myPow(superPow(a, b), 10);
        //合并出结果
        return part_1 * part_2;
    }

//    private static int myPow(int a, int k) {
//        //对因子求模
//        a %= base;
//        int res = 1;
//        for (int i = 0; i < k; i++)
//            res = res * a % base;
//
//        return res;
//
//    }

    //快速幂优化
    private static int myPow(int a, int k) {

        if (k == 0)
            return 1;

        a %= base;

        if ((k & 1) == 1) {
            return (a * myPow(a, k - 1)) % base;
        } else {
            int sub = myPow(a, k / 2);
            return (sub * sub) % base;
        }
    }
}
