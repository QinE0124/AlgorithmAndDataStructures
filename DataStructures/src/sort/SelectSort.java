package sort;


/**
 * @author Qine
 * @create 2021-07-16 20:10
 */
public class SelectSort {
    public static void main(String[] args) {
        //创建一个80000的随机数组
        int[] arr = new int[800000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
    //选择排序
    public static void selectSort(int[] arr) {

        //选择排序时间复杂度为O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j ++) {
                if (min > arr[j]) {
                    //说明假定的最小值并不是最小
                    min = arr[j];
                    minIndex = j;
                }
            }

            //将最小值放在arr[0]
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }

}

