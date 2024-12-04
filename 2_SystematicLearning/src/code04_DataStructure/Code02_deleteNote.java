package code04_DataStructure;


//删除一个单链表的固定的元素
public class Code02_deleteNote {


    //单链表指针
    public static class Node {
        int  value;
        Node next;

        public Node(int  value) {
            this.value = value;
        }
    }


    public static Node removeValue(Node head,int num){
        //head来到第一个不需要删的位置
        while (head!=null){
            if(head.value!=num){
                break;
            }
            head=head.next;
        }

        //还是三个浮标进行控制
        Node pre=head;
        Node cur=head;
        while (cur!=null){
            if (cur.value==num){
                pre.next=cur.next;
            }else {
                pre=cur;
            }
            cur=cur.next;
        }
        return  head;

    }


    //注意点：需要使用 「Node<你传入的类型>」 这种形式进行包装
    //队列玩的就是两个指针，一个头指针一个尾指针，双向队列实现
    //栈的话也是一样的，只需要找一个指针，双向队列实现


}
