package code10_List_SortSummary;



//给定一个单链表的头节点 head，判断该链表是否是回文链表。
//回文链表指的是从前往后和从后往前读的序列相同。
/*
* 1.用容器：用栈实现，遍历每一个放入栈中，遍历结束后，将栈中的元素依次取出来，判断是否相同。
*
* 2.不使用容器：
* 2.1.找到链表的中点：利用快慢指针（tortoise and hare）技术。
* 2.2.反转链表的后半部分：将链表后半部分反转。
* 2.3.比较前半部分和反转后的后半部分：如果两部分相同，则链表是回文的。
* 2.4.恢复链表：如果需要保持链表的原始结构，可以在检查完毕后将后半部分再次反转。
* */
public class Code03_palindrome {


    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: 找中点
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: 逆序
        ListNode secondHalf = reverseList(slow);

        // Step 3: 逆序拿到最后一个节点开头的逆序
        ListNode firstHalf = head;
        ListNode secondHalfCopy = secondHalf;  // To restore the list later

        while (secondHalf != null) {
            if (firstHalf.value != secondHalf.value) {
                // Step 4: 不一致了，恢复链表，返回false
                reverseList(secondHalfCopy);
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        // Step 4: 一致了，恢复链表，返回true
        reverseList(secondHalfCopy);
        return true;
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            ListNode nextNode = current.next;

            current.next = prev;
            prev = current;

            current = nextNode;
        }

        return prev;
    }



    class ListNode {
        int value;
        ListNode next;

        ListNode(int value) {
            this.value = value;
            this.next = null;
        }
    }

}



