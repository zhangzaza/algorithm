package code08_mergeRecursion;

//问题描述
//假设你有一个数组，其中包含三种不同类型的元素，可以类比为红色、白色和蓝色（例如，元素分别用0、1、2表示）。
//目标是对这个数组进行重新排序，使得所有相同类型的元素相邻，并按照红色、白色、蓝色的顺序排列。
//详细的看 2_SystematicLearning/src/code06_MergeSortExtended/Code02_TheDutchFlagProblem.java
public class Code03_TheDutchFlagProblem {

    //大于小于两个区域
    //需要两个下标:小于区的下标，不断移动的浮标
    public static void splitNum1(int [] arr){
        int N = arr.length;

        int lessEqualR =-1;
        int index =0;

        int mostR =N-1;
        while(index<N){
            if(arr[index]<arr[mostR]){
                swap(arr,lessEqualR+1,index);
                lessEqualR++;
                index++;
                // 上面三步可以缩写：swap(arr,++lessEqualR,index++);
            }else {
                index++;
            }
        }
    }


    //荷兰国旗问题
    //大于小于等于 三个区域
    //需要三个下标:小于区的下标，不断移动的浮标，大于区的下标
    public static void splitNum2(int [] arr){
        int N = arr.length;

        int lessR =-1;
        int mostL =N-1;
        int index =0;

        while(index<mostL){
            if(arr[index]<arr[N-1]){
                swap(arr,++lessR,index++);
            }else if(arr[index]>arr[N-1]){
                swap(arr,--lessR,index);
            }else {
                index++;
            }
        }
        swap(arr,mostL,N-1);
    }





    //交换一个数组种两个数的方法
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
