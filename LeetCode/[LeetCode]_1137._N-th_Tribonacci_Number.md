# [LeetCode] 1137. N-th Tribonacci Number

[TOC]

## 链接

[1137. N-th Tribonacci Number](https://leetcode.com/problems/n-th-tribonacci-number/)

## 题目

The Tribonacci sequence $ T_n $ is defined as follows: 

$ T_0 = 0 $, $ T_1 = 1 $, $ T_2 = 1, $ and $ T_{n+3} = T_n + T_{n+1} + T_{n+2} $ for $ n >= 0 $.

Given `n`, return the value of $ T_n $.

**Example 1:**

```text
Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
```

**Example 2:**

```text
Input: n = 25
Output: 1389537
```

**Constraints:**

* `0 <= n <= 37`
* The answer is guaranteed to fit within a 32-bit integer, ie. `answer <= 2^31 - 1`.

## 代码

```Java
class Solution {
    public int tribonacci(int n) {
        int t0 = 0;
        int t1 = 1;
        int t2 = 1;

        int tn = 0;
        switch (n) {
            case 0: {
                tn = t0;
            }
            break;
            case 1: {
                tn = t1;
            }
            break;
            case 2: {
                tn = t2;
            }
            break;
            default: {
                while (n >= 3) {
                    tn = t0 + t1 + t2;
                    t0 = t1;
                    t1 = t2;
                    t2 = tn;
                    --n;
                }
            }
            break;
        }
        return tn;
    }
}
```

