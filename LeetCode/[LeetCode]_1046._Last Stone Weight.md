# 1046. Last Stone Weight

[TOC]

## 链接

[1046. Last Stone Weight](https://leetcode.com/problems/last-stone-weight/)

## 题目

We have a collection of stones, each stone has a positive integer weight.

Each turn, we choose the two **heaviest** stones and smash them together.  Suppose the stones have weights `x` and `y` with `x <= y`.  The result of this smash is:

* If `x == y`, both stones are totally destroyed;
* If `x != y`, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.

At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)

**Example 1:**

> **Input:** [2,7,4,1,8,1]
> **Output:** 1
> **Explanation:** 
> We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
> we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
> we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
> we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.

**Note:**

1. `1 <= stones.length <= 30`
2. `1 <= stones[i] <= 1000`

### 代码

```Java
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int s : stones) {
            heap.add(s);
        }

        while (heap.size() > 1) {
            Integer y = heap.poll();
            Integer x = heap.poll();
            if (x < y) {
                heap.add(y - x);
            }
        }

        return heap.isEmpty() ? 0 : heap.poll();
    }
}
```

