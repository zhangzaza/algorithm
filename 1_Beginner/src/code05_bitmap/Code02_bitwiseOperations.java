package code05_bitmap;

public class Code02_bitwiseOperations {

    //不能用加号，只能用位运算
    /* 加法 */
    public static int add(int a, int b) {
        if (a == 0 || b == 0) {
            return a == 0 ? b : a;
        }
        int sum = a ^ b;
        int uintSum ;
        int carry = (a & b) <<1;
        while (carry != 0){
            uintSum = sum ^ carry; // 无进位相加信息
            carry =  (uintSum& carry)<<1; //进位信息
            sum =uintSum;//无进位相加信息
        }

        return sum;
    }

    /*减法。a + b的相反数 ，b的相反数为 ～b+1 ，使用add逻辑 */
    public static int sub(int a, int b) {
        return add(a, add(~b,1));
    }


    /*乘法 ： a * b。把 b 的进位和 是否为1 分开进行 */
    public static int mul(int a, int b) {
        int ans =0;
        while (b != 0) {
            if ((b&1)!=0){
                ans = add(ans,a);
            }
            a <<= 1;  // 带符号
            b >>>= 1; // 不带符号
        }
        return ans;

    }


    /*除法*/
    //系统最小值无法转绝对值
    //1.a和b都是系统最小
    //2.a不是，b是
    //3.a是，b不是。 3.1.b的绝对值为1，那就是a ； 3.2.系统最小值除以b 为c，（系统最二小 - c*b ） 再除以 c 是否是为 1 ，为1 就 c+1 ，不是1 就是c
    //4.a不是，b不是
    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE &&  b==Integer.MIN_VALUE) {
            return 1;
        }else if (b==Integer.MIN_VALUE) {
            return 0;
        }else if (a==Integer.MIN_VALUE){
            if (negNum(b)==1){ //3.1
                return Integer.MAX_VALUE;
            }else{//3.2
                int ans =  div(add(a,1),b);
                return add(ans,div(sub(a,mul(ans,b)),b));
            }
        }else{
            return divide(a,b);
        }
    }


    /*a除以b能除尽*/
    public static int div(int a, int b) {
        //取绝对值
        int x = isNeg(a) ? negNum(a) :b;
        int y = isNeg(b) ? negNum(b) :a;
        int res =0;

        //x/y
        for (int i = 30; i >=0 ; i=sub(i,1)) {
            if ( (x>>i) >= y){ //从头开始看，b能移动到第几位，才有的除
                res |= (1<<i); // 能被除的那位加上 标志1
                x=sub(x,y<<i); // 都已经除去了，就把 这个数字减掉
            }
        }

        //判断符号
        return isNeg(a)^isNeg(b) ? negNum(res) : res;

    }
    /*判断正负*/
    public static boolean isNeg(int a) {
        return a<0;
    }
    /*取绝对值*/
    public static int negNum(int a) {
        if (isNeg(a)) {
            return add(~a,1);
        }
        return a;
    }


}
