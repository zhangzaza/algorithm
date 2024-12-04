package code06_MergeSortExtended;


//Quick Sort是一种高效的排序算法，使用了分治（Divide and Conquer）策略。它在平均情况下的时间复杂度为
//工作原理
//1.选择一个基准元素（Pivot）：从数组中选择一个元素作为基准元素，通常可以选择第一个元素、最后一个元素、随机选择或选择中间元素。
//2.分区（Partitioning）：将数组重新排序，以使所有小于基准元素的元素放到基准元素的左边，所有大于基准元素的元素放到基准元素的右边。此时，基准元素在其最终位置。
//3.递归排序子数组：递归地对基准元素左右两侧的子数组进行排序，直到整个数组有序。
public class Code03_quickSortRecursion {

    //快排 使用递归
    public static void quickSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr,0,arr.length-1);
    }

    public static void process(int[] arr,int left,int right){
        if (left >= right) {
            return;
        }
        int[] equalE=partition(arr,left,right);
        process(arr,left,equalE[0]-1);//equalE[0]-1。等于区域第一个数下标
        process(arr,equalE[1]+1,right);//equalE[1]+1。等于区域最后一个下标
    }



    //分层
    //arr[L....R] 范围上，拿arr[R]做划分值
    //L.....R 可以分成三个区域 < = >
    public static int[] partition(int[] arr,int L,int R){
        int lessR =L-1;
        int moreL =R;
        int index = L;
        while(index<moreL){
            if(arr[index]<arr[R]){
                swap(arr,++lessR,index++);
            }else if(arr[index]>arr[R]){
                swap(arr,--lessR,index);
            }else {
                index++;
            }
        }
        swap(arr,moreL,R);
        return new int[]{lessR+1,moreL};//返回相等的这个区域 的 数组下标
    }


    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
