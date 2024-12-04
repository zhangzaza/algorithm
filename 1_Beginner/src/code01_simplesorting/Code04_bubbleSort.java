package code01_simplesorting;

//冒泡排序
//1.从待排序的数组的第一个元素开始，依次比较相邻的两个元素。
//2.如果前一个元素大于后一个元素，则交换这两个元素的位置。
//3.继续向数组的下一个位置移动，重复步骤1和步骤2，直到比较到数组的倒数第二个元素。
//4.重复上述步骤，每次比较的范围减少一个元素，直到最后一个元素。重复以上步骤，直到整个数组排序完成。

//冒泡排序的基本思想是通过不断比较相邻元素的大小，并交换位置，将较大（或较小）的元素逐渐向右（或向左）移动，较大的元素会逐渐浮到数组的末尾。
//这个过程类似于气泡在水中上浮的过程。

public class Code04_bubbleSort {


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
        int[] arr = {14,35,23,44,56,13,76,22};
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
        //14 35 23 44 56 13 76 22
        //13 14 22 23 35 44 56 76

    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length-1;
        for (int i = n; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

}
