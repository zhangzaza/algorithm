package code06_binaryTree1;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
* 数个有序单向链表 变成一个 有序的list集合「小根堆实现」
* */
public class Code03_sortList {



    //时间复杂度 小根堆大小M：O(logM) 与 N个链表 ，时间复杂度就是 O(N*logM)
    public static  Node sortList(Node[] nodes ) {
        PriorityQueue<Node> nodeHeap = new PriorityQueue<>(new NodeComparator());
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) { //因为是引用类型，所以也有可能为空
                nodeHeap.add(nodes[i]);
            }
        }

        if (nodeHeap.isEmpty()) {
            return null;
        }

        Node head = nodeHeap.poll(); // 弹出一个先抓住,最后要返回的节点
        Node pre = head;

        if (head.next != null) {
            nodeHeap.add(head.next);
        }

        while (nodeHeap!=null) {
            Node cur = nodeHeap.poll();
            pre.next = cur;
            pre = cur;
            if (cur.next != null) {
                nodeHeap.add(cur.next);
            }
        }

        return head;
    }


    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.data - o2.data;
        }
    }


    public static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
        }
    }



}
