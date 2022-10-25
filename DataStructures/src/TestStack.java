import java.util.Stack;

/**
 * @author Qine
 * @create 2021-07-05 9:48
 */
//演示栈Stack的基本使用
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        //smith，Tom，jack
        while (stack.size() > 0) {
            System.out.println(stack.pop());//pop就是将栈顶的数据取出
        }
    }
}
