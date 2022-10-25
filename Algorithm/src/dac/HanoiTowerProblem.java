package dac;

/**使用分治算法解决汉诺塔问题
 * @author Qine
 * @create 2021-07-30 21:13
 */
public class HanoiTowerProblem {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');

    }

    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第一个盘子从" + a + "->" + c);
        } else {
            //如果盘子多于一个，则看成两个盘，1、最下边的一个盘 2、上面所有的盘
            //1.先把最上面的所有盘A->B，移动过程会用到C
            hanoiTower(num - 1, a, c, b);
            //2.把最下边的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.把B塔的所有盘从B->C，移动过程使用A塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
