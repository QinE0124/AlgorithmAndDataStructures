import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Qine
 * @create 2021-07-14 18:53
 */
public class PolandNotaiton {
    public static void main(String[] args) {
        //说明
        //1. 1+((2+3)×4)-5 => 转成  1 2 3 + 4 × + 5 –
        //2. 因为直接对str 进行操作，不方便，因此 先将  "1+((2+3)×4)-5" =》 中缀的表达式对应的List
        //   即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的List => 后缀表达式对应的List
        //   即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]  =》 ArrayList [1,2,3,+,4,*,+,5,–]

        String expression = "1 + ( ( 2 + 3 ) × 4 )-5";

        //先定义一个逆波兰表达式
        //(30+4)×5-6  => 30 4 + 5 × 6 - => 164
        // 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        //测试
        //说明为了方便，逆波兰表达式 的数字和符号使用空格隔开
        //String suffixExpression = "30 4 + 5 * 6 -";
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
//        List<String> listString = getListString(suffixExpression);
////        System.out.println(listString);
//        System.out.println(calculate(listString));
    }


    //将得到的中缀表达式对应的list转化为后缀表达式对应的list
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //预定义两个栈
        //因为s2这个栈，再转换过程中，没有pop操作，而且后面还需要逆序打印，所以用集合List代替
        Stack<String> s1 = new Stack<>();//符号栈
        ArrayList<String> s2 = new ArrayList<>();//储存中间结果的list2

        //遍历
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止
                while (!s1.peek().equals(")")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将(弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶运算符弹出并加入到s2中，再次与s1中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;

    }

    //将中缀表达式转换成对应的list
    public static  List<String> toInfixExpressionList(String s) {
        ArrayList<String> ls = new ArrayList<>();
        int i = 0;//指针，用来遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c;//每遍历一个字符，就放入到c
        do {
            //如果c是一个非数字，则加入s
            if ((c = s.charAt(i)) < 48 || (c=s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                //如果是一个数，需要考虑多位数
                str = "";//先将str 置成"" '0'[48]->'9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;//返回
    }
  //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
   public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
      String[] split = suffixExpression.split(" ");
      List<String> list = Arrays.asList(split);
      return list;
  }

  //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls) {
        //只需创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式来取数
            if (item.matches("\\d+")) {//匹配的是多位数
                stack.push(item);
            } else {
                //pop出两个数再计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(item)) {
                    res = num1 + num2;
                } else if ("-".equals(item)) {
                    res = num1 - num2;
                } else if ("*".equals(item)) {
                    res = num1 * num2;
                } else if ("/".equals(item)){
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }


}

//编写一个类Operation可以返回一个运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int res = 0;
        switch (operation) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
        }

        return res;
    }
}