package LRU;

/**
 * @author QinE
 * @create 2022-06-09 20:18
 */
public class Node {
    public int key, val;
    public Node next, prev;

    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
