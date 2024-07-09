package Bai1;

import java.util.Arrays;

public class Solution {
   static ListNode list = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3,
            new ListNode(4, new ListNode(5, new ListNode(6)))))));

    public static ListNode middleNode(ListNode head) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        int mid = count / 2;
        if (mid % 2 == 0) {
            for (int i = 0; i < mid; i++) {
                head = head.next;
            }
        } else {
            for (int i = 0; i <= mid; i++) {
                head = head.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4,5,6};
        System.out.println("Your list: " + Arrays.toString(arr));
        System.out.println("Out put: " + middleNode(list).val);
        System.out.println("Explanation: The middle node of the list is: " + middleNode(list).val);
    }
}
