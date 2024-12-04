package code04_list;

/*
* 单链表实现的栈
* 1.是否为空
* 2.返回size
* 3.poll（） 出队列
* 4.peek（） 返回队列第一个元素，但是不弹出
* 5.offer() 入队列
*
* */
public class Code03_queueOfSingleList<V> {

    //有两个节点，是因为 栈的话是栈顶和栈底，所以需要两个节点，没有head，就会消失，没有tail，那每次都需要遍历
    private Node<V> head;
    private Node<V> tail;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void offer(V node){
        Node<V> nodeNode = new Node<>(node);
        if (tail==null){
            head=nodeNode;
            tail=nodeNode;
        }else{
            //尾部指向新的节点
            tail.next=nodeNode;

            //换环境
            tail=nodeNode;
        }
        size++;
    }

    public V peek(){
        V value = null;
        if (head!=null){
            return head.value;
        }
        return value;
    }


    public V poll(){
        V value = null;
        if (head != null){
            value= head.value;
            head=head.next;
            size--;
        }
        if (head==null){
            tail=null;
        }
        return value;

    }








    class Node<V>{
        V value;
        Node<V> next;

        public Node(V value)
        {
            this.value = value;
            next = null;
        }
    }


}


