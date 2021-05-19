# Python Math

[TOC]

## 函数

### int

将一个数字或字符串转换为整数

```python
int(12.345)

# 12
```

```python
int("12345")

# 12345
```

如果第 1 个参数为字符串，则还可以指定第 2 个参数用于指定进制

```python
int("17", 8)

# 15
```

### abs

绝对值函数

```python
abs(-1)
```

### round

舍入函数

```python
import math

round(math.pi, 2)
# 3.14

round(math.pi, 6)
# 3.141593
```

### floor

```python
math.floor(math.pi)

# 3
```

