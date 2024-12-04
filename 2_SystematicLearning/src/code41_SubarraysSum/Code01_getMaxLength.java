package code41_SubarraysSum;


/// 要找到一个全是正数数组的子数组，使得该子数组的累加和等于某个给定值，并且该子数组的长度最大
public class Code01_getMaxLength {

    /// 思路：
    /// 窗口滑动
    /// 必须存在单调性：累加和单调递增「从左往右走的时候」
    public static int getMaxLength(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == num) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < num) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }


}
