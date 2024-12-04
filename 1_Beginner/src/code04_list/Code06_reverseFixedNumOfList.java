package code04_list;

/*
* 完整的问题是：逆序调整链表的每K个节点。
* 在这个问题中，给定一个单链表和一个整数K。你的任务是逆序调整链表中每K个节点的顺序。如果链表中的节点数不是K的倍数，剩余的节点应保持原样。
* 例如，如果链表是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9，K的值为3，那么调整后的链表应为：3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 9 -> 8 -> 7。每3个节点一组进行逆序调整。
* */
public class Code06_reverseFixedNumOfList {

    //总流程
    public static ListNode reverseKGroup(ListNode head,int k){
        //「第一遍不参与循环因为要拿到头节点和排除特殊情况」

        //开始这一段的节点
        ListNode start = head;
        //确定下一组逆序的链表段
        ListNode end =getKGroupList(start,k);
        //一组都没满,直接返回
        if (end ==null){
            return head;
        }
        //第一组凑齐了,拿到最先开始的头节点
        head = end;
        //第一段进行逆序
        reserve(start,end);

        //上一组的结尾节点「已经逆序」，start这时候就是第二组的头节点
        ListNode lastEnd = start;
        while(lastEnd.next!=null) {
            //重新开始确定这一段的头
            start = lastEnd.next;
            //下一段是否存在k个，end是下第二段开始的第一个节点
            end = getKGroupList(start, k);
            if (end == null) {
                return head;
            }
            //重新进行逆序
            reserve(start, end);


            //这一段结束的下一个节点指向end
            lastEnd.next = end;
            //排完序之后又变成尾
            lastEnd = start;

        }
        return head;

    }



    /*规定的两个链表段 进行逆序*/
    public static void reserve(ListNode start , ListNode end){
        end = end.next;

        //逆序的过程
        ListNode pre = null;
        ListNode cur = start;
        ListNode next=null;
        while(cur!=end){
            next=cur.next;
            cur.next=pre;
            pre = cur;
            cur =next;
        }

        start.next=end;

    }



    /*这里确定下一组逆序的是否存在k个*/
    public static ListNode getKGroupList(ListNode start, int k) {
        if (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }


}
