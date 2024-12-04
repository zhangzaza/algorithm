package code38_SortedList3;

import java.util.HashSet;

/// 在数组中有一个滑动窗口，长度为k，窗口移动的过程中取每一个窗口的状态的中位数
/// 「相对于画图，题目是阉割版的」
public class Code02_MidIndexWindows {

    public static double[] medianSlidingWindow(int[] arr, int k) {
        SizeBalancedTreeSet map = new SizeBalancedTreeSet();

        // 1. 将前 k 个元素放入 map 中
        for (int i = 0; i < k; i++) {
            map.add(arr[i]);
        }

        double[] ans = new double[arr.length - k + 1];
        int index = 0;

        for (int i = k; i <= arr.length; i++) {
            // 2.1. 计算当前窗口的中位数
            if (map.size() % 2 == 0) { // 如果是偶数
                ans[index++] = (map.getIndexValue(map.size() / 2) + map.getIndexValue(map.size() / 2 - 1)) / 2.0;
            } else { // 如果是奇数
                ans[index++] = map.getIndexValue(map.size() / 2);
            }

            // 2.2. 删除窗口最左边的元素
            if (i < arr.length) {
                map.remove(arr[i - k]);
                // 2.3. 添加新的元素
                map.add(arr[i]);
            }
        }

        return ans;
    }


     static class SBTNode {
        int key;
        SBTNode left;
        SBTNode right;
        int size; // 不同的key的size
        int all; // 总的size

        public SBTNode(int key) {
            this.key = key;
            this.size = 1;
            this.all = 1;
        }
    }

    static class SizeBalancedTreeSet {
        private SBTNode root;
        private HashSet<Integer> set = new HashSet<>();

        private SBTNode rightRotate(SBTNode cur) {
            int same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
            SBTNode leftNode = cur.left;
            cur.left = leftNode.right;
            leftNode.right = cur;
            leftNode.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            leftNode.all = cur.all;
            cur.all = (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0) + same;
            return leftNode;
        }

        private SBTNode leftRotate(SBTNode cur) {
            int same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
            SBTNode rightNode = cur.right;
            cur.right = rightNode.left;
            rightNode.left = cur;
            rightNode.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            rightNode.all = cur.all;
            cur.all = (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0) + same;
            return rightNode;
        }

        private SBTNode add(SBTNode cur, int key, boolean contains) {
            if (cur == null) {
                return new SBTNode(key);
            } else {
                cur.all++;
                if (key == cur.key) {
                    return cur;
                } else {
                    if (!contains) {
                        cur.size++;
                    }
                    if (key < cur.key) {
                        cur.left = add(cur.left, key, contains);
                    } else {
                        cur.right = add(cur.right, key, contains);
                    }
                    return maintain(cur);
                }
            }
        }

        private SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left != null ? cur.left.size : 0;
            int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rightSize = cur.right != null ? cur.right.size : 0;
            int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }

        public void add(int key) {
            boolean contains = set.contains(key);
            root = add(root, key, contains);
            set.add(key);
        }

        public void remove(int key) {
            if (set.contains(key)) {
                set.remove(key);
                root = remove(root, key);
            }
        }

        private SBTNode remove(SBTNode cur, int key) {
            if (cur == null) {
                return null;
            }
            cur.all--;
            if (key < cur.key) {
                cur.left = remove(cur.left, key);
            } else if (key > cur.key) {
                cur.right = remove(cur.right, key);
            } else {
                if (cur.left == null && cur.right == null) {
                    return null;
                } else if (cur.left == null) {
                    return cur.right;
                } else if (cur.right == null) {
                    return cur.left;
                } else {
                    SBTNode successor = getSuccessor(cur);
                    cur.key = successor.key;
                    cur.size--;
                    cur.right = remove(cur.right, successor.key);
                }
            }
            return maintain(cur);
        }

        private SBTNode getSuccessor(SBTNode node) {
            SBTNode successorParent = node;
            SBTNode successor = node.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            if (successor != node.right) {
                successorParent.left = successor.right;
                successor.right = node.right;
            }
            return successor;
        }

        public int size() {
            return root != null ? root.size : 0;
        }

        public int getIndexValue(int index) {
            SBTNode cur = root;
            int rank = 0;
            while (cur != null) {
                int leftSize = cur.left != null ? cur.left.size : 0;
                if (index < rank + leftSize) {
                    cur = cur.left;
                } else if (index == rank + leftSize) {
                    return cur.key;
                } else {
                    rank += leftSize + 1;
                    cur = cur.right;
                }
            }
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
    }



}
