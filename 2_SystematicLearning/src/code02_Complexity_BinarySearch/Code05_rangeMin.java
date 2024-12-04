package code02_Complexity_BinarySearch;


/*
 * 局部最小
 * 在一个无序数组中，找出一个局部最小值。局部最小值的定义是：如果一个元素小于它的相邻元素（如果存在），那么它被称为局部最小。请设计一个算法来找到数组中的任意一个局部最小值。
 * 输入: 一个包含不重复整数的无序数组。
 * 输出: 返回数组中的任意一个局部最小值的索引。
 * 例如，给定数组 [5, 3, 4, 6, 7, 2]，其中 2 是一个局部最小值。返回值为 5，代表 2 在数组中的索引位置。
 * */
public class Code05_rangeMin {

    public static void main(String[] args) {
        int maxLen =100;
        int maxValue  = 200;
        int testTime =1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = ranArray(maxLen, maxValue);
            int ans = rangeMinNumber(arr);
            if (!check(arr,ans)) {
                printArray(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束！");
    }


    /*
     * 1.先判断 第一位 和 最后一位 是否是 局部最小，都不是那就肯定存在局部最小
     * 2.使用二分法找到一个mid数字，如果中间这个数字 是局部最小 直接返回，如果不是 就判断左边还是右边
     * 3.mid左边的小于mid 就直接取左边的范围循环，如果没有就拿 右边的进行循环
     * */
    public static int rangeMinNumber(int[] arr) {

        if (arr == null || arr.length == 0) {
            return -1;
        }
        int len = arr.length - 1;
        if (len == 1) {
            return 0;
        }
        if (len == 2) {
            return arr[0] < arr[1] ? 0 : 1;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[len - 1] < arr[len]) {
            return 0;
        }
        int left = 0;
        int right = len;
        int mid = left + (right - left) / 2;
        while (left <= right-1) { // 边界问题：如果边界三个数，就直接比较 left 和 right 谁小就是谁 ，如果是 left<=right 会有边界问题，实例【3，2，3，2，3】
            if (arr[mid] < arr[mid + 1] && arr[mid] < arr[mid - 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return arr[left] <arr[right] ? left : right;
    }


    //生成一个无序相邻不相等的数组
    public static int[] ranArray(int maxLen, int maxValue) {
        int length = (int) (maxLen * Math.random());
        int[] arr = new int[length];
        arr[0] = (int) (maxValue * Math.random());
        for (int i = 0; i < length; i++) {
            do {
                arr[i] = (int) (maxValue * Math.random());
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }


    //检查
    public static boolean check(int[] arr, int minIndex) {
        if (arr == null || arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    //打印数组
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i1 = 0; i1 < arr.length; i1++) {
            System.out.print(arr[i1]+" ");
        }
        System.out.print(" ]");
        System.out.println();
    }


}
