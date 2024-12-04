package code30_bfprt;

/// 蓄水池算法：
/// 假设有一个源源不断吐出不同球的机器，
/// 只有装下10个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
/// 如何做到机器吐出每一个球之后，所有吐出的球都被放入袋子中，且每个球的概率都相同？
///
/// 蓄水池抽样的核心思想是：
/// 在读取数据流的过程中，动态保持一个大小为 k 的样本集合。每次读入一个新元素，都会以一定的概率用新元素替换掉当前样本集合中的某个元素。
public class Code04_ReservoirSampling {

    /// 可以用于抽奖
    /// 1.查看用户第几次登陆，第一次登陆就参与抽奖
    /// 2.第i名用户 以100/i 的概率进入奖池，然后奖池中随机等 1/10 概率的被抽走

    public static void main(String[] args) {
        int test =100000;
        int[] count = new int[1730];
        for (int i=0;i<=test;i++){
            int [] bag = new int[10];
            int bagi=0;
            for (int num=1;num<=1729;num++){
                if (num<=10){
                    bag[bagi++]=num;
                }else {// num > 10
                    int j=random(num);// 一定要把num球放入袋子，但是要随机替换掉一个球
                    if (j<=10){
                        bagi=(int) (Math.random()*10);
                        bag[bagi]=num;
                    }
                }
            }
            for (int num:bag){
                count[num]++;
            }
        }

        //测试打印
        for (int i = 0; i < 1729; i++) {
            System.out.println(count[i]);
        }
    }


    /// 请等概率返回 1～i 中的一个数字
    public static int random(int i){
        return (int)(Math.random()*i)+1;
    }


}
