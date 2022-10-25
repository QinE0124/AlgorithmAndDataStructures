package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 区间调度算法
 * @author QinE
 * @create 2022-06-15 14:33
 */
public class IntervalSchedule {

    public static int intervalSchedule(int[][] ints) {
        if (ints.length == 0)
            return 0;
        //按end升序排序
        Arrays.sort(ints, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        //至少有一个区间不相交
        int count = 1;
        //排序后，第一个区间就是x
        int x_end = ints[0][1];
        for (int[] interval : ints) {
            int start = interval[0];
            if (start >= x_end) {
                //找到下个选择区间
                count++;
                x_end = interval[1];
            }
        }
        return count;
    }

    //无重叠区间问题：输入一个区间的集合，请计算，要使其中的区间互相不重叠，至少需要移除几个区间
    public static int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        return n - intervalSchedule(intervals);
    }

    //用最少的箭头射爆气球
    public static int findMinArrowShotts(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        //按照end升序排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int count = 1;
        int x_end = 0;
        for (int[] interval : intervals) {
            int start =  interval[0];
            //在intervalSchedule算法中，如果两个的区间边间触碰，不算重叠
            //而这个问题，如果箭头碰到气球的边界的也会爆炸，所以相当于区间的边界触碰也算重叠
            if (start > x_end) {
                count++;
                x_end = interval[1];
            }
        }

        return count;
    }
}
