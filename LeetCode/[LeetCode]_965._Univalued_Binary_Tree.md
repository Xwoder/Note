# [LeetCode] 965. Univalued Binary Tree

[TOC]

## 链接

[965. Univalued Binary Tree](https://leetcode.com/problems/univalued-binary-tree/)

## 题目

A binary tree is univalued if every node in the tree has the same value.

Return `true` if and only if the given tree is univalued.

**Example 1:**

```text
Input: [1,1,1,1,1,null,1]
Output: true
```

**Example 2:**

```text
Input: [2,2,2,5,2]
Output: false
```
 
**Note:**

1. The number of nodes in the given tree will be in the range [1, 100].
2. Each node's value will be an integer in the range [0, 99].

## 代码

### 实现1

```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.val != root.val) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return true;
    }
}
```

### 实现2

```Java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        boolean leftTreeIsUnivalTree = (root.left == null ||
                                        (root.val == root.left.val && isUnivalTree(root.left)));
        boolean rightTreeIsUnivalTree = (root.right == null ||
                                         (root.val == root.right.val && isUnivalTree(root.right)));
        return leftTreeIsUnivalTree && rightTreeIsUnivalTree;
    }
}
```

