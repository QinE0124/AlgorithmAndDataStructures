package monotonous_stack;

import java.util.ArrayDeque;

/**
 *单调解题模板
 * 下一个更大元素I
 * @author QinE
 * @create 2022-06-12 13:31
 */
public class NextGreateElement {

    public int[] nextGreateElementI(int[] nums) {
        int[] ans = new int[nums.length];//存放答案的数组
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            //倒着往栈里放
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();//矮个子出列
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();//这个元素身后的第一个高个
            stack.push(nums[i]);
        }
        return ans;
    }

    //处理环形数组
    public int[] nextGreateElementII(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n])
                stack.pop();
            ans[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i % n);
        }
        return ans;
    }
}
