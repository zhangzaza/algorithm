package code04_list;

/*
* 单链表实现栈
* 1. 入栈：头插法
*
* */

public class Code04_stackOfSingleList<V> {


    private  Node<V> head;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public void push(V value){
        Node<V> node = new Node<>(value);
        if (head!=null){
            //新头指向老头
            node.next=node;
            head=node;
        }else {
            head =node;
        }
        size++;
    }


    public V pop(){
        if(head==null){
            return null;
        }
        Node<V> node = head;
        head=head.next;
        size--;
        return node.value;
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
