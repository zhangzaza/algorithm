package code08_mergeRecursion;


import java.util.Stack;

//快排的非递归版本
public class Code05_quickSortStack {


    public static void quickSort(int[] arr) {
        if (arr == null || arr.length<2) {
            return;
        }
        Stack<Job> stack = new Stack<>();
        stack.push(new Job(0, arr.length-1));
        while (!stack.isEmpty()) {
            Job job = stack.pop();
            int[] equals =partition(arr,job.L,job.R);
            if (equals[0]<job.L) {//有 < 区域
                stack.push(new Job(job.L, equals[0]-1));
            }
            if (equals[1]<job.R) {// 有 > 区域
                stack.push(new Job( equals[1]+1, job.R));
            }
        }
    }


    public static class Job{
        public int L;
        public int R;
        public Job(int l, int r) {
            L = l;
            R = r;
        }
    }


    //分层
    //arr[L....R] 范围上，拿arr[R]做划分值
    //L。。。。R < = >
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
        return new int[]{lessR+1,moreL};//返回相等的这个区域 的 数组下标  ？？？这里为什么要写成这个格式
    }


    //交换一个数组种两个数的方法
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
