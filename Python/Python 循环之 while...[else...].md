# Python 循环之 while...else...

[TOC]

## 使用方法

```python
names = ['Michael', 'Bob', 'Tracy']

i = 0
while i < len(names):
    print(names[i])
    i += 1

# 输出
# Michael
# Bob
# Tracy
```

## `else` 子句

```python
names = ['Michael', 'Bob', 'Tracy']

i = 0
while i < len(names):
    print(names[i])
    i += 1
else:
    print('while:else:')
    
# 输出
# Michael
# Bob
# Tracy
# while:else:
```

`else` 子句中的代码只会在全部循环结束且该结束不是由 `break` 语句的触发的才会执行。

```python
names = ['Michael', 'Bob', 'Tracy']

i = 0
while i < len(names):
    print(names[i])
    if i == 1:
        break
    i += 1
else:
    print('while:else:')
    
# 输出
# Michael
# Bob
```

上例中的 `while` 循环由于内部执行了 `break` 语句，从而导致 `else` 子句的代码没有被执行

