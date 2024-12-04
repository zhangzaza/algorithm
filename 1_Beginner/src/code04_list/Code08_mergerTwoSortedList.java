package code04_list;


/*
 * 给定两个升序排列的链表，将它们合并为一个新的有序链表并返回。
 * 例如，链表1为：1 -> 2 -> 4，链表2为：1 -> 3 -> 4。将这两个链表合并后得到的有序链表为：1 -> 1 -> 2 -> 3 -> 4 -> 4。
 * 另一个例子是，链表1为：1 -> 3 -> 5，链表2为：2 -> 4 -> 6。合并后的有序链表为：1 -> 2 -> 3 -> 4 -> 5 -> 6。
 * 注意，合并后的链表应该是升序排列的。如果链表1为空，那么合并后的链表应为链表2；如果链表2为空，那么合并后的链表应为链表1。如果两个链表都为空，那么合并后的链表也为空。
 * */
public class Code08_mergerTwoSortedList {


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //判断两个是否都为空
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode head = l1.value >= l2.value ? l1 : l2;
        ListNode cur1 = head.next;
        ListNode cur2 = head == l1 ? l2 : l1;
        ListNode pre = head;

        //两个都不为空
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }

        //两个中有一个遍历结束跳出了循环
        pre.next = cur1 == null ? l2 : l1;

        return head;

    }


    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }


}
