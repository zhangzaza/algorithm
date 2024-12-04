package code03_binarySearch;


import java.util.Arrays;

/*
* 在一个数组中
*1.找大于等于num 最左位置
*2.找小于等于num 最右边位置
* */
public class Code02_findMostNum {

    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
        int testTime = 10000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] randArr = createRandArr(maxSize, maxValue);
            Arrays.sort(randArr);
            int value =  ((int)(Math.random()* (maxValue+1))- (int)( Math.random()* maxValue)) ;
            int index =searchNumber(randArr,value);
            int index2 = binarySearch(randArr,value);
            if (index!=index2) {
                System.out.println("出错了");
                System.out.println(value+" searchNumber:"+index +" , binarySearch:"+index2);
                printArray(randArr);
                success = false;
                break;
            }

        }
        System.out.println(success ? "Nice !" : " Fucking fucked");

    }



    //有序数组arr， >=num 最左
    public static int binarySearch(int[] arr, int value) {
        int ans =-1;
        if (arr == null ||arr.length == 0) {
            return ans;
        }
        int left = 0;
        int right = arr.length-1;
        while (left <= right) {
            int mid = (left + right)/2;
            if (arr[mid] >= value) {
                ans=mid;
                right = mid-1;
            }else{
                left=mid+1;
            }
        }
        return ans;
    }



    //找数字的对数器  大于等于num
    public static int searchNumber(int[] arr,int number){
        int ans =-1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= number){
               return i;  // 这里不能写成 ans =i；，不然得出的结果会多一位
            }
        }
        return ans;
    }


    //生成随机数组
    public static int[] createRandArr(int maxSize, int maxValue) {
        int [] arr =new int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] =  ((int)(Math.random()* (maxValue+1))- (int)( Math.random()* maxValue)) ;
        }
        return arr;
    }

    //打印数组
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i1 = 0; i1 < arr.length; i1++) {
            System.out.print(arr[i1]+" ");
        }
        System.out.print(" ]");
        System.out.println();
    }



}
