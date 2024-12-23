package code07_Heap;



//大根堆和小根堆都是使用数组实现的
//当然，这里是对 int堆 的原理说明：

//### 数组表示法
//
//由于堆是完全二叉树，可以用数组表示，不需要指针来表示树结构。
//
//#### 节点索引关系
//- 给定一个节点在数组中的索引 `i`：
//  - **父节点**的索引： `(i - 1) / 2`
//  - **左子节点**的索引： `2 * i + 1`
//  - **右子节点**的索引： `2 * i + 2`
//


//### 堆操作 原理
//
//#### 1. 插入
//- **步骤**：
//  1. 将新元素添加到数组末尾。
//  2. 执行上滤操作（Bubble Up）：比较该元素与其父节点，如果不符合堆的性质，交换它们，直到满足堆的性质。
//
//#### 2. 删除堆顶（最大值或最小值）
//- **步骤**：
//  1. 将堆顶元素（最大值或最小值）移除。
//  2. 用数组末尾的元素替换堆顶。
//  3. 执行下滤操作（Bubble Down）：比较该元素与其子节点，将其与较大的（大根堆）或较小的（小根堆）子节点交换，直到满足堆的性质。


//### 具体例子

//#### 大根堆插入
//假设我们有大根堆 `[9, 5, 6, 2, 3]`，插入 `7`：
//1. 插入后数组为 `[9, 5, 6, 2, 3, 7]`。
//2. 上滤操作：
//   - `7` 与其父节点 `6` 比较并交换：`[9, 5, 7, 2, 3, 6]`。
//   - `7` 再与其新父节点 `9` 比较，无需交换。

//#### 小根堆删除
//假设我们有小根堆 `[1, 3, 6, 5, 9, 8]`，删除堆顶：
//1. 移除 `1`，用最后的 `8` 替代堆顶：`[8, 3, 6, 5, 9]`。
//2. 下滤操作：
//   - `8` 与其较小的子节点 `3` 比较并交换：`[3, 8, 6, 5, 9]`。
//   - `8` 再与其新较小的子节点 `5` 比较并交换：`[3, 5, 6, 8, 9]`。
//
//### 复杂度分析
//- **插入**：O(log n)，因为在完全二叉树中，树的高度为log n。
//- **删除**：O(log n)，同样因为涉及调整树的高度。


//堆，大小堆，优先级队列
//以下是大根堆为例子
public class Code04_Heap_Tree_Array {

    /*
    * 1.引用类型的话大根堆和小根堆的转换就是比较器不一样
    * 2.大根堆要转换成小根堆，就是直接对new 对象的直接传的比较器进行修改即可
    * */


    //下沉
    //整理排序，整理index在数组上的位置 「这是一个下沉的过程，看看那index位置上有没有他的孩子比他大」
    //注意点：
    //1.index 为 你在这一个堆中发生变化的位置下标
    //2.heapSize 为 这个堆的大小，arr.length>=heapSize,后面没有用到数组上的位置，我们不用管，要用到，我们可以直接赋值覆盖
    public static void heapify(int[] arr,int index,int heapSize){
        int left=index*2+1;
        int right=index*2+2;
        while (left<heapSize){
            int larger = right<heapSize && arr[right]>arr[left] ? right:left;
            larger = arr[larger]>arr[index] ? larger:index;
            if (larger==index){
                break;
            }
            swap(arr,larger,index);
            index=larger;
            left=index*2+1;

        }
    }


    //上升
    //新进来的数停在了index位置上,原理就是在数组上更换位置「这是一个上升的过程，看看index之前有没有比他小的数」
    public static void heapInsert(int[] arr,int index){
        //1.index到0时候停止
        //2.父节点比该节点大了也停止
        while(arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }



    public static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


}
