import java.util.Scanner;

/**
 * 判断闰年
 *
 * @author QinE
 * @create 2021-11-11 17:25
 */
public class ToDetermineLeapYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
