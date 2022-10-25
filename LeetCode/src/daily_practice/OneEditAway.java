package daily_practice;

/**
 * 一次编辑
 * @author QinE
 * @create 2022-05-13 10:52
 */
public class OneEditAway {

    public static void main(String[] args) {
        boolean b = oneEditAway("ab", "bc");
        System.out.println(b);
    }

    //双指针
    public static boolean oneEditAway(String first, String second) {
        int n = first.length();
        int m = second.length();
        if (n - m == 1)
            return oneInsert(first, second);
        else if (m - n == 1){
                return oneInsert(second, first);
        } else if (m == n) {
            boolean foundDifference = false;
            for (int i = 0; i < n; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (!foundDifference)
                        foundDifference = true;
                    else
                        return false;
                }
            }
            return true;
        }

        return false;
    }

    private static boolean oneInsert(String longer, String shorter) {
        int n = longer.length();
        int m = shorter.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (longer.charAt(i) == shorter.charAt(j))
                j++;
            i++;
            if (i - j > 1)
                return false;
        }

        return true;
    }
}
