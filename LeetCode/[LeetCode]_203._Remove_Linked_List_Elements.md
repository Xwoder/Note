# [LeetCode] 203. Remove Linked List Elements

[TOC]

## 链接

[203. Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/)

## 题目

> Remove all elements from a linked list of integers that have value val.
> 
> **Example:**
> 
> ```
> Input:  1->2->6->3->4->5->6, val = 6
> Output: 1->2->3->4->5
> ```

## 代码

```Java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            ListNode cur = prev.next;
            if (cur.val == val) {
                prev.next = cur.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }
}
```

