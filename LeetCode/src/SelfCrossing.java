/**
 * 路径交叉
 *
 *使用归纳法，归纳出所有路径相交的情况
 * 具体思路图解见《路径交叉.pptx》
 *
 * @author QinE
 * @create 2021-10-30 0:38
 */
public class SelfCrossing {

    public static void main(String[] args) {

//        int[] distance = {2,1,1,2};
//        int[] distance = {1,1,3,2,1,1};
        int[] distance = {1,1,2,2,3,3,3,3,1};

        SelfCrossing selfCrossing = new SelfCrossing();
        System.out.println(selfCrossing.isSelfCrossing(distance));

    }

    //处理路径相交的情况
    public boolean isSelfCrossing(int[] distance) {

        int len = distance.length;

        if (len < 3) {
            return false;
        }

        for (int i = 3; i < len; i++) {
            //处理第一种相交情况
            if (distance[i - 1] <= distance[i - 3] && distance[i] >= distance[i - 2]) {
                return true;
            }
            //处理第二种相交情况
            if ( i == 4 && distance[4] >= distance[2] - distance[0] && distance[2] > distance[0]
                    && distance[3] == distance[1]) {
                return true;
            }
            //处理第三种相交情况
//            if (i >= 5 && distance[i] >= distance[i - 2] - distance[i - 4] && distance[i - 1] < distance[i -3]
//                    && distance[i - 2] > distance[i - 4] && distance[i - 1] >= distance[i - 3] - distance[i - 5]
//                    && distance[i - 3] > distance[i - 5]) {
//                return true;
//            }
            if (i >= 5 && (distance[i - 3] - distance[i - 5] <= distance[i - 1]
                    && distance[i - 1] <= distance[i - 3]
                    && distance[i] >= distance[i - 2] - distance[i - 4]
                    && distance[i - 2] > distance[i - 4])) {
                return true;
            }

        }

        return false;

    }
}
