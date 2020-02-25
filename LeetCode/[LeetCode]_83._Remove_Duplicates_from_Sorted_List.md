# [LeetCode] 83. Remove Duplicates from Sorted List

[TOC]

## 链接

[83. Remove Duplicates from Sorted List
](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)

## 题目

Given a sorted linked list, delete all duplicates such that each element appear only once.

**Example 1:**

```text
Input: 1->1->2
Output: 1->2
```

**Example 2:**

```text
Input: 1->1->2->3->3
Output: 1->2->3
```

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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}
```

