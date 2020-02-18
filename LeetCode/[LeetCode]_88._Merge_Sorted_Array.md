# [LeetCode] 88. Merge Sorted Array

[TOC]

## 链接

[88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)

## 题目

Given two sorted integer arrays *nums1* and *nums2*, merge *nums2* into *nums1* as one sorted array.

**Note:**

* The number of elements initialized in *nums1* and *nums2* are *m* and *n* respectively.
* You may assume that *nums1* has enough space (size that is greater or equal to *m + n*) to hold additional elements from *nums2*.

**Example:**

```text
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
```

## 代码

```Java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int cur1 = m - 1;
        int cur2 = n - 1;

        int tailCur = m + n - 1;

        while (cur1 >= 0 && cur2 >= 0) {
            if (nums1[cur1] > nums2[cur2]) {
                nums1[tailCur--] = nums1[cur1--];
            } else {
                nums1[tailCur--] = nums2[cur2--];
            }
        }

        while (cur2 >= 0) {
            nums1[tailCur--] = nums2[cur2--];
        }
    }
}
```

