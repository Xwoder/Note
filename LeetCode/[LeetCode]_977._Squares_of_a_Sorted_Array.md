# [LeetCode] 977. Squares of a Sorted Array

[TOC]

## 链接

[977. Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)

## 题目

Given an array of integers `A` sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.

**Example 1:**

```text
Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
```

**Example 2:**

```text
Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]
```

**Note:**

* `1 <= A.length <= 10000`
* `-10000 <= A[i] <= 10000`
* `A` is sorted in non-decreasing order.

## 代码

### 实现1

```Java
class Solution {
    public int[] sortedSquares(int[] A) {
        return Arrays
                .stream(A)
                .map(i -> i * i)
                .sorted()
                .toArray();
    }
}
```

### 实现2

```Java
class Solution {
    public int[] sortedSquares(int[] A) {

        int[] B = new int[A.length];
        int index = A.length - 1;

        int startIndex = 0;
        int endIndex = A.length - 1;

        while (startIndex <= endIndex) {
            int startValue = A[startIndex];
            int endValue = A[endIndex];

            int square;
            if (Math.abs(startValue) > Math.abs(endValue)) {
                square = startValue * startValue;
                ++startIndex;
            } else {
                square = endValue * endValue;
                --endIndex;
            }

            B[index] = square;
            --index;
        }

        return B;
    }
}
```

