package daily_practice;

import org.junit.Test;

/**
 * @author QinE
 * @create 2022-09-13 18:33
 */
public class MaximumSwap {

    //贪心
    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxId = n - 1;
        int idx1 = -1, idx2 = -1;

        for (int i = n - 1; i >= 0; i--) {
            if (charArray[i] > charArray[maxId]) {
                maxId = i;
            } else if (charArray[i] < charArray[maxId]) {
                idx1 = i;
                idx2 = maxId;
            }
        }

        if (idx1 >= 0) {
            swap(charArray, idx1, idx2);
            return Integer.parseInt(new String(charArray));
        }

        return num;
    }

    private void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }

    @Test
    public void test() {
        maximumSwap(2736);
    }
}
