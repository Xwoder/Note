# Python 内置数据结构之 tuple

[TOC]

## 定义

### 多元素元组

定义一个包含 2 个元素的元组

```python
tuple1 = (1, 2)
```

### 单元素元组

```python
single_element_tuple = (10, )

# <class 'tuple'>
type(single_element_tuple)
```

如果省略了逗号，则其定义不是一个元组

```python
t = (10)

# <class 'int'>
type(t)
```

### 空元组

定义一个不包含任何元素的**空元组**

```python
empty_tuple = ()
```

## 统计

### 长度

```python
len(tuple1)
```

### 计数

因计数方式与 `list` 的相同，参见 `list` 中的相关内容

## 访问

因访问方式与 `list` 的相同，参见 `list` 中的相关内容

## 查找

因查找方式与 `list` 的相同，参见 `list` 中的相关内容

## 遍历

### for 循环编列

```python
T = (1, 3, 5, 7, 9)

for item in T:
    print(item)

# 输出
# 1
# 3
# 5
# 7
# 9
```

### while 循环遍历

```python
T = (1, 3, 5, 7, 9)

i = 0
while i < len(T):
    item = T[i]
    print(item)
    i += 1

# 输出
# 1
# 3
# 5
# 7
# 9s
```

## 类型转换

### 转 list

```python
name_tuple = ('Jack', 'Rose', 'Emma')
name_list = list(name_tuple)

# list
type(name_list)
```

## 解包

### *rest

```
values = (1, 2, 3, 4, 5, 6, 7, 8, 9)
one, two, *rest = values

# 1
print(one)

# 2
print(two)

# [3, 4, 5, 6, 7, 8, 9]
print(rest)
```

可以通过将 `rest` 替换为 `_` 来表示忽略剩余数据

```Python
values = (1, 2, 3, 4, 5, 6, 7, 8, 9)
one, two, *_ = values
```