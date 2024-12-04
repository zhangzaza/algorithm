package code18_bitmap2_ForceRecursion1;

import java.util.ArrayList;
import java.util.List;

/// 第一个问题：打印一个字符串的全部排列
/// 第二个问题：打印一个字符串的全部排列，要求不要出现重复的排列
public class Code06_Permutation1 {


    public static List<String> permutation1(String str) {
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return ans;
        }
        char[] chs = str.toCharArray();
        ArrayList<Character> rest = new ArrayList<>();
        for (char ch : chs) {
            rest.add(ch);
        }
        String path = "";
        process1(rest, path, ans);
        return ans;

    }


    // 从rest中，依次取出一个字符，得到一个字符串，再从rest中取出一个字符，得到一个字符串，依次类推，得到所有的字符串
    public static void process1(ArrayList<Character> rest, String path, List<String> ans) {
        if (rest.size() == 0) {
            ans.add(path);
        } else {
            for (int i = 0; i < rest.size(); i++) {
                Character cur = rest.get(i);
                rest.remove(i);
                process1(rest, path + cur, ans);
                rest.add(i, cur);//恢复现场
            }
        }
    }


}
