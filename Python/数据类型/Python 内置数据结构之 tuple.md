# Python 内置数据结构之 tuple

[TOC]

## 基本使用

### 定义

定义一个包含 2 个元素的元组

```python
tuple1 = (1, 2)
```

定义一个只包含 1 个元素的元组

```python
tuple2 = (1, )
```

定义一个不包含任何元素的空元组

```python
empty_tuple = ()
```

### 长度

```python
len(tuple1)
```

### 访问

参见 [list](mweblib://15382946802795)

## 空元组

```python
empty_tuple = ()

# <class 'tuple'>
type(empty_tuple)
```

## 单元素元组

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

## 遍历

### for 循环编列

```python
T = [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]

for item in T:
    print(item)

# 输出
# 1
# 3
# 5
# 7
# 9
# 2
# 4
# 6
# 8
# 10
```

### while 循环遍历

```python
T = [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]

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
# 9
# 2
# 4
# 6
# 8
# 10
```

## 类型转换

### list 转 tuple

```python
name_list = ['Jack', 'Rose', 'Emma']
name_tuple = tuple(name_list)

# tuple
type(name_tuple)
```

## *rest

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