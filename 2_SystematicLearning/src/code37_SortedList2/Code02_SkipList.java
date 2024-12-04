package code37_SortedList2;

import java.util.ArrayList;

public class Code02_SkipList {

    // 跳表的节点定义
    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public ArrayList<SkipListNode<K, V>> nextNodes;//这里面有好多链表，level就是该数组的高度

        public SkipListNode(K key, V value) {
            this.key = key;
            this.value = value;
            nextNodes = new ArrayList<>();
        }


        // 遍历的时候，如果是往右遍历的null(next==null),遍历结束
        // 头(null),头节点的null，认为最小
        // node -> 头，node(null,"") node.isKeyLess(!null) true
        // node里面的key是否比otherKey小，true，不是false
        public boolean isKeyLess(K otherKey) {
            // otherKey==null -> false
            return otherKey != null && (key == null || key.compareTo(otherKey) > 0);

        }

        public boolean isKeyEquals(K key) {
            return key != null && key.compareTo(this.key) == 0;
        }
    }

    public static class SkipListMap<K extends Comparable<K>, V> {
        private static final double PROBABILITY = 0.5;//<=0.5 继续做；>0.5 停止做
        private SkipListNode<K, V> head;
        private int maxLevel;
        private int size;

        public SkipListMap() {
            head = new SkipListNode<K, V>(null, null); //「key是唯一的」
            maxLevel = 0;
            size = 0;
            head.nextNodes.add(null);//0
        }

        // 从最高层开始，一路找下去
        // 最终，找到第0层的 < key 的最右的节点
        private SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> cur = head;
            int level = maxLevel;
            while (level >= 0) { // 从上层跳下层
                // cur level -> level-1
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        // 在level层里，如何往右移动
        // 现在来到的节点是cur，来到了cur的level层，在level层上，找到 < key 最后一个节点并返回
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> next = cur.nextNodes.get(level);
            while (next != null && next.isKeyLess(key)) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }


        // 新增，改value
        // 原理 🌟🌟🌟
        // 1.从最高层开始找，找到最高层的 < key 的最右节点，「通过head，maxLevel和head中的数组找到 第0层的 < key 的最右的节点 」
        // 2.如果存在，修改value
        // 3.如果不存在，新增
        // 3.1.扔骰子，如果扔的数 <= 0.5，继续做，否则停止做，看看level能做到第几层
        // 3.2.如果新层数大于 maxLevel，maxLevel+1，数组加null
        // 3.3.扔出来了几层，就有几层链表，链表 add null一直加几层
        // 3.4.从最高层往下遍历，在每层中，将 newNode加入到 原先两个中间，然后一直塞到 0 层
        public void put(K key, V value) {
            if (key == null) {
                return;
            }
            // 1.
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            // 2.找到了 这个数 下一个节点就是key的话，说明key存在
            if (find != null && find.isKeyEquals(key)) {
                find.value = value;
            } else { // find == null
                size++;
                // 3.1
                int newNodeLevel = 0;
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                // newNodeLevel
                // 3.2
                while (newNodeLevel >= maxLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                // 3.3
                SkipListNode<K, V> newNode = new SkipListNode<K, V>(key, value);
                for (int i = 0; i < newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                // 3.4
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    // level 层中，找到最优的 < key 的节点
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    if (level <= newNodeLevel) {
                        // 在level层中，将 newNode加入到 原先两个中间，然后一直塞到 0 层
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
        }


        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEquals(key);
        }


        // 删除
        // 原理 🌟🌟🌟
        // 1.先判断是否存在，不存在直接不操作 「containsKey」
        // 2.存在的话 从每层开始找 <key 的最右节点，每层进行删除
        // 3.删的时候需要判断 前一个节点是否是 头节点，如果是头节点，则直接 删除该层
        public void remove(K key) {
            // 1.
            if (containsKey(key)) {
                size--;
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                // 2.
                while (level >= 0) {
                    //要删除节点的 前一个节点
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    //要删除的节点
                    SkipListNode<K, V> deleteNode = pre.nextNodes.get(level);
                    //将要删除的节点从链表中删除
                    if (deleteNode != null && deleteNode.isKeyEquals(key)) {
                        pre.nextNodes.set(level, deleteNode.nextNodes.get(level));
                    }
                    // 3. 该节点在该层为独享状态
                    // 3.1.level不是0层
                    // 3.2.通过mostRightLessNodeInLevel方法，结果还是头节点
                    // 3.3.并且该节点的下一个 指向null
                    if (level != 0 && pre.nextNodes.get(level) == null && pre == head) {
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                SkipListNode<K, V> next = cur.nextNodes.get(level);
                while (next != null) {
                    cur = next;
                    next = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        public K firstKey() {
            return head.nextNodes.get(0).key;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.isKeyEquals(key) ? next.key : less.key;
        }

        public K ceilingKey(K key) {
            if (key == null){
                return  null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null ? next.key : null;
        }


        public int size() {
            return size;
        }

        //for test
        public static void printAll(SkipListMap<String, String> map) {
            for (int i = map.maxLevel; i >=0; i--) {
                System.out.println("Level " + i + " : ");
                SkipListNode<String, String> cur = map.head;
                while (cur.nextNodes.get(i) != null) {
                    SkipListNode<String,String> next = cur.nextNodes.get(i);
                    System.out.print(cur.key + " : " + cur.value + " | ");
                    cur = next;
                }
                System.out.println();
            }
        }


    }


}
