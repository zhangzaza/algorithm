package code08_ReinforcedHeap;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//最大线段重合问题（用堆实现）
//给定很多线段，每个线段都有两个数 [start，end]，表示线段开始位置和结束位置，左右都是闭区间
//规定：
//1.线段的开始和结束位置一定都是整数值
//2.线段重合区域长度必须 >= 1
//返回线段最多重合区域中，包含了几条线段
public class Code01_maxOverlappingIntervals {


    //思路：
    //1.所有线段 开始的位置进行排序 从小到大
    //2.对每个线段 按顺序 进行遍历
    //3.定义一个小根堆，
    //将 第一个线段的 end1 放进小根堆中，此时小根堆的size为1，表示 strart1-end1 这一段有一条线段 ，记下 1
    //将 第二个线段的 end2 放进小根堆中，弹出小根堆中 < start2 的数，此时小根堆的size就是 包含了几条线段，记下 n
    //.....
    //4.对每个 线段对应的n进行比较 ，最大的n就是所求的数量


    //第一种方法「不得分」
    public static int maxCover1(int[][] lines){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }

        int cover =0;

        //就是暴力遍历的方式
        for (double i = 0; i < max; i++) {
            int cur=0;
            for (int i1 = 0; i1 < lines.length; i1++) {
                if(lines[i1][0]< i && lines[i1][1]>i){
                    cur++;
                }
            }
            cover= Math.max(cover, cur);
        }

        return cover;
    }




    //第二种方式
    //时间复杂度 O(N * logN) 「小根堆的时间复杂度是：logN，每个线段都要遍历：N」
    public static int maxCover2(int[][] coverLines){
        Line[] lines = new Line[coverLines.length];
        for (int i = 0; i < coverLines.length; i++) {
            lines[i] = new Line(coverLines[i][0], coverLines[i][1]);
        }
        Arrays.sort(lines,new EndComparator());

        //使用小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max=0;
        for (int i = 0; i < lines.length; i++) {
            while(!heap.isEmpty() && heap.peek()<lines[i].start){
                heap.poll();
            }
            heap.add(lines[i].end);
            max = Math.max(max, heap.size());
        }
        return max;

    }



    //定义包装类
    public static class Line{
        public int start;
        public int end;
        public Line(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    //定义比较器，小根堆的里面的排序方式
    public static class EndComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }









































}
