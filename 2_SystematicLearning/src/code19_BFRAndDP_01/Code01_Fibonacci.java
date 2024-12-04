package code19_BFRAndDP_01;

///动态规划和暴力递归
//BruteForceRecursionAndDynamicProgramming
public class Code01_Fibonacci {


    public static int fib1(int n){
        if (n==1){
            return 1;
        }else if (n==2){
            return 1;
        }else {
            return fib1(n - 1) + fib1(n - 2);
        }
    }

    /// 如果不加缓存 ， 需要重复计算
    /// 比如fib(6)需要计算fib(5) fib(4) fib(3) fib(2) fib(1)
    /// fib(5) 需要计算fib(4) fib(3) fib(2) fib(1)
    /// fib(4) 需要计算fib(3) fib(2) fib(1)
    /// fib(3) 需要计算fib(2) fib(1)
    /// 如果添加了缓存，那么就避免了重复计算

}
