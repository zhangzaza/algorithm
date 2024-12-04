package code18_bitmap2_ForceRecursion1;


///你有三根杆（通常称作 A、B 和 C），一开始，所有的盘子都按照从大到小的顺序堆叠在杆 A 上，最小的盘子在顶部。
/// 你的目标是将所有的盘子从杆 A 移动到杆 C。
/// 1.每次只能移动一个盘子，且任何时候都不能将较大的盘子放在较小的盘子上。
/// 2.可以使用杆 B 作为辅助杆。
public class Code03_TowersOfHanoi1 {


    /**
     * 思路：
     * 1.先递归地将前 n-1 个盘子从源杆移动到辅助杆。
     * 2.然后将第 n 个盘子（最大的盘子）从源杆移动到目标杆。
     * 3.最后递归地将 n-1 个盘子从辅助杆移动到目标杆。
     * */

    /// 请把1～n个圆盘，从左->右 「（n-1）放到 辅助杠 上， n放到目标杠上，再将（n-1） 放到辅助杠上」

    public static void main(String[] args) {
        int n = 3;
        leftToRight(n);
        //移动 1 从左->右
        //移动 2 从左->中
        //移动 1 从右->中
        //移动 3 从左->右
        //移动 1 从中->左
        //移动 2 从中->右
        //移动 1 从左->右
    }


    public static void leftToMid(int n ){
        if(n==1){
            System.out.println("移动 1 从左->中");
        }else{
            leftToRight(n-1);
            System.out.println("移动 "+(n)+" 从左->中");
            rightToMid(n-1);
        }
    }


    public static void rightToMid(int n ){
        if(n==1){
            System.out.println("移动 1 从右->中");
        }else{
            rightToLeft(n-1);
            System.out.println("移动 "+(n)+" 从右->中");
            leftToMid(n-1);
        }
    }


    public static void midToLeft(int n ){
        if(n==1){
            System.out.println("移动 1 从中->左");
        }else{
            midToRight(n-1);
            System.out.println("移动 "+(n)+" 从中->左");
            rightToLeft(n-1);
        }
    }


    public static void midToRight(int n ){
        if(n==1){
            System.out.println("移动 1 从中->右");
        }else{
            midToLeft(n-1);
            System.out.println("移动 "+(n)+" 从中->右");
            leftToRight(n-1);
        }
    }


    public static void leftToRight(int n ){
        if(n==1){
            System.out.println("移动 1 从左->右");
        }else{
            leftToMid(n-1);
            System.out.println("移动 "+(n)+" 从左->右");
            midToRight(n-1);
        }
    }


    public static void rightToLeft(int n ){
        if(n==1){
            System.out.println("移动 1 从右->左");
        }else{
            rightToMid(n-1);
            System.out.println("移动 "+(n)+" 从右->左");
            midToLeft(n-1);
        }
    }

}
