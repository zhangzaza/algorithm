package code08_ReinforcedHeap;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//加强堆的实现
//所有的实现都是基于数组实现的，所以时间复杂度都是O(logN)
//实现的功能：可删除堆内的任意元素，允许在对数时间内删除任意元素，而不仅仅是删除堆顶元素。
public class Code02_HeapGreater<T> {

    private ArrayList<T> heap;
    private int heapSize;
    private Comparator<? super T> comparator;//这个字段用于存储一个比较器对象，该比较器可以对类型 T 或其父类的对象进行比较。
    private HashMap<T, Integer> indexMap;//反向索引表

    public Code02_HeapGreater(Comparator<T> c) {
        heap = new ArrayList<>();
        heapSize = 0;
        indexMap = new HashMap<>();
        comparator=c;
    }

    public int size(){
        return heapSize;
    }

    public boolean isEmpty(){
        return heapSize==0;
    }

    public boolean contains(T obj){
        return indexMap.containsKey(obj);
    }

    public T peek(){
        return heap.get(0);
    }

    public void push(T obj){
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }


    public T pop(){
        T ans=heap.get(0);
        swap(0,--heapSize);

        heap.remove(heapSize);
        indexMap.remove(ans);

        heapify(0);
        return ans;
    }

    public void remove(T obj){
        T replace=heap.get(heapSize-1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);

        //如果删除的不是最后一个元素
        if (replace!=obj){
            heap.set(index,replace);
            indexMap.put(replace,index);
            resign(replace);
        }

    }


    //上升或者下沉只发生一次
    public void resign(T obj){
        Integer i = indexMap.get(obj);
        heapInsert(i);
        heapify(i);
    }


    //上升
    //使用比较器进行比较
    private void heapInsert(int index){
        while(comparator.compare(heap.get((index-1)/2),heap.get(index))>0){
            swap(index,(index-1)/2);
            index = (index-1)/2;
        }
    }


    //返回堆上的所有元素
    public List<T> getAllElements(){
        ArrayList<T> ts = new ArrayList<>();
        for (T t : heap) {
            ts.add(t);
        }
        return ts;
    }


    //下沉
    //使用比较器进行实现
    public  void heapify(int index){
        int left=index*2+1;
        int right=index*2+2;
        while (left<heapSize){
            int larger = right<heapSize && comparator.compare(heap.get(right),heap.get(left))>0 ? right:left;
            larger = comparator.compare(heap.get(larger),heap.get(index))<0 ? larger:index;
            if (larger==index){
                break;
            }
            swap(larger,index);
            index=larger;
            left=index*2+1;

        }
    }



    public  void swap(int a,int b){
       T o1=heap.get(a);
       T o2=heap.get(b);
       heap.set(a,o2);
       heap.set(b,o1);
       indexMap.put(o2,a);
       indexMap.put(o1,b);
    }



}
