package code01_simplesorting;


//什么是算法？
//1.有具体的问题
//2.解决问题的具体步骤
//3.具体步骤会有不同的方案 ，所以会有时间复杂度和空间复杂度 来作为标准来衡量

//算法的分类：
//1.你知道怎么算的过程「你知道这一步一步下去知道得出结果」
//2.你知道怎么尝试「但是你不知道需要几步，但是你知道一直这样试下去肯定能有结果」
public class Code02_factorial {


    public static void main(String[] args) {
        int n = 10;
        System.out.println(f1(n));
        System.out.println(f2(n));
        //4037913
        //4037913
    }


    /*求 N！的和 */
    //第一种方式：求出每个阶乘 然后 再累加
    public static long f1(int n){
        long ans =0;
        for (int i = 1; i <=n; i++) {
            ans += factorial(i);
        }
        return ans;
    }

    public static long factorial(int n) {
        long ans=1;
        for (int i = 1; i <= n; i++) {
            ans*=i;
        }
        return ans;
    }

    //第二种方式：使用迭代的方式，即 只需每个数字乘以一次即可。所以肯定第二种方式好
    public static long f2(int n){
        long ans=0;
        long curr=1;
        for (int i = 1; i <=n; i++) {
            curr = curr*i;
            ans+=curr;
        }
        return ans;
    }

}
