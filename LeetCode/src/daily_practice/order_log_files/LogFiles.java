package daily_practice.order_log_files;

import java.util.Arrays;

/**
 * 重新排列日志文件
 * 自定义排序
 * @author QinE
 * @create 2022-05-03 11:31
 */
public class LogFiles {

    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        String[] res = new LogFiles().reOrderLogFiles(logs);
        for (String data : res)
            System.out.print(data + " ");
    }

    public String[] reOrderLogFiles(String[] logs) {
        int len = logs.length;
        Pair[] arr = new Pair[len];
        for (int i = 0; i < len; i++)
            arr[i] = new Pair(logs[i], i);
        Arrays.sort(arr, this::orderLog);
        String[] res = new String[len];
        for (int i = 0; i < len; i++) {
            res[i] = arr[i].log;
        }
        return res;
    }

    public int orderLog(Pair a, Pair b) {
        int index1 = a.index;
        int index2 = b.index;
        String log1 = a.log;
        String log2 = b.log;
        String[] split1 = log1.split(" ", 2);
        String[] split2 = log2.split(" ", 2);
        boolean digit1 = Character.isDigit(split1[1].charAt(0));
        boolean digit2 = Character.isDigit(split2[1].charAt(0));
        if (digit1 && digit2)
            return index1 - index2;
        if (!digit1 && !digit2) {
            int isSame = split1[1].compareTo(split2[1]);
            if (isSame != 0)
                return isSame;
            return split1[0].compareTo(split2[0]);
        }
        return digit1 ? 1 : -1;
    }

}

class Pair {
    String log;
    int index;

    public Pair(String log, int index) {
        this.log = log;
        this.index = index;
    }

}