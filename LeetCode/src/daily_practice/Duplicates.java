package daily_practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 数组中重复的数据
 * @author QinE
 * @create 2022-05-08 14:27
 */
public class Duplicates {

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> ans = findDuplicates(nums);
        ans.forEach(System.out :: println);
    }

    //效率太低，自写
//    public static List<Integer> findDuplicates(int[] nums) {
//        ArrayList<Integer> res = new ArrayList<>();
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int data : nums) {
//            map.put(data, map.getOrDefault(data, 0) + 1);
//            if (map.get(data) == 2)
//                res.add(data);
//        }
//
//        return res;
//    }

    //方法一：将元素交换到对应的地方
//    public static List<Integer> findDuplicates(int[] nums) {
//      int n = nums.length;
//        ArrayList<Integer> ans = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//          while (nums[i] != nums[nums[i] - 1])
//              swap(nums, i, nums[i] - 1);
//      }
//
//      for (int i = 0; i < n; i++) {
//          if (nums[i] != i + 1)
//              ans.add(nums[i]);
//      }
//
//      return ans;
//    }
//
//    private static void swap(int[] nums, int index1, int index2) {
//        int temp = nums[index1];
//        nums[index1] = nums[index2];
//        nums[index2] = temp;
//    }

    //方法二：使用正负号做标记
    public static List<Integer> findDuplicates(int[] nums) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0 ; i < nums.length; i++) {
            int x = Math.abs(nums[i]);
            if (nums[x - 1] > 0)
                nums[x - 1] = -nums[x - 1];
            else
                ans.add(x);
        }

        return ans;
    }
}
