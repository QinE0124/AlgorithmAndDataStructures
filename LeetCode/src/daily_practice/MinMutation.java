package daily_practice;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 最小基因变化
 * @author QinE
 * @create 2022-05-07 10:05
 */
public class MinMutation {

    public static void main(String[] args) {
//        String[] bank = {"AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"};
//        int res = minMutation("AAAACCCC", "CCCCCCCC", bank);
//        System.out.println(res);
    }

    //方法一：广度优先搜索
//    public static int minMutation(String start, String end, String[] bank) {
//      if (start.equals(end))
//          return 0;
//        HashSet<String> visited = new HashSet<>();
//        HashSet<String> cnt = new HashSet<>(Arrays.asList(bank));
//        char[] key = {'A', 'T', 'C', 'G'};
//        if (!cnt.contains(end))
//            return -1;
//        int step = 1;
//        Queue<String> queue = new ArrayDeque<>();
//        queue.add(start);
//        visited.add(start);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                String curr = queue.poll();
//                for (int j = 0; j < 8; j++) {
//                    for (int k = 0; k < 4; k++) {
//                        if (curr.charAt(j) != k) {
//                            StringBuilder sb = new StringBuilder(curr);
//                            sb.setCharAt(j, key[k]);
//                            String next = sb.toString();
//                            if (!visited.contains(next) && cnt.contains(next)) {
//                                if (next.equals(end))
//                                    return step;
//                                visited.add(next);
//                                queue.add(next);
//                            }
//                        }
//                    }
//                }
//            }
//            step++;
//        }
//
//        return -1;
//    }

    //预处理优化方法一
    //将每个基因的合法变化关系存储在领接表adj中， 每次搜索只在adj中
    public static int minMutation(String start, String end, String[] bank) {
        int n = bank.length;
        int m = start.length();
        int endIndex = -1;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (bank[i].equals(end))
                endIndex = i;
            for (int j = i + 1; j < n; j++) {
                int mutation = 0;
                for (int k = 0; k < m; k++) {
                    if (bank[i].charAt(k) != bank[j].charAt(k))
                        mutation++;
                    if (mutation > 1)
                        break;
                }
                if (mutation == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        if (endIndex == -1)
            return -1;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        int step = 1;
        for (int i = 0; i < n; i++) {
            int mutation = 0;
            for (int k = 0; k < m; k++) {
                if (bank[i].charAt(k) != start.charAt(k))
                    mutation++;
                if (mutation > 1)
                    break;
            }
            
            if (mutation == 1) {
                queue.add(i);
                visited[i] = true;
            }
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == endIndex)
                    return step;
                for (int next : adj[curr]) {
                    if (visited[next])
                        continue;
                    visited[next] = true;
                    queue.add(next);
                }
            }
            step++;
        }

        return -1;
    }
}
