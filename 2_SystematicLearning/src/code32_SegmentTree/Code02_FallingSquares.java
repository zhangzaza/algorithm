package code32_SegmentTree;


import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/falling-squares
public class Code02_FallingSquares {

    private Node root;

    public List<Integer> fallingSquares(int[][] positions) {
        // 线段树
        int max = (int) (1e9);
        root = new Node(0, max);
        List<Integer> res = new ArrayList<>();
        for (int[] position : positions) {
            int rangeMaxValue = query(root, position[0], position[0] + position[1] - 1);
            update(root, position[0], position[0] + position[1] - 1, rangeMaxValue + position[1]);
            int curGlobalMaxValue = query(root, 0, max);
            res.add(curGlobalMaxValue);
        }
        return res;
    }

    /**
     * 线段树的结点
     */
    class Node {
        private int leftX; // 左范围
        private int rightX; // 右范围
        private int maxValue; // 范围内的最大值
        private int lazy; // 懒标记
        Node leftChild, rightChild; // 左子树和右子树

        public Node(int leftX, int rightX) {
            this.leftX = leftX;
            this.rightX = rightX;
        }
    }

    /**
     * 区间求值
     *
     * @param root  树的根
     * @param left  左边界
     * @param right 右边界
     */
    public int query(Node root, int left, int right) {
        // 不在范围内 直接返回
        if (root.rightX < left || right < root.leftX) {
            return 0;
        }
        // 查询的区间包含当前结点
        if (left <= root.leftX && root.rightX <= right) {
            return root.maxValue;
        } else {
            lazyCreate(root); // 动态开点
            pushDown(root); // 下传 lazy
            int l = query(root.leftChild, left, right); // 求左子树
            int r = query(root.rightChild, left, right); // 求右子树
            return Math.max(l, r);
        }
    }

    /**
     * 区间更新
     *
     * @param root  树的根
     * @param left  左边界
     * @param right 右边界
     * @param value 更新值
     */
    public void update(Node root, int left, int right, int value) {
        // 不在范围内 直接返回
        if (root.rightX < left || right < root.leftX) {
            return;
        }
        // 修改的区间包含当前结点
        if (left <= root.leftX && root.rightX <= right) {
            root.maxValue = value;
            root.lazy = value;
        } else {
            lazyCreate(root); // 动态开点
            pushDown(root); // 下传 lazy
            update(root.leftChild, left, right, value); // 更新左子树
            update(root.rightChild, left, right, value); // 更新右子树
            pushUp(root); // 上传结果
        }
    }

    /**
     * 创建左右子树
     *
     * @param root
     */
    public void lazyCreate(Node root) {
        if (root.leftChild == null) {
            root.leftChild = new Node(root.leftX, root.leftX + (root.rightX - root.leftX) / 2);
        }
        if (root.rightChild == null) {
            root.rightChild = new Node(root.leftX + (root.rightX - root.leftX) / 2 + 1, root.rightX);
        }
    }

    /**
     * 下传 lazy
     *
     * @param root
     */
    public void pushDown(Node root) {
        if (root.lazy > 0) {
            root.leftChild.maxValue = root.lazy;
            root.leftChild.lazy = root.lazy;
            root.rightChild.maxValue = root.lazy;
            root.rightChild.lazy = root.lazy;
            root.lazy = 0;
        }
    }

    /**
     * 上传结果
     *
     * @param root
     */
    public void pushUp(Node root) {
        root.maxValue = Math.max(root.leftChild.maxValue, root.rightChild.maxValue);
    }
}
