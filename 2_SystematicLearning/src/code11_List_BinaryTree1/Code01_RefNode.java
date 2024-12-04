package code11_List_BinaryTree1;


//链表的引用传递
public class Code01_RefNode {

    public static class Node {
        public int value;
        public Node next;

        public Node() {
            this.value = 0;
        }

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        //node1 和 node2 都指向同一个 Node 对象。当你通过 node1 修改 value 属性时，node2 也会看到这个变化，因为它们引用的是同一个对象。
        Node node1 = new Node(); // 创建一个新的 Node 对象
        Node node2 = node1; // node2 指向 node1 所指向的同一个对象
        node1.value = 5; // 修改 node1 的 value 属性
        System.out.println(node2.value); // 通过 node2 访问 value 属性，将会输出 5


    }


}
