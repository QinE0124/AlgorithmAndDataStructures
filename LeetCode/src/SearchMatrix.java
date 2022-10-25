

/**
 * 搜索二维矩阵
 *
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix.
 * The matrix has the following properties:
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 * @author QinE
 * @create 2021-10-27 0:06
 */
public class SearchMatrix {

    public static void main(String[] args) {

//        int[][] matrix =  {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int[][] matrix = {{3,5,9,9,14},{7,8,11,15,15},{8,10,16,16,17}};
        SearchMatrix searchMatrix = new SearchMatrix();
        boolean bool = searchMatrix.zSearchMatrix(matrix, 12);
        System.out.println(bool);


    }

    //查找指定的值
    public boolean searchMatrix(int[][] matrix, int target) {

        for (int[] row : matrix) {
            if (search(row, 0, row.length - 1, target) >= 0) {
                return true;
            }
        }
        return false;
    }

    //插值算法，递归实现
    public int  search(int[] nums, int low, int high, int target) {


        if (nums.length == 0 || target < nums[low] || target > nums[high]) {
            return -1;
        }


            //插值查找算法优化
            //保证nums[high] - nums[low] != 0, 否则发生算术异常
            if (nums[high] == nums[low]) {
                if (target == nums[low]) {
                    return low;
                }
                return -1;
            }

            //求出mid，自适应
            int mid = low + (target - nums[low]) * (high - low) / (nums[high] - nums[low]);
//            int mid = (low + high) / 2;
            int num = nums[mid];
            if (target > num) {
                search(nums, mid + 1, high, target);
            }else if (target < num) {
               search(nums, low, mid + 1, target);
            } else {
                return mid;
            }




        return -1;

    }

    //由于矩阵自左向右，自上向下递增
    //因此，从矩阵右上角向左下角使用Z字查找
    public boolean zSearchMatrix(int[][] matrix, int target) {
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int x = 0, y = n;

        while (x <= m && y >= 0) {

            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] >= target) {
                y--;
            } else {
                x++;
            }
        }

        return false;


    }

}
