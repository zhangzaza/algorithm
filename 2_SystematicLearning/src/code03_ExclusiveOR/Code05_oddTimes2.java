package code03_ExclusiveOR;


//一个数组中有两种数出现了奇数次，其他数都出现了偶数次
//找到并打印这两种数
public class Code05_oddTimes2 {

    //1.遍历，全部 a^b 几过为 eor
    //2.找出最最低位的 1 这个结果的不同
    //3.可以分为两类，把 a 和 b 分开，并且所有其他的数，一类该位置为0，一类该位置为1
    //4.eor 与所有其中的一类数进行 异或运算，可以得出其中一个数，根据eor就可以得出两一个数

    public static int[] oddTimes(int[] arrs){
        //1.
        int eor =arrs[0];
        for (int i = 1; i < arrs.length; i++) {
            eor ^= arrs[i];
        }
        //2.
        int rightMostOne=eor &(~eor+1);
        //3.
        int eorTemp = eor;
        for (int i = 0; i < arrs.length; i++) {
            if ((rightMostOne ^ arrs[i]) ==0 ) {
                eorTemp ^= arrs[i];
            }
        }

        //4.
        eorTemp = eor ^ eorTemp;
        eor = eor ^ eorTemp;

        return  new int[]{eorTemp,eor};

    }


}
