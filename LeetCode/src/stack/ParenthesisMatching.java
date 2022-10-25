package stack;

import java.util.ArrayDeque;

/**
 * 多种括号匹配
 * @author QinE
 * @create 2022-06-15 15:05
 */
public class ParenthesisMatching {

    public static boolean isValid(String str) {
        ArrayDeque<Character> left = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                //左括号直接入栈
                left.push(c);
            }  else {
                //字符c是右括号
                if (!left.isEmpty() && leftOf(c) == left.peek())
                    left.pop();
                else
                    //和最近的左括号不匹配
                    return false;
            }
        }

        //是否所有的左括号都被匹配了
        return left.isEmpty();
    }

    //返回对应的左括号类型
    private static char leftOf(char c) {
        if (c == '}')
            return '{';
        if (c == ']')
            return '[';
        return '(';
    }
}
