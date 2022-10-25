package daily_practice;

/**
 * 二维区域和检索-矩阵不可变
 * @author QinE
 * @create 2022-05-02 13:20
 */
//方法1：一维前缀和
public class NumMatrix1 {
    int[][] sums;

    public static void main(String[] args) {
        int[][] matrix = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix1 numMatrix = new NumMatrix1(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
    }

    public NumMatrix1(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            sums = new int[m][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    sums[i][j + 1] = sums[i][j] + matrix[i][j];
                }
            }
        }
    }


    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            sum += sums[i][col2 + 1] - sums[i][col1];
        }
        return sum;
    }
}
