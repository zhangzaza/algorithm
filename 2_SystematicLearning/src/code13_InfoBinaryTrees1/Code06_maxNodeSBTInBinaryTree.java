package code13_InfoBinaryTrees1;

//LeetCode 333: Largest BST Subtree。「hard」「锁题」
//找到一棵树中最大的搜索二叉树，并返回该搜素二叉树的节点数量
public class Code06_maxNodeSBTInBinaryTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
    }

    /// 思路：
    /// 1.左子树 SBT ？
    /// 2.右子树 SBT ？
    /// 3.左 max<X
    /// 4.右 min>X
    /// 5.如果都满足，左 size + 右 size + 1

    /// 每个节点需要的信息：
    /// 1.最大 SBT 的大小
    /// 2.max
    /// 3.min
    /// 4.size
    /// 5.isSBT 「这个点可以省略，应为如果 4==1，那就是搜索二叉树SBT」
    public static class Info {
        public int maxSBTSize;
        public int max;
        public int min;
        public int allSize;
        public Info(int maxSBTSize, int max, int min, int allSize) {
            this.maxSBTSize = maxSBTSize;
            this.max = max;
            this.min = min;
            this.allSize = allSize;
        }
    }


    public static int maxSBTSize(TreeNode head) {
        if (head == null){
            return 0;
        }
        return process(head).maxSBTSize;
    }


    /// 1.拿取并定义我们需要的信息
    /// 2.加工处理完成该节点的定义 「需要知道各种可能性进行判断」
    /// 3.返回该节点的信息
    public static Info process(TreeNode head) {
        if (head == null) {
            return null;
        }
        //1.拿取并定义我们需要的信息
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int maxBSTSize = 0;
        int allSize = 1;
        int max = head.val;
        int min = head.val;

        //2.加工处理完成该节点的定义
        //2.1.拿到取该节点为根节点的最大值 max 和 最小值 min
        if (leftInfo != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            allSize += leftInfo.allSize;
        }
        if (rightInfo != null){
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            allSize += rightInfo.allSize;
        }

        //2.2.左右子树的maxSBTSize
        int p1=-1;
        if (leftInfo != null){
            p1=leftInfo.maxSBTSize;
        }
        int p2=-1;
        if (rightInfo != null){
            p2=rightInfo.maxSBTSize;
        }

        //2.3.判断左右子树是否是搜索二叉树
        int p3=-1;
        boolean leftIsBST = leftInfo == null ? true : (leftInfo.maxSBTSize == leftInfo.allSize && head.val > leftInfo.max);
        boolean rightIsBST = rightInfo == null ? true : (rightInfo.maxSBTSize == rightInfo.allSize && head.val < rightInfo.min);

        //2.4. 判断要不要将该根节点也加入搜索二叉树中
        if (leftIsBST && rightIsBST){
            boolean leftMaxLessHead = leftInfo == null ? true : (leftInfo.max < head.val);
            boolean rightHeadLessMin = rightInfo == null ? true : (rightInfo.min > head.val);
            if (leftMaxLessHead && rightHeadLessMin){
                int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
                int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        maxBSTSize = Math.max(Math.max(p1, p2), p3);

        //3.返回该节点的信息
        return new Info(maxBSTSize,max,min,allSize);
    }



}
