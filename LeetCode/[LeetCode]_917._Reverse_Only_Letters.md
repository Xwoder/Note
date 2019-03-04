# [LeetCode] 917. Reverse Only Letters

[TOC]

## 链接

[917. Reverse Only Letters](https://leetcode.com/problems/reverse-only-letters/)

## 题目

> Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
> 
> **Example 1:**
>
> ```
> Input: "ab-cd"
> Output: "dc-ba"
> ```
> 
> **Example 2:**
> 
> ```
> Input: "a-bC-dEf-ghIj"
> Output: "j-Ih-gfE-dCba"
> ```
> 
> **Example 3:**
> 
> ```
> Input: "Test1ng-Leet=code-Q!"
> Output: "Qedo1ct-eeLg=ntse-T!"
> ```
> 
> **Note:**
> 
> 1. `S.length <= 100`
> 2. `33 <= S[i].ASCIIcode <= 122`
> 3. `S` doesn't contain `\` or `"`

## 代码

```C++
class Solution {
public:
    string reverseOnlyLetters(string S) {
        
        if (S.empty()) {
            return S;
        }
        
        string::size_type low = 0;
        string::size_type high = S.length() - 1;
        
        while (low < high) {
            while (low < high && !isalpha(S[low])) {
                ++low;
            }

            while (low < high && !isalpha(S[high])) {
                --high;
            }

            if (low < high) {
                swap(S[low++], S[high--]);
            }
        }

        return S;
    }
};
```

