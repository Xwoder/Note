# [LeetCode] 141. Linked List Cycle

[TOC]

## 链接

[141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)

## 题目

> Given a linked list, determine if it has a cycle in it.
> 
> To represent a cycle in the given linked list, we use an integer `pos` which represents the position (0-indexed) in the linked list where tail connects to. If `pos` is `-1`, then there is no cycle in the linked list.
> 
> **Example 1:**
> 
> ```text
> Input: head = [3,2,0,-4], pos = 1
> Output: true
> Explanation: There is a cycle in the linked list, where tail connects to the second node.
> ```
> 
> **Example 2:**
> 
> ```text
> Input: head = [1,2], pos = 0
> Output: true
> Explanation: There is a cycle in the linked list, where tail connects to the first node.
> ```
> 
> **Example 3:**
> 
> ```text
> Input: head = [1], pos = -1
> Output: false
> Explanation: There is no cycle in the linked list.
> ```

## 代码

```Java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;

        }
        return true;
    }
}
```

