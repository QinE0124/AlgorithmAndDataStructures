package double_pointer;

/**
 * @author QinE
 * @create 2022-06-15 11:30
 */
public class Palindrome {

    public String longetPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            //寻找长度为奇数的回文子串
            String s1 = palindrome(s, i, i);
            //处理长度为偶数的回文子串
            String s2 = palindrome(s, i, i + 1);
            res = (res.length() > s1.length()) ? res : s1;
            res = (res.length() > s2.length()) ? res : s2;
        }

        return res;
    }

    private String palindrome(String s, int l, int r) {
        //防止索引越界
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        return s.substring(l + 1, r - l + 1);
    }
}
