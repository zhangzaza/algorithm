package code01_simplesorting;

//插入排序的流程如下：
//1.从第二个元素开始，将当前元素插入到已经排好序的子数组中的合适位置。
//2.对于未排序部分的每一个元素，将其与已排序部分的元素逐个比较，找到其合适的插入位置，并插入。重复上述过程，直到整个数组排序完成。
//
//插入排序的基本思想是将未排序的元素一个个插入到已排序的部分中，直到所有元素都插入完毕。
//这个过程类似于打扑克牌时，将手中的牌逐个插入已经排好序的牌堆中。
public class Code05_insertionSort {

    //打印数组用于比较
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    //交换一个数组种两个数的方法
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {35,14,23,44,56,13,76,22};
        printArray(arr);
        insertionSort1(arr);
        printArray(arr);
        //14 35 23 44 56 13 76 22
        //13 14 22 23 35 44 56 76
    }

    //第一种写法
    public static void insertionSort1(int[] arr) {
        if (arr == null || arr.length <2) {
            return;
        }
        int n = arr.length-1;
        for (int i=1; i<=n; i++) {
            int minValueIndex = i;
            while (minValueIndex-1 >= 0 && arr[minValueIndex] < arr[minValueIndex-1] ) {
                swap(arr, minValueIndex, minValueIndex-1);
                minValueIndex--;
            }

            //注意点1:
            //while (arr[minValueIndex] < arr[minValueIndex-1] &&  minValueIndex-1 >= 0  ) {  如果变成这样，会发生数组越界的问题

            //注意点2：
            // 如果是下面这样写的话会有重复操作，增加时间复杂度
            // for (int j=i; j>0; j--) {
            //     if (arr[j] < arr[j-1]) {
            //         swap(arr, j, j-1);
            //     }
            // }
        }
    }

    //第二种写法
    public static void insertionSort2(int[] arr) {
        if (arr == null || arr.length <2) {
            return;
        }
        int n = arr.length-1;
        for (int i=1; i<=n; i++) {
           for (int j =i-1; j>=0 && arr[j] > arr[j+1]; j--) {
               swap(arr,j,j+1);
           }
        }
    }

}
