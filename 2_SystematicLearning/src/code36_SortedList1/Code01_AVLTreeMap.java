package code36_SortedList1;

/// 搜索二叉树的性质
/// 节点特性：
/// 1.每个节点最多有两个子节点，分别称为左子节点和右子节点。
/// 2.对于每个节点，左子树中所有节点的键值都小于该节点的键值。
/// 3.右子树中所有节点的键值都大于该节点的键值。
/// 递归性质：
/// 1.左子树和右子树本身也是二叉搜索树。
///
/// 删除（Delete）：
/// 删除节点有三种情况：
/// 1.叶节点：直接删除。
/// 2.有一个子节点：用该子节点替代被删除节点。
/// 3.有两个子节点：找到右子树中最小的节点（或左子树中最大的节点），替代被删除节点，然后删除替代节点。
///
///
/// 插入（Insert）：
/// 从根节点开始，比较待插入值与当前节点的键值。
/// 1.若待插入值小于节点的键值，递归或迭代地在左子树中寻找插入位置。
/// 2.若大于，在右子树中寻找插入位置。
/// 3.找到空位后，将新节点插入。


import com.sun.source.tree.BreakTree;

/// AVL 树就是在 搜索二叉树的形式下，保证每个节点的左右子树的高度差不超过 1。
/// AVL树的标准特性
/// 平衡因子：
/// 1.每个节点都有一个平衡因子（Balance Factor），它是左子树高度减去右子树高度的值。
/// 2.对于AVL树的每个节点，其平衡因子必须是-1、0或1。这意味着树在每个节点上都近似平衡。
/// 高度平衡：
/// 1.AVL树是高度平衡的二叉搜索树。其高度的平衡特性保证了在最坏情况下，基本操作的时间复杂度为O(log n)。
public class Code01_AVLTreeMap<K extends Comparable<K>, V> {


    private AVLNode<K, V> root;
    private int size;

    /**
     * 平衡二叉树树的节点
     */
    public static class AVLNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public AVLNode<K, V> left;
        public AVLNode<K, V> right;
        public int height;

        public AVLNode(K key, V value) {
            this.key = key;
            this.value = value;
            height = 1;
        }


        // 右旋 图片「Code01_12」
        public AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.left;
            //调整节点的指向
            cur.left = left.right;
            left.right = cur;
            //重新调整两个节点的高度
            cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
            left.height = Math.max(left.left != null ? left.left.height : 0, left.right != null ? left.right.height : 0) + 1;
            return left;
        }


        // 左旋 原理与右旋相同
        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.right;
            cur.right = right.left;
            right.left = cur;
            cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
            right.height = Math.max(right.left != null ? right.left.height : 0, right.right != null ? right.right.height : 0) + 1;
            return right;
        }


        // 添加节点
        // 搜索二叉树不接受相同的key
        private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
            if (cur == null) {
                return new AVLNode<K, V>(key, value);
            } else {
                if (key.compareTo(cur.key) < 0) {
                    cur.left = add(cur.left, key, value); // 左子树可能是换头的，所以必须返回一个节点
                } else {
                    cur.right = add(cur.right, key, value); // 右子树也可能是换头的，所以必须返回一个节点
                }
                cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
                // 对该节点调整平衡
                return maintain(cur);
            }
        }


        // 在cur这棵树上，删掉key所代表的节点
        // 返回cur这棵树的新头部
        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right, key);
            } else if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left, key);
            } else {
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else {
                    AVLNode<K, V> next = cur.right;//拿到原先的右节点
                    while (next.left != null) {//拿到最左节点
                        next = next.left;
                    }
                    cur.right = delete(cur.right, next.key);//删除原先右子树上，key为next.key的节点
                    next.left = cur.left; // 调整指针
                    next.right = cur.right; // 调整指针
                    cur = next;
                }
            }
            if (cur != null) {
                //调整高度
                cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
            }
            return maintain(cur);
        }


        // 平衡性调整
        private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
            if (cur == null) {
                return null;
            }
            int leftHeight = cur.left != null ? cur.left.height : 0;
            int rightHeight = cur.right != null ? cur.right.height : 0;
            if (Math.abs(leftHeight - rightHeight) > 1) {
                if (leftHeight > rightHeight) {// LL 或者 LL与LR联合不平衡的情况
                    int leftLeftHeight = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
                    int leftRightHeight = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;
                    if (leftLeftHeight >= leftRightHeight) {// LL 型 ，注意这个大于等于
                        cur = cur.rightRotate(cur);
                    } else {//LL与LR联合不平衡的情况
                        cur.left = leftRotate(cur.left);
                        cur = rightRotate(cur);
                    }
                } else {// RR 或者 RL与RR联合不平衡的情况
                    int rightLeftHeight = cur.right != null && cur.right.left != null ? cur.right.left.height : 0;
                    int rightRightHeight = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;
                    if (rightRightHeight >= rightLeftHeight) {// RR 型 ，注意这个大于等于
                        cur = leftRotate(cur);
                    } else {//RL与RR联合不平衡的情况
                        cur.right = rightRotate(cur.right);
                        cur = leftRotate(cur);
                    }
                }

            }
            return cur;
        }
    }


    public V get(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> lastNode = findLastIndex(key);
        if (lastNode != null && key.compareTo(lastNode.key) == 0) {
            return lastNode.value;
        }
        return null;
    }


    public AVLNode<K, V> findLastIndex(K key) {
        AVLNode<K, V> pre = root;
        AVLNode<K, V> cur = root;
        while (cur != null) {
            pre = cur;
            if (key.compareTo(cur.key) == 0) {
                break;
            } else if (key.compareTo(cur.key) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return pre;
    }


    private AVLNode<K, V> findLastNoSmallIndex(K key) {
        AVLNode<K, V> ans = null;
        AVLNode<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.key) < 0) {
                ans = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return ans;
    }


    public K firstKey() {
        if (root == null) {
            return null;
        }
        AVLNode<K, V> cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.key;
    }


    public K lastKey() {
        if (root == null) {
            return null;
        }
        AVLNode<K, V> cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.key;
    }


    public K floorKey(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
        return lastNoBigNode != null ? lastNoBigNode.key : null;
    }

    private AVLNode<K, V> findLastNoBigIndex(K key) {
        AVLNode<K, V> ans = null;
        AVLNode<K, V> cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                ans = cur;
                break;
            } else if (key.compareTo(cur.key) > 0) {
                ans = cur;
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return ans;
    }


    public void remove(K key) {
        if (key == null) {
            return;
        }
        if (containsKey(key)) {
            size--;
            root = delete(root, key);
        }
    }

    private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
        if (cur == null) {
            return null;
        }
        cur = cur.delete(cur, key);
        return cur;
    }


    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        AVLNode<K, V> lastNode = findLastIndex(key);
        return lastNode != null && key.compareTo(lastNode.key) == 0;
    }


    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        AVLNode<K, V> lastNode = findLastIndex(key);
        if (lastNode != null && key.compareTo(lastNode.key) == 0) {
            lastNode.value = value;
        } else {
            size++;
            root = add(root, key, value);
        }
    }

    public AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
        if (cur == null) {
            return new AVLNode<>(key, value);
        }
        if (key.compareTo(cur.key) < 0) {
            cur.left = add(cur.left, key, value);
        } else if (key.compareTo(cur.key) > 0) {
            cur.right = add(cur.right, key, value);
        } else {
            return cur;
        }
        return cur;
    }


    public K ceilingKey(K key) {
        if (key == null) {
            return null;
        }
        AVLNode<K, V> lastNoSmallIndex = findLastNoSmallIndex(key);
        return lastNoSmallIndex != null ? lastNoSmallIndex.key : null;
    }


}
