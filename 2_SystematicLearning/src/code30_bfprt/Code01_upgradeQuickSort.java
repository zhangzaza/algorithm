package code30_bfprt;

/// 在无序数组中求第K个小的数字
public class Code01_upgradeQuickSort {


    ///第一种:使用递归实现
    public static int minKth2(int[] array,int k){
        int[] arr = copyArray(array);
        return process(arr,0,arr.length-1,k-1);
    }

    public static int[] copyArray(int[] arr){
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    //arr 第k小的数
    //process(arr,0,N-1,k-1)
    //arr[L...R] 范围上，如果排序的话(不是真的去排序)，找位于index的数
    //index[L...R]
    public static int process(int[] arr,int L,int R,int index){
        if (L==R){
            return arr[L];
        }

        int pivot = arr[L+(int)(Math.random()*(R-L+1))];

        // range[0] range[1]
        // L ..... R pivot
        // 0   10000   70....800
        int[] range = partition(arr,L,R,pivot);
        if (index>=range[0]&&index<=range[1]){
            return arr[index];
        }else if (index<range[0]){
            return process(arr,L,range[0]-1,index);
        }else{
            return process(arr,range[1]+1,R,index);
        }
    }

    public static int[] partition(int[] arr,int L,int R,int pivot){
        int less = L-1;
        int more = R+1;
        int cur = L;
        while (cur<more){
            if (arr[cur]<pivot){
                swap(arr,++less,cur++);
            }else if (arr[cur]>pivot){
                swap(arr,--more,cur);
            }else{
                cur++;
            }
        }
        return new int[]{less+1,more-1};
    }

    public static void swap(int[] arr,int i,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /// 第二种：使用迭代的方式实现
    /// 时间复杂度「O(N)」
    public static int minKth(int[] arr,int index){
        int L = 0;
        int R = arr.length-1;
        int pivot=0;
        int[] range = null;
        while (L<R){
            pivot  =arr[L+(int)(Math.random()*(R-L+1))];
            range = partition(arr,L,R,pivot);
            if (range[0]>index){
                R = range[0]-1;
            }else if (range[1]<index){
                L = range[1]+1;
            }else{
                return pivot;
            }
        }
        return arr[index];
    }


}
