import java.util.Scanner;

/**
 * 字母图形
 * @author QinE
 * @create 2021-11-17 22:52
 */
public class LettersGraphics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.print((char)(Math.abs(j - i) + 65));
            }
            System.out.println();
        }
    }
}
