package code28_KMP;

/// KMP 解决的问题 ：
/// KMP（Knuth-Morris-Pratt）算法主要用于解决字符串匹配问题。具体来说，它用于在一个文本字符串中寻找一个模式字符串（子串）的出现位置
///
/// 常规思路 ： 遍历一遍，时间复杂度O(N*M)
/// KMP ： 能做到时间复杂度O(N)
public class Code01_KMP {

    public static int getIndexOf(String s1, String m2) {
        if (s1 == null || m2 == null || m2.length() < 1 || s1.length() < m2.length()) {
            return -1;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = m2.toCharArray();
        int x = 0;
        int y = 0;
        int[] next = getNextArray(str2);
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }


    public static int[] getNextArray(char[] str2){
        if (str2.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        // 定义死的下标
        next[0] = -1;
        next[1] = 0;

        int i = 2;//目前在哪个位置上求next数组的值
        int cn = 0;
        while (i < str2.length){
            if (str2[i-1] == str2[cn]){//配成功的时候
                next[i++] = ++cn;
            }else if (cn > 0){
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }
        return next;
    }


}
