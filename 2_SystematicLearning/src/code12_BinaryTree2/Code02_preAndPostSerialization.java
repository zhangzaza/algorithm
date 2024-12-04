package code12_BinaryTree2;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//实现二叉树的序列化和反序列化
//1.先序方式序列化和反序列化

///注意点：
/// 1.后序遍历也可以实现
/// 2.中序遍历无法实现 [null,1,null,2,null] 可以生成两种树结构
public class Code02_preAndPostSerialization {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node (int value){
            this.value = value;
        }

    }


    ///序列化
    ///递归序中的先序遍历
    public static Queue<String> preSerialize(Node head) {
        //双端队列
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }
    public static void pres(Node head, Queue<String> ans) {
        if (head == null){
            ans.add(null);
        }else{
            ans.add(String.valueOf(head.value));
            pres(head.left, ans);
            pres(head.right, ans);
        }
    }


    ///反序列化
    public static Node buildByPreQueue(Queue<String> preList) {
        if (preList == null || preList.isEmpty()){
            return null;
        }
        return preBuild(preList);

    }

    private static Node preBuild(Queue<String> preList) {
        String value= preList.poll();
        if (value == null){
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.left = preBuild(preList);
        head.right = preBuild(preList);
        return head;
    }


    ///后序遍历序列化
    public static Queue<String> postSerialize(Node head) {
        //双端队列
        Queue<String> ans = new LinkedList<>();
        posts(head, ans);
        return ans;
    }

    public static void posts(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            posts(head.left, ans);
            posts(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    ///反序列化
    public static Node buildByPostQueue(Queue<String> postList) {
        if (postList == null || postList.isEmpty()) {
            return null;
        }
        // 由于后序遍历是从左、右、根的顺序，需要先将队列转成栈来方便反序列化
        Stack<String> stack = new Stack<>();
        while (!postList.isEmpty()) {
            stack.push(postList.poll());
        }
        return postBuild(stack);
    }

    private static Node postBuild(Stack<String> stack) {
        String value = stack.pop();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        // 由于是后序遍历，先构建右子树，再构建左子树
        head.right = postBuild(stack);
        head.left = postBuild(stack);
        return head;
    }


}
