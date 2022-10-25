package tree;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Qine
 * @create 2021-07-22 10:55
 */
public class HeapSort {
    public static void main(String[] args) {
//要求将数组进行升序排序
//        int arr[] = {4, 6, 8, 5, 9, 3, 19, 23, 1};
        // 创建80000个的随机的数组
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        heapSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);
//        System.out.println("排序后=" + Arrays.toString(arr));
    }

    //堆排序
    public static void heapSort(int[] arr) {
        int temp = 0;

        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        //重新调整结构，时其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行，直到整个序列有序
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 功能：完成将以i对应的非叶子结点的树调整成大顶堆
     * @param arr 待调整的数组
     * @param i 非叶子结点在数组中索引
     * @param length 调整元素数量，length逐渐减少
     */
    public static void adjustHeap (int[] arr, int i, int length) {

        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //1、k = i * 2 + 1 k 是 i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k ++;//k指向右子结点
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //当for循环结束后，已将i为父结点的树的最大值，放在了最顶（局部）
        arr[i] = temp;
    }
}
