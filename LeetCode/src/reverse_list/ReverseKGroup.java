package reverse_list;

import linkedlist.ListNode;

/**
 * 迭代实现单链表以k个为一组反转，不足k个的那组保持顺序不变
 * @author QinE
 * @create 2022-06-12 16:33
 */
public class ReverseKGroup {

     //反转以a为头结点的链表
    public ListNode reverse(ListNode a) {
        ListNode pre, cur, next;
        pre = null;
        cur = a;
        next = null;
        while (cur != null) {
            next = cur.next;
            //逐个节点反转
            cur.next = pre;
            //更新指针位置
            pre = cur;
            cur = next;
        }

        //返回反转后的头结点
        return pre;
    }

    //反转区间[a,b)的元素，左闭右开
    public ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, next;
        pre = null;
        cur = a;
        next = a;
        while (cur != b) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;
        //区间[a, b)包含k个带反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            //base case 不足k个不需要反转
            if (b == null)
                return head;
            b = b.next;
        }

        //反转前k个元素
        ListNode newHead = reverse(a, b);
        //递归反转后续链表，并拼接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }
}
