package code35_ResourceLimitation;

public class Code03_bitMapTwoTimes {
private static final long TOTAL_NUMBERS = 1L << 32;
    private static final int BITS_PER_NUMBER = 2;
    private static final int NUMBERS_PER_BYTE = 8 / BITS_PER_NUMBER;
    private static final long BYTE_ARRAY_SIZE = TOTAL_NUMBERS / NUMBERS_PER_BYTE;

    public static void main(String[] args) {
        byte[] bitmap = new byte[(int)BYTE_ARRAY_SIZE];

        // 示例输入，假设 inputNumbers 是从文件或其他输入获取的
        int[] inputNumbers = {/* 40 亿个整数 */};

        // 遍历输入，标记状态
        for (int number : inputNumbers) {
            int index = number / NUMBERS_PER_BYTE;
            int offset = (number % NUMBERS_PER_BYTE) * BITS_PER_NUMBER;

            int currentState = (bitmap[index] >> offset) & 0b11;

            if (currentState == 0) {
                // 从未出现变为出现一次
                bitmap[index] |= (0b01 << offset);
            } else if (currentState == 1) {
                // 从出现一次变为出现两次
                bitmap[index] &= ~(0b11 << offset); // 先清除当前状态
                bitmap[index] |= (0b10 << offset);  // 设置为出现两次
            }
            // 如果状态为 10 或 11，不做任何处理，因为我们只关心出现两次的数字
        }

        // 输出所有只出现两次的整数
        for (int i = 0; i < BYTE_ARRAY_SIZE; i++) {
            for (int j = 0; j < NUMBERS_PER_BYTE; j++) {
                int offset = j * BITS_PER_NUMBER;
                int state = (bitmap[i] >> offset) & 0b11;

                if (state == 0b10) { // 只出现两次的状态
                    int number = i * NUMBERS_PER_BYTE + j;
                    System.out.println(number);
                }
            }
        }
    }
}



