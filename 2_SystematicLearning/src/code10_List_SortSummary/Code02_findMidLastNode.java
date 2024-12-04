package code10_List_SortSummary;




/*
* 面试时链表解决的方法论
* 1.对于笔试，不用太在乎空间复杂度，一切为了时间复杂度
* 2.对于笔试，时间复杂度依然放在第一位，但是一定要找空间复杂度最低的情况
* */


//单链表：满足条件
//1.奇数个返回唯一的中点的上一个
//2.偶数个返回中间上中点的上一个
public class Code02_findMidLastNode {


    //使用快慢指针实现
    public static ListNode findMidLastNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }

        // 如果 fast 指针不为 null，说明链表长度是奇数
        if (fast != null) {
            return prev; // 返回中点的上一个节点
        } else {
            return prev; // 返回上中点的上一个节点
        }
    }

    class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
            this.next = null;
        }
    }


}
