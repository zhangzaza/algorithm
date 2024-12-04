package code35_ResourceLimitation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Code01_HashCode {



    public static String[] diffStr(int N){
        String[] ans = new String[N];
        for(int i=0;i<N;i++){
            ans[i] = "zhangzazazazaza"+String.valueOf(i);
        }
        return ans;
    }



    public static void main(String[] args) {
        int N=1080;
        String [] strs=diffStr(N);

        int [] count = new int[10];

        for (String str : strs) {
            int hashCode = str.hashCode();
            count[Math.abs(hashCode%10)]++;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(i+":"+count[i]);
        }
        //0:108
        //1:108
        //2:108
        //3:108
        //4:108
        //5:108
        //6:108
        //7:108
        //8:108
        //9:108


    }


}
