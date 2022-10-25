package sparsearray;

import java.io.*;

/**
 * 稀疏数组与二维数组相互转换
 * 存储与读取
 *
 * @author QinE
 * @create 2021-11-05 18:03
 */
public class SparseArray {

    public static void main(String[] args) {
        /*
        保存棋盘，0表无子，1表白子，2表黑子
         */
        int[][] chessBoard = new int[11][11];
        chessBoard[0][1] = 1;
        chessBoard[1][2] = 2;
        chessBoard[2][3] = 1;
        chessBoard[3][4] = 2;
        chessBoard[4][5] = 1;

//        showArray(chessBoard);
        int[][] sparseArray = sparseArray(chessBoard);
//        showSparseArray(sparseArray);
//        System.out.println("恢复的二维数组···");
//        int[][] array = array(sparseArray);
//        showArray(array);
//        save(sparseArray, "SparseArray.data");
        int[][] load = load("SparseArray.data");
        showSparseArray(load);


    }

    public static void showArray(int[][] arr) {
        System.out.println("二维数组···");
        for (int[] line : arr) {
            for (int data : line) {
                System.out.print(data + "\t");
            }
            System.out.println();
        }
    }

    public static void showSparseArray(int[][] sparseArr) {
        System.err.println("稀疏数组···");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length;j++) {
                System.err.print(sparseArr[i][j] + "\t");
            }
            System.err.println();
        }
    }

    /**
     * 将二维数组转换为稀疏数组
     *
     * @param arr
     * @return
     */
    public static int[][] sparseArray(int[][] arr) {
        int sum = 0;

        for (int[] line : arr) {
            for (int data : line) {
                if (data != 0)
                    sum++;
            }
        }

        int[][] sparseAry = new int[sum + 1][3];
        sparseAry[0][0] = arr.length;
        sparseAry[0][1] = arr[0].length;
        sparseAry[0][2] = sum;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    count++;
                    sparseAry[count][0] = i;
                    sparseAry[count][1] = j;
                    sparseAry[count][2] = arr[i][j];
                }
            }
        }

        return sparseAry;
    }

    /**
     * 将稀疏数组恢复为二维数组
     *
     * @param sparseArr
     * @return
     */
    public static int[][] array(int[][] sparseArr) {
        int[][] arr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return arr;
    }

    //利用流保存、恢复稀疏数组

    /**
     * 将稀疏数组保存到磁盘文件中
     *
     * @param sparseArr
     */
    public static void save(int[][] sparseArr, String saveSrc) {
        System.out.println("将稀疏数组写入到SparseArray.data文件中~~~");
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(saveSrc));
            for (int i = 0; i < sparseArr.length; i++) {
                if (i == sparseArr.length - 1) {
                    osw.write(sparseArr[i][0] + "," + sparseArr[i][1] + "," + sparseArr[i][2]);
                } else {
                    osw.write(sparseArr[i][0] + "," + sparseArr[i][1] + "," + sparseArr[i][2] + ",");
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从磁盘文件中读取稀疏数组
     * @return
     */
    public static int[][] load(String loadSrc) {
        System.out.println("从磁盘文件中读取稀疏数组");
        BufferedReader br = null;
        int[][] sparseArr = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(loadSrc)));
            StringBuilder sb = new StringBuilder();
            while (br.ready()) {
                sb.append((char) br.read());//转为char型，不然只会打印地址值
            }
            System.out.println("读取的文件为；" + sb.toString());
            //将读取的数据赋值给稀疏数组
            String[] str = sb.toString().split(",");
            sparseArr = new int[str.length / 3][3];
            for (int i = 0; i < str.length; i++) {
                sparseArr[i / 3][i % 3] = Integer.parseInt(str[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sparseArr;
    }
}
