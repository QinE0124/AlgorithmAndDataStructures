package daily_practice;

/**
 * 文件夹操作日志收集器
 *
 * @author QinE
 * @create 2022-09-09 18:41
 */
public class MinOperations {

    public int minOperations(String[] logs) {

        int depth = 0;

        for (String log : logs) {
            if ("./".equals(log)) {

                continue;
            } else if ("../".equals(log)) {

                if (depth > 0)
                    depth--;

            } else {

                depth++;
            }
        }

        return depth;
    }
}
