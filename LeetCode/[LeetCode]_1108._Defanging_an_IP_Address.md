# [LeetCode] 1108. Defanging an IP Address

[TOC]

## 链接

[1108. Defanging an IP Address](https://leetcode.com/problems/defanging-an-ip-address/)

## 题目

Given a valid (IPv4) IP `address`, return a defanged version of that IP address.

A defanged IP address replaces every period `"."` with `"[.]"`.

**Example 1:**

```text
Input: address = "1.1.1.1"
Output: "1[.]1[.]1[.]1"
```

**Example 2:**

```text
Input: address = "255.100.50.0"
Output: "255[.]100[.]50[.]0"
```

**Constraints:**

* The given `address` is a valid IPv4 address.

## 代码

```Java
class Solution {
    public String defangIPaddr(String address) {
        return address.replaceAll("\\.", "\\[\\.\\]");
    }
}
```

