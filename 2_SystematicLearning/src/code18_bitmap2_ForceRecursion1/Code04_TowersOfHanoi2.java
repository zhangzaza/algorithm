package code18_bitmap2_ForceRecursion1;

public class Code04_TowersOfHanoi2 {


    /// 1~N : 在 from，去to，另一个other「辅助杠」
    public static void func(int n,String from ,String to,String other){
        if(n==1){
            System.out.println("Move 1 from "+from+" to "+to);
        }else{
            func(n-1,from,other,to);
            System.out.println("Move "+n+" from "+from+" to "+to);
            func(n-1,other,to,from);
        }
    }

}
