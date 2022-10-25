package dp;

import java.util.HashMap;

/**
 * 树形排列
 * @author QinE
 * @create 2022-06-08 18:48
 */
public class TreeNodeRob {

    //备忘录
    static HashMap<TreeNode, Integer> memo = new HashMap<>();

    public static int rob(TreeNode root) {
        if (root == null)
            return 0;
        if (memo.containsKey(root))
            return memo.get(root);
        //选择
        int do_it = root.val + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.left));
        //不选择
        int not_it = rob(root.left) + rob(root.right);

        int res = Math.max(do_it, not_it);
        memo.put(root, res);
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
