package code35_ResourceLimitation;

public class Code02_bitMap {

    private static final long BITMAP_SIZE = 4294967296L; // 2^32
    private static final int BITS_PER_BYTE = 8;
    private byte[] bitmap = new byte[(int)(BITMAP_SIZE / BITS_PER_BYTE)];

    // 模拟处理整数输入，假设这些是文件中读取到的整数
    public void processNumbers(int[] numbers) {
        for (int number : numbers) {
            setBit(number);
        }
    }

    private void setBit(int number) {
        int byteIndex = number / BITS_PER_BYTE;
        int bitIndex = number % BITS_PER_BYTE;
        bitmap[byteIndex] |= (1 << bitIndex);
    }

    public void findMissingNumbers() {
        for (int i = 0; i < BITMAP_SIZE / BITS_PER_BYTE; i++) {
            for (int bit = 0; bit < BITS_PER_BYTE; bit++) {
                if ((bitmap[i] & (1 << bit)) == 0) {
                    System.out.println((i * BITS_PER_BYTE) + bit);
                }
            }
        }
    }

    public static void main(String[] args) {
        Code02_bitMap finder = new Code02_bitMap();
        // 示例输入数据
        int[] numbers = {0, 1, 2, 3, 5, 7, 8, 9, 10}; // 仅作为示例
        finder.processNumbers(numbers);
        finder.findMissingNumbers();
    }


}
