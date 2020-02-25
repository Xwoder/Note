# [LeetCode] 1299. Replace Elements with Greatest Element on Right Side

[TOC]

## 链接

[1299. Replace Elements with Greatest Element on Right Side](https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/)

## 题目

Given an array `arr`, replace every element in that array with the greatest element among the elements to its right, and replace the last element with `-1`.

After doing so, return the array.

**Example 1:**

```text
Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]
```
 
**Constraints:**

* `1 <= arr.length <= 10^4`
* `1 <= arr[i] <= 10^5`

## 代码

```Java
class Solution {
    public int[] replaceElements(int[] arr) {
        int[] ans = new int[arr.length];
        ans[ans.length - 1] = -1;

        int max = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            ans[i] = Math.max(arr[i + 1], ans[i + 1]);
        }

        return ans;
    }
}
```

