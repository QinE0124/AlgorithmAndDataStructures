package daily_practice;

/**
 * @author QinE
 * @create 2022-09-07 19:41
 */
public class ReOrderSpaces {

    public String reOrderSpaces(String text) {
        int length = text.length();
        String[] words = text.trim().split("\\s+");
        int cntSpace = length;
        for (String word : words) {
            cntSpace -= word.length();
        }

        StringBuilder sb = new StringBuilder();
        if (words.length == 1) {
            sb.append(words[0]);
            while (cntSpace --> 0)
                sb.append(" ");
            return sb.toString();
        }

        int preSpace = cntSpace / (words.length - 1);
        int restSpace = cntSpace % (words.length - 1);
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                for (int j = 0; j < preSpace; j++)
                    sb.append(" ");
            }
            sb.append(words[i]);
        }

        for (int i = 0; i < restSpace; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
