package code04_list;

/*
* 双链表实现双端队列
*1.单链表实现不了因为不知道前面那个，不能都从两端拿数据
*2.poll 弹出
*3.push 推入
* */
public class Code05_doubleQueueOfDoubleList<V> {


    private DoubleNode<V> head;
    private DoubleNode<V> tail;
    private int size;


    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize () {
        return size ;
    }


    public Code05_doubleQueueOfDoubleList(){
        head=null;
        tail=null;
        size=0;
    }


    public void pushHead(V value) {
        DoubleNode<V> node =new DoubleNode<>(value);
        if (head ==null){
            head=node;
            tail=node;
        }else {
            node.next=head;
            head.last=node;

            //移位，更换环境
            head = node ;
        }
        size++;
    }

    public void pushTail(V value){
        DoubleNode<V> node =new DoubleNode<>(value);
        if (tail ==null){
            tail=node;
            head =node;
        }else{
            node.last=tail;
            tail.next=node;

            //移位，更换环境
            tail=node;
        }
        size++;
    }


    public V pollHead() {
        V value = null;
        if (head==null){
            return value;
        }
        size--;
        if (head==tail){
            value=head.value;
            head=null;
            tail=null;
        }else {
            head=head.next;
            head.last=null;
        }
        return value;
    }


    public V pollTail() {
        V value =null;
        if (tail ==null){
            return value;
        }
        size--;
        if (head==tail){
            value=tail.value;
            head=null;
            tail=null;
        }else {
            tail=tail.last;
            tail.next=null;
        }
        return value;
    }


    class DoubleNode<V>{
        public V value;
        public DoubleNode<V> last;
        public DoubleNode<V> next;
        public DoubleNode(V value) {
            this.value = value;
            this.last=null;
            this.next=null;
        }
    }


}
