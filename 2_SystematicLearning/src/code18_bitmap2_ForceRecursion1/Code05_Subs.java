package code18_bitmap2_ForceRecursion1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/// 第一个问题：打印一个字符串的全部子序列
/// 第二个问题：打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
public class Code05_Subs {


    /// 可以重复的子序列，每个位置上的字母会不一样
    public static List<String> subs(String s){
        char[] str = s.toCharArray();
        String path="";
        List<String> ans = new ArrayList<>();
        process1(str,0,ans,path);
        return ans;
    }

    /// 子序列可以重复
    /// 思路：遍历所有的子序列就是判断每个位置是否选择，如果选择就加入path，否则不加入，会变成一个二叉树的思维
    private static void process1(char[] str, int index, List<String> ans, String path) {
        /// 1.base case
        if (index==str.length){
            ans.add(path);
            return;
        }
        /// 2.不加入
        String no = path;
        process1(str,index+1,ans,no);
        /// 3.加入
        String yes = path+str[index];
        process1(str,index+1,ans,yes);

    }


    /// 子序列不可重复
    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        /// 1.base case
        if (index==str.length){
            set.add(path);
            return;
        }
        /// 2.不加入
        String no = path;
        process2(str,index+1,set,no);
        /// 3.加入
        String yes = path+str[index];
        process2(str,index+1,set,yes);



    }




}
