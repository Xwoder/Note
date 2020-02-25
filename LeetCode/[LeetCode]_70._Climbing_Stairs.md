# [LeetCode] 70. Climbing Stairs

[TOC]

## 链接

[70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

## 题目

You are climbing a stair case. It takes `n` steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given `n` will be a positive integer.

**Example 1:**

```text
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
```

**Example 2:**

```text
Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
```

## 代码

### 实现1

> HashMap 实现

```Java
class Solution {
    public int climbStairs(int n) {
        HashMap<Integer, Integer> stairsMap = new HashMap<>();

        stairsMap.put(0, 0);
        stairsMap.put(1, 1);
        stairsMap.put(2, 2);

        if (stairsMap.containsKey(n)) {
            return stairsMap.get(n);
        } else {
            int cur = stairsMap.size();
            while (cur <= n) {
                stairsMap.put(cur, stairsMap.get(cur - 1) + stairsMap.get(cur - 2));
                ++cur;
            }
        }

        return stairsMap.get(n);
    }
}
```

### 实现2

> List 实现

```Java
class Solution {
    public int climbStairs(int n) {
        List<Integer> stairsList = new ArrayList<>();

        stairsList.add(0);
        stairsList.add(1);
        stairsList.add(2);

        while (stairsList.size() <= n) {
            stairsList.add(stairsList.get(stairsList.size() - 1) + stairsList.get(stairsList.size() - 2));
        }

        return stairsList.get(n);
    }
}
```

