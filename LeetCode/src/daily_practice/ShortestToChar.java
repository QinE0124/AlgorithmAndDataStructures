package daily_practice;

import org.junit.Test;

/**
 * 字符最短距离
 * @author QinE
 * @create 2022-04-19 8:28
 */
public class ShortestToChar {

    @Test
    public void test() {
        String s = "loveleetcode";
        char c = 'e';
        int[] ints = shortestToChar(s, c);
        for (int data : ints)
            System.out.print(data + " \t");

    }

    //二分
    public static int[] shortestToChar(String s, char c) {
        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                res[i] = 0;
                continue;
            }
            int l = i - 1;
            int r = i + 1;
            boolean lFlag = false;
            boolean rFlag = false;
            while (l >= 0) {
                if (s.charAt(l) == c) {
                    lFlag = true;
                    break;
                }
                l--;
            }
            while (r < s.length()) {
                if (s.charAt(r) == c) {
                    rFlag = true;
                    break;
                }
                r++;
            }

            if (lFlag && !rFlag)
                res[i] = Math.abs(i - l);
            else if (!lFlag && rFlag)
                res[i] = Math.abs(i - r);
            else {
                res[i] = Math.min(Math.abs(i - l), Math.abs(i - r));
            }


        }
        return res;
    }
}



