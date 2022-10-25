import java.util.Scanner;

/**
 * @author QinE
 * @create 2021-11-11 16:40
 */
public class SequenceSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        long sum = (1 + num) * num / 2;
        System.out.println(sum);

    }

}
