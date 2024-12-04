package code38_SortedList3;

import java.util.HashSet;

//测试链接：
//https://leetcode.com/problems/count-of-range-sum/
//给定一个整数数组 nums 和两个整数 lower 和 upper，求出数组中所有连续子数组的范围和（即子数组的和）在 [lower, upper] 范围内的数量。
public class Code01_CountOfRangeSum {



    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 黑盒，加入数组(前缀和)，不去重，可以接受重复数字
        // <num ,有几个数？
        SizeBalancedTreeSet treeSet = new SizeBalancedTreeSet();
        long sum =0;
        int ans =0;
        treeSet.add(0); // 一个都没有的时候，就已经有一个前缀和累加和为0
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // [sum - upper,sum - lower]
            // [10,20] ?
            // < 10 ? < 20 ?
            long lessKeySize = treeSet.lessKeySize(sum - lower +1 );//+1 确保了在 treeSet 中小于等于 sum - lower 的所有元素都被计算在内
            long moreKeySize = treeSet.lessKeySize(sum - upper);
            ans += moreKeySize - lessKeySize;
            treeSet.add(sum);
        }
        return ans;

    }



    public static class SBTNode {
        public long key;
        public SBTNode left;
        public SBTNode right;
        public long size; // 不同的key的size
        public long all; // 总的size

        public SBTNode(long key) {
            this.key = key;
            this.size = 1;
            this.all = 1;
        }
    }


    public static class SizeBalancedTreeSet {
        private SBTNode root;
        private HashSet<Long> set = new HashSet<>();

        private SBTNode rightRotate(SBTNode cur) {
            long same= cur.all - (cur.left !=null?cur.left.all:0)-(cur.right!=null?cur.right.all:0);
            SBTNode leftNode = cur.left;
            cur.left = leftNode.right;
            leftNode.right = cur;
            leftNode.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            //all modify
            leftNode.all=cur.all;
            cur.all=(cur.left != null ? cur.left.all :0) + (cur.right != null ? cur.right.all :0)+same;
            return leftNode;
        }


        private SBTNode leftRotate(SBTNode cur) {
            long same= cur.all - (cur.left !=null?cur.left.all:0)-(cur.right!=null?cur.right.all:0);
            SBTNode rightNode = cur.right;
            cur.right = rightNode.left;
            rightNode.left = cur;
            rightNode.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            //all modify
            rightNode.all=cur.all;
            cur.all=(cur.left != null ? cur.left.all :0) + (cur.right != null ? cur.right.all :0)+same;
            return rightNode;
        }

        private SBTNode add(SBTNode cur,long key,boolean contains) {
            if (cur == null) {
                return new SBTNode(key);
            }else {
                cur.all++;
                if (key == cur.key) {
                    return cur;
                } else {//还在左滑或者右滑
                    if (!contains){
                        cur.size++;
                    }
                    if (key < cur.key) {
                        cur.left = add(cur.left, key,contains);
                    }else {
                        cur.right = add(cur.right, key,contains);
                    }
                    return maintain(cur);
                }
            }
        }

        private SBTNode maintain(SBTNode cur) {
			if (cur == null) {
				return null;
			}
			long leftSize = cur.left != null ? cur.left.size : 0;
			long leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
			long leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
			long rightSize = cur.right != null ? cur.right.size : 0;
			long rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
			long rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
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


        public void add(long sum) {
            boolean contains = set.contains(sum);
            root = add(root, sum,contains);
            set.add(sum);
        }


        public long lessKeySize(long key){
            SBTNode cur = root;
            long lessKeySize = 0;
            while (cur != null) {
                if (key < cur.key) {
                    cur = cur.left;
                } else if(key > cur.key) {
                    lessKeySize += cur.left != null ? cur.left.all : 0;
                    lessKeySize += cur.size;
                    cur = cur.right;
                }else {
                    return lessKeySize + (cur.left != null ? cur.left.all : 0);
                }
            }
            return lessKeySize;
        }
    }





}
