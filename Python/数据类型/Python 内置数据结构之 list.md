# Python 内置数据结构之 list

[TOC]

## 基本使用

### 定义

```python
# 元素类型相同
classmates = ['Michael', 'Bob', 'Tracy']

# 元素类型不同
list1 = ['Apple', 123, True]

# 嵌套
list2 = ['python', 'java', ['asp', 'php'], 'scheme']

list3 = ['asp', 'php']
list4 = ['python', 'java', list3, 'scheme']

# 空列表
emptyList = []
```

### 统计

#### 长度

```python
len(classmates)
```

#### 计数

```python
L = [10, 20, 30, 40, 50, 10, 10, 60]

# 3
L.count(10)
```

### 访问

```python
classmates[0]
classmates[1]
classmates[2]
```

访问最后一个元素

```python
classmates[len(classmates) - 1]

classmates[-1]
```

### 查找

```python
L = [10, 20, 30, 40, 50, 10, 10, 60]

# 1
L.index(20)
```

### 添加

#### `append`

在列表的尾部追加元素

```python
classmates.append("Adam")

# classmates == ['Michael', 'Bob', 'Tracy', 'Adam']
```

#### `insert`

在列表指定的索引位置插入元素

```python
classmates.insert(1, "Jack")

# classmates == ['Michael', 'Jack', 'Bob', 'Tracy', 'Adam']
```

#### `extend`

将一个可遍历类型的元素追加至列表


```python
names = ['ChenLiu']

classmates = ['ZhangSan', 'LiSi', 'WangWu']

names.extend(classmates)

# names == ['ChenLiu', 'ZhangSan', 'LiSi', 'WangWu']
```

`extend` 方法会在原列表的尾部直接插入元素

#### `+`

```Python
['abc', 'def', 'ghi'] + ['jkl']

# ['abc', 'def', 'ghi', 'jkl']
```

`+` 运算符并不会修改原列表，还是返回一个连接后的新的列表

### 删除

#### `pop`

删除末尾元素

```python
member = classmates.pop()

# member == "Adam"
# classmates == ['Michael', 'Jack', 'Bob', 'Tracy']
```

删除指定位置元素

```python
member = classmates.pop(1)

# member == 'Jack'
# classmates == ['Michael', 'Bob', 'Tracy']
```

#### `del`

删除元素。其本质是将一个变量从内存中删除

```python
L = [1, 2, 3]
del L[1]

# 或者
# del(L[1])

# L == [1, 3]
```

#### `remove`

删除指定元素，如果元素在列表存在多个，则仅删除第1个

```python
L = ['Jack', 'Ross', 'Tom', 'Emma']
L.remove('Tom')

# L == ['Jack', 'Ross', 'Emma']
```

#### `clear`

清空列表

```python
L.clear()

# L == []
```

### 修改

```python
classmates[1] = 'Sarah'

# classmates == ['Michael', 'Sarah', 'Tracy']
```

### 排序

```python
L = [22, 17, 64, 14, 28, 39, 80, 76, 89, 47]

# 升序
L.sort()
# L == [14, 17, 22, 28, 39, 47, 64, 76, 80, 89]

# 降序
L.sort(reverse=True)
# L == [89, 80, 76, 64, 47, 39, 28, 22, 17, 14]
```

### 切片

```python
L = ['Michael', 'Sarah', 'Tracy', 'Bob', 'Jack']

# ['Michael', 'Sarah', 'Tracy']
L[0:3]

# ['Tracy', 'Bob', 'Jack']
L[2:5]

# ['Michael', 'Sarah', 'Tracy', 'Bob', 'Jack']
L[:]
```

如果从第一个下标从0开始，则其可以省略

```python
# True
L[0:3] == L[:3]
```

切片的索引可以为负数

```python
# ['Bob', 'Jack']
L[-2:]

# ['Tracy', 'Bob']
L[-3:-1]
```

切片还可以指定步长

```python
L = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

# [0, 2, 4, 6]
L[0:8:2]

# [0, 2, 4, 6, 8]
L[::2]
```

### 列表生成式

```python
# [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
[x for x in range(10)]

# [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
[x * x for x in range(10)]

# [0, 4, 16, 36, 64]
[x * x for x in range(10) if x % 3 == 0]

# ['AX', 'AY', 'AZ', 'BX', 'BY', 'BZ', 'CX', 'CY', 'CZ']
[m + n for m in 'ABC' for n in 'XYZ']

# ['x=A', 'y=B', 'z=C']
d = {'x': 'A', 'y': 'B', 'z': 'C' }
[key + '=' + value for key, value in d.items()]
# 或
['%s=%s' % (key, value) for key, value in d.items()]

# ['hello', 'world', 'ibm', 'apple']
[s.lower() for s in ['Hello', 'World', 'IBM', 'Apple']]

# ['hello', 'world', 'ibm', 'apple']
[s.lower() for s in ['Hello', 'World', 100, 'IBM', 'Apple', 200] if isinstance(s, str)]
```

#### 嵌套生成式

```Python
all_data = [
    ['John', 'Emily', 'Michael', 'Mary', 'Steven'],
    ['Maria', 'Juan', 'Javier', 'Natalia', 'Pilar']
]

# ['Steven']
[name for names in all_data for name in names if name.count('e') >= 2]
```

```Python
some_tuples = [(1, 2, 3), (4, 5, 6), (7, 8, 9)]

# [1, 2, 3, 4, 5, 6, 7, 8, 9]
[x for tup in some_tuples for x in tup]
```

## 排序

```python
L = [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]

# 升序排序
L.sort()
# L == [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# 降序排序
L.sort(reverse=True)
# L == [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
```

### `itemgetter`

```Python
from operator import itemgetter

l = [
    {'fname': 'Big', 'uid': 1004, 'lname': 'Jones'},
    {'fname': 'Brian', 'uid': 1003, 'lname': 'Jones'},
    {'fname': 'David', 'uid': 1002, 'lname': 'Beazley'},
    {'fname': 'John', 'uid': 1001, 'lname': 'Cleese'}
]

# [
#      {'fname': 'John', 'uid': 1001, 'lname': 'Cleese'},
#      {'fname': 'David', 'uid': 1002, 'lname': 'Beazley'},
#      {'fname': 'Brian', 'uid': 1003, 'lname': 'Jones'},
#      {'fname': 'Big', 'uid': 1004, 'lname': 'Jones'}
# ]
l.sort(key=itemgetter("uid"))

# [
#     {'fname': 'David', 'uid': 1002, 'lname': 'Beazley'},
#     {'fname': 'John', 'uid': 1001, 'lname': 'Cleese'},
#     {'fname': 'Big', 'uid': 1004, 'lname': 'Jones'},
#     {'fname': 'Brian', 'uid': 1003, 'lname': 'Jones'}
# ]
l.sort(key=itemgetter("lname", "fname"))
```

## 逆序

```python
L = [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]

L.reverse()
# L == [10, 8, 6, 4, 2, 9, 7, 5, 3, 1]
```

## 遍历

### `for` 循环编列

```python
L = [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]

for item in L:
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

### `while` 循环遍历

```python
L = [1, 3, 5, 7, 9, 2, 4, 6, 8, 10]

i = 0
while i < len(L):
    item = L[i]
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

## 是否包含

### `in`

```Python
'def' in ['abc', 'def', 'ghi']
# True
```

### `not in`

```Python
'def' not in ['abc', 'def', 'ghi']
# False
```

