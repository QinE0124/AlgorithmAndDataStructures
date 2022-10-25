package daily_practice;

/**
 * @author QinE
 * @create 2022-09-10 20:10
 */
public class TrimBST {

    //方法一：递归
//    public TreeNode trimBST(TreeNode root, int low, int high) {
//
//        if (root == null)
//            return null;
//
//        if (root.val < low) {
//            return trimBST(root.right, low, high);
//        } else if (root. val > high) {
//            return trimBST(root.left, low, high);
//        } else {
//            root.left = trimBST(root.left, low, high);
//            root.right = trimBST(root.right, low, high);
//            return root;
//        }
//    }

    //方法二：迭代
    public TreeNode trimBST(TreeNode root, int low, int high) {
        while (root != null && (root.val < low || root.val > high)) {
            if (root.val < low) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        if (root == null)
            return null;

       for (TreeNode node = root; node.left != null; ) {
           if (node.left.val < low) {
               node.left = node.left.right;
           } else {
               node = node.left;
           }
       }

       for (TreeNode node = root; node.right != null; ) {
           if (node.right.val > high) {
               node.right = node.right.left;
           } else {
               node = node.right;
           }
       }

       return root;

    }
}
