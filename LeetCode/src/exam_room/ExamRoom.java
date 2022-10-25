package exam_room;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 构造数据结构
 * @author QinE
 * @create 2022-06-16 10:43
 */
public class ExamRoom {

    //将端点p映射到以p为左端点的线段
    private Map<Integer, int[]> startMap;
    //将端点p映射到以p为右端点的线段
    private Map<Integer, int[]> endMap;
    //根据线段长度从小到大存放所有线段
    private TreeSet<int[]> pq;
    private int N;

    public ExamRoom(int N) {
        this.N = N;
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        pq = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //算出两个线段的长度
                int disA = distance(o1);
                int disB = distance(o2);
                //如果长度相同，就比较索引
                if (disA == disB)
                    return o2[0] - o1[0];
                //长度更大的排在后面
                return disA - disB;
            }
        });
        //在有序集合中先放一个虚拟线段
        addInterval(new int[]{-1, N});
    }

    public int seat() {
        //从有序集合拿出最长的线段
        int[] longest = pq.last();
        int x = longest[0];
        int y = longest[1];
        int seat;
        if (x == -1) {
            //情况1，最左边没人的话，坐最左边
            seat = 0;
        } else if (y == N) {
            //情况2:，最右边没人的话，做最右边
            seat = N - 1;
        } else {
            //情况3，不是边界的话，坐最右边
            seat = x + (y - x) / 2;
        }

        //将最长的线段划分成两段
        int[] left = new int[]{x, seat};
        int[] right = new int[]{seat, y};
        removeInterval(longest);
        addInterval(left);
        addInterval(right);
        return seat;
    }

    public void leave(int p) {
        //将p左右的线段找出来
        int[] right = startMap.get(p);
        int[] left = endMap.get(p);
        //将两条线段合并成一条线段
        int[] merged = new int[]{left[0], right[1]};
        //删除旧线段，插入新线段
        removeInterval(left);
        removeInterval(right);
        addInterval(merged);
    }

    //去除线段
    private void removeInterval(int[] interval) {
        pq.remove(interval);
        startMap.remove(interval[0]);
        endMap.remove(interval[1]);
    }

    //增加线段
    private void addInterval(int[] interval) {
        pq.add(interval);
        startMap.put(interval[0], interval);
        endMap.put(interval[1], interval);
    }

    //不要简单计算线段的长度，而要计算改线段两个端点间的长度
    private int distance(int[] interval) {
        int x = interval[0];
        int y = interval[1];
        if (x == -1)
            return y;
        if (y == N)
            return N - 1 - x;
        //中点和端点之间的长度
        return (y - x) / 2;
    }
}
