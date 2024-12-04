package code03_binarySearch;

import java.util.HashMap;
import java.util.TreeMap;

public class Code04_hashMapTreeMap {

    public static void main(String[] args) {

        System.out.println("=======================HashMap======================");

        //无论hashmap什么操作，时间复杂度都是常数级别的
        HashMap<String, String> map = new HashMap<>();
        map.put("zhangjunhao", "我是zhangzaza");
        System.out.println(map.containsKey("zhangjunhao"));
        System.out.println(map.containsValue("zhangjunhao"));
        System.out.println(map.get("zhangjunhao"));

        map.put("zhangjunhao","他是zhangzaza");
        System.out.println(map.get("zhangjunhao"));

//        map.remove("zhangjunhao");
//        System.out.println(map.containsKey("zhangjunhao"));
//        System.out.println(map.get("zhangjunhao"));

        String test1 ="zhangjunhao";
        String test2 ="zhangjunhao";
        System.out.println(map.containsKey(test1));
        System.out.println(map.containsKey(test2));

        HashMap<Integer, String> map1 = new HashMap<>();
        map1.put(1234567,"我是12345687");

        Integer key1 = 1234567;
        Integer value1 = 1234567;
        System.out.println(key1==value1);
        System.out.println(key1.equals(value1));
        System.out.println(map1.containsKey(key1));
        System.out.println(map1.containsKey(value1));



        Node node1 =new Node(1);
        Node node2 =new Node(1);
        HashMap<Node, String> nodeMap = new HashMap<>();
        nodeMap.put(node1,"12345");
        //在hashmap中的耗损就是存储的字节  key为8字节
        System.out.println(nodeMap.containsKey(node1));
        System.out.println(nodeMap.containsKey(node2));




        System.out.println("=======================TreeMap======================");

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1,"juan有点2");
        treeMap.put(2,"juan有点2");
        treeMap.put(13,"juan有点2");
        treeMap.put(4,"juan有点2");
        treeMap.put(3,"juan有点2");
        treeMap.put(5,"juan有点2");
        treeMap.put(16,"juan有点2");
        treeMap.put(7,"juan有点2");
        treeMap.put(81,"juan有点2");
        treeMap.put(19,"juan有点2");

        System.out.println(treeMap.containsKey(7));
        System.out.println(treeMap.containsKey(6));
        System.out.println(treeMap.get(8));

        treeMap.put(3,"juan有点牛");
        System.out.println(treeMap.get(3));

        treeMap.remove(3);
        System.out.println(treeMap.get(3));


        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        //<=5 离5最近的key告诉我
        System.out.println(treeMap.floorKey(5));
        //<=6 离6最近的key告诉我
        System.out.println(treeMap.floorKey(5));
        //>=5 离5最近的key告诉我
        System.out.println(treeMap.ceilingKey(5));
        //>=6 离6最近的key告诉我
        System.out.println(treeMap.ceilingKey(5));

        Node node3 =new Node(3);
        Node node4 =new Node(4);
        TreeMap<Node, String> nodeMap1 = new TreeMap<>();
        nodeMap1.put(node3,"12345"); // 会报错
        nodeMap1.put(node4,"12344");
//        Exception in thread "main" java.lang.ClassCastException: class code03binarySearch.Node cannot be cast to class java.lang.Comparable (code03binarySearch.Node is in unnamed module of loader 'app'; java.lang.Comparable is in module java.base of loader 'bootstrap')
//        at java.base/java.util.TreeMap.compare(TreeMap.java:1607)
//        at java.base/java.util.TreeMap.addEntryToEmptyMap(TreeMap.java:812)
//        at java.base/java.util.TreeMap.put(TreeMap.java:821)
//        at java.base/java.util.TreeMap.put(TreeMap.java:570)
//        at code03binarySearch.Code04_hashMapTreeMap.main(Code04_hashMapTreeMap.java:93)

    }


}


class Node{
    int key;

    public Node(int i) {
        this.key = i;
    }
}
