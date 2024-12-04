package code03_binarySearch;


import java.util.Arrays;

/*通过二分法在有序数组里面找数字*/
public class Code01_findNumBinary {


    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
        int testTime = 10000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] randArr = createRandArr(maxSize, maxValue);
            Arrays.sort(randArr);
            int value =  ((int)(Math.random()* (maxValue+1))- (int)( Math.random()* maxValue)) ;
            if (searchNumber(randArr,value)!=binarySearch(randArr,value)) {
                System.out.println("出错了");
                success = false;
                break;
            }
        }
        System.out.println(success ? "Nice !" : " Fucking fucked");

    }


    //生成随机数组
    public static int[] createRandArr(int maxSize, int maxValue) {
        int [] arr =new int[(int) ((maxSize+1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] =  ((int)(Math.random()* (maxValue+1))- (int)( Math.random()* maxValue)) ;
        }
        return arr;
    }


    //找数字的对数器
    public static boolean searchNumber(int[] arr,int number){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == number){
                return true;
            }
        }
        return false;
    }


    //二分法
    public static boolean binarySearch(int[] arr, int number) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int leftIndex = 0;
        int rightIndex = arr.length - 1;
        int mid = 0;
        while (leftIndex <= rightIndex) {
            mid = (leftIndex + rightIndex) / 2;
            if (arr[mid] == number) {
                return true;
            } else if (arr[mid] > number) {
                rightIndex = mid - 1;
            } else {
                leftIndex = mid + 1;
            }
        }
        return false;
    }


}
