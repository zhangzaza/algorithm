package code12_BinaryTree2;


/// 微软面试题「一种现象级的二叉树面试题」
//请把一段纸条竖着放在桌面上，然后从纸条的下边向上方对着一次，压出折痕后展开。
//此时折痕是凹下去，即折痕凸起的方向指向纸条的背面。
//如果从纸条的下边向上方连续对着2次，那么每次压出折痕后展开，此时有三条折痕，从上到下依次是凹下去，凹下去，凸上去。
//给定一个输入参数N，代表纸条的下边向上方连续压N次，然后展开，打印从上到下打印所有的折痕的方向
//例如：N=1，打印down，N=2，打印down，up，N=3，打印down，down，up
public class Code07_latestMicrosoftInterview {


    //二叉树的中序遍历
    //规则：
    //1.第一个节点肯定是凹折痕
    //2.所有左子树的头都是凹的
    //3.所有右子树的头都是凸的
    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    /*
    * 需要你使用天马行空的想象力
    * 1.这个节点在第i层，一共有N层，N固定不变的
    * 2.这个节点是凹的话，down=true，如果是凸的话，down=false
    * 3.函数的功能：中序打印以你想象的节点为头的整棵树
    * */
    private static void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "down" : "up");
        printProcess(i + 1, N, false);
    }


}
