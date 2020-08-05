# Python 生成器

## 生成器

```Python
def squares(n=10):
    for i in range(1, n + 1):
        yield i ** 2


gen = squares()
for i in gen:
    print(i)
```

输出

```text
1
4
9
16
25
36
49
64
81
100
```

## 生成器表达式

```python
gen = (i ** 2 for i in range(1, 11))
for i in gen:
    print(i)
```

生成

```text
1
4
9
16
25
36
49
64
81
100
```

生成器表达式可以用于函数参数

```Python
gen = (i ** 2 for i in range(1, 11))

# 385
sum(gen)
```

```Python
dict((i, i * 10) for i in range(1, 11))                                                                         

# {1: 10, 2: 20, 3: 30, 4: 40, 5: 50, 6: 60, 7: 70, 8: 80, 9: 90, 10: 100}
```

