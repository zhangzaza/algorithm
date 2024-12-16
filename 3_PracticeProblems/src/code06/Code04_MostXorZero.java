package code06;

import java.util.ArrayList;
import java.util.HashMap;


/// 数组中所有数都异或起来的结果，叫做异或和
/// 给定一个数组arr，可以任意切分成若干个不相交的子数组
/// 其中一定存在一种最优方案，使得切出异或和为0的子数组最多
/// 返回这个最多数量
public class Code04_MostXorZero {

	/// 1.暴力方法 「对数器：时间复杂度 2^N 」
	public static int comparator(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] eor = new int[N];//前缀异或和数组
		eor[0] = arr[0];
		for (int i = 1; i < N; i++) {
			eor[i] = eor[i - 1] ^ arr[i];
		}
		return process(eor, 1, new ArrayList<>());
	}

	// index去决定：前一坨部分，结不结束！
	// 如果结束！就把index放入到parts里去
	// 如果不结束，就不放
	public static int process(int[] eor, int index, ArrayList<Integer> parts) {
		int ans = 0;
		if (index == eor.length) {
			parts.add(eor.length);//将终止位置进行加入
			ans = eorZeroParts(eor, parts);//计算这个终止位置中，有多少个异或和为0的子数组
			parts.remove(parts.size() - 1);//将最新加入终止位置进行删除
		} else {
			int p1 = process(eor, index + 1, parts);
			parts.add(index);
			int p2 = process(eor, index + 1, parts);
			parts.remove(parts.size() - 1);
			ans = Math.max(p1, p2);
		}
		return ans;
	}

	//计算数组 eor数组中，有多少异或和为属于0的部分
	public static int eorZeroParts(int[] eor, ArrayList<Integer> parts) {
		int L = 0;
		int ans = 0;
		for (Integer end : parts) {
			//如果 L 为0，异或和为 eor[end - 1]。
			//否则，异或和为 eor[end - 1] ^ eor[L - 1]。
			//如果异或和为0，ans 增加1。
			if ((eor[end - 1] ^ (L == 0 ? 0 : eor[L - 1])) == 0) {// 计算从 L 到 end - 1 的子数组的异或和。
				ans++;
			}
			L = end;
		}
		return ans;
	}



	/// 2.时间复杂度O(N)的方法
	public static int mostXor(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] dp = new int[N];
		
		// key 某一个前缀异或和
		// value 这个前缀异或和上次出现的位置(最晚！)
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		// 0~i整体的异或和
		int xor = 0;
		for (int i = 0; i < N; i++) {
			xor ^= arr[i];
			if (map.containsKey(xor)) { // 可能性2
				int pre = map.get(xor);
				dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
			}
			if (i > 0) {
				dp[i] = Math.max(dp[i - 1], dp[i]);//与 可能性1 pk
			}
			// xor 可能在map里，也可能不在，所以要更新，更新最晚的出现的位置
			map.put(xor, i);
		}
		return dp[N - 1];
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 150000;
		int maxSize = 12;
		int maxValue = 5;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int res = mostXor(arr);
			int comp = comparator(arr);
			if (res != comp) {
				succeed = false;
				printArray(arr);
				System.out.println(res);
				System.out.println(comp);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
