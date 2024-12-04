package code04_list;

/*双向链表的逆序*/
public class Code02_reverseDoubleList {

    //和前面一样，记现在的环境然后走下一步
    public static DoubleNode reverseDoubleList(DoubleNode head){
        DoubleNode pre =null;
        DoubleNode next =null;
        while (head != null){
            next = head.next;

            //就这两步骤是操作
            head.next=pre;
            head.last=next;

            pre=head;
            head=next;
        }
        return pre;
    }

}


class DoubleNode{
    public int value;
    public DoubleNode last;
    public DoubleNode next;

    public DoubleNode(int data){
        value = data;
    }
}