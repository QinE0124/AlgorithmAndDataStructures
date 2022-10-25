package daily_practice.binary_search_trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 序列化和反序列化二叉搜索树
 * @author QinE
 * @create 2022-05-11 7:55
 */
public class SerializeAndDeserialize {

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
//        String s = serialize(root);
//        System.out.println(s);
    }

   //序列化
    public static String serialize(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        postOrder(root, list);
        String s = list.toString();
        return s.substring(1, s.length() - 1);
    }

    //反序列化
    public static TreeNode deserialize(String data) {
        if (data.isEmpty())
            return null;
        String[] split = data.split(", ");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (String s : split) stack.push(Integer.parseInt(s));
        return construct(Integer.MIN_VALUE, Integer.MAX_VALUE, stack);
    }

    private static void postOrder(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.val);
    }

    private static TreeNode construct(int lower, int upper, Deque<Integer> stack) {
        if (stack.isEmpty() || stack.peek() < lower || stack.peek() > upper)
            return null;
        Integer val = stack.pop();
        TreeNode root = new TreeNode(val);
        root.right = construct(val, upper, stack);
        root.left = construct(lower, val, stack);
        return root;
    }
}
