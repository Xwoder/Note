# [LeetCode] 206. Reverse Linked List

[TOC]

## 链接

[206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)

## 题目

> Reverse a singly linked list.
> 
> **Example:**
> 
> ```
> Input: 1->2->3->4->5->NULL
> Output: 5->4->3->2->1->NULL
> ```
> 
> **Follow up:**
> 
> A linked list can be reversed either iteratively or recursively. Could you implement both?

## 代码

### 递归版

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
    public ListNode reverseList(ListNode head) {
    	if (head == null || head.next == null) {
			return head;
		}
    	
        ListNode reversedList = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        
        return reversedList;
    }
}
```

### 迭代版

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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reversedList = null;
        ListNode tempNext = null;

        while (head != null) {
            tempNext = head.next;
            head.next = reversedList;
            reversedList = head;
            head = tempNext;
        }

        return reversedList;
    }
}
```

