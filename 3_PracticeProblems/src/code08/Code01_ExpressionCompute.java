package code08;

import java.util.LinkedList;

// 本题测试链接 : https://leetcode.com/problems/basic-calculator-iii/
/// 给定一个字符串str，str表是一个公式
/// 公式里可能有整数，加减乘除符号和左右括号
/// 返回公式的计算结果，难点在于括号可能嵌套很多层
/// str=“48*((70-65)-43)+8*1”,返回 -1816
/// str=“3+1*4”，返回7
/// str=“3+(1*4)”,返回7
/// 说明：
/// 1.可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
/// 2.如果是负数，就需要用括号括起来，比如“4*(-3)” 但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"(-3*4)"都是合法的
/// 3.不用考虑计算过程中的会发生溢出的情况
public class Code01_ExpressionCompute {

	public static int calculate(String str) {
		return f(str.toCharArray(), 0)[0];
	}

	// 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
	// 返回两个值，长度为2的数组
	// 0) 负责的这一段的结果是多少
	// 1) 负责的这一段计算到了哪个位置
	public static int[] f(char[] str, int i) {
		LinkedList<String> queue = new LinkedList<String>();
		int cur = 0;
		int[] bra = null;
		// 从i出发，开始撸串
		while (i < str.length && str[i] != ')') {
			if (str[i] >= '0' && str[i] <= '9') {
				cur = cur * 10 + str[i++] - '0';
			} else if (str[i] != '(') { // 遇到的是运算符号
				addNum(queue, cur, str[i++]);
				cur = 0;
			} else { // 遇到左括号了
				bra = f(str, i + 1);
				cur = bra[0];
				i = bra[1] + 1;
			}
		}
		addNum(queue, cur, '+');//最后一个数字
		return new int[] { getAns(queue), i };
	}


	// 遇到运算符号，将前面的数字和符号加入队列中
	// 看看之前的符号是不是乘除法运算符，是的话就拿出来计算
	/// 有个问题：如果是 3*4/5 这样的运算这样好像无法解决
	public static void addNum(LinkedList<String> queue, int num, char op) {
		if (!queue.isEmpty() && (queue.peekLast().equals("*") || queue.peekLast().equals("/"))) {
			String top = queue.pollLast();
			int pre = Integer.valueOf(queue.pollLast());
			num = top.equals("*") ? (pre * num) : (pre / num);
		}
		queue.addLast(String.valueOf(num));
		queue.addLast(String.valueOf(op));
	}

	public static int getAns(LinkedList<String> queue) {
		int ans = Integer.valueOf(queue.pollFirst());
		while (queue.size() > 1) {
			String op = queue.pollFirst();
			int num = Integer.valueOf(queue.pollFirst());
			ans += op.equals("+") ? num : -num;
		}
		return ans;
	}

}
