package search;

/**
 * @author Qine
 * @create 2021-07-19 20:15
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
        int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
        System.out.println("index = " + index);
    }

   //插值查找算法，也要求数组有序
   public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

         if (left > right) {
             return -1;
         }

         //求出mid，自适应
         int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
         int midVal = arr[mid];
         if (findVal > midVal) {
             return insertValueSearch(arr, mid + 1, right, findVal);
         } else if (findVal < midVal) {
             return insertValueSearch(arr, left, mid - 1, findVal);
         } else {
             return mid;
         }
   }
}
