package code01_simplesorting;

//选择排序
//1.从待排序的数组中选出最小的元素。
//2.将该最小元素与数组的第一个元素交换位置。
//3.在剩下的元素中重复上述步骤，将最小的元素放到已排序部分的末尾。
//4.重复以上步骤，每次在未排序的部分中选出最小的元素，并放到已排序部分的末尾。最终完成排序。

//这个过程类似于人们在一组数中找到最小的数并将其放到最左边，然后在剩下的数中再找到最小的，放到它的右边，以此类推，直到所有数都排好序为止。

public class Code03_selectSort {

    //打印数组用于比较
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] arr = {14,35,23,44,56,13,76,22};
        printArray(arr);
        selectSort(arr);
        printArray(arr);
        //14 35 23 44 56 13 76 22
        //13 14 22 23 35 44 56 76

    }

    //选择排序的实现
    //1.处理边界条件，不为空｜｜为一个数就不需要排序「其他情况就需要排序」
    //2.0～n-1 找最小zhi；1～n-1找最小zhi；2～n-1找最小值 「一遍一遍找最小值」
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length ; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }


    //交换一个数组种两个数的方法
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
