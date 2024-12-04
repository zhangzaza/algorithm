package code07_binaryTree2;


import java.util.ArrayList;
import java.util.List;

//测试链接 ： https://leetcode.com/problems/path-sum-li
//算累加和 + 还需要算到这个累加和的路径
//叶子节点（Leaf Node）是树形数据结构中的一种特殊节点。具体来说，叶子节点是没有子节点的节点。换句话说，叶子节点是树中最底层的节点，它们既没有左子节点也没有右子节点。
public class Code05_binaryPathSum2 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //获取路径
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        process(root,path,0,sum,res);
        return res;
    }



    private static List<List<Integer>> process(TreeNode root,List<Integer> path, int preSum,int sum,List<List<Integer>> ans) {
        //叶子节点
        if(root.left == null && root.right == null) {
            if(preSum + root.val == sum) {
                path.add(root.val);
                ans.add(copy(path));
                path.remove(path.size()-1);//恢复现场，删除自己
            }
        }

        //非叶子节点
        path.add(root.val);
        if(root.left != null) {
            process(root.left,path,preSum+root.val,sum,ans);
            //path.remove(path.size()-1);
        }

        if(root.right != null) {
            process(root.right,path,preSum+root.val,sum,ans);
            //path.remove(path.size()-1);
        }
        
        //将上述的提取出来，恢复现场，删除自己
        path.remove(path.size()-1);

        return ans;

    }




    //copy函数
    private static List<Integer> copy(List<Integer> arr) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            res.add(arr.get(i));
        }
        return res;
    }





}
