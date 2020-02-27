# [LeetCode] 1185. Day of the Week

[TOC]

## 链接

[1185. Day of the Week](https://leetcode.com/problems/day-of-the-week/)

## 题目

Given a date, return the corresponding day of the week for that date.

The input is given as three integers representing the `day`, `month` and `year` respectively.

Return the answer as one of the following values `{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}`.

**Example 1:**

```text
Input: day = 31, month = 8, year = 2019
Output: "Saturday"
```

**Example 2:**

```text
Input: day = 18, month = 7, year = 1999
Output: "Sunday"
```

**Example 3:**

```text
Input: day = 15, month = 8, year = 1993
Output: "Sunday"
```
 
**Constraints:**

* The given dates are valid dates between the years 1971 and 2100.

## 代码

```Java
class Solution {
    public String dayOfTheWeek(int day, int month, int year) {
        if (month == 1 || month == 2) {
            month += 12;
            year--;
        }

        int iWeek = (day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7;

        String result[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        return result[iWeek];
    }
}
```

