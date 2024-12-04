package code10_List_SortSummary;


import java.util.HashMap;

/*
 * rand指针是单链表节点结构中新增的指针
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点
 * 要求：时间复杂度O(N),额外空间复杂度O(1)
 * */
public class Code05_randomList {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }


    //第一种方法：用容器的方法
    public static Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;//新节点链表头

        // 第二步：建立新链表，并建立新旧链表之间的对应关系
        while (cur != null) {
            //map.get(cur):新节点
            //map.get(cur.next)：老节点
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }


    //第二种方法：链表添加和拆分
    public static Node copyListWithRand2(Node head) {
        Node cur = head;
        Node next = null;
        // 第一步：复制每个节点，并插入到原节点后面
        //1->2
        //1->1'->2
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;

        Node copy = null;

        // 第二步：建立新旧节点之间的对应关系
        // 1->1'->2->2'
        while (cur != null) {
            //cur 老
            //cur.next 新 copy
            next = cur.next.next;
            copy = cur.next;
            //老节点的ran不为空，就指向老节点的next，需要注意的是这里的next节点是已经变化过后的
            copy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }

        Node res = head.next;
        cur = head;
        //第三步：拆分链表
        while (cur != null) {
            next = cur.next.next;//1->2
            copy = cur.next;//1'
            //调整老链表
            cur.next = next;//1->2
            //新链表，如果2 不为空，就将 2‘ 加到新数组后
            copy.next = next != null ? next.next : null;//1'->2'
            cur = next;
        }

        //更加可以看出这里是引用传递的关系
        return  res;


    }


}
