# Python 输入输出

[TOC]

## 输出

```python
print('Hello World')
```

`print` 还可以接受多个字符串，每个字符串使用逗号分隔。在输出时，每个字符串都使用空格分隔

```python
print('The quick brown fox', 'jumps over', 'the lazy dog')
```

```python
print('100 + 200 =', 100 + 200)

# 输出
# 100 + 200 = 300
```

`print` 函数等价于如下代码：

```python
import sys
_ = sys.stdout.write('...')
```

## 输入

Python 使用 `input` 函数进行输入


```python
name = input()

name = input('Please input your name: ')
```


