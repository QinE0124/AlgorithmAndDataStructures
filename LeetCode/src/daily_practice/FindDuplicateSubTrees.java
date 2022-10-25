package daily_practice;

import javafx.util.Pair;

import java.util.*;

/**
 *Given the root of a binary tree, return all duplicate subtrees.
 *
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *
 * Two trees are duplicate if they have the same structure with the same node values.
 *
 * @author QinE
 * @create 2022-09-05 19:26
 */
public class FindDuplicateSubTrees {

    /**
     *方法一：使用序列化进行唯一表示
     *  将每一棵子树都序列化成一个字符串，并且保证：
     *      相同的子树序列化为相同的字符串
     *      不同的子树序列化为不同的字符串
     *  那么只需使用一个哈希表存储所有子树的序列化结果，如果某一个结果超过一次，那么就发现了一类相同子树。
     */

//    Map<String, TreeNode> seen = new HashMap<>();
//    Set<TreeNode> repeat = new HashSet<>();
//
//    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
//        dfs(root);
//        return new ArrayList<>(repeat);
//    }
//
//    public String dfs(TreeNode root) {
//        if (root == null)
//            return "";
//        StringBuilder sb = new StringBuilder();
//        sb.append(root.val);
//        sb.append("(");
//        sb.append(dfs(root.left));
//        sb.append(")(");
//        sb.append(dfs(root.right));
//        sb.append(")");
//
//        String serial = sb.toString();
//        if (seen.containsKey(serial))
//            repeat.add(seen.get(serial));
//        else
//            seen.put(serial, root);
//
//        return serial;
//    }

    /**
     * 方法二：使用三元数组进行唯一表示
     * 通过方法一的递归序列化技巧，继续优化
     * 可以使用一个三元数组直接表示一个子树，即(x, l, r)，它们分别表示：
     * 1.根节点的值为x
     * 2.左子树的序号为l
     * 3.右子树的序号为r
     * 这里的序号指：每当发现一棵新的子树，就给这棵子树一个序号，用来表示其结构，那么
     * 两棵子树是重复的，当且仅当它们对应的三元数组完全相同。
     */

    Map<String, Pair<TreeNode, Integer>> seen = new HashMap<>();
    Set<TreeNode> repeat = new HashSet<>();
    int idx = 0;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<>(repeat);
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;
        int[] tri = {node.val, dfs(node.left), dfs(node.right)};
        String hash = Arrays.toString(tri);
        if (seen.containsKey(hash)) {
            Pair<TreeNode, Integer> pair = seen.get(hash);
            repeat.add(pair.getKey());
            return pair.getValue();
        } else {
            seen.put(hash, new Pair<>(node, idx++));
            return idx;
        }
    }
}

