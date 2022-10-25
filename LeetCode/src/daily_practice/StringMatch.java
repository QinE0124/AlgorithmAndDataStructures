package daily_practice;

/**
 * 增减字符串匹配
 * @author QinE
 * @create 2022-05-09 9:15
 */
public class StringMatch {

    public static void main(String[] args) {
        String s = "IDID";
        int[] ints = diStringMatch(s);
        for (int i : ints)
            System.out.print(i);
    }
    //贪心+双指针
    public static int[] diStringMatch(String s) {
        int n = s.length();
        int l = 0, r = n;
        int[] ans = new int[n + 1];
        for (int i = 0; i < s.length(); i++) {
            ans[i] = s.charAt(i) == 'I' ? l++ : r--;
        }
        ans[n] = l;
        return ans;
    }
}
