package search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qine
 * @create 2021-07-19 19:32
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };
        List<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 12);
        System.out.println("resIndexList=" + resIndexList);
    }

    //二分查找算法
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    //有多个相同的数值时，将所有的值都查找到
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) /2;
        int midVal = arr[mid];

        if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            resIndexList.add(mid);
            //向mid索引值的左边扫描，将满足条件的元素的下标加入到集合中
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;
            }

            //向mid索引值的右边扫描，将满足条件的元素的下标加入到集合中
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            return resIndexList;
        }
    }

}
