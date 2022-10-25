package sort;

/**
 * @author Qine
 * @create 2021-07-18 23:53
 */
public class RadixSort {
    public static void main(String[] args) {
//        创建一个80000的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        long start = System.currentTimeMillis();
        int[] temp = new int[arr.length];
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //基数排序方法
    public static void radixSort(int[] arr) {

        //1.得到数组中最大的数的位数
        int max = arr[0];//假设第一位数就是最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        //得到最大数的位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组，大小定义为arr.length
        //3.基数排序是使用空间交换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素的对应座位进行排序处理
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index = 0;
            //每遍历一桶，就将桶中的数据放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，放入原数组
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i + 1轮处理后，需要将每个bucketElementCounts[k]置空
                bucketElementCounts[k] = 0;
            }
        }
    }
}
