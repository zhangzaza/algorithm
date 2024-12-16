package code06;


/// Nim 博弈：「华人被卖到旧金山时候路上发行的赌博游戏」
/// 有若干堆物品，每堆物品数量若干。两个玩家轮流进行操作，每次操作可以从任意一堆中取走至少一个物品（也可以取走整堆物品）。
/// 不能进行操作的玩家为输家。给定初始各堆物品的数量，判断先手是否有必胜策略。
///
/// 例如，假设有三堆物品，数量分别为 3、4、5。玩家 A 和玩家 B 轮流取物品，A 先取。
/// A 可以从第一堆取走 2 个物品，此时三堆物品数量变为 1、4、5；然后 B 可以从第二堆取走 3 个物品，变为 1、1、5 等等，直到一方无法取物品为止。
public class Code05_Nim {

	// 保证arr是正数数组
	public static void printWinner(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		if (eor == 0) {
			System.out.println("后手赢");
		} else {
			System.out.println("先手赢");
		}
	}

}
