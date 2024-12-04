package code04_DataStructure;

//递归：
//所有的递归都可以改成非递归
public class Code07_recursion {

    //求arr中的最大值
    public static int getMax(int[] arr){
        return process(arr,0,arr.length-1);
    }

    public static int process(int[] arr,int L,int R){
        //arr[L..R]范围上求最大值
        if(L==R){
            return arr[L];
        }
        int mid = L + ((R-L)>>1);
        int leftMax = process(arr,L,mid);
        int rightMax = process(arr,mid+1,R);
        return Math.max(leftMax,rightMax);
    }

    //只有子规模一样的递归才叫完全递归，可以用mast公式
    //T(O)=a*T(O/b)+O(N^d);其中a，b，d 都为常数。  「这个只是一层的计算，不是对于整个递归来说，而是对于每个层次来说」
    //1.logb(a) < N^d  O(N^d)
    //2.logb(a) > N^d  O(N^logb(a))
    //3.logb(a) = N^d  O(N^d * N^logN)

    //例子：getMax为例子
    //T(O)=2*T(O/2)+O(1);
    //时间复杂度：是第一种，O(N)


}
