# [LeetCode] 20. Valid Parentheses

[TOC]

## 链接

[20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)

## 题目

> Given a string containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.
> 
> An input string is valid if:
> 
> 1. Open brackets must be closed by the same type of brackets.
> 2. Open brackets must be closed in the correct order.
> 
> Note that an empty string is also considered valid.
> 
> **Example 1:**
> 
> ```text
> Input: "()"
> Output: true
> ```
> 
> **Example 2:**
> 
> ```text
> Input: "()[]{}"
> Output: true
> ```
> 
> **Example 3:**
> 
> ```text
> Input: "(]"
> Output: false
> ```
> 
> **Example 4:**
> 
> ```text
> Input: "([)]"
> Output: false
> ```
> 
> **Example 5:**
> 
> ```text
> Input: "{[]}"
> Output: true
> ```

## 代码

### 版本1

```Java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character parentheses : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(parentheses);
            } else {
                Character topParentheses = stack.peek();
                if (parentheses == ')' && topParentheses == '(') {
                    stack.pop();
                } else if (parentheses == ']' && topParentheses == '[') {
                    stack.pop();
                } else if (parentheses == '}' && topParentheses == '{') {
                    stack.pop();
                } else {
                    stack.push(parentheses);
                }
            }
        }

        return stack.isEmpty();
    }
}
```

### 版本2

```Java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character parentheses : s.toCharArray()) {
            if (parentheses == '(' || parentheses == '['  || parentheses == '{' || stack.isEmpty()) {
                stack.push(parentheses);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                Character topParentheses = stack.pop();

                if (topParentheses == '(' && parentheses != ')') {
                    return false;
                }

                if (topParentheses == '[' && parentheses != ']') {
                    return false;
                }

                if (topParentheses == '{' && parentheses != '}') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
```

