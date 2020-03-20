# [LeetCode] 1154. Day of the Year

[TOC]

## 链接

[1154. Day of the Year](https://leetcode.com/problems/day-of-the-year/)

## 题目

Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.

**Example 1:**

```text
Input: date = "2019-01-09"
Output: 9
Explanation: Given date is the 9th day of the year in 2019.
```

**Example 2:**

```text
Input: date = "2019-02-10"
Output: 41
```

**Example 3:**

```text
Input: date = "2003-03-01"
Output: 60
```

**Example 4:**

```text
Input: date = "2004-03-01"
Output: 61
```

## 代码

> `Swift` 实现

```Swift
class Solution {
    func dayOfYear(_ date: String) -> Int {
        let dateComponent = date.split(separator: "-").map {
            Int($0)!
        }
        
        let y = dateComponent[0]
        let m = dateComponent[1]
        let d = dateComponent[2]
        
        let isLeapYear = ((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)
        
        let numberOfDaysInMonth = [
            0,
            31,
            isLeapYear ? 29 : 28,
            31,
            30,
            31,
            30,
            31,
            31,
            30,
            31,
            30
        ]

        return numberOfDaysInMonth[1..<m].reduce(d, +);
    }
}
```

