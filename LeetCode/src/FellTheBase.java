import java.util.Scanner;

/**
 * 进制转换
 * 将16进制转换为8进制
 *
 * 先把16进制转换为2进制
 * 再将2进制转换为8进制
 * @author QinE
 * @create 2021-11-17 23:31
 */
public class FellTheBase {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        sc.close();

        for (int i = 0; i < n; i++) {
            StringBuilder str = hexToBinary(arr[i]);
            //因为八进制是3个二进制一组，所以对数组长度取余，不能整除的在前面补零
            if (str.length() % 3 == 1) str.insert(0, "00");
            if (str.length() % 3 == 2) str.insert(0, "0");
//            if (str.length() % 3 == 1) str = "00" + str;
//            if (str.length() % 3 == 2) str += "0" + str;
            StringBuilder builder = binaryToOctal(str);
            System.out.println(builder);
        }

    }

    /**
     * 十六进制转换为2进制
     * @param strHex
     * @return
     */
    private static StringBuilder hexToBinary(String strHex) {
        int length = strHex.length();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            if (strHex.charAt(i) == '0') {
                builder.append("0000");
            } else if (strHex.charAt(i) == '1') {
                builder.append("0001");
            } else if (strHex.charAt(i) == '2') {
                builder.append("0010");
            } else if (strHex.charAt(i) == '3') {
                builder.append("0011");
            } else if (strHex.charAt(i) == '4') {
                builder.append("0100");
            } else if (strHex.charAt(i) == '5') {
                builder.append("0101");
            } else if (strHex.charAt(i) == '6') {
                builder.append("0110");
            } else if (strHex.charAt(i) == '7') {
                builder.append("0111");
            } else if (strHex.charAt(i) == '8') {
                builder.append("1000");
            } else if (strHex.charAt(i) == '9') {
                builder.append("1001");
            } else if (strHex.charAt(i) == 'A') {
                builder.append("1010");
            } else if (strHex.charAt(i) == 'B') {
                builder.append("1011");
            } else if (strHex.charAt(i) == 'C') {
                builder.append("1100");
            } else if (strHex.charAt(i) == 'D') {
                builder.append("1101");
            } else if (strHex.charAt(i) == 'E') {
                builder.append("1110");
            } else if (strHex.charAt(i) == 'F') {
                builder.append("1111");
            }
        }

        return builder;
    }

    /**
     * 二进制转换为八进制
     * @param strBinary
     * @return
     */
    private static StringBuilder binaryToOctal(StringBuilder strBinary) {
        int length = strBinary.length();
        StringBuilder builder = new StringBuilder(length);
        int k = 0;
        if (strBinary.substring(0, 3).equals("000")) {
            k = 3;
        } else {
            k = 0;
        }
        for (int i = k; i <= length - 3; i += 3) {
            if (strBinary.substring(i, i + 3).equals("000")) {
                builder.append("0");
            } else if (strBinary.substring(i, i + 3).equals("001")) {
                builder.append("1");
            } else if (strBinary.substring(i, i + 3).equals("010")) {
                builder.append("2");
            } else if (strBinary.substring(i, i + 3).equals("011")) {
                builder.append("3");
            } else if (strBinary.substring(i, i + 3).equals("100")) {
                builder.append("4");
            } else if (strBinary.substring(i, i + 3).equals("101")) {
                builder.append("5");
            } else if (strBinary.substring(i, i + 3).equals("110")) {
                builder.append("6");
            } else if (strBinary.substring(i, i + 3).equals("111")) {
                builder.append("7");
            }

        }
        return builder;
    }

}
