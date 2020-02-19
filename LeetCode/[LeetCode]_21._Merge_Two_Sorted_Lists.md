# [LeetCode] 21. Merge Two Sorted Lists

[TOC]

## 链接

[21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)

## 题目

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

**Example:**

```text
Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
```

## 代码

### 实现1

> 迭代实现

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode mergedList = new ListNode(0);
        ListNode mergedListCur = mergedList;

        ListNode cur1 = l1;
        ListNode cur2 = l2;

        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                mergedListCur.next = cur1;
                cur1 = cur1.next;
            } else {
                mergedListCur.next = cur2;
                cur2 = cur2.next;
            }
            mergedListCur = mergedListCur.next;
        }

        if (cur1 != null) {
            mergedListCur.next = cur1;
        }

        if (cur2 != null) {
            mergedListCur.next = cur2;
        }

        return mergedList.next;
    }
}
```

### 实现2

> 递归实现

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```

