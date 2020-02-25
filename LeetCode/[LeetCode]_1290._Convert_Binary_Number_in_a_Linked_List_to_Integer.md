# [LeetCode] 1290. Convert Binary Number in a Linked List to Integer

[TOC]

## 链接

[1290. Convert Binary Number in a Linked List to Integer](https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/)

## 题目

Given `head` which is a reference node to a singly-linked list. The value of each node in the linked list is either 0 or 1. The linked list holds the binary representation of a number.

Return the decimal value of the number in the linked list.

**Example 1:**

```text
Input: head = [1,0,1]
Output: 5
Explanation: (101) in base 2 = (5) in base 10
```

**Example 2:**

```text
Input: head = [0]
Output: 0
```

**Example 3:**

```text
Input: head = [1]
Output: 1
```

**Example 4:**

```text
Input: head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
Output: 18880
```

**Example 5:**

```text
Input: head = [0,0]
Output: 0
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
    public int getDecimalValue(ListNode head) {
        ListNode cur = head;
        
        int number = 0;
        while (cur != null) {
            number = (number << 1) + cur.val;
            cur = cur.next;
        }
        
        return number;
    }
}
```

