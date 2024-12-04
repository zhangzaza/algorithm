package code24_BFRAndDP_06;


/// N皇后问题是一个经典的计算机科学和数学问题。这个问题的目标是在一个 N×N 的棋盘上放置 N 个皇后，使得它们之间互不攻击。
/// 根据国际象棋的规则，皇后可以沿着横行、竖列和对角线移动。因此，问题的关键在于确保没有两个皇后在同一行、同一列或同一对角线上。
///
/// 给定一个正数n，返回n个皇后的摆法有多少种。
/// n=1，返回1
/// n=2或者3，2皇后和3皇后没有摆法，返回0
/// n=8，返回92
public class Code03_N_Queens {

    /// 第一种：没有使用位运算的解法
    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process(0, record, n);
    }

    // 当前来到了第row行，一共是0～n-1行
    // 在row行上放皇后，所有列都尝试
    // 必须要保证跟之前所有的皇后不打架
    // int[] record record[x]=y 之前的第x行的皇后，放在了y列上
    // 返回：不关心row以上发生了什么，row....后续有多少和发的方法数「这一步需要理解」
    public static int process(int row, int[] record, int n) {
        if (row == n) {
            return 1;
        }
        int ways = 0;
        for (int col = 0; col < n; col++) {
            if (isValid(record, row, col)) {
                record[row] = col;
                ways += process(row + 1, record, n);
            }
        }
        return ways;
    }

    public static boolean isValid(int[] record, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (col == record[i] || Math.abs(row - i) == Math.abs(col - record[i])) {
                return false;
            }
        }
        return true;

    }


    /// 第二种：使用了位运算的解法 「时间复杂度没有变化，但是优化了常数时间，但是效率提高了几十几百位」
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        //如果你是13皇后问题，limit最后13个1，其他都是0
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /// 比如说是 7 皇后问题
    /// limit：0.......1 1 1 1 1 1 1 「传入之前已经在num2种进行了处理」
    /// 之前皇后的列影响：colLim
    /// 之前皇后的左斜线影响：leftDiaLim
    /// 之前皇后的右斜线影响：rightDiaLim
    public static int process2(
            int limit,
            int colLimit,
            int leftDiaLimit,
            int rightDiaLimit
    ) {
        if (colLimit == limit) {
            return 1;
        }
        // pos 中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLimit | leftDiaLimit | rightDiaLimit));
        int mostRightOne =0;
        int ways = 0;
        while(pos!=0){
            mostRightOne = pos & (-pos+1);//找到最右侧的1「计算机位上」
            pos -= mostRightOne;//pos先标记一下，把最右侧的1给减去
            ways += process2(
                    limit,
                    colLimit | mostRightOne, //列影响，就是不移动，为1就是有皇后，为0就是没有皇后
                    (leftDiaLimit | mostRightOne) << 1, //左斜线影响：向左移动
                    (rightDiaLimit | mostRightOne) >>> 1 //右斜线影响：向右移动
            );
        }
        return ways;

    }

}
