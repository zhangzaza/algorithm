package code03_ExclusiveOR;

//异或交换
public class Code02_swap {

    //1.一个数异或自己等于本身
    //2.满足 交换律
    public static void swap(int a,int b){
        a=a^b;
        b=a^b; // b = (a ^ b) ^ b
        a=a^b; // a = (a ^ b) ^ a
    }


    //数组交互的时候就要考虑不是一个位置
    public static void swap(int[] arrs ,int a ,int b ){
        if (a!=b){
            arrs[a]=arrs[a]^arrs[b];
            arrs[b]=arrs[a]^arrs[b];
            arrs[a]=arrs[a]^arrs[b];
        }
    }


}
