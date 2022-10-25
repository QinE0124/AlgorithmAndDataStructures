package sort;

/**
 * @author Qine
 * @create 2021-07-16 19:30
 */
public class BubbleSort {
    public static void main(String[] args) {
        //创建一个80000的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    public static void bubbleSort(int[] arr) {
        //冒泡排序的时间复杂度O(n^2)
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }

            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
