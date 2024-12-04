package code06_MergeSortExtended;

//问题描述
//假设你有一个数组，其中包含三种不同类型的元素，可以类比为红色、白色和蓝色（例如，元素分别用0、1、2表示）。
//目标是对这个数组进行重新排序，使得所有相同类型的元素相邻，并按照红色、白色、蓝色的顺序排列。
public class Code02_TheDutchFlagProblem {

    //对数组分两个区域：大于 ， 小于等于 两个区域
    //需要两个下标:小于区的下标，不断移动的浮标
    //以数组最后一个数为指标进行比较，小于arr[arr.length-1]则在前面，大于等于arr[arr.length-1]在后面，变成两个区域
    public static void splitNum1(int [] arr){

        int lessEqualR =-1;
        int index =0;

        //三个指标：lessEqualR<index
        while(index<arr.length){
            if(arr[index]<arr[arr.length-1]){
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
    //对数组分三个区域：大于，小于，等于
    //需要三个下标:小于区的下标，不断移动的浮标，大于区的下标
    public static void splitNum2(int [] arr){

        int lessR =-1;
        int mostL =arr.length-1;
        int index =0;
        // lessR < index < mostL
        // 0 ～ lessR : 小于arr[arr.length-1]
        // lessR ～ mostL : 等于arr[arr.length-1]
        // mostL ～ arr.length : 大于arr[arr.length-1]
        while(index<mostL){
            if(arr[index]<arr[arr.length-1]){
                swap(arr,++lessR,index++);
            }else if(arr[index]>arr[arr.length-1]){
                swap(arr,--lessR,index);
            }else {
                index++;
            }
        }
        swap(arr,mostL,arr.length-1);
    }


    //交换一个数组种两个数的方法
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    //改进荷兰国旗问题
    public static int[] netherLandsFlag(int[] arr,int L ,int R){
        if(L>R){
            return new int[]{-1,-1};
        }
        if(L==R){
            return new int[]{L,R};
        }

        int less =L-1;
        int more =R;
        int index =L;
        while(index<more){
            if(arr[index]<arr[R]){
                swap(arr,++less,index);
            }else if(arr[index]>arr[R]){
                swap(arr,--more,index);
            }else {
                index++;
            }
        }
        swap(arr,more,R);

        return new int[]{less+1,more};


    }


}
