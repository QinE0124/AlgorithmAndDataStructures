package daily_practice;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 * @author QinE
 * @create 2022-05-05 14:49
 */
public class SubString {

    //滑动窗口
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int l = 0, r = 0;
        int ret = 0;
        HashMap<Character, Integer> windows = new HashMap<>();
        while (r < n) {
            char c1 = s.charAt(r);
            windows.put(c1, windows.getOrDefault(c1, 0) + 1);
            r++;
            while (windows.get(c1) > 1) {
                windows.replace(s.charAt(l), windows.get(s.charAt(l)) - 1);
                l++;
            }
            ret = Math.max(ret, r - l);
        }
        return ret;
    }

}
