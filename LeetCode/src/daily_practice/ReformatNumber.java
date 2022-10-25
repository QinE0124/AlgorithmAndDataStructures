package daily_practice;

/**
 * @author QinE
 * @create 2022-10-01 20:24
 */
public class ReformatNumber {

    public String reformatNumber(String number) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : number.toCharArray()) {
            if (Character.isDigit(c))
                stringBuilder.append(c);
        }

        int idx = 0, len = stringBuilder.length();
        StringBuilder ans = new StringBuilder();
        while (len > 0) {
            if (len > 4) {
                ans.append(stringBuilder.substring(idx, idx + 3) + "-");
                idx += 3;
                len -= 3;
            } else {
                if (len == 4) {
                    ans.append(stringBuilder.substring(idx, idx + 2) + "-" + stringBuilder.substring(idx + 2, idx + 4));
                } else {
                    ans.append(stringBuilder.substring(idx, idx + len));
                }

                break;
            }

        }

        return ans.toString();
    }
}
