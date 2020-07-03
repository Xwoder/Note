# Python 内置数据结构之 tuple

[TOC]

## 基本使用

### 定义

```python
tuple1 = (1, 2)
tuple2 = (1, )

emptyTuple = ()
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

