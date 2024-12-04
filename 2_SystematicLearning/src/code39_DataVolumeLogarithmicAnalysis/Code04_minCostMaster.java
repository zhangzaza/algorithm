package code39_DataVolumeLogarithmicAnalysis;


// int[] d，d[i]:i号怪兽的能力
// int[] p，p[i]:i号怪兽要求的钱
// 开始时你的能力是0，你的目标是从0号怪兽开始通过所有的怪兽。
// 如果你当前的能力，小于i号怪兽的能力,你必须付出p[]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上;
// 如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
// 返回通过所有的怪兽，需要花的最小钱数
public class Code04_minCostMaster {


    // int[] d，d[i]:i号怪兽的能力
    // int[] p，p[i]:i号怪兽要求的钱
    // ability:当前你所具有的能力
    // index：来到了第index个怪兽的面前

    // 目前，你的能力是ablity，你来到了index号怪兽的面前，如果要通过后续的所有怪兽
    // 你需要最少花费多少钱？

    /// 1.第一种暴力递归
    /// 注意：code04_1 这里的动态规划需要修改的数据量很大
    ///      如果上述的问题 我的能力和 贿赂 用 code01_1 来描述，那么数据量 过了 亿，那就 几秒内根本填不完，如果都是几百，那就没事
    public static int minCost(int[] d, int[] p, int ability, int index) {
        if (index == d.length) {
            return 0;
        }
        if (ability < d[index]) {//必须贿赂
            return p[index] + minCost(d, p, ability, index + 1);
        } else {//可以贿赂，也可以不贿赂
            return Math.min(minCost(d, p, ability, index + 1), p[index] + minCost(d, p, ability + d[index], index + 1));
        }
    }

    /// 2.第二种暴力递归

    // 从0....index号怪兽，花的钱，必须严格 ==money
    // 如果通过不了，返回-1
    // 如果可以通过，返回能通过情况下的最大能力值
    public static long process(int[] d, int[] p, int index, int money) {
        if (index == -1) {//一个怪兽也没遇到呢
            return money == 0 ? 0 : -1;
        }
        //index >= 0
        //1.不贿赂当前index号怪兽
        long preMaxAbility = process(d, p, index - 1, money);
        long p1 = -1;
        if (preMaxAbility != -1 && preMaxAbility >= d[index]) {
            p1 = preMaxAbility;
        }

        //2.贿赂当前index号怪兽,当前的钱 p[index]
        long preMaxAbility2 = process(d, p, index - 1, money - p[index]);
        long p2 = -1;
        if (preMaxAbility2 != -1) {
            p2 = preMaxAbility2 + d[index];
        }

        return Math.max(p1, p2);
    }

    public static int minCost2(int[] d, int[] p, int ability) {
        int allMoney = 0;
        for (int i = 0; i < p.length; i++) {
            allMoney += p[i];
        }

        int N = d.length;
        for (int money = 0; money < allMoney; money++) {
            if (process(d, p, N - 1, money) != -1) {
                return money;
            }
        }

        return allMoney;
    }


    /// 3.由第一种改成的动态规划
    public static long minCost3(int[] d, int[] p) {
        int sum = 0;
        for (int i : d) {
            sum += i;
        }
        long[][] dp = new long[d.length + 1][sum + 1];
        for (int i = 0; i < sum; i++) {
            dp[0][i] = 0;
        }

        for (int cur = d.length - 1; cur >= 0; cur--) {
            for (int hp = 0; hp <= sum; hp++) {
                //如果这种情况发生，那么这个hp必然是递归过程中不会出现的状态
                //既然动态规划是尝试过程的优化，尝试过程碰不到的状态，不必计算
                if (hp + d[cur] > sum) {
                    continue;
                }
                if (hp <= d[cur]) {
                    dp[cur][hp] = p[cur] + dp[cur + 1][hp + d[cur]];
                } else {
                    dp[cur][hp] = Math.min(p[cur] + dp[cur + 1][hp + d[cur]], dp[cur + 1][hp]);
                }
            }
        }
        return dp[0][0];
    }


    /// 4.由第二种暴力递归改成的动态规划
    public static long minCost4(int[] d, int[] p) {
        int sum = 0;
        for (int i : d) {
            sum += i;
        }

        // dp[i][j]含义：
        // 能经过0～i的怪兽，且花钱为j(花钱的严格等于j) 时的武力值 最大值是多少？
        // 如果dp[i][j]== -1 ,表示基恩过 0～i 的怪兽，花钱为j是无法通过的，或者之前的钱怎么组合也得不到整好为j的钱数
        int[][] dp = new int[d.length][sum + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < sum; j++) {
                dp[i][j] = -1;
            }
        }

        // 经过0～i 的怪兽，花钱数一定为p[],达到武力值d[0] 的地步，其他第0行的状态一律是无效的
        dp[0][p[0]] = d[0];
        for (int i = 1; i < d.length; i++) {
            for (int j = 0; j < sum; j++) {
                //可能性1，为当前怪兽花钱
                //存在条件：
                //j-p[i] 要不越界，并且在钱数为j-p[i]时，要通过0～ i-1 的怪兽，并且钱数组合是有效的。
                if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                //可能性2，不为当前怪兽花钱
                //存在条件：
                //0～i-1怪兽在花钱为j的情况下，能保证通过当前i位置的怪兽
                if (dp[i - 1][j] >= d[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }

        int ans =0;
        // dp表最后一行上，dp[N-1][j]代表：
        // 能经过 0～N-1 怪兽，花钱为j(花钱的严格等于j) 时的武力值 最大值是多少？
        // 最后一行上，最左侧的不为-1的列数(j)，就是答案
        for (int i = 0; i <= sum; i++) {
            if (dp[d.length - 1][i] != -1) {
                ans = i;
                break;
            }
        }
        return ans;
    }


}
