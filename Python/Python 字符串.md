# Python 字符串

[TOC]

## 定义

利用双引号定义字符串

```python
"Hello World"
"I'm a programmer."
```

利用单引号定义字符串

```Python
'Hello World'
```

利用三引号定义多行字符串

```Python
'''123
456
789'''
```

非转义符串

```python
r'\\\t\\'
```

## 操作

### 长度

`len` 函数用于求字符串长度

```python
# 11
len('Hello World')
```

### 重复

```
"=" * 50
```

### 连接

使用 `+` 操作符连接字符串

```python
s1 = 'Hello'
s2 = ' '
s3 = 'World'

s4 = s1 + s2 + s3
print(s4)
​```~~~~

### 重复

使用操作符`*`可以重复一个指定的字符串指定次

​```python
'-' * 10
```

会得到

```
'----------'
```

### 访问字符

```python
str = 'Hello World'

# 'H'
str[0]

# 'd'
str[-1]
```

### 切片

```python
# 'Hello'
str[0:5]
```

### 遍历

使用`for`循环迭代

```python
for ch in str:
    print(ch)
    
# 输出
# H
# e
# l
# l
# o
#  
# W
# o
# r
# l
# d
```

### 计数

统计子字符串出现的次数

```python
# 2
'Hello World'.count('o')

# 1
'Hello World'.count('ld')

# 0
'Hello World'.count('abc')
```

### 格式判定

是否以指定字符串开头

```python
# True
print(str.startswith("He"))

# False
print(str.startswith("abc"))
```

是否以指定字符串结尾

```python
# True
print(str.endswith("ld"))

# False
print(str.endswith("abc"))
```

### 查找

`find` 方法

```python
# 2
print(str.find("llo"))

# -1
print(str.find("abc"))
```

### 替换

`replace` 方法不会修改原字符串，而是返回一个新的经过替换的字符串

```python
# Hello Python
'Hello World'.replace("World", "Python")
```

### 对齐

`center` 方法

```python
str_list = ['A', 'BCD', 'EFGHI', 'KLMNOPQ']

for str in str_list:
    print(str.center(7))
    
# 输出
#    A   
#   BCD  
#  EFGHI 
# KLMNOPQ

for str in str_list:
    print(str.center(7, '-'))
    
# 输出
# ---A---
# --BCD--
# -EFGHI-
# KLMNOPQ
```

### 左对齐/右对齐

```python
# 左对齐
for str in str_list:
    print(str.ljust(7))

# 输出
# A      
# BCD    
# EFGHI  
# KLMNOPQ

# 右对齐
for str in str_list:
    print(str.rjust(7))
    
# 输出
#       A
#     BCD
#   EFGHI
# KLMNOPQ
```

### 修剪

有如下定义的字符串

```python
str = ' Hello World '

# | Hello World |
print('|%s|' % str)
```

利用 `lstrip()` 进行左侧修剪

```Python
# |Hello World |
print('|%s|' % str.lstrip())
```

利用 `rstrip()` 进行左侧修剪

```Python
# | Hello World|
print('|%s|' % str.rstrip())
```

利用 `strip()` 进行两侧修剪


```Python
# |Hello World|
print('|%s|' % str.strip())
```

### 分割

利用 `split()` 方法根据指定字符分割字符串

`split()`方法默认使用空白字符切割字符串

```python
str = 'Hello World\nMy,name@is\tPython'

# ['Hello', 'World', 'My,name@is', 'Python']
str.split()
```

`sep` 参数可以接受一个字符，用于改变默认的切割字符

```Python
# ['Hello World\nMy', 'name@is\tPython']
str.split(sep=',')
```

### 拼接

####  `join()`函数

```python
# Hello World My name is Python
" ".join(['Hello', 'World', 'My', 'name', 'is', 'Python'])
```

#### `+` 运算符

```python
# 'Jack and Rose'
"Jack" + " and " + "Rose"
```

### 切片

可以把字符串视为 `list` 对其进行切片操作。请参考 `list` 的切片操作

### 查找

#### `index()`方法

```Python
# 7
'Hello World'.index('or')                                                                             
```

#### `find()`方法

```Python
# 7
'Hello World'.find('or')
```

### 包含关系

```Python
# True
'el' in 'Hello World'
```

## 格式化

### 占位符格式化

```python
name = 'Jack'
age = 18

'name = %s, age = %d' % (name, age)

# 结果
# 'name = Jack, age = 18'
```

如果仅有一个占位符，则可省略圆括号

```python
name = 'Python'
'Hello, %s' % name

# 结果
# 'Hello, Python'
```

#### 占位符

占位符 | 替换内容
--- | ---
`%d` | 整数
`%f` | 浮点数
`%s` | 字符串
`%x`| 十六进制整数

`%s` 除了表示字符串占位，还可以用于任意类型，其会将其他类型转换为字符串

```python
name = 'Jack'
age = 18

'name = %s, age = %s' % (name, age)

# 结果
# 'name = Jack, age = 18'
```

### format 函数格式化

```python
name = 'Jack'
age = 18
score = 90.5

'name = {0}, age = {1}, score = {2:.2f}'.format(name, age, score)

# 结果
# 'name = Jack, age = 18, score = 90.50'
```


