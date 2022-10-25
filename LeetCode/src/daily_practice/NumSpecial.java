package daily_practice;

/**
 *Given an m x n binary matrix mat, return the number of special positions in mat.
 *
 * A position (i, j) is called special if mat[i][j] == 1 and
 * all other elements in row i and column j are 0 (rows and columns are 0-indexed).
 *
 * @author QinE
 * @create 2022-09-04 19:15
 */
public class NumSpecial {

//    public int numSpecial(int[][] mat) {
//        int n = mat.length, m = mat[0].length;
//        int res = 0;
//        int[] rowSum = new int[n];
//        int[] colsSum = new int[m];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                rowSum[i] += mat[i][j];
//                colsSum[j] += mat[i][j];
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (mat[i][j] == 1 && colsSum[j] == 1 && rowSum[i] == 1)
//                    res++;
//            }
//        }
//
//        return res;
//    }

    //列的标记值
    public int numSpecial(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        for (int i = 0; i < n; i++) {
             int cnt1 = 0;
             for (int j = 0; j < m; j++) {
                 if (mat[i][j] == 1)
                     cnt1++;
             }

             if (i == 0)
                 cnt1--;

             if (cnt1 > 0) {
                 for (int j = 0; j < m; j++)
                     if (mat[i][j] == 1)
                         mat[0][j] += cnt1;
             }
        }

        int sum = 0;
        for (int num : mat[0]) {
            if (num == 1)
                sum++;
        }

        return sum;
    }
}
