package code07_Heap;

//堆排序
//时间复杂度 O(N*logN)
public class Code05_heapSort {

    /*
    * 堆 [10,9,7,5,3,1] 此时：heapSize=6
    *
    * add原理：
    * 1.数组添加一个数，变成 [10,9,7,5,3,1,6] index为6，heapSize为7
    * 2.直接对 arr[index] 进行heapInsert
    *
    * delete原理：
    * 1.数组减少一个数 [10,9,7,5,3,1,6] ，直接将 10和6进行调换[6,9,7,5,3,1,10]，index为0 ，heapSize-1=6
    * 2.直接对 arr[index] 进行heapify
    *
    * */




    /*
    * 无序数组 -> 大根堆 -> 小根堆
    * 总结：
    * 1.先让整个数组都变成大根堆结构，建立堆的过程：
    * 2.把堆的最大值和堆末尾的值交换，然后减少堆的大小之后，再去调整堆，一直周而复始，时间复杂度为O(N*logN)
    * 3.堆的大小减小成0之后，排序完成
    * */
    public static void heapSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //数组变成大根堆
        for (int i = 0; i < arr.length; i++) { //时间复杂度：O(N)
            heapInsert(arr, i);//上升  时间复杂度：O(lngN)
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);

        //变成小根堆
        while (heapSize > 0) {//时间复杂度：O(N)
            heapify(arr, 0, heapSize);//下沉 时间复杂度：O(lngN)
            swap(arr,0,--heapSize);//O(1)
        }
    }


    /*
    * 对heapSort2 进行优化
    * 总共有N个数，叶子节点的为 N/2 调用一次heapify
    * 次叶子节点数为 N/4 调用2次heapify
    * 次次叶子节点数为 N/8 调用3次heapify
    * 次次次叶子节点数为 N/16 调用4次heapify
    * .....
    * 以此类推
    * 计算出来无限接近 2N 次，所以时间复杂度为O(N)
    * */
    public static void heapSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
//        //数组变成大根堆
//        for (int i = 0; i < arr.length; i++) { //时间复杂度：O(N)
//            heapInsert(arr, i);//上升  时间复杂度：O(lngN)
//        }
//         被代替为以下

        //数组变成大根堆，但是时间复杂度是 O(N)
        for (int i = arr.length-1; i >=0; i--) {
            heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);

        //变成小根堆
        while (heapSize > 0) {//时间复杂度：O(N)
            heapify(arr, 0, heapSize);//下沉 时间复杂度：O(lngN)
            swap(arr,0,--heapSize);//O(1)
        }
    }




    //下沉
    public static void heapify(int[] arr,int index,int heapSize){
        int left=index*2+1;
        int right=index*2+2;
        while (left<heapSize){
            int larger = right<heapSize && arr[right]>arr[left] ? right:left;
            larger = arr[larger]>arr[index] ? larger:index;
            if (larger==index){
                break;
            }
            swap(arr,larger,index);
            index=larger;
            left=index*2+1;

        }
    }

    //上升
    public static void heapInsert(int[] arr,int index){
        while(arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }


    public static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
