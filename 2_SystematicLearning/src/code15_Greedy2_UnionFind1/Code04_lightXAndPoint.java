package code15_Greedy2_UnionFind1;

/// 给定一个字符串str，只由 "X" 和 “.” 两种字符构成
/// “X” 表示墙，不能放灯，也不需要点亮
/// “.” 表示居民点，可以放灯，需要点亮
/// 如果灯放在i位置，可以让 i-1 ，i和i+1三个位置被点亮
/// 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
public class Code04_lightXAndPoint {

    /// i 为未点亮但是需要点亮的位置
    public static int minLight(String road){
        char[] charArray = road.toCharArray();
        int i=0;
        int light =0;
        while (i<charArray.length){
            if (charArray[i]=='X'){
                i++;//1. 遇到墙，直接跳过
            }else {
                if (i+1==charArray.length) {//2. 遇到最后一个点，i位置需要被点亮，直接+1，结束
                    light++;
                    break;
                }else{
                    if (charArray[i+1]=='X') {//3. 遇到下一个也是墙，i位置需要被点亮，i+1位置不需要，i+2位置需要
                        light++;
                        i=i+2;
                    }else {//4. 遇到下一个点，i位置不需要，i+1位置需要，i+2位置需要「这时候灯是放在 i+1 位置」
                        light++;
                        i=i+3;
                    }
                }
            }
        }
        return light;
    }

}
