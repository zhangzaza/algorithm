package code40_CatalanNumber;

import java.util.TreeMap;

/// 牛牛家里一共有n袋零食，第i袋零食体积为v[i],背包容量为w
/// 牛牛想知道在总体积不超过背包容量的情况下，
/// 一共有多少种零食放法，体积为0也算一种放法
/// 1<=n<=30,1<=w<=2*10^9,1<=v[i]<=10^9
public class Code02_bag {


    public static long ways(int[] arr, int bag) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] <= bag ? 2 : 0;
        }
        int mid = (arr.length - 1) / 2;
        //1.前半组和后半组的结果
        TreeMap<Long, Long> lmap = new TreeMap<>();
        long ways = func(arr, 0, 0, mid, bag, lmap);
        TreeMap<Long, Long> rmap = new TreeMap<>();
        ways += func(arr, mid + 1, 0, arr.length - 1, bag, rmap);
        //2.记录 rmap 累加和的map 集合
        TreeMap<Long, Long> rpre = new TreeMap<>();
        long pre = 0;
        for (Long key : rmap.keySet()) {
            pre += rmap.get(key);
            rpre.put(key, pre);
        }
        //3.拿取左边的数组「遍历」，拿取右边的数组累加和「两者相乘就是有多少种组合方式」，累加和 <= bag，就累加到ways上
        for (Long key : lmap.keySet()) {
            long lweight = key;
            long lways = lmap.get(key);
            Long floor = rpre.floorKey(bag - lweight);
            if (floor != null) {
                ways += lways * rmap.get(floor);
            }
        }
        return ways+1;//包含了0

    }


    //arr 30
    //func(arr,0,14,0,bag,map)
    //func(arr,15,29,0,bag,map)

    //从index出发，到end结束
    //之前的选择，已经形成的累加和sum
    //零食[index...end]自由选择，出来的所有累加和，不能超过bag，每一种累加和对应的方法数，填在map里面
    //最后不能什么货都没选
    //[3，3，3，3] bag =6
    //0 1 2 3
    // - - - - 0 -> (0 : 1)
    // - - - $ 3 -> (0 : 1)(3,1)
    // - - $ - 3 -> (0 : 1)(3,2)
    public static long func(int[] arr, int index, int end, long sum, long bag, TreeMap<Long, Long> map) {
        if (sum > bag) {
            return 0;
        }
        //sum <= bag
        if (index > end) {//所有商品自由选择完了
            //sum
            if (sum != 0) {
                if (!map.containsKey(sum)) {
                    map.put(sum, 1L);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
                return 1;
            } else {
                return 0;
            }
        }
        //sum<=bag 并且 index <= end（还有货）
        //1.不要当前index位置的货物
        long ways = func(arr, index + 1, end, sum, bag, map);
        //2.要当前index位置的货物
        ways += func(arr, index + 1, end, sum + arr[index], bag, map);
        return ways;
    }


}
