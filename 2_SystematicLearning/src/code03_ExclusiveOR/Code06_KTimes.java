package code03_ExclusiveOR;

import java.util.HashMap;

//一个数组中有一种数出现了K次，其他数都出现了M次
//M>1 , K<M
//找到出现了K次的数
//要求，额外空间复杂度O(1),时间复杂度O(N)
public class Code06_KTimes {


    //思路：
    //1.每个数都是32位，那二进制的话，计算每个数的32进制数每位上的1出现了几次
    //2.如果 %M！=0，那就这个位置上的就是出现了K次，这个数对应的32位的这个位置为1
    //3.返回 2进制的 32位 就是这个数 「排除0这个特殊情况」
    public static int onlyKTimes(int[] arrs, int K, int M) {
        int[] t = new int[32];

        //1. 「注意这里的时间复杂度为O(N),因为一个for循环定下的次数是固定的」
        for (int arr : arrs) {
            for (int i = 0; i < 32; i++) {
                if (((arr >> i) & 1) != 0) { //计算在这个位置上的数相差多少位
                    t[i]++;
                }
            }
        }

        //2.
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % M == 0) {
                continue;
            }
            if (t[i] % M == K) {
                ans |= (1 << i);
            } else {
                return -1;
            }
        }

        //3. 排除 0 这种特殊情况，因为如果存在 0 的话，上述循环后，不会 return -1
        if (ans == 0) {
            int count=0;
            for (int arr : arrs) {
                if (arr==0){
                    count++;
                }
            }
            if (count!=K){
                return -1;
            }
        }
        return ans;
    }


    //对数器 中上述方法对应的测试方法
    public static int test(int[] arrs, int K, int M) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int arr : arrs) {
            if (map.containsKey(arr)) {
                map.put(arr, map.get(arr) + 1);
            } else {
                map.put(arr, 1);
            }
        }

        for (int num : map.keySet()) {
            if (map.get(num) == K) {
                return num;
            }
        }

        return -1;
    }

}
