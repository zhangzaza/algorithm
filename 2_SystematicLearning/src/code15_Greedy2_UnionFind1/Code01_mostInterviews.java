package code15_Greedy2_UnionFind1;

import java.util.Arrays;
import java.util.Comparator;

/// 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲
/// 给你每一个项目开始的时间和结束的时间
/// 你来安排宣讲的日程，要求会议室进行宣讲的场次最多
/// 返回最多的宣讲场次
///
/// 怎么贪？
public class Code01_mostInterviews {

    public static class Program{
        public int start;
        public int end;
        public Program(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    //比较器：结束时间早的排前面
    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }


    /// 先按时间结束节点排序
    /// 贪心：
    /// 1.结束时间早的优先，结束时间早的先安排
    /// 2.然后一次从时间结束最早的事开始找 最先开始的
    public static int bestArrange(Program[] programs){
        Arrays.sort(programs,new ProgramComparator());
        int timeLine = 0;
        int result = 0;
        for (int i = 0; i < programs.length; i++) {
            if(programs[i].start >= timeLine){
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }





}
