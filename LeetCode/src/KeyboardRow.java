import java.util.ArrayList;

/**
 *
 * 键盘行
 * Given an array of strings words, return the words that can be typed using letters
 * of the alphabet on only one row of American keyboard like the image below.
 *
 * In the American keyboard:
 *
 * the first row consists of the characters "qwertyuiop",
 * the second row consists of the characters "asdfghjkl", and
 * the third row consists of the characters "zxcvbnm".
 *
 * @author QinE
 * @create 2021-10-31 16:46
 */
public class KeyboardRow {

    public static void main(String[] args) {
        String[] words = {"Hello","Alaska","Dad","Peace"};
        KeyboardRow keyboardRow = new KeyboardRow();


    }

    /**
     * 返回只存在同一键盘行的单词数组
     *
     * @param words
     * @return
     */
    public String[] findWords (String[] words) {
        String lineId = "12210111011122000010020202";
        ArrayList<String> list = new ArrayList<>();

        for (String word : words) {

            boolean isValId =  true;
            char lineNum = lineId.charAt(Character.toLowerCase(word.charAt(0)) - 'a');

            for (int i = 0; i < word.length(); i++) {

                if (lineId.charAt(Character.toLowerCase(word.charAt(i)) - 'a') != lineNum) {
                    isValId = false;
                    break;
                }
            }

            if (isValId) {
                list.add(word);
            }

        }

        String[] subWords = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            subWords[i] = list.get(i);
        }
        return subWords;
    }
}
