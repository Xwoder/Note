# Python 函数式编程

[TOC]

## map

```python
l = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

# [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]
l_square = list(map(lambda x: x ** 2, l))
```

## filter

```python
l = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

# [1, 3, 5, 7, 9]
l_odd = list(filter(lambda x: x % 2 == 1, l))
```

## reduce

```python
from functools import reduce

l = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

# 45
reduce(lambda x, y: x + y, l)
```

