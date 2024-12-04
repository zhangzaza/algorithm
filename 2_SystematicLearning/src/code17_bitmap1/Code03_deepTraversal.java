package code17_bitmap1;

import java.util.HashSet;
import java.util.Stack;

/// 深度优先遍历
public class Code03_deepTraversal {

    public static void dfs(BitmapNode node){
        if (node == null){
            return;
        }
        Stack<BitmapNode> stack = new Stack<>();
        HashSet<BitmapNode> set = new HashSet<>();

        stack.push(node);
        set.add(node);
        System.out.println(node.value);

        ///1.上面已经打印头节点
        ///2.弹出栈顶节点，拿取该节点的子节点
        ///3.打印子节点
        ///4.再将子节点压入stack和set中，如果set中不包含，打印 break，如果包含，就已经弹出了不操作，继续下一个子节点
        /// 注意点：这里的迭代是用stack来实现的，并且是每遇到一个新的子节点就打印
        while (!stack.isEmpty()){
            BitmapNode cur = stack.pop();
            for (BitmapNode next : cur.nexts){
                if (!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }

    }

}
