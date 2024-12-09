package code03;

/// 求一个字符串中，最长无重复字符子串长度
// 本题测试链接 : https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class Code01_LongestSubstringWithoutRepeatingCharacters {

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		//0～255
		//map[i] = v i这个ascii码的字符，上次出现在v位置
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}

		map[str[0]] = 0;
		int N = str.length;
		int ans = 1;
		int pre = 1;//上一个位置向前推了多长
		for (int i = 1; i < N; i++) {
			pre = Math.min(i - map[str[i]], pre + 1);
			ans = Math.max(ans, pre);
			map[str[i]] = i;
		}
		return ans;
	}

}
