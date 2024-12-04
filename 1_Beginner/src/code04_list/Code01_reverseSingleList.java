package code04_list;

/*单链表的逆序*/
public class Code01_reverseSingleList {
    //java 的引用传递
    //如果调用了 其他方法，并且是一个引用对象，传进去的只是一个引用，不会改变原来的值
    //例子 ： 我在外面定义了 Node（1）为node ，然后node传进去后修改了值变成node（2），对于外部来说 node还是还是Node（1）

    /*拿纸画一下*/

    public static Node reverseList(Node head){
        Node pre = null;
        Node next = null;
        while(head != null){
            next=head.next;

            //就这一步是操作
            head.next=pre;

            pre=head;
            head=next;
        }
        return head;
    }

}

class Node{
    int value;
    Node next;
    public Node(int value){
        this.value = value;
    }

}