package daily_practice;

import java.util.Map;
import java.util.TreeMap;

/**
 * 自定义日期类（日程安排表）
 *
 * @author QinE
 * @create 2022-07-19 21:14
 */
public class Calendar {

    /*方法一：直接遍历
    List<int[]> booked;
    List<int[]> overlaps;

    public Calendar() {
        booked = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] arr : overlaps) {
            int l = arr[0];
            int r = arr[1];
            if (start < r && end < l) {
                return false;
            }
        }

        for (int[] arr : booked) {
            int l = arr[0];
            int r = arr[1];
            if (start < r && end < l)
                overlaps.add(new int[]{Math.max(l, start), Math.min(r, end)});
        }

        booked.add(new int[]{start, end});
        return true;
    }
    */

    /*方法二：差分数组
    TreeMap<Integer, Integer> cnt;
    public Calendar() {
       cnt = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        int ans = 0;
        int maxBook = 0;
        cnt.put(start, cnt.getOrDefault(start, 0) + 1);
        cnt.put(end, cnt.getOrDefault(end, 0) - 1);
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int freq = entry.getValue();
            maxBook += freq;
            ans = Math.max(ans, maxBook);
            if (maxBook > 2) {
                cnt.put(start, cnt.getOrDefault(start, 0) - 1);
                cnt.put(end, cnt.getOrDefault(end, 0) + 1);
                return false;
            }
        }


        return true;
    }*/

    /*方法三：线段树
     懒标记lazy标记区间[l,r]进行累加的次数。tree记录区间[l,r]最大值，每次动态更新线段树*/
    Map<Integer, int[]> tree;

    public Calendar() {
        tree = new TreeMap<>();
    }

//    public boolean book(int start, int end) {
//
//    }

    //操作线段树
    private void update(int start, int end, int val, int l, int  r, int idx) {
        if (start > r || end < l)
            return;
        tree.putIfAbsent(idx, new int[2]);
        if (start <= l && end > r) {
            tree.get(idx)[0] += val;
            tree.get(idx)[1] += val;
        }
    }
}
