package daily_practice;

/**
 * 数组嵌套
 * @author QinE
 * @create 2022-07-17 20:15
 */
public class ArrayNesting {

    public static void main(String[] args) {
        int[] A = {5,4,0,3,1,6,2};
        System.out.println(arrayNesting(A));
    }

    //方法一：有向图，由i指向nums[i]，所以每个结点的入度和出度都为1
    //遍历数组
//    public static int arrayNesting(int[] nums) {
//
//        int ans = 0;
//        boolean[] vis = new boolean[nums.length];
//
//        for (int i = 0; i < nums.length; i++) {
//            int cnt = 0;
//            while (!vis[i]) {
//                vis[i] = true;
//                i = nums[i];
//                cnt++;
//            }
//
//            ans = Math.max(ans, cnt);
//        }
//        return ans;
//    }

    //方法二：原地标记
    //利用数组元素在0-N-1,这一条件可以省略vis数组，改为标记nums[i]=N来实现和vis数组相同的效果
    public static int arrayNesting(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            while (nums[i] != n) {
                int num = nums[i];
                nums[i] = n;
                i = num;
                cnt++;
            }

            ans = Math.max(ans, cnt);
        }

        return ans;
    }
}
