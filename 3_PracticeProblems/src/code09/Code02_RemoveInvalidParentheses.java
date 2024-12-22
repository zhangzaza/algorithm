package code09;

import java.util.ArrayList;
import java.util.List;

// 测试链接 : https://leetcode.com/problems/remove-invalid-parentheses/
// https://leetcode.cn/problems/remove-invalid-parentheses/
///
/// 题目描述
/// 给定一个由若干括号（'(' 和 ')'）和字母组成的字符串 s，要求删除最小数量的无效括号，使输入的字符串有效，并返回所有可能的结果，答案可以按任意顺序返回。
/// 示例
/// 示例 1：
/// 输入：s = "()())()"
/// 输出：["(())()","()()()"]
/// 示例 2：
/// 输入：s = "(a)())()"
/// 输出：["(a())()","(a)()()"]
/// 示例 3：
/// 输入：s = ")("
/// 输出：[""]
///
/// 提示
/// 1 <= s.length <= 25
/// s 由小写英文字母以及括号 '(' 和 ')' 组成
/// s 中至多含 20 个括号
///
/// 解题思路提示
/// 1.由于不知道哪些括号可以被删除，所以尝试所有的选项，可使用递归方法。
/// 2.在递归过程中，对于每个括号，可以选择使用它或者删除它。
/// 3.递归会生成所有有效的括号字符串，但题目只需要删除最少括号数量的有效字符串。因此，可以先计算需要删除的无效括号数量，然后在递归中只生成有效的字符串。
public class Code02_RemoveInvalidParentheses {

	// 来自leetcode投票第一的答案，实现非常好，我们来赏析一下
	public static List<String> removeInvalidParentheses(String s) {
		List<String> ans = new ArrayList<>();
		remove(s, ans, 0, 0, new char[] { '(', ')' });
		return ans;
	}

	// modifyIndex <= checkIndex
	// 只查s[checkIndex....]的部分，因为之前的一定已经调整对了
	// 但是之前的部分是怎么调整对的，调整到了哪？就是modifyIndex
	// 比如：
	// ( ( ) ( ) ) ) ...
	// 0 1 2 3 4 5 6
	// 一开始当然checkIndex = 0，modifyIndex = 0
	// 当查到6的时候，发现不对了，
	// 然后可以去掉2位置、4位置的 )，都可以
	// 如果去掉2位置的 ), 那么下一步就是
	// ( ( ( ) ) ) ...
	// 0 1 2 3 4 5 6
	// checkIndex = 6 ，modifyIndex = 2
	// 如果去掉4位置的 ), 那么下一步就是
	// ( ( ) ( ) ) ...
	// 0 1 2 3 4 5 6
	// checkIndex = 6 ，modifyIndex = 4
	// 也就是说，
	// checkIndex和modifyIndex，分别表示查的开始 和 调的开始，之前的都不用管了  par  (  )
	public static void remove(String s, List<String> ans, int checkIndex, int deleteIndex, char[] par) {//par ：'(', ')' 或者 ')', '('
		for (int count = 0, i = checkIndex; i < s.length(); i++) {
			if (s.charAt(i) == par[0]) {
				count++;
			}
			if (s.charAt(i) == par[1]) {
				count--;
			}
			// i check计数<0的第一个位置
			if (count < 0) {
				for (int j = deleteIndex; j <= i; ++j) {
					// 比如
					if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) {
						remove(
								s.substring(0, j) + s.substring(j + 1, s.length()),//拼接
								ans, i, j, par);
					}
				}
				return;
			}
		}
		String reversed = new StringBuilder(s).reverse().toString();//逆序
		if (par[0] == '(') {
			remove(reversed, ans, 0, 0, new char[] { ')', '(' });
		} else {
			ans.add(reversed);
		}
	}

}
