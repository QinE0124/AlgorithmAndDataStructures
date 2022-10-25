package daily_practice;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * Given the array prices where prices[i] is the price of the ith item in a shop.
 * There is a special discount for items in the shop, if you buy the ith item,
 * then you will receive a discount equivalent to prices[j] where j is the minimum index such that j > i
 * and prices[j] <= prices[i], otherwise, you will not receive any discount at all.
 *
 * Return an array where the ith element is the final price you will pay for the ith item
 * of the shop considering the special discount.
 *
 * 单调栈
 *
 * @author QinE
 * @create 2022-09-01 18:12
 */
public class FinalPrices {

    public int[] finalPrices(int[] prices) {

        int[] ans = new int[prices.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        //倒着压入栈中
        for (int i = prices.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i])
                stack.pop();

            ans[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);

        }

        return ans;
    }

    @Test
    public void test() {
        finalPrices(new int[]{8, 4, 6, 2, 3});
    }
}
