/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    
    // To initiate the divide and conquer merging process
    public ListNode mergeKLists(ListNode[] lists) {
        //Checking if the array is null 
        if (lists == null || lists.length == 0) {
            return null;
        }
        // Recursively merge lists from index 0 to the last index
        return mergeLists(lists, 0, lists.length - 1);
    }

    
    private ListNode mergeLists(ListNode[] lists, int start, int end) {
        //  case 1: Single list left in range, return it directly
        if (start == end) {
            return lists[start];
        }
        
        //  case 2: Exactly two lists left, merge them directly
        if (start + 1 == end) {
            return mergeTwoLists(lists[start], lists[end]);
        }

        //  Finding the middle index to split the range into two halves
        int mid = start + (end - start) / 2;
        
        // Conquer step: recursively process left half and right half
        ListNode left = mergeLists(lists, start, mid);
        ListNode right = mergeLists(lists, mid + 1, end);

        // Combine step: merge the two sorted halves and return
        return mergeTwoLists(left, right);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Compare nodes from both lists and append the smaller value
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next; 
        }

        // Add remaining nodes if l1 is longer
        if (l1 != null) {
            current.next = l1;
        // Add remaining nodes if l2 is longer
        } else if (l2 != null) {
            current.next = l2;
        }

        
        return dummy.next;
    }
}