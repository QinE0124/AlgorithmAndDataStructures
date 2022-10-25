package bst;

/**
 * 二叉搜索树操作集锦
 * @author QinE
 * @create 2022-06-10 20:55
 */
public class BSTDemo {

    //1.如何把二叉树所有节点的值+1
    void plusOne(TreeNode root) {
        if (root == null)
            return;
        root.val++;
        plusOne(root.left);
        plusOne(root.right);
    }

    //2.如何判断两棵二叉树是否完全相同
    boolean isSameTree(TreeNode root1, TreeNode root2) {
         //都为空的话，显然相同
        if (root1 == null && root2 == null)
            return true;
        //一个为空，一个不为空，显然不同
        if (root1 == null || root2 == null)
            return false;
        //两个都不为空，但val不一样也不行
        if (root1.val != root2.val)
            return false;

        return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }

    //3.判断BFS的合法性
    boolean isValidBST(TreeNode root) {
        //给所有的节点上添加min和max边界
        //约束root的左子树节点值不超过root的值，root的右子树节点的值不小于root的值
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null)
            return true;
        if (min != null && root.val <= min.val)
            return false;
        if (max != null && root.val >= max.val)
            return false;
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    //4.在BST中查找一个数是否存在
    boolean isInBST(TreeNode root, int target) {
        if (root == null)
            return false;
        if (root.val == target)
            return true;
        //递归框架
        if (root.val > target)
            return isInBST(root.left, target);
        return isInBST(root.right, target);
    }

    //5.在BST中插入一个数
    TreeNode insertIntoBST(TreeNode root, int val) {
        //找到空位置插入新节点
        if (root == null)
            return new TreeNode(val);
        //如果已经存在，则不要再重复插入，直接返回
        if (root.val == val)
            return root;
        //val大，则应该插到右子树上面
        if (root.val > val)
            root.right = insertIntoBST(root.right, val);
        //val小，则插到左子树上
        if (root.val < val)
            root.left = insertIntoBST(root.left, val);

        return root;
    }

    //6.在BST中删除一个数
    TreeNode deleteNode(TreeNode root, int key) {

        if (root == null)
            return null;
        if (root.val == key) {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);
        } else if (root.val > key)
            root.left = deleteNode(root.left, key);
        else root.right = deleteNode(root.right, key);
        return root;
    }

    private TreeNode getMin(TreeNode node) {
        //BST最左边的就是最小的
        while (node.left != null)
            node = node.left;
        return node;
    }
}
