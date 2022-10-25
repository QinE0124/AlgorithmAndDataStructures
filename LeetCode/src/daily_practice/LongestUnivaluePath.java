package daily_practice;

/**
 *Given the root of a binary tree, return the length of the longest path,
 * where each node in the path has the same value. This path may or may not pass through the root.
 *
 * The length of the path between two nodes is represented by the number of edges between them.
 *
 *
 * @author QinE
 * @create 2022-09-02 15:57
 */
public class LongestUnivaluePath {

    int res = 0;

    public int longestUnivaluePath(TreeNode root) {

        if (root == null)
            return 0;
        maxLen(root, root.val);
        return res;
    }

    private int maxLen(TreeNode root, int parentValue) {
        if (root == null)
            return 0;
        int leftLen = maxLen(root.left, root.val);
        int rightLen = maxLen(root.right, root.val);

        res = Math.max(res, leftLen + rightLen);

        if (root.val != parentValue)
            return 0;

        return 1 + Math.max(leftLen, rightLen);

    }
}
