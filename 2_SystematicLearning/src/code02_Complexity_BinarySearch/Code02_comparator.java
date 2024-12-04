package code02_Complexity_BinarySearch;

/*比较器 / 对数器*/
public class Code02_comparator {

    public static void main(String[] args) {
        int maxSize = 500;
        int maxValue = 10000;
        int testTime = 10000;
        boolean success = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = lenRanValueRan(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            insertionSort2(arr1);
            bubbleSort(arr2);
            if (!isSorted(arr1)) {
                System.out.println("  插入排序错了！ ");
                printArray(arr1);
                success = false;
                break;
            }
            if (!isSorted(arr2)) {
                System.out.println("  冒泡排序错了！ ");
                printArray(arr2);
                success = false;
                break;
            }
            if (!isEqual(arr1, arr2)) {
                printArray(arr1);
                printArray(arr2);
            }
        }
        System.out.println(success ? "Nice!" : "Fuck fucking!");

    }

    //打印数组
    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i1 = 0; i1 < arr.length; i1++) {
            System.out.print(arr[i1] + ",");
        }
        System.out.print(" ]");
        System.out.println();
    }

    //返回一个数组 长度随机 ，每个位置的值也随机
    public static int[] lenRanValueRan(int maxSize, int maxValue) {
        int len = (int) (Math.random() * maxSize);
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }


    //复制：能生成一个新数组，并且和原来的包场一样
    public static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    //判断数组是否有顺序
    public static boolean isSorted(int[] arr) {
        if (arr.length < 2) {
            return true;
        }
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max > arr[i]) {
                return false;
            }
            max = Math.max(max, arr[i]);
        }
        return true;
    }


    //比较两个数组是否完全一致
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    //选择排序「举例1」
    public static void insertionSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length - 1;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    //冒泡排序「举例2」
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length - 1;
        for (int i = n; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    //交换一个数组种两个数的方法
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
