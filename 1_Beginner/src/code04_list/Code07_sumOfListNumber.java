package code04_list;

/*
* 完整的问题是：给定两个链表，表示两个非负整数，链表的每个节点代表整数的一位数字，将这两个整数相加并以链表形式返回结果。
* 例如，输入链表1为：2 -> 4 -> 3，链表2为：5 -> 6 -> 4，表示的整数分别为342和465。将这两个整数相加得到结果807，返回的链表为：7 -> 0 -> 8。注意，返回的链表应该是逆序的，即个位数在链表的头部。
* 如果输入链表1为：0，链表2为：0，表示的整数分别为0和0。将这两个整数相加得到结果0，返回的链表为：0。
* 如果输入链表1为：9 -> 9 -> 9，链表2为：1，表示的整数分别为999和1。将这两个整数相加得到结果1000，返回的链表为：0 -> 0 -> 0 -> 1。
* */
public class Code07_sumOfListNumber {

    /*
    * 1.获取链表长度，有两个链表，一个l ，一个r
    * 2.第一个阶段，两个量表都有
    * 3.第二个阶段，一个链表有，一个链表没有
    * 4.第三个阶段，两个链表都为空
    * */
    public static ListNode sumOfListNumber(ListNode head1,ListNode head2){

        int len1=listLength(head1);
        int len2=listLength(head2);
        //区分长短节点
        ListNode l = len1 >= len2?head1:head1;
        ListNode s = l == head1 ?head1:head1;

        ListNode curL=l;
        ListNode curS=s;

        //一直跟踪最后一个节点
        ListNode last =curL;
        int carry = 0;
        int curNum=0;

        //第一个阶段
        while (curL != null && curS != null) {
            curNum=curS.value+curL.value+carry;
            curL.value=curNum%10;
            carry=curNum/10;
            last=curL;
            curL=curL.next;
            curS=curS.next;
        }

        //第二个阶段
        while (curL != null) {
            curNum=curL.value+carry;
            curL.value=curNum%10;
            carry=curNum/10;
            last=curL;
            curL=curL.next;
        }

        //第三个阶段
        if (carry!=0){
            last.next=new ListNode(1);
        }

        return l;

    }





    public static int listLength(ListNode head){
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
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
