package code20_BFRAndDP_02;

/// 规定1和A对应，2和B对应，3和C对应...26和Z对应
/// 那么一个数字字符串比如“111” 就可以转化为：“AAA”，“KA”，“AK”
/// 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
public class Code02_Str {

    /// 第一种：暴力递归
    /// 注意点：找好base case
    public static int  number(String str){
        if (str==null||str.length()==0){
            return 0;
        }
        return process(str.toCharArray(),0);
    }


    // str[0....i-1]转化无需过问
    // str[i....]去转化，返回有多少种转化方法
    public static int process(char[] str,int i){
        if (i==str.length){
            return 1;
        }
        //i 没到最后，说明有字符
        if (str[i]=='0'){ // 0 不能转化，返回0，说明之前转化的有问题
            return 0;
        }

        // 1.可能性一：i单转
        int ways= process(str,i+1);
        // 2.可能性二：i+1 组合转
        if (i+1<str.length && (str[i]-'0')*10+str[i+1]-'0'<=26){
            ways+=process(str,i+2);
        }
        return ways;

    }


    /// 第二种：dp
    public static int dp(String s){
        if (s==null||s.length()==0){
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N+1];
        dp[N]=1;//因为遍历到最后一步的话，只有一种方法
        for(int i=N-1;i>=0;i--){
            if (str[i]!='0'){ // 只有这个位置不是0 的时候才会有下一步，不然就是0
                int ways = dp[i+1];
                if (i+1<str.length && (str[i]-'0')*10+str[i+1]-'0'<=26){
                    ways+=dp[i+2];
                }
                dp[i]=ways;
            }else {
                dp[i]=0;
            }
        }
        return dp[0];

    }




}
