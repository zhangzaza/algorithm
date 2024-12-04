package code04_DataStructure;


//基本数据结构
public class Code01_ListDataStructure {


    //单链表指针
    public static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }


    //双链表指针
    public static class DoubleNode<T> {
        T value;
        DoubleNode<T> last;
        DoubleNode<T> next;

        public DoubleNode(T value) {
            this.value = value;
        }
    }

    //单链表的反转
    public static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head!=null){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }



}
