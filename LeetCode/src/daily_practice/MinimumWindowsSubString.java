package daily_practice;

import java.util.HashMap;

/**
 * 最小覆盖子串
 * @author QinE
 * @create 2022-05-06 9:08
 */
public class MinimumWindowsSubString {

    public static void main(String[] args) {
        String s = "AA";
        String t = "AA";
        String s1 = minWindows(s, t);
        System.out.println(s1);
    }

    public static String minWindows(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        int start = 0, minLen = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int match = 0;
        for (char c : t.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);
        while (r < s.length()) {
            char c1 = s.charAt(r);
            r++;
            if (need.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if (window.get(c1).equals(need.get(c1)))
                    match++;
            }

            while (match == need.size()) {
                if (r - l < minLen) {
                    minLen = r - l;
                    start = l;
                }

                char c2 = s.charAt(l);
                l++;
                if (need.containsKey(c2)) {
                    if (window.get(c2).equals(need.get(c2)))
                        match--;
                    window.put(c2, window.getOrDefault(c2, 0) - 1);
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
