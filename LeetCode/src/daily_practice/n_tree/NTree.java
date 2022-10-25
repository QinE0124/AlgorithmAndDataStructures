package daily_practice.n_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**N叉树的前序遍历
 * @author QinE
 * @create 2022-04-13 13:11
 */
public class NTree {

    public static void main(String[] args) {

    }

    /**
     * 迭代优化前序遍历n叉树
     * @param root
     * @return
     */
    public List<Integer> preOrder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }

        return res;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {

    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }

}
