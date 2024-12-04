package code17_bitmap1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/// 图的宽度优先遍历
public class Code02_widthTraversal {

    /// 从node出发，进行宽度优先遍历
    public static void bfs(BitmapNode start){
        if (start == null){
            return;
        }

        HashSet<BitmapNode> set = new HashSet<>();
        Queue<BitmapNode> queue = new LinkedList<>();

        queue.add(start);
        set.add(start);

        /// 1.弹出队列，打印，加入set，加入队列
        /// 2.遇到子节点就马上打印「可以理解为层数，只要遍历到了就马上打印」
        /// 3.知道所有的 节点都打印过了
        while(!queue.isEmpty()){
            BitmapNode cur = queue.poll();
            System.out.println(cur.value);
            for (BitmapNode next : cur.nexts){
                if (!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }



}
