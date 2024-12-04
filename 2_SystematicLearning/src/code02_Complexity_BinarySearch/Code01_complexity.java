package code02_Complexity_BinarySearch;


import java.util.List;

//常数时间的时间操作
public class Code01_complexity {

    /*
    * 常数时间：固定时间
    * arr[1千万]，arr[5千万]，找到某个数都是固定时间所以是固定时间，因为内存都是连续的，偏移量能知道
    * list[1千万]，list[5千万]，找到某个数的话只能一个一个循环跳，所以找地址不是常数操作
    * */

    ////时间复杂度O(N)
    public static void printList1(List<Integer> list) {
        list.forEach(System.out::println);
    }


    //时间复杂度O(N*2)
    public static void printList2(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    //以选择排序为例
    //1.从待排序的数组中选出最小的元素。
    //2.将该最小元素与数组的第一个元素交换位置。
    //3.在剩下的元素中重复上述步骤，将最小的元素放到已排序部分的末尾。
    //4.重复以上步骤，每次在未排序的部分中选出最小的元素，并放到已排序部分的末尾。最终完成排序。
    //看+比较 ： 等差数列
    //交换的次数 ： N


    /*
    * 评估算法最重要：时间复杂度/空间复杂度
    * 最重要的就是时间复杂度，然后再是空间复杂度
    *
    *
    * 如果是同等时间复杂度，那就是比拼常数项
    * 使用大文本加上大量 来测试，
    * */



// 1. **常数时间**: \(O(1)\)
// - 操作次数不随输入规模变化而变化。
//
// 2. **对数时间**: \(O(\log n)\)
// - 常见于二分查找。
//
// 3. **线性时间**: \(O(n)\)
// - 例如简单遍历数组。
//
// 4. **线性对数时间**: \(O(n \log n)\)
// - 常见于高效排序算法，如归并排序和快速排序。
//
// 5. **平方时间**: \(O(n^2)\)
// - 典型于简单的双重循环，如冒泡排序。
//
// 6. **立方时间**: \(O(n^3)\)
// - 三重循环的算法。
//
// 7. **指数时间**: \(O(2^n)\)
// - 例如解决某些递归问题的简单递归算法。
//
// 8. **阶乘时间**: \(O(n!)\)
// - 常见于全排列生成算法。





}
