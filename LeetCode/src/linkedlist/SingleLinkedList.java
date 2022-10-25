package linkedlist;

/**
 * @author QinE
 * @create 2022-05-21 21:50
 */
public class SingleLinkedList {

    public static void main(String[] args) {
        ListNode headA = new ListNode(2);
        headA.next = new ListNode(6);
        headA.next.next = new ListNode(4);
        ListNode headB = new ListNode(1);
        headB.next = new ListNode(5);
        ListNode node = getIntersectionNode(headA, headB);
        System.out.println(node);
    }
    /**
     * 判断链表是否含环
     * @param head 头节点
     * @return 返回true表示含环，返回false表示不含环
     */
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }

        return false;
    }

    /**
     * 如果链表中有环，计算环的起点
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }

        if (fast == null || fast.next == null)
            //如果fast遇到空指针说明没有环
            return null;
        //重新指向头结点
        slow = head;

        //快慢指针同时前进，相交点就是环起点
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            if (p1 == null)
                p1 = headB;
            else
                p1 = p1.next;
            if (p2 == null)
                p2 = headA;
            else
                p2 = p2.next;
        }

        return p1;
        
    }


}
