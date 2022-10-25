package reverse_list;

import linkedlist.ListNode;

/**
 * 递归反转单链表
 * @author QinE
 * @create 2022-06-12 16:12
 */
public class ReverseLinkedList {

    //反转整条链表
    public ListNode reverse(ListNode head) {
        //base case
        if (head == null || head.next == null)
            return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /*反转链表前N个节点*/
    //后驱节点
    ListNode successor = null;
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            //记录第n+1个节点，后面要用
            successor = head.next;
            return head;
        }

        //以head.next为起点，需要反转前n-1个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        //让反转后的head节点和后面的节点连接起来
        head.next = successor;
        return last;
    }

    //饭庄链表的一部分
    public ListNode reverseBetween(ListNode head, int m, int n) {
        //base case
        if (m == 1)
            //相当于反转前n个元素
            return reverseN(head, n);

        //对于head.next来说，就是反转区间[m - 1, n - 1]
        //前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
}
