package bst;

/**
 * Git原理之二叉树最近公共祖先
 *
 * @author QinE
 * @create 2022-06-11 19:19
 */
public class Git {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //base case
        if (root == null)
            return null;
        if (root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //情况1
        if (left != null && right != null)
            return root;
        //情况2
        if (right == null && left == null)
            return null;
        //情况3
        return left == null ? right : left;
    }
}
