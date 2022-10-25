package daily_practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A sentence is a list of tokens separated by a single
 * space with no leading or trailing spaces. Every token
 * is either a positive number consisting of digits 0-9 with no leading zeros,
 * or a word consisting of lowercase English letters
 * @author QinE
 * @create 2022-04-10 18:57
 */
public class CheckAscending {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine();
        boolean res = areNumbersAscending(s);
        System.out.println(res);
    }

    public static boolean areNumbersAscending(String s) {
        String[] tokens = s.split(" ");
        int l = -1;
        for (String v : tokens) {
            if (v.charAt(0) < 'A') {
                int num = Integer.parseInt(v);
                if (num <= l)
                    return false;
                l = num;
            }
        }
        return true;
    }
}
