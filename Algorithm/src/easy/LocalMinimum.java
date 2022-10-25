package easy;

/**
 * 局部最小值
 * @author QinE
 * @create 2022-05-14 19:40
 */
public class LocalMinimum {

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 400;
        int testTimes = 100000;
        while (testTimes --> 0) {
            int[] arr = randomArray(maxLen, maxValue);
            int minIndex = ondMinIndex(arr);
            if (!check(arr, minIndex)) {
                System.out.println("failed");
                printArray(arr);
            }
        }

        System.out.println("测试完成");
    }

    public static int ondMinIndex(int[] arr) {
        if (arr == null || arr.length == 0)
            return -1;
        int n = arr.length;
        if (n == 1)
            return 0;
        if (arr[0] < arr[1])
            return 0;
        if (arr[n - 1] < arr[n - 2])
            return n - 1;
        int l = 0, r = n - 1;
        while (l < r - 1) {
            int mid = l + r >> 1;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1])
                return mid;
            else {
                if (arr[mid] > arr[mid - 1]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }

            }
        }

        return -1;
    }

    public static boolean check(int [] arr, int index) {
        if (arr.length == 0)
            return index == -1;
        int left = index - 1;
        int right = index + 1;
        boolean leftBigger = left < 0 || arr[left] > arr[index];
        boolean rightBigger = right >= arr.length || arr[right] > arr[index];

        return leftBigger && rightBigger;
    }


    //对数器
    public static int[] randomArray(int maxLen, int maxValue) {
        int randomLen = (int) (Math.random() * maxLen);
        int[] randomArray = new int[randomLen];
        if (randomLen > 0) {
            randomArray[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < randomLen; i++) {
                do {
                    randomArray[i] = (int) (Math.random() * maxValue);
                } while (randomArray[i] == randomArray[i - 1]);
            }
        }

        return randomArray;
    }

    public static void printArray(int[] arr) {
        for (int d : arr)
            System.out.print(d + " ");
        System.out.println();
    }
}
