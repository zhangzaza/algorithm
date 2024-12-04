package code18_bitmap2_ForceRecursion1;

import java.util.ArrayList;
import java.util.List;

/// 第一个问题：打印一个字符串的全部排列
/// 第二个问题：打印一个字符串的全部排列，要求不要出现重复的排列
public class Code07_Permutation2 {

    public static List<String> permutation2(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null|| str.isEmpty()){
            return ans;
        }
        char[] chs = str.toCharArray();
        process2(chs,0,ans);
        return ans;
    }


    /// 不去重
    public static void process2(char[] chs,int index,List<String> ans) {
        if (index==chs.length){
            ans.add(String.valueOf(chs));
        }else {
            for (int i = index; i < chs.length; i++) {
                swap(chs,index,i);
                process2(chs,index+1,ans);
                swap(chs,index,i);//回溯,恢复现场
            }
        }
    }


    /// 去重
    public static void process3(char[] str,int index,List<String> ans) {
        if (index==str.length){
            ans.add(String.valueOf(str));
        }else {
            boolean[] visited = new boolean[256];//默认为false
            for (int i = 0; i < str.length; i++) {
                if (!visited[str[i]]){//如果不加这一步，进入到了循环的后面，子process中肯定会替换回来
                    visited[str[i]] = true;
                    swap(str,i,index);
                    process3(str,index+1,ans);
                    swap(str,i,index);
                }
            }
        }
    }

    public static void swap(char[] chs,int a,int b) {
        char tmp = chs[a];
        chs[a] = chs[b];
        chs[b] = tmp;
    }

}
