package code11_List_BinaryTree1;

//常见面试题：环状链表「List：top2 hard」
//给定两个可能有环也可能无环的单链表，头节点head1和head2，
//请实现一个函数，如果两个链表相交，请返回相交的第一个节点。
//如果不相交，返回null
//要求：如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。

///注意：只有一个next，所以相交了就不会叉开
/// 思路：
/// 先判断两个链表是否有环，有环的话，返回第一个入环的节点，没有环的话，返回null    「getLoopNode方法」
/// 1.一个有环，一个无环：
/// 1.1.如果一个链表有环，一个链表无环，肯定不相交  「直接返回return null」
/// 2.如果两个链表无环 「noLoop方法」
/// 2.1.如果不相交，两个链表的最后一个节点不相等，直接返回null
/// 2.2.如果相交，先知道两个链表的length差多少，长的链表先走length差值步，然后再一起走，如果两个节点一样了，就是相交节点
/// 3.如果两个链表有环 「bothLoop方法」
/// 3.1.两个链表不相交：
/// 3.1.两个链表相交，入环节点是同一个：loop1==loop2 ,把环那部分去掉，就是重新走 2.「noLoop方法」
/// 3.2.两个链表相交，入环节点不是同一个：那loop1和loop2都对
public class Code02_CycleList{

    //结构上相交，不用判断值
    public static class Node {
        public int value;
        public Node next;
    }

    public static Node getIntersectNode(Node head1,Node head2){

        if (head1==null||head2==null){
            return null;
        }

        Node loop1=getLoopNode(head1);
        Node loop2=getLoopNode(head2);

        //1.如果两个链表有一个无环，肯定不相交
        if (loop1==null&&loop2==null){
            return noLoop(head1,head2);
        }

        //2.如果两个链表都有环
        if (loop1!=null&&loop2!=null){
            return bothLoop(head1,loop1,head2,loop2);
        }

        //3.一个链表有环，一个链表没环
        return null;

    }





    //找到链表的第一个入环节点，如果无环，返回null
    ///这里涉及到了一个定理：
    /// 1.使用快慢指针，快慢指针，快指针每次走两步，慢指针每次走一步，如果有环，快慢指针一定会相遇
    /// 2.相遇的时候，fast从head开始从头走
    /// 3.只要走一样的步数，slow指针和fast指针一定会相遇，相遇的节点就是入环节点
    public static Node getLoopNode(Node head){
        if(head==null||head.next==null||head.next.next==null){
            return null;
        }
        //slow 慢 ； fast 快
        Node slow=head.next;
        Node fast=head.next.next;
        while(slow!=fast){
            if(fast.next==null||fast.next.next==null){
                return null;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        //fast 从头开始走
        fast=head;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }


    //如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
    public static Node noLoop(Node head1,Node head2){
        if (head1==null||head2==null){
            return null;
        }

        Node cur1=head1;
        Node cur2=head2;
        int count=0;
        while(cur1.next!=null){
            count++;
            cur1=cur1.next;
        }
        while(cur2.next!=null){
            count--;
            cur2=cur2.next;
        }

        //如果最后一个极点不是同一个，返回null，表示没有相交的地方
        if(cur1!=cur2){
            return null;
        }


        //count：链表1长度减去链表2长度的值
        //谁长谁的头变成cur1，谁短谁的头变成cur2
        cur1=count>0?head1:head2;
        cur2=cur1==head1?head2:head1;
        int lengthMore = Math.abs(count);

        //找差值的节点
        while(lengthMore>0){
            lengthMore--;
            cur1=cur1.next;
        }

        //直到遍历到一样的
        while(cur1!=cur2){
            cur1=cur1.next;
            cur2=cur2.next;
        }

        return cur1;

    }


    //两个有环链表，返回第一个相交节点，如果不相交返回null
    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1=null;
        Node cur2=null;

        //3.2.入环节点是同一个
        if(loop1==loop2){

            //noLoop方法
            cur1=head1;
            cur2=head2;
            int count=0;
            while(cur1.next!=null){
                count++;
                cur1=cur1.next;
            }
            while(cur2.next!=null){
                count--;
                cur2=cur2.next;
            }

            //count：链表1长度减去链表2长度的值
            //谁长谁的头变成cur1，谁短谁的头变成cur2
            cur1=count>0?head1:head2;
            cur2=cur1==head1?head2:head1;
            int lengthMore = Math.abs(count);

            while(lengthMore>0){
                lengthMore--;
                cur1=cur1.next;
            }

            while(cur1!=cur2){
                cur1=cur1.next;
                cur2=cur2.next;
            }
            return cur1;

        }else {
            //3.1.和3.3.入环节点不是同一个
            cur1=loop1.next;
            while (cur1!=loop1){//判断是否是单节点形成了环
                //3.3.遍历环，如果遍历到loop2，说明loop1和loop2是同一个环，返回loop1
                if (cur1==loop2){
                    return loop1;
                }
                cur1=cur1.next;
            }
            //3.1.上述遍历不到，就是不相交
            return null;
        }




    }





}
