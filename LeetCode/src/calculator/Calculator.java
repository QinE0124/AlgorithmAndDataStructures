package calculator;

import java.util.*;

/**
 * 表达式求值算法（hard）
 * 实现计算器
 * 细节：遇到括号递归处理
 * 乘除法优于加减法得体现：乘除法可以和栈顶的数结合然后把结果加入栈；而加减法只能自己入栈
 * @author QinE
 * @create 2022-06-14 11:29
 */
public class Calculator {

    public static int calculator(String s) {
       List<Character> list = new LinkedList<>();
       for (char c : s.toCharArray())
           list.add(c);
       return helper(list);
    }

    private static int helper(List<Character> list) {

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        //记录表达式中的数字
        int num = 0;
        //记录表达式中的符号
        char sign = '+';
        while (list.size() > 0) {
            char c = list.remove(0);
            //遇到左括号，进入递归
            if (c == '(')
                helper(list);
            //如果是数字，连续读出来
            if (Character.isDigit(c))
                num = 10 * num + (c - '0');
            //如果不是数字，就是遇到了下个符号
            //之前的数字和符号都要存入栈中
            if ((!Character.isDigit(c) && c != ' ') || list.size() == 0) {//处理空白符号
                int pre = 0;
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        //拿出前一个数字做对应计算
                        pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre / num);
                        break;
                }
                //更新符号为当前符号看，并将数字
                // 清零
                sign = c;
                num = 0;
            }

            //遇到右括号返回递归结果集
            if (c == ')')
                break;
        }

        //将栈中所有结果求和就是答案
        int res = 0;
        while (!stack.isEmpty())
            res += stack.pop();
        return res;
    }
}
