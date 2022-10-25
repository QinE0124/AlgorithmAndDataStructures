package monotonous_stack;

import java.util.ArrayDeque;

/**
 * @author QinE
 * @create 2022-06-12 13:56
 */
public class DailyTemperatueres {

    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();//存放元素索引，而非元素
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i])
                stack.pop();
            ans[i] = stack.isEmpty() ? 0 : (stack.pop() - i);//得到索引间距
            stack.push(i);//加入索引，而非元素
        }

        return ans;
    }
}
