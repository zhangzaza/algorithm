package code19_BFRAndDP_01;


/// 给定一个整型数组arr，代表数值不同的纸牌排成一条线
/// 玩家A和玩家B 依次拿走每张纸牌
/// 规定玩家A先拿，玩家B后拿
/// 但是每个玩家每次只能拿走最左或者左右的纸牌
/// 玩家A和玩家B都绝顶聪明
/// 请返回最后获胜者的分数
public class Code03_firstPlayerAndSecondPlayer {


    /// 第一步：暴力递归
    /// arr[L...R] ,后手获得最好分数返回
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f(arr, 0, arr.length - 1);
        int second = g(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    /// arr[L...R] ,先手获得最好分数返回
    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g(arr, L + 1, R);
        int p2 = arr[R] + g(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    /// arr[L...R] ,后手获得最好分数返回
    public static int g(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f(arr, L + 1, R); // 对手拿走了拿走A
        int p2 = f(arr, L, R - 1); // 对手拿走了拿走R
        /// 策略考虑了先手玩家的最佳玩法
        /// 重要：后手玩家实际上只能在先手玩家选择后，从剩余的最佳选项中选择得分较小的那一个
        return Math.min(p1, p2);
    }


    /// 第二步：「傻缓存」
    /// 二维缓存表的意思：
    /// 行列
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        /// 先手玩家在子数组 arr[i...j] 中进行选择，fmap[i][j] 给出了先手玩家在最优策略下可以获得的分数。
        /// 后手玩家从子数组 arr[i...j] 中进行选择，gmap[i][j] 提供了后手玩家在最优策略下可以取得的分数。
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int i1 = 0; i1 < N; i1++) {
                fmap[i][i] = -1;
                gmap[i][i] = -1;
            }
        }

        int first = f2(arr, 0, N - 1, fmap, gmap);
        int second = g2(arr, 0, N - 1, fmap, gmap);
        return Math.max(first, second);
    }


    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        fmap[L][R] = ans;
        return ans;
    }

    public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = 0;
        } else {
            int p1 = f2(arr, L + 1, R, fmap, gmap);
            int p2 = f2(arr, L, R - 1, fmap, gmap);
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }


    /// 第三部：动态规划
    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
            gmap[i][i] = 0;//这里可以不用写，因为默认值就是0
        }

        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < N) { // 不用判断L，因为肯定是R先触发条件
                // 两个表结合，填满对角线的值
                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                L++;
                R++;
            }
        }

        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);

    }


}
