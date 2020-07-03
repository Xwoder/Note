# Python 循环之 for

[TOC]

```python
names = ['Michael', 'Bob', 'Tracy']
for name in names:
    print(name)

# 输出
# Michael
# Bob
# Tracy
```

```python
sum = 0

for x in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]:
    sum += x

print(sum)

# 输出
# 55
```

```python
sum = 0

for x in range(101):
    sum = sum + x

print(sum)

# 输出
# 5050
```

## 迭代元组列表

```python
for (x, y) in [(1, 1), (2, 4), (3, 9)]:
    print('x = %d, y = %d' % (x, y))
    
# 输出
# x = 1, y = 1
# x = 2, y = 4
# x = 3, y = 9
```

## else 子句

```python
for i in list(range(1, 11)):
    print(i)
else:
    print("for:else:")
    
# 输出
# 1
# 2
# 3
# 4
# 5
# 6
# 7
# 8
# 9
# 10
# for:else:
```

`else` 子句中的代码只会在全部循环结束且该结束不是由 `break` 语句的触发的才会执行。

```python
for i in list(range(1, 11)):
    print(i)

    if i == 5:
        break
else:
    print("for:else:")
    
# 输出
# 1
# 2
# 3
# 4
# 5
```

```python
students = [
    {
        'name': 'Jack',
        'age': 18,
        'height': 175
    },
    {
        'name': 'Ross',
        'age': 20,
        'height': 165
    }
]

student_name_target = 'Tom'

for s in students:
    if s['name'] == student_name_target:
        print('Found')
        break
else:
    print('Not Found')
```

上例中的 `for` 循环由于内部执行了 `break` 语句，从而导致 `else` 子句的代码没有被执行

