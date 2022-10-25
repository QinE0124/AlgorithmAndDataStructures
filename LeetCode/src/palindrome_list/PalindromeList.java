package palindrome_list;

import linkedlist.ListNode;

/**
 * 判断回文链表
 * @author QinE
 * @create 2022-06-12 15:46
 */
public class PalindromeList {

    //左侧指针
//    ListNode left;

//    public boolean isPalindrome(ListNode head) {
//        left = head;
//        return traverse(head);
//    }
//
//    //利用递归，倒序遍历链表
//    private boolean traverse(ListNode right) {
//        if (right == null)
//            return true;
//        boolean res = traverse(right.next);
//        //后序遍历代码
//        res = res && (right.val == left.val);
//        left = left.next;
//        return res;
//    }

    public boolean isPalindrome(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //这里分奇偶情况，如果fst指针没有指向null，说明链表长度为奇数，slow还要前进一步
        if (fast != null)
            slow = slow.next;

        ListNode left = head;
        ListNode right = reverse(slow);

        while (right != null) {
            if (left.val != right.val)
                return false;
            left = left.next;
            right = right.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
