package bst;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 后序遍历序列化、反序列化
 * @author QinE
 * @create 2022-06-11 17:18
 */
public class CodecPost {

    final String SEP = ",";
    final String NULL = "#";

    //主函数：将二叉树序列化为字符串
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    //辅助函数：将二叉树存入StringBuilder
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        serialize(root.left);
        serialize(root.right);

        sb.append(root.val).append(SEP);
    }

    //主函数：将字符串反序列化为二叉树结构
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>(Arrays.asList(data.split(SEP)));
        return deserialize(nodes);
    }

    //辅助函数：通过nodes列表构造二叉树
    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty())
            return null;
        //从后往前取出元素
        String last = nodes.removeLast();
        if (last.equals(NULL))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(last));
        //先构造右子树，再构造左子树
        root.right = deserialize(nodes);
        root.left = deserialize(nodes);

        return root;
    }
}
