import org.junit.Test;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication,
 * division, and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 * Note: Assume we are dealing with an environment that could only store integers
 * within the 32-bit signed integer range: [−231, 231 − 1]. For this problem,
 * assume that your function returns 231 − 1 when the division result overflows.
 *
 * @author QinE
 * @create 2021-10-18 11:10
 */
public class Div {

    @Test
    public void Solution() {
        System.out.println(divide3(-7, 3));
    }

    /**
     * 因为将 -2147483648 转成正数会越界，但是将 2147483647 转成负数，则不会
     * 因此，将 a 和 b 都转成负数
     * 时间复杂度：O(n)，n 是最大值 2147483647 --> 10^10 --> 超时
     * @param a
     * @param b
     * @return
     */
    public int divide1(int a, int b) {
        // 32 位最大值：2^31 - 1 = 2147483647
        // 32 位最小值：-2^31 = -2147483648
        // -2147483648 / (-1) = 2147483648 > 2147483647 越界了

        //解决边界越界问题
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;

        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        if(a > 0) a = -a;
        if(b > 0) b = -b;
        int res = 0;
        //将a和b转为负数运算
        while (a <= b) {
            a -= b;
            res ++;
        }

        //因为不能使用乘法运算,所以使用三元运算符
        return sign == 1 ? res : -res;
    }

    //优化：降低时间复杂度
    // 时间复杂度：O(logn * logn)，n 是最大值 2147483647 --> 10^10
    public int divide2(int a, int b) {
        if (a == Integer.MIN_VALUE && b== -1)
            return Integer.MAX_VALUE;
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        int res = 0;
        while(a <= b) {
            int value = b;
            int k = 0;
            // 0xc0000000 是十进制 -2^30 的十六进制的表示
            // 判断 value >= 0xc0000000 的原因：保证 value + value 不会溢出
            // 可以这样判断的原因是：0xc0000000 是最小值 -2^31 的一半，
            // 而 a 的值不可能比 -2^31 还要小，所以 value 不可能比 0xc0000000 小
            // -2^31 / 2 = -2^30
            while (value >= 0xc0000000 && a <= value + value) {
                value++;
                k++;
            }

            a -= value;
            res += k;
        }

        return sign == 1 ? res : -res;
    }

    /**
     * 使用位运算优化
     * 时间复杂度：O(1)
     * @param a
     * @param b
     * @return
     */
    public int divide3(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1)
            return Integer.MAX_VALUE;

        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        a = Math.abs(a);
        b = Math.abs(b);
        int res = 0;

        for (int i = 31; i >= 0; i--) {
            //首先，右移的话，无论如何都不会越界
            // 其次，无符号右移的目的是：将 -2147483648 看成 2147483648
            // 这里不能是 (a >>> i) >= b 而应该是 (a >>> i) - b >= 0
            // 这个也是为了避免 b = -2147483648，如果 b = -2147483648
            // 那么 (a >>> i) >= b 永远为 true，但是 (a >>> i) - b >= 0 为 false
            if ((a >>> i) - b >= 0) {
                a -= b << i;
                res += 1 << i;
            }

        }
        return sign == 1 ? res : -res;
    }
}
