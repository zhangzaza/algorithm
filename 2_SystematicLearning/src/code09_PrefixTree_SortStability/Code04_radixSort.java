package code09_PrefixTree_SortStability;


//基数排序
public class Code04_radixSort {


    //下面是针对正数的，如果有负数的话，就找到最小的负数，然后 + 最小值的绝对值，再做基数排序，但是要考虑溢出
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxBit(arr));

    }

    //找到最大数的位数
    private static int maxBit(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max = max / 10;
        }
        return res;
    }

    //模拟的是一个入桶和出桶的操作
    //这里的出桶操作很骚气，代替了 桶排序的交换操作
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        //有多少个数准备多少个辅助空间
        int[] help = new int[R - L + 1];

        for (int d = 0; d < digit; d++) {//有多少位就进出几次 d表示位数
            //10个空间
            //count[0] 当前位(d位)是0的数字有多少个
            //count[1] 当前位(d位)是(0,1)的数字有多少个
            //count[2] 当前位(d位)是(0,1,2)的数字有多少个
            //...
            //count[9] 当前位(d位)是(0,1,2,...,9)的数字有多少个
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }

            //生成前缀和的数组
            for (int i1 = 1; i1 < count.length; i1++) {
                count[i1] = count[i1 - 1] + count[i1];
            }

            //从右往左开始遍历，放入辅助数组中，一个出桶的概念
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }

            //将辅助里面的数放入到原数组中
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }

        }

    }

    //x这个数在d位上出现的是几
    public static int getDigit(int x, int d) {
        return ((x / (int) Math.pow(10, d)) % 10);
    }


}
