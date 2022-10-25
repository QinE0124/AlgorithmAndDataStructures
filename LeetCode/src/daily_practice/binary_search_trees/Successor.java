package daily_practice.binary_search_trees;

/**
 * 中序后继
 * @author QinE
 * @create 2022-05-16 10:47
 */
public class Successor {
    //方法一：中序遍历
//    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
//        TreeNode curr = root;
//        TreeNode prev = null;
//        while (!stack.isEmpty() || curr != null) {
//            while (curr != null) {
//                stack.push(curr);
//                curr = curr.left;
//            }
//            curr = stack.pop();
//            if (p.val == prev.val)
//                return curr;
//
//            prev = curr;
//            curr = curr.right;
//        }
//        return null;
//    }

    //方法二：二分搜索
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        int target = p.val;
        TreeNode curr = root;
        TreeNode ans = null;
        while (curr != null) {
            if (curr.val > target) {
                ans = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        return ans;
    }
}
