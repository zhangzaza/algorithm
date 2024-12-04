package code14_InfoBinaryTrees2_Greedy1;

import java.util.ArrayList;
import java.util.List;

///在多叉树的场景中，假设我们有一个树结构来表示员工之间的上下级关系，而邀请吃饭的规则是：一个员工不能直接邀请其上下级。
///每个员工对应一个快乐值，求出所有员工快乐值的和最大是多少。
///注意：快乐值可能是负数！！！
public class Code04_MultiTreeMaxHappiness {

    static class Employee {
        int happiness; // 员工快乐值
        List<Employee> subordinates; // 下属员工

        Employee( int happiness) {
            this.happiness = happiness;
            this.subordinates = new ArrayList<>();
        }
    }




    /// 罗列需要知道的信息：
    /// 1.该员工来的时候的最大快乐值
    /// 2.该员工不来的时候的最大快乐值
    public static class Info {
        public int noCome;
        public int yesCome;

        public Info(int noCome, int yesCome) {
            this.noCome = noCome;
            this.yesCome = yesCome;
        }
    }


    public static int maxHappiness(Employee boss) {
        Info info = process(boss);
        return Math.max(info.noCome, info.yesCome);
    }

    public static Info process(Employee employee) {
        if (employee == null){
            return new Info(0,0);
        }
        int noCome = 0;
        int yesCome = employee.happiness;
        for (int i = 0; i < employee.subordinates.size(); i++) {
            Info nextInfo = process(employee.subordinates.get(i));
            ////注意，快乐值可能是负数
            noCome += Math.max(nextInfo.noCome, nextInfo.yesCome);
            yesCome += nextInfo.noCome;
        }
        return new Info(noCome,yesCome);
    }





}
