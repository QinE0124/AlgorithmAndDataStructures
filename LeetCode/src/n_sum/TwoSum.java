package n_sum;

import java.util.HashMap;

/**
 * @author QinE
 * @create 2022-06-13 19:58
 */
public class TwoSum {


    static HashMap<Integer, Integer> freq = new HashMap<>();

    //向数据结构中添加一个数num
    public static void add(int num) {
        //记录num出现的次数
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    //寻找当前数据结构中是否存在两个数的和为value
    public static boolean find(int value) {
        for (int key : freq.keySet()) {
            int other = value - key;
            //情况一
            if (other == key && freq.get(key) > 1)
                return true;
            //情况二
            if (other != key && freq.containsKey(other))
                return true;
        }

        return false;
    }
}
