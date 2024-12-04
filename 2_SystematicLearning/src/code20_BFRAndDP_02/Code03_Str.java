package code20_BFRAndDP_02;


import java.util.HashMap;

/// 给定一个字符串arr，给定一个字符串类型的数组arr，出现的字符都是小写英文
/// arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出来str来
/// 返回需要至少多少张贴纸可以完成这个任务
/// 例子：str=“babac”，arr={“ba”,“c”,“bcdef”}
/// ba + ba + c 为3
/// abcd + abcd 为2
/// 所以返回2
///
/// 注意点：与贴纸的数量无关
public class Code03_Str {

    /// 第一种：暴力递归
    /// 思路：将str贴掉第一个，看看之后还要几张贴纸，将str贴纸第一次贴第二个，看看之后还要几张贴纸
    /// 存在无效解 “zxy” ： 贴纸：“a” “b”
    public static int dp(String target, String[] stickers) {
        if (target == null || target.length() == 0) {
            return 0;
        }
        return process(target, stickers);
    }


    public static int process(String target, String[] stickers) {
        //1.base case : starget已经为0了，不需要在使用贴纸了
        if (target.length() == 0) {
            return 0;
        }
        //2.处理逻辑，尝试方法
        //2.1.先拿取到最大的值
        //2.2.依次遍历剪掉一个字符，看剩下的字符串是否可以拼出来
        //2.3.如果剪掉该字符的过程中，该字符没有发生变化，则跳过该字符，说明是无效解「最后如果最后一个也是无效解那就是 target根本化解不掉」
        int restMin = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus(target, sticker);
            if (rest.length() != target.length()) {
                restMin = Math.min(restMin, process(rest, stickers));
            }
        }
        return restMin + (restMin == Integer.MAX_VALUE ? 0 : 1);
    }

    /// target - str 的数量
    public static String minus(String target, String str) {
        char[] chars = target.toCharArray();
        char[] strings = str.toCharArray();
        int[] counts = new int[26];
        for (char c : chars) {
            counts[c - 'a']++;
        }
        for (char c : strings) {
            counts[c - 'a']--;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                for (int j = 0; j < counts[i]; j++) {
                    builder.append((char) ('a' + i));
                }
            }
        }
        return builder.toString();
    }


    /// int[] 就是贴纸数
    /// int[][] stickers 就是词频统计
    /// 优化：
    /// 1.少了拼接的过程，直接使用了词频统计
    /// 2.直接从左开始往右开始遍历并且递归，在一次遍历中，如果stickers[i]没有包含target，那不会再使用到这个张贴纸，不会像上面的再去调用
    /// 上面的process是剪掉了一个贴纸，还会从头开始遍历，比上面可以提告命中率
    public static int process2(int[][] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        char[] targets = target.toCharArray();
        int[] counts = new int[26];
        for (char c : targets) {
            counts[c - 'a']++;
        }

        int N = stickers.length;
        int restMin = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            //1.尝试第i张贴纸
            int[] sticker = stickers[i];
            //2.最重要的一步，最关键的优化
            //2.1.贴纸中包含了target中的字符,剪掉这个贴纸
            if (sticker[targets[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (counts[j] > 0) {
                        int nums = counts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) ('a' + j));
                        }
                    }
                }
                //2.2.剪掉这个贴纸之后，再去计算后面的贴纸与这个剩余字符串
                String rest = builder.toString();
                restMin = Math.min(restMin, process2(stickers, rest));//进入递归
            }
        }
        return restMin + (restMin == Integer.MAX_VALUE ? 0 : 1);
    }




    /// 第二种：记忆化搜索，傻缓存 这个只能做到这一步
    public static int process3(int[][] stickers, String target, HashMap<String,Integer> dp) {
        //base case 变了
        if (dp.containsKey(target)){
            return dp.get(target);
        }
        char[] targets = target.toCharArray();
        int[] counts = new int[26];
        for (char c : targets) {
            counts[c - 'a']++;
        }
        int N = stickers.length;
        int restMin = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[targets[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (counts[j] > 0) {
                        int nums = counts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) ('a' + j));
                        }
                    }
                }
                String rest = builder.toString();
                restMin = Math.min(restMin, process3(stickers, rest,dp));//进入递归，这里变了
            }
        }
        int ans = restMin + (restMin == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target,ans);
        return ans;
    }


}
