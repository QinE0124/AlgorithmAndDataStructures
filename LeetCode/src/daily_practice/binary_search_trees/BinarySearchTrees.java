package daily_practice.binary_search_trees;

import java.util.ArrayList;
import java.util.List;

/**
 *  两棵二叉搜索树中的所有元素
 *  提供root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.、
 *  中序遍历
 * @author QinE
 * @create 2022-05-02 17:38
 */
public class BinarySearchTrees {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(2, new TreeNode(1), new TreeNode(4));
        TreeNode root2 = new TreeNode(1, new TreeNode(0), new TreeNode(3));
        List<Integer> res = new BinarySearchTrees().getAllElements(root1, root2);
        res.forEach(System.out :: print);
    }

   public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
       ArrayList<Integer> nums1 = new ArrayList<>();
       ArrayList<Integer> nums2 = new ArrayList<>();
       infixOrder(root1, nums1);
       infixOrder(root2, nums2);
       ArrayList<Integer> merged = new ArrayList<>();
       int p1 = 0, p2 = 0;
       while (true) {
           if (p1 == nums1.size()) {
               merged.addAll(nums2.subList(p2, nums2.size()));
               break;
           }
           if (p2 == nums2.size()) {
               merged.addAll(nums1.subList(p1, nums1.size()));
               break;
           }
           if (nums1.get(p1) < nums2.get(p2))
               merged.add(nums1.get(p1++));
           else
               merged.add(nums2.get(p2++));
       }
       return merged;
   }

   private void infixOrder(TreeNode node, List<Integer> res) {
        if (node != null) {
            infixOrder(node.left, res);
            res.add(node.val);
            infixOrder(node.right, res);
        }
   }
}

class  TreeNode {
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