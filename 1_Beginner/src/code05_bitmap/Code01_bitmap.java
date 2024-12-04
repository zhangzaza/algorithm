package code05_bitmap;


/*
* 位图是一种数据结构，用于表示一个二维的矩阵，其中每个元素只有两个可能的取值，通常是0和1。它可以用于表示图像、布尔值的集合、集合的成员关系等。
* 位图由一个存储在计算机内存或硬盘上的连续的二进制序列组成。每个位对应于矩阵中的一个元素，
* 如果位的值为1，则表示该元素是存在或被标记的，如果位的值为0，则表示该元素是不存在或未被标记的。
* byte：占用1个字节（8位）。
* short：占用2个字节（16位）。
* int：占用4个字节（32位）。
* long：占用8个字节（64位）。
* float：占用4个字节（32位）。
* double：占用8个字节（64位）。
* char：占用2个字节（16位）。
* boolean：理论上占用1个位，但在实际存储时，通常以1个字节（8位）的形式存储。
* */
public class Code01_bitmap {

    /*位图思路*/
    //1.用long类型，有8字节64位，创建一个长度为「max/64」的数组，使用2进制来记录
    //2.在那位数字上，哪位上为1 ，那这个数字就是 「64*index + 那个数字在64位中排在第几个」
    //add 就是 与  ｜1
    //delete 就是 取反 再 &1
    //contains 就是查看是否包含

    public static class BitMap{

        private long[] bits;

        public BitMap(int max){
            bits = new long[(max+64)>>6];
        }

        public void add(int num){
            bits[(num-1)>>6] |= (1L<<(num&63));
        }

        public void delete(int num){
            bits[(num-1)>>6] &= ~(1L<<(num&63));
        }

        public boolean contains(int num){
            return (bits[num>>6] & (1L<<(num&63))) != 0;
        }

    }


}
