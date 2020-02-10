# [LeetCode] 429. N-ary Tree Level Order Traversal

[TOC]

## 链接

[429. N-ary Tree Level Order Traversal](https://leetcode.com/problems/n-ary-tree-level-order-traversal/)

## 题目

Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

**Example 1:**

```text
Input: root = [1,null,3,2,4,null,5,6]
Output: [[1],[3,2,4],[5,6]]
```

**Example 2:**

```text
Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
```

**Constraints:**

1. The height of the n-ary tree is less than or equal to `1000`
2. The total number of nodes is between `[0, 10^4]`

## 代码

```Java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> treeValList = new ArrayList<>();

        List<Node> nodeQueue = new ArrayList<>();

        nodeQueue.add(root);
        while (!nodeQueue.isEmpty()) {
            List<Integer> levelValList = new ArrayList<>();
            for (Node node : nodeQueue) {
                levelValList.add(node.val);
            }

            treeValList.add(levelValList);

            ArrayList<Node> tempNodeQueue = new ArrayList<>();
            for (Node node : nodeQueue) {
                tempNodeQueue.addAll(node.children);
            }

            nodeQueue = tempNodeQueue;
        }

        return treeValList;
    }
}
```

