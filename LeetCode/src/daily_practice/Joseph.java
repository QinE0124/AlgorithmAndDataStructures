package daily_practice;

/**
 * 约瑟夫问题
 * @author QinE
 * @create 2022-05-04 13:36
 */
public class Joseph {

    public static void main(String[] args) {

//        System.out.println(new Joseph().findTheWinner(6, 5));
    }

    //方法一：模拟+队列
//    public int findTheWinner(int n, int k) {
//        ArrayDeque<Integer> queue = new ArrayDeque<>();
//        for (int i = 1; i <= n; i++) {
//            queue.offer(i);
//        }
//        while (queue.size() > 1) {
//            for (int i = 1; i < k; i++) {
//                queue.offer(Objects.requireNonNull(queue.poll()));
//            }
//            queue.poll();
//        }
//        return queue.peek();
//    }

    //方法二：数学+递归
//    public int findTheWinner(int n, int k) {
//
//    }

    //方法三：数学＋迭代
    public int findTheWinner(int n, int k) {
        int pos = 0;
        for (int i = 2; i <= n; i++)
            pos = (pos + k) % i;
        return pos + 1;
    }
}
