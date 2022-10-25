package bst;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 前序遍历序列化
 * @author QinE
 * @create 2022-06-11 16:32
 */
public class CodecPrev {

    String SEP = ",";
    String NULL = "#";
    //主函数：把一棵二叉树序列化为字符串
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    //辅助函数：，将二叉树存入StringBuilder
    private void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }

        sb.append(root.val).append(SEP);

        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    //主函数：将字符串反序列化为二叉树
    public TreeNode deserialize(String data) {
        //将字符串转化成列表
        LinkedList<String> nodes = new LinkedList<>(Arrays.asList(data.split(SEP)));
        return deserialize(nodes);
    }

    //辅助函数，通过nodes列表构造二叉树
    private TreeNode deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty())
            return null;
        //前序遍历，列表最左侧就是根节点
        String first = nodes.removeFirst();
        if (first.equals(NULL))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(first));

        root.left = deserialize(nodes);
        root.right = deserialize(nodes);

        return root;
    }
}
