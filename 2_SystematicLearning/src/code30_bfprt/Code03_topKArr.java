package code30_bfprt;


/// 给定一个无序数组arr中，给定一个正数k，返回top K 个最大的数
/// 不同时间复杂度三个方法：
/// 1.O(N*logN)。   快排的方式
/// 2.O(N+K*logN)。 大根堆的方式，因为弹出一个调整一次大根堆的时间复杂度为logK，所以是O(N+K*logN)
/// 3.O(N+k*logK)。 使用bfprt，找到第(N-k)小的数，返回的是由小变大的数组，然后使用快排的方式，返回数组，时间复杂度就是O(k*logK)。
public class Code03_topKArr {


}
