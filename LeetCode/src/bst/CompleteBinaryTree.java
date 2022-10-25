package bst;

/**
 * 计算完全二叉树的节点
 * @author QinE
 * @create 2022-06-11 16:15
 */
public class CompleteBinaryTree {

    public int contNodes(TreeNode root) {
        TreeNode l = root, r = root;
        //记录左右子树的高度
        int hl = 0, hr = 0;
        while (l != null) {
            l = l.left;
            hl++;
        }
        while (r != null) {
            r = r.right;
            hr++;
        }

        //如果左右子树的高度相同，说明是棵满二叉树
        if (hl == hr)
            return (int) Math.pow(2, hl) - 1;

        //如果左右高度不同，则按照普通二叉树的逻辑运算
        return 1 + contNodes(root.left) + contNodes(root.right);
    }

}
