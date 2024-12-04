package code08_mergeRecursion;


//使用非递归的方式 进行归并排序
//时间复杂O(n*logN)
//这里更好的写法，看 SystematicLearning/src/code05_MergeSort/Code02_mergeSortWhile.java 文件的描述，对这里进行了优化
public class Code02_mergeSortWhile {


    //这里的递归形式是从 小到大
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <2) {
            return;
        }
        int step =1;
        int N = arr.length;

        //这里需要考虑 integer.max ，step可能会溢出
        while (step < N) {
            int L =0;

            while (L < N) {
                int M=0;
                //L+step-1 仍然需要检查越界问题,这里需要判断下一段是否越界
                if (N-L >= step) {
                    M=L+step-1;
                }else {
                    M=N-1;
                }


                if (M==N-1){
                    break;
                }

                int R=0;
                //判断R的位置，并且检查是否会越界
                if ((N-1)-(M+1)+1 >= step){
                    R=M+step-1;
                }else {
                    R=N-1;
                }

                merger(arr,L,M,R);

                if (R==N-1){
                    break;
                }else {
                    L=R+1;
                }

            }

            //可能会溢出，在这里进行判断 , 但是step >=(N/2) 就会出错，
            if (step > (N/2)) {
                break;
            }else {
                step*=2;
            }
        }
    }




    private static void merger(int[] arr, int l, int mid, int r) {
        int [] help = new int[r-l+1];
        int i = 0;
        int left =0;
        int right = mid+1;

        //如果数组的左右两边都有值
        while (left <= l && right <= mid) {
            help[i++]= help[left]<help[right]?arr[left++]:arr[right++];
        }

        //如果只剩下数组的左边
        while (left <= l) {
            help[i++]= arr[left++];
        }

        //如果只剩下数组的右边
        while (right <= r) {
            help[i++]= arr[right++];
        }

        //将help数组移动进去
        for (i = 0; i < help.length; i++) {
            arr[l+i] = help[i];
        }

    }




}
