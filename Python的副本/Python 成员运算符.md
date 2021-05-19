# Python 成员运算符

[TOC]

成员运算符 `in` 和 `not in` 用于确定序列中是否包含指定的元素或子序列

## 字符串

```python
# True
"llo" in "Hello World"     

# False
"llo" not in "Hello World"                                                                                       

# False
"abc" in "Hello World"

# True
"abc" not in "Hello World"                                                               
```

## list/tuple

```python
# True
20 in [10, 20, 30, 40]

# False
20 not in [10, 20, 30, 40]
```

## dict

```python
person_info = {
    'name': 'Jack',
    'age': 18,
    'height': 175
}

# True
'name' in person_info
# False
'name' not in person_info

# True
'age' in person_info
# False
'age' not in person_info

# True
'height' in person_info
# False
'height' not in person_info
```

将成员运算符作用于字典，其比较和判断的是字典的 `key` 而不是 `value`

