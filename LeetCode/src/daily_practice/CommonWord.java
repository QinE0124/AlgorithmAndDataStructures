package daily_practice;

import java.util.*;

/**
 * 最常见单词
 * 哈希表+计数
 * @author QinE
 * @create 2022-04-17 17:39
 */
public class CommonWord {

    public static void main(String[] args) {

    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        HashSet<String> set = new HashSet<>(Arrays.asList(banned));
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        int len = paragraph.length();
        int maxFrequency = 0;
        for (int i = 0; i <= len; i++) {
            if (i < len && Character.isLetter(paragraph.charAt(i))) {//判断此字符是否为字母
                builder.append(Character.toLowerCase(paragraph.charAt(i)));
            } else if (builder.length() > 0) {
                String word = builder.toString();
                if (!set.contains(word)) {
                    int frequency = map.getOrDefault(word, 0) + 1;
                    map.put(word, frequency);
                    maxFrequency = Math.max(maxFrequency, frequency);
                }
                builder.setLength(0);
            }
        }

        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        String mostCommonWord = "";
        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getValue() == maxFrequency) {
                mostCommonWord = entry.getKey();
                break;
            }
        }

        return mostCommonWord;
    }
}
