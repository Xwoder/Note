# set

[TOC]

## 定义

`set` 是一个无序的不重复元素序列。

可以使用大括号 `{ }` 或者 `set()` 函数创建集合。创建一个空集合必须用 `set()` 而不是 `{ }`，因为 `{ }` 是用来创建一个空字典。

```python
s = set([1, 2, 3])
# s == {1, 2, 3}

s = set([1, 1, 2, 2, 3, 3])
# s == {1, 2, 3}

s = set(1, 2, 3)
# s == {1, 2, 3}

s = {1, 2, 3, 3, 4, 4, 4, 5}
# s == {1, 2, 3, 4, 5}
```

## 基本操作

### 添加

```python
s.add(4)
# s == {1, 2, 3, 4}
```

### 删除

#### remove

删除指定数据，若数据不存在，则报错

```python
s.remove(3)
# s == {1, 2, 4}

s.remove(3)
# KeyError                                  Traceback (most recent call last)
# <ipython-input-35-b06e0f5ff900> in <module>
# ----> 1 s.remove(3)
# 
# KeyError: 3
```

#### discard

删除指定数据，若数据不存在也不报错

```python
s.discard(3)
# s == {1, 2, 4}
```

#### pop

从集合中随机删除一个数据，并返回

## 多集合操作

### 交集

```python
s1 = set([1, 2, 3])
s2 = set([2, 3, 4])

s3 = s1 & s2
# s3 == {2, 3}
```

### 并集

```python
s1 = set([1, 2, 3])
s2 = set([2, 3, 4])

s3 = s1 | s2
# s3 == {1, 2, 3, 4}
```

### 更新

```Python
s1 = set([1, 2, 3])
s2 = set([2, 3, 4])

s1.update(s2)

# s1 == {1, 2, 3, 4}
```

