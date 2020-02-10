# [LeetCode] 958. Check Completeness of a Binary Tree

[TOC]

## 链接

[958. Check Completeness of a Binary Tree](https://leetcode.com/problems/check-completeness-of-a-binary-tree/)

## 题目

Given a binary tree, determine if it is a complete binary tree.

**Definition of a complete binary tree from Wikipedia:**
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and $ 2^h $ nodes inclusive at the last level h.

**Example 1:**

```text
Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
```

**Example 2:**

```text
Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.
```

**Note:**

1. The tree will have between 1 and 100 nodes.

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
    public boolean isCompleteTree(TreeNode root) {
        boolean hasMetNull = false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                hasMetNull = true;
            } else {
                if (hasMetNull) {
                    return false;
                }
                queue.offer(node.left);
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
    public boolean isCompleteTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
    
        boolean hasMetNull = false;
    
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
    
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
    
            if (hasMetNull 
                        && (node.left != null || node.right != null)) {
                return false;
            }
    
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }
    
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                hasMetNull = true;
            }
        }
    
        return true;
    }
}
```

