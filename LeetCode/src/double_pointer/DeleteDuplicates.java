package double_pointer;

import linkedlist.ListNode;

/**
 * @author QinE
 * @create 2022-06-15 11:17
 */
public class DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head, fast = head.next;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }

            fast = fast.next;
        }

        slow.next = null;
        return head;
    }
}
