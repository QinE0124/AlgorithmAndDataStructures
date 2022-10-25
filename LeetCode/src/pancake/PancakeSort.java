package pancake;

import java.util.LinkedList;
import java.util.List;

/**
 * 烧饼排序：假设盘子上有n块大小不一的烧饼，如何用一把锅铲进行若干此翻转，让这些烧饼大小有序排列
 * 数组的索引以0开始，而需要的结果要以1开始
 * @author QinE
 * @create 2022-06-14 14:27
 */
public class PancakeSort {

    List<Integer> res = new LinkedList<>();

    public List<Integer> pancakeSort(int[] cakes) {
        sort(cakes, cakes.length - 1);
        return res;
    }

    //将前n个烧饼排序
    private void sort(int[] cakes, int n) {
        //base case
        if (n == 1)
            return;

        //寻找最大烧饼的索引
        int maxCake = 0;
        int maxCakeIndex = 0;
        for (int i = 0; i < n; i++) {
            if (cakes[i] > maxCake) {
                maxCakeIndex = i;
                maxCake = cakes[i];
            }
        }

        //第一次翻转，将最大烧饼翻转到最上面
        reverse(cakes, 0, maxCakeIndex);
        //记录这次翻转
        res.add(maxCakeIndex + 1);
        //第二次翻转，将最大的煎饼翻转到最下面
        reverse(cakes, maxCakeIndex, n - 1);
        //记录这次翻转
        res.add(n);

        //递归调用，翻转剩下的烧饼
        sort(cakes, n - 1);
    }

    //翻转arr[i...j]的元素
    private void reverse(int[] arr, int i, int j) {

        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
