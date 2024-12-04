package code03_ExclusiveOR;


//寻找一个数最右边的1
public class Code04_rightMostOne {


    public static int rightMostOne(int num) {
        return num &(~num + 1);
    }

//    例子
//    假设 num = 12，其二进制表示为 1100。
//
//    1.num = 12 的二进制表示是 1100。
//    2.~num 的二进制表示是 0011（因为 ~1100 等于 0011）。
//    3.~num + 1 的二进制表示是 0100（因为 0011 + 1 等于 0100）。
//    4.num & (~num + 1) 的操作是 1100 & 0100。做按位与运算后，得到 0100，即二进制数 4。


    //就是 & 上自己的相反数

}
