# Python 迭代器

[TOC]

## 使用迭代器

使用迭代器遍历 `list` 对象

```python
x = [1, 2, 3, 4, 5]

iter = x.__iter__()
print(iter)

while True:
    try:
        each = next(iter)
    except StopIteration as error:
        break

    print(each)
```

## 自定义可迭代对象

```python
class MyRange():
    def __init__(self, num):
        self.i = 0
        self.num = num

    def __iter__(self):
        return self

    def __next__(self):
        if self.i < self.num:
            i = self.i
            self.i += 1
            return i
        else:
            raise StopIteration

for i in MyRange(6):
    print(i)
```

## enumerator

```python
colors = ['red', 'green', 'yello', 'blue']

for (i, c) in enumerate(colors):
    print(f"{i} --> {c}")
```

