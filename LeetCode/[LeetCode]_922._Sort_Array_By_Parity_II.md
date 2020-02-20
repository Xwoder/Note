# [LeetCode] 922. Sort Array By Parity II

[TOC]

## 链接

[922. Sort Array By Parity II](https://leetcode.com/problems/sort-array-by-parity-ii/)

## 题目

Given an array `A` of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever `A[i]` is odd, `i` is odd; and whenever `A[i]` is even, `i` is even.

You may return any answer array that satisfies this condition.

**Example 1:**

```text
Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
```

**Note:**

1. `2 <= A.length <= 20000`
2. `A.length % 2 == 0`
3. `0 <= A[i] <= 1000`

## 代码

```Java
class Solution {
    public int[] sortArrayByParityII(int[] A) {
        int evenIndex = 0;
        int oddIndex = A.length - 1;

        while (true) {
            while (evenIndex < A.length && A[evenIndex] % 2 == 0) {
                evenIndex += 2;
            }

            while (oddIndex >= 0 && A[oddIndex] % 2 == 1) {
                oddIndex -= 2;
            }

            if (evenIndex >= A.length || oddIndex < 0) {
                break;
            }

            swap(A, evenIndex, oddIndex);
        }

        return A;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
```

