package daily_practice;

/**
 * @author QinE
 * @create 2022-09-30 20:24
 */
public class SetZeroes {

    public void setZeros(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        boolean flagCol0 = false, flagRow0 = false;
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 0)
                flagCol0 = true;
        }

        for (int j = 0; j < m; j++) {
            if (matrix[0][j] == 0)
                flagRow0 = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (flagCol0) {
            for (int i = 0; i < n; i++)
                matrix[i][0] = 0;
        }

        if (flagRow0) {
            for (int j = 0; j < m; j++) {
                matrix[0][j] = 0;
            }
        }
    }
}
