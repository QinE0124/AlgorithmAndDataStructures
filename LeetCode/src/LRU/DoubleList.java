package LRU;

/**
 * @author QinE
 * @create 2022-06-09 20:23
 */
public class DoubleList {

    //头部虚节点
    private Node head, tail;
    //链表元素数
    private int size;

    public DoubleList() {
        //初始化双链表的数据
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    //在链表尾部添加节点x，时间复杂度O(1)
    public void addLast(Node x) {
        x.prev = tail.prev.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    //删除链表的x节点（x一定存在）
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    //删除列表中的第一个节点，并返回这个节点
    public Node removeFirst() {
        if (head.next == tail)
            return null;
        Node first = head.next;
        remove(first);
        return first;
    }

    //返回链表长度
    public int getSize() {
        return size;
    }
}
