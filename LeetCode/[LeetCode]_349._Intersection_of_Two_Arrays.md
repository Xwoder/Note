# [LeetCode] 349. Intersection of Two Arrays

[TOC]

## 链接

[349. Intersection of Two Arrays](https://leetcode.com/problems/intersection-of-two-arrays/)

## 题目

Given two arrays, write a function to compute their intersection.

**Example 1:**

```text
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
```

**Example 2:**

```text
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
```

**Note:**

* Each element in the result must be unique.
* The result can be in any order.

## 代码

> Swift 实现

```Swift
class Solution {
    func intersection(_ nums1: [Int], _ nums2: [Int]) -> [Int] {
        let s1 = Set<Int>(nums1)
        let s2 = Set<Int>(nums2)
        let intersection = s1.intersection(s2)
        return Array(intersection)
    }
}
```

