package sort;

/**
 * @author Qine
 * @create 2021-07-17 12:47
 */
public class ShellSort {
    public static void main(String[] args) {

//        创建一个80000的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        shellSort2(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    //希尔排序时，对有序序列在插入时使用交换法
    public static void shellSort(int[] arr) {

        int temp = 0;

        for (int gap = arr.length / 2; gap > 0; gap /= 2 ) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素（共gap组），步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，则交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //对交换式的希尔排序进行优化->移位法
    public static void shellSort2(int[] arr) {

        //逐渐减小增量gap
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}
