package code01;


/// 给定一个非负整数num
/// 如何不用循环语句
/// 返回>= num ,并且离 num 最近的，2的某次方
public class Code03_Near2Power {

    // 已知n是正数
    // 返回大于等于，且最接近n的，2的某次方的值
    public static final int tableSizeFor(int n) {
        n--;// 这一句是为了防治n是2的某次方
        //下面这段代码跑完之后，最高位1的后面全是1
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    public static void main(String[] args) {
        int cap = 120;
        System.out.println(tableSizeFor(cap));
    }



}
