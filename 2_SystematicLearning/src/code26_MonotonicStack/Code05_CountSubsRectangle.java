package code26_MonotonicStack;

import java.util.Stack;

/// 给定一个二维数组matrix，其中的值不是0就是1，返回全部由 1 组成的子矩形数量
public class Code05_CountSubsRectangle {

    ///思路：
    /// 1. **高度计算**:
    ///    - 对于每一个元素 `matrix[i][j]`，需要计算高度 `heights[i][j]`。这一步的复杂度为 \(O(n * m)\)，其中 `n` 是矩阵的行数，`m` 是矩阵的列数。
    ///
    /// 2. **计算子矩形数量**:
    ///    - 对于每一行 `i`，我们调用 `countRectanglesInRow` 函数来计算以该行作为底的所有子矩形。
    ///    - 在 `countRectanglesInRow` 函数中，我们使用了一个单调栈来维护每个位置的高度。这部分的复杂度是 \(O(m)\)。
    ///    - 因为我们对每一行都要调用这个函数，所以总的复杂度也是 \(O(n * m)\)。
    public static int countSubWithAllOnes(int[][] matrix) {
        // 如果矩阵为空，直接返回0
        if (matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        // 高度数组，用于记录每一列的连续1的高度
        int[][] heights = new int[rows][cols];
        // 记录总的矩形数量
        int count = 0;

        // 计算每个位置的连续1的高度
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    // 如果当前位置是1，计算高度
                    heights[i][j] = i == 0 ? 1 : heights[i - 1][j] + 1;
                } else {
                    // 如果当前位置是0，高度为0
                    heights[i][j] = 0;
                }
            }
        }

        // 对于每一行，计算以该行作为底的子矩形数量
        for (int i = 0; i < rows; i++) {
            count += countRectanglesInRow(heights[i]);
        }

        return count;
    }

    // 辅助函数，计算单行中能形成的所有子矩形数量
    private static int countRectanglesInRow(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        // 用于记录以当前高度为矩形高度的子矩形数量
        int[] count = new int[heights.length];

        for (int j = 0; j < heights.length; j++) {
            //快速找到当前元素j的左边界
            //1.「重要」
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[j]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                // 如果栈不为空，计算当前矩形数量
                int prevIndex = stack.peek();
                //2.「重要」
                count[j] = count[prevIndex] + (j - prevIndex) * heights[j];
            } else {
                // 如果栈为空，表示前面都是0，计算从头到当前位置的矩形数量
                count[j] = (j + 1) * heights[j];
            }

            sum += count[j];
            stack.push(j);
        }

        return sum;
    }

}
