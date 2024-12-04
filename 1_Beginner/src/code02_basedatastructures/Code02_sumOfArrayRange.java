package code02_basedatastructures;

/*
* 题目描述：
* 在一个数组中，arr(left,right),这个区间内的求和非常频繁，并且left和right不是固定的「left,right表示数组下标」
* 求这个区间内累加和，可能要在这个数组的求和要高达数亿次
*
* 解决方式：预处理结构
* */
public class Code02_sumOfArrayRange {

    public static void main(String[] args) {
        int [] arr = {4,34,53,234,642,65,8,33,29};
        int left =0;
        int right =2;
        //正方向表实现
        int sum1 = sumOfArrayRange1(arr, left,right);
        System.out.println(sum1);
        //前缀和实现
        int sum2 = sumOfArrayRange2(arr, left,right);
        System.out.println(sum2);
    }

    //第一种方法：使用正方形表
    private static int[][] squareTable;
    public static int sumOfArrayRange1(int[] arr,int left,int right){
        if (arr == null || arr.length ==0 ) {
            return 0;
        }
        if (left<0 || right >= arr.length || left > right){
            return 0;
        }
        getsquareTable(arr);
        return squareTable[left][right];
    }
    private static void getsquareTable(int[] arr) {
        int n =arr.length;
        squareTable = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i;j < n;j++){
                if (i == j){
                    squareTable[i][j] = arr[i];
                }else {
                    squareTable[i][j] = squareTable[i][j-1]+arr[j];
                }
            }
        }
    }


    //第二种方法：使用前缀和数组
    private static int[] preSum;
    public static int sumOfArrayRange2(int[] arr, int left, int right){
        if (arr == null || arr.length ==0 ) {
            return 0;
        }
        if (left<0 || right >= arr.length || left > right){
            return 0;
        }
        getPerSum(arr);
        if (left == 0){
            return preSum[right];
        }
        return preSum[right]-preSum[left-1];
    }
    public static void getPerSum(int[] arr){
        int n= arr.length;
        preSum =new int[n];
        preSum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            preSum[i]=preSum[i-1]+arr[i];
        }
    }
}
