# [LeetCode] 1295. Find Numbers with Even Number of Digits

[TOC]

## 链接

[1295. Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/)

## 题目

Given an array `nums` of integers, return how many of them contain an **even number** of digits.

**Example 1:**

```text
Input: nums = [12,345,2,6,7896]
Output: 2
Explanation: 
12 contains 2 digits (even number of digits). 
345 contains 3 digits (odd number of digits). 
2 contains 1 digit (odd number of digits). 
6 contains 1 digit (odd number of digits). 
7896 contains 4 digits (even number of digits). 
Therefore only 12 and 7896 contain an even number of digits.
```

**Example 2:**

```text
Input: nums = [555,901,482,1771]
Output: 1 
Explanation: 
Only 1771 contains an even number of digits.
```

**Constraints:**

* `1 <= nums.length <= 500`
* `1 <= nums[i] <= 10^5`

## 代码

### 实现1

```Java
class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;

        for (int n : nums) {
            int digits = 0;
            while (n > 0) {
                n /= 10;
                ++digits;
            }

            if ((digits & 1) == 0) {
                ++count;
            }
        }

        return count;
    }
}
```

### 实现2

```Java
class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;

        for (int n : nums) {
            int digits = (int) (Math.log10(n) + 1);
            if ((digits & 1) == 0) {
                ++count;
            }
        }

        return count;
    }
}
```


