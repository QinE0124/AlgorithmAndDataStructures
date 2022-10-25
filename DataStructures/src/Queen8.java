/**
 * @author Qine
 * @create 2021-07-15 20:43
 */
public class Queen8 {
    //定义一个max表示共有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果
    int[] array = new int[max];
    int count;
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println(queen8.count);
    }

    //编写一个方法，放置第n个皇后
    //特别注意：check每一次递归时，进入到check中都有for循环，因此会有回溯
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先将当前这个皇后n，放到该行的第一列
            array[n] = i;
            //判断当前放置第n个皇后到第i列时，是否冲突
            if (judge(n)) {
                //接着放第n + 1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array[n] = i;即将第n个皇后，放置在本行后移的一个位置

        }
    }

    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //说明
            //1.array[i] == array[n]判断第n个皇后是否和前面的n - 1个皇后在一列
            //2.Math.abs(n - i) == Math.abs(array[n] - array[i]表示判断第n个皇后和第i个皇后是否在同一斜线
            //3.判断是否在同一行，没有必要，n每次都在递增
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，将皇后摆放的位置输出
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");

        }
        System.out.println();
        count++;

    }
}
