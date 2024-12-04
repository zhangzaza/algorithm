package code03_ExclusiveOR;


//一个数组中，有一个数出现了奇数次，其他数都出现了偶数次，请求出这个数
public class Code03_oddTimes1 {

    //只需要一个变量
    public static int findOddTimes(int[] arr) {
        int ors =arr[0];
        for (int i = 1; i < arr.length; i++) {
            ors ^=arr [i];
        }
        return ors;
    }



}
