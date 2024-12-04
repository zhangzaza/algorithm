package code10_List_SortSummary;


/*
 * 常见面试题
 * 将单向链表按某值划分为左边小，中间相等，右边大的形式
 * 第一种方法：把链表放入数组里，在数组上做partition(笔试用)
 * 第二种方法：分成小于，大于，等于，然后重新连接(面试用)
 * */
public class Code04_quickSortList {

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static Node listPartition(Node head, int pivot) {
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;

        Node next = null;
        while (head != null) {
            //隔离链表的下一个元素，为head
            next = head.next;
            head.next = null;


            //对三个链表进行拼接
            if (head.value < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else if (head.value == pivot) {
                if (head.value == pivot) {
                    if (equalHead == null) {
                        equalHead = head;
                        equalTail = head;
                    } else {
                        equalTail.next = head;
                        equalTail = head;
                    }
                }
            } else {
                if (head.value > pivot) {
                    if (bigHead == null) {
                        bigHead = head;
                        bigTail = head;
                    } else {
                        bigTail.next = head;
                        bigTail = head;
                    }
                }
            }

            //下一个元素
            head = next;

        }

        //小于区域的尾巴 拼接 等于区域的头，等于区域的尾巴连大于区域的头
        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;//连接等于区域的尾巴
        }

        //上述已经整合了小于等于区域，所以这里的equalTail是两个区域的尾巴
        if (equalTail != null) {
            equalTail.next = bigHead;
        }


        //如果小于区域为空，返回等于区域的头，如果等于区域也为空，返回大于区域的头
        return smallHead != null ? smallHead : equalHead != null ? equalHead : bigHead;


    }


}
