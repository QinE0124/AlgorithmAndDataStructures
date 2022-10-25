package daily_practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典序排数
 * @author QinE
 * @create 2022-04-18 16:49
 */
public class LexicalOrder {

    public static void main(String[] args) {

        List<Integer> list = lexicalOrder(100);
        list.forEach(System.out::println);
    }
    public static List<Integer> lexicalOrder(int n) {
        int num = 1;
        ArrayList<Integer> list = new ArrayList<>();
        int i = n;
        while (i --> 0) {
            list.add(num);
            if (num * 10 <= n)
                num *= 10;
            else {
                while (num % 10 == 9 || num + 1 > n) {//num % 10 == 9说明末尾数位已经搜索完，回溯到上一位
                    num /= 10;
                }
                num++;
            }
        }
        return list;
    }
}
