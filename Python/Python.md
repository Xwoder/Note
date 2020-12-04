# Python

[TOC]

## 解释器

### 启动

```shell
$ pyhton
```

终端窗口将输出类似如下内容

```shell
Python 3.8.5 (default, Sep  4 2020, 02:22:02) 
[Clang 10.0.0 ] :: Anaconda, Inc. on darwin
Type "help", "copyright", "credits" or "license" for more information.
```

### 执行语句

```Python
>>> print("Hello World")

# 输出
# Hello World
```

### 退出

*  `CTRL + D` 组合键
*  `exit()` 命令

### 版本

```shell
$ python --version

# 输出
# Python 3.8.5
```

## 注释

参见[注释](./Python 注释.md)

## 运算符

### 算数运算符

| 操作符 | 名称 |   示例   |
| :----: | :--: | :------: |
|  `+`   |  加  | `1 + 1`  |
|  `-`   |  减  | `2 - 1`  |
|  `*`   |  乘  | `3 * 4`  |
|  `/`   |  除  | `3 / 4`  |
|  `//`  | 整除 | `3 // 4` |
|  `%`   | 取余 | `3 % 4`  |
|  `**`  |  幂  | `2 ** 3` |

其中，整除通常又被称为**地板除**

### 比较运算符

| 操作符 |   名称   |   示例   |
| :----: | :------: | :------: |
|  `>`   |   大于   | `2 > 1`  |
|  `>=`  | 大于等于 | `2 >= 4` |
|  `<`   |   小于   | `1 < 2`  |
|  `<=`  | 小于等于 | `5 <= 2` |
|  `==`  |   等于   | `3 == 4` |
|  `!=`  |  不等于  | `3 != 5` |

### 逻辑运算符

| 操作符 | 名称 |         示例          |
| :----: | :--: | :-------------------: |
| `and`  |  与  | `(3 > 2) and (3 < 5)` |
|  `or`  |  或  | `(1 > 3) or (9 < 2)`  |
| `not`  |  非  |     `not (2 > 1)`     |

### 位运算符

| 操作符 |   名称   |   示例   |
| :----: | :------: | :------: |
|  `~`   | 按位取反 |   `~4`   |
|  `&`   |  按位与  | `4 & 5`  |
|  `|`   |  按位或  | `4 | 5`  |
|  `^`   | 按位异或 | `4 ^ 5`  |
|  `<<`  |   左移   | `4 << 2` |
|  `>>`  |   右移   | `4 >> 2` |

### 三元运算符

```Python
x, y = 4, 5
small = x if x < y else y	
```

### is / not is 运算符

|  操作符  |  名称  |             示例             |
| :------: | :----: | :--------------------------: |
|   `is`   |   是   |     `"hello" is "hello"`     |
| `not is` |  不是  |   `"hello" is not "hello"`   |

与 `==` 和 `!=` 运算符的对比

* `is` 和 `is not` 对比的是两个变量的内存地址
* `==` 和 `!=` 对比的是两个变量的值

### in / not in 运算符

|  操作符  |  名称  |             示例             |
| :------: | :----: | :--------------------------: |
|   `in`   |  存在  |   `'A' in ['A', 'B', 'C']`   |
| `not in` | 不存在 | `'h' not in ['A', 'B', 'C']` |

例

```Python
letters = ['A', 'B', 'C']
if 'A' in letters:
    print('A' + ' exists')
if 'h' not in letters:
    print('h' + ' not exists')
```

### 运算符的优先级

* 一元运算符优于二元运算符

    例如`3 ** -2` 等价于 `3 ** (-2)`

* 先算术运算，后移位运算，最后位运算

    例如 `1 << 3 + 2 & 7` 等价于 `(1 << (3 + 2)) & 7`

* 逻辑运算最后结合

    例如`3 < 4 and 4 < 5` 等价于 `(3 < 4) and (4 < 5)`

例

```Python
print(-3 ** 2)  # -9
print(3 ** -2)  # 0.1111111111111111
print(1 << 3 + 2 & 7)  # 0
print(-3 * 2 + 5 / -2 - 4)  # -12.5
print(3 < 4 and 4 < 5)  # True
```

## 标识符

### 命名

标识符的名称可有以下元素组成

* 字母
* 数字
* 下划线

但不能以数字开头

### 赋值

在使用变量之前，需要对其先赋值。

### 大小写敏感

标识符的名称区分大小写

## 数据类型

|  类型   |          名称           |      示例      |
| :-----: | :---------------------: | :------------: |
|  `int`  |  整型 `<class 'int'>`   |   `-876, 10`   |
| `float` | 浮点型`<class 'float'>` | `3.149, 11.11` |
| `bool`  | 布尔型`<class 'bool'>`  | `True, False`  |

### 整型

#### bin() 函数

`bin()` 函数可以返回一个整型数据的二进制模式

```Python
print(bin(3))  # 0b11
print(bin(-3))  # -0b11

print(bin(-3 & 0xffffffff))  
# 0b11111111111111111111111111111101

print(bin(0xfffffffd))       
# 0b11111111111111111111111111111101

print(0xfffffffd)  # 4294967293
```

由输出结果可知：

Python中的整型是补码形式存储的。

### 浮点型

包含小数点的数字类型，例

```Python
1.
1.375
.618
```

### 布尔型

布尔  型变量只能取两个值

* `True`
* `False`

当把布尔型变量用在数字运算或打印输出中，用 `1` 和 `0` 代表 `True` 和 `False`。

```Python
print(True + True)  # 2
print(True + False)  # 1
print(True * False)  # 0
```

可以通过 `bool()` 函数来创建变量，其参数可接受的类型如

### 字符串

参见[字符串](./数据类型/Python 字符串.md)

* 基本类型

    * 整型

    * 浮点型

    * 布尔型

        `X` 只要不是整型 `0`、浮点型 `0.0`，`bool(X)` 就是 `True`，其余就是 `False`。例

        ```Python
        print(type(0), bool(0), bool(1))
        # <class 'int'> False True
        
        print(type(10.31), bool(0.00), bool(10.31))
        # <class 'float'> False True
        
        print(type(True), bool(False), bool(True))
        # <class 'bool'> False True
        ```

* 容器类型

    * 字符串

    * 元组

    * 列表

    * 字典

    * 集合

        `X` 只要不是空的变量，`bool(X)` 就是 `True`，其余就是 `False`。例

        ```Python
        print(type(''), bool(''), bool('python'))
        # <class 'str'> False True
        
        print(type(()), bool(()), bool((10,)))
        # <class 'tuple'> False True
        
        print(type([]), bool([]), bool([1, 2]))
        # <class 'list'> False True
        
        print(type({}), bool({}), bool({'a': 1, 'b': 2}))
        # <class 'dict'> False True
        
        print(type(set()), bool(set()), bool({1, 2}))
        # <class 'set'> False True
        ```
        

### 类型信息

#### type() 函数

一个数据的类型可通过 `type()`函数查看，例

```Python
a = 1031
print(a, type(a))
# 1031 <class 'int'>
```

#### isinstance() 函数

可通过 `isinstance()` 函数判断变量是否是指定类型的

```Python
print(isinstance(1, int))  # True
print(isinstance(5.2, float))  # True
print(isinstance(True, bool))  # True
print(isinstance('5.2', str))  # True
```

#### dir() 函数

`dir()` 函数可以返回一个类型全部的**属性**和**方法**，例

```Python
b = dir(int)
print(b)

# ['__abs__', '__add__', '__and__', '__bool__', '__ceil__', '__class__',
# '__delattr__', '__dir__', '__divmod__', '__doc__', '__eq__',
# '__float__', '__floor__', '__floordiv__', '__format__', '__ge__',
# '__getattribute__', '__getnewargs__', '__gt__', '__hash__',
# '__index__', '__init__', '__init_subclass__', '__int__', '__invert__',
# '__le__', '__lshift__', '__lt__', '__mod__', '__mul__', '__ne__',
# '__neg__', '__new__', '__or__', '__pos__', '__pow__', '__radd__',
# '__rand__', '__rdivmod__', '__reduce__', '__reduce_ex__', '__repr__',
# '__rfloordiv__', '__rlshift__', '__rmod__', '__rmul__', '__ror__',
# '__round__', '__rpow__', '__rrshift__', '__rshift__', '__rsub__',
# '__rtruediv__', '__rxor__', '__setattr__', '__sizeof__', '__str__',
# '__sub__', '__subclasshook__', '__truediv__', '__trunc__', '__xor__',
# 'bit_length', 'conjugate', 'denominator', 'from_bytes', 'imag',
# 'numerator', 'real', 'to_bytes']
```

### 类型转换

* 转换为整型 `int(x, base=10)`
* 转换为字符串 `str(object='')`
* 转换为浮点型 `float(x)`

例

```Python
print(int('520'))  # 520
print(int(520.52))  # 520
print(float('520.52'))  # 520.52
print(float(520))  # 520.0
print(str(10 + 10))  # 20
print(str(10.1 + 5.2))  # 15.3
```

## print() 函数

函数声明

```Python
print(*objects, sep=' ', end='\n', file=sys.stdout, flush=False)
```

作用

* 将对象以字符串表示的方式格式化输出到流文件对象file里。其中所有非关键字参数都按`str()`方式进行转换为字符串输出；
* 关键字参数`sep`是实现分隔符，比如多个参数输出时想要输出中间的分隔字符；
* 关键字参数`end`是输出结束时的字符，默认是换行符`\n`；
* 关键字参数`file`是定义流输出的文件，可以是标准的系统输出`sys.stdout`，也可以重定义为别的文件；
* 关键字参数`flush`是立即把内容输出到流文件，不作缓存。

例

```Python
shoplist = ['apple', 'mango', 'carrot', 'banana']
for item in shoplist:
    print(item)

# apple
# mango
# carrot
# banana
```

```Python
shoplist = ['apple', 'mango', 'carrot', 'banana']
for item in shoplist:
    print(item, end='&')
print('hello world')

# apple&mango&carrot&banana&hello world
```

## 条件语句

### if 语句

语法

```Python
if expression:
    expr_true_suite
```

* if 语句的 `expr_true_suite` 代码块只有当条件表达式 `expression` 结果为真时才执行，否则将继续执行紧跟在该代码块后面的语句。
* 单个 if 语句中的 `expression` 条件表达式可以通过布尔操作符 `and`，`or`和`not` 实现多重条件判断。

例

```Python
if 2 > 1 and not 2 > 3:
    print('Correct Judgement!')

# Correct Judgement!
```

#### if - else 语句

```Python
if expression:
    expr_true_suite
else:
    expr_false_suite
```

Python 提供与 `if` 搭配使用的 `else`，如果 `if` 语句的条件表达式结果布尔值为假，那么程序将执行 `else` 语句后的代码。

####  if - elif - else 语句

```Python
if expression1:
    expr1_true_suite
elif expression2:
    expr2_true_suite
elif expressionN:
    exprN_true_suite
else:
    expr_false_suite
```

`elif` 语句即为 `else if`，用来检查多个表达式是否为真，并在为真时执行特定代码块中的代码。

### 嵌套

`if` 语句支持嵌套，即在一个 `if` 语句中嵌入另一个 `if` 语句，从而构成不同层次的选择结构。

```Python
hi = 6
if hi > 2:
    if hi > 7:
        print('Good')
else:
    print('Bad')

# 无输出
```

例

```Python
temp = "85"
source = int(temp)
if 100 >= source >= 90:
    print('A')
elif 90 > source >= 80:
    print('B')
elif 80 > source >= 60:
    print('C')
elif 60 > source >= 0:
    print('D')
else:
    print('输入错误！')
    
# B
```

## assert 关键字

`assert`关键词我们称之为“断言”，当这个关键词后边的条件为 `False` 时，程序自动崩溃并抛出 `AssertionError` 的异常。例

```Python
my_list = ['lsgogroup']
my_list.pop(0)
assert len(my_list) > 0

# AssertionError
```

```Python
assert 3 > 7

# AssertionError
```

## 循环语句

### while 循环

```Python
while 布尔表达式:
    代码块
```

`while` 循环的代码块会一直循环执行，直到布尔表达式的值为布尔假。

* 当`while`后写入一个非零整数时，视为真值，执行循环体；
* 写入`0`时，视为假值，不执行循环体；
* 写入`str、list`或任何序列，长度非零则视为真值，执行循环体；否则视为假值，不执行循环体。

```Python
string = 'abcd'
while string:
    print(string)
    string = string[1:]

# abcd
# bcd
# cd
# d
```

#### while - else 循环

```python
while 布尔表达式:
    代码块
else:
    代码块
```

当 `while` 循环正常执行完的情况下，执行 `else` 中的语句，如果 `while` 循环中执行了跳出循环的语句，比如 `break`，将不执行 `else` 代码块的内容。

```Python
count = 0
while count < 5:
    print("%d is less than 5" % count)
    count = count + 1
else:
    print("%d is not less than 5" % count)
    
# 0 is  less than 5
# 1 is  less than 5
# 2 is  less than 5
# 3 is  less than 5
# 4 is  less than 5
# 5 is not less than 5
```

```Python
count = 0
while count < 5:
    print("%d is less than 5" % count)
    count = 6
    break
else:
    print("%d is not less than 5" % count)

# 0 is  less than 5
```

###  for 循环

语法

```Python
for 迭代变量 in 可迭代对象:
    代码块
```

例

```Python
for i in 'ILoveLSGO':
    print(i, end=' ')  # 不换行输出

# I L o v e L S G O
```

```Python
member = ['张三', '李四', '刘德华', '刘六', '周润发']
for each in member:
    print(each)

# 张三
# 李四
# 刘德华
# 刘六
# 周润发

for i in range(len(member)):
    print(member[i])

# 张三
# 李四
# 刘德华
# 刘六
# 周润发
```

```Python
dic = {'a': 1, 'b': 2, 'c': 3, 'd': 4}

for key, value in dic.items():
    print(key, value, sep=':', end=' ')
    
# a:1 b:2 c:3 d:4 
```

```Python
dic = {'a': 1, 'b': 2, 'c': 3, 'd': 4}

for key in dic.keys():
    print(key, end=' ')
    
# a b c d 
```

```Python
dic = {'a': 1, 'b': 2, 'c': 3, 'd': 4}

for value in dic.values():
    print(value, end=' ')
    
# 1 2 3 4
```

#### for - else 循环

语法

```Python
for 迭代变量 in 可迭代对象:
    代码块
else:
    代码块
```

当 `for` 循环正常执行完的情况下，执行 `else` 输出，如果 `for` 循环中执行了跳出循环的语句，比如 `break`，将不执行 `else` 代码块的内容，与 `while - else` 语句一样。

## range() 函数

语法

```Python
range([start,] stop[, step=1])
```

这是一个内置函数

其作用是生成一个从 `start` 参数的值开始到 `stop` 参数的值结束的数字序列，该序列包含 `start` 的值但不包含 `stop` 的值。

```Python
for i in range(2, 9):  # 不包含9
    print(i)

# 2
# 3
# 4
# 5
# 6
# 7
# 8
```

```Python
for i in range(1, 10, 2):
    print(i)

# 1
# 3
# 5
# 7
# 9
```

## enumerate() 函数

定义

```Python
enumerate(sequence, [start=0])
```

参数

* sequence
    * 一个序列、迭代器或其他支持迭代对象。
* start
    * 下标起始位置

返回类型

* enumerate 对象

例

```Python
seasons = ['Spring', 'Summer', 'Fall', 'Winter']
lst = list(enumerate(seasons))
print(lst)
# [(0, 'Spring'), (1, 'Summer'), (2, 'Fall'), (3, 'Winter')]

lst = list(enumerate(seasons, start=1))  # 下标从 1 开始
print(lst)
# [(1, 'Spring'), (2, 'Summer'), (3, 'Fall'), (4, 'Winter')]
```

与循环语句结合使用

```Python
for i, v in enumerate(A)
    代码块
```

例

```Python
languages = ['Python', 'R', 'Matlab', 'C++']
for language in languages:
    print('I love', language)
print('Done!')
# I love Python
# I love R
# I love Matlab
# I love C++
# Done!

for i, language in enumerate(languages, 2):
    print(i, 'I love', language)
print('Done!')
# 2 I love Python
# 3 I love R
# 4 I love Matlab
# 5 I love C++
# Done!
```

## break 语句

`break` 语句可以跳出当前所在层的循环

## continue 语句

`continue` 终止本轮循环并开始下一轮循环

```Python
for i in range(10):
    if i % 2 != 0:
        print(i)
        continue
    i += 2
    print(i)

# 2
# 1
# 4
# 3
# 6
# 5
# 8
# 7
# 10
# 9
```

## pass 语句

例

```Python
def a_func():
    pass
```

## 推导式

### 列表推导式

```Python
[ expr for value in collection [if condition] ]
```

例

```Python
x = [-4, -2, 0, 2, 4]
y = [a * 2 for a in x]
print(y)
# [-8, -4, 0, 4, 8]
```

```Python
x = [i ** 2 for i in range(1, 10)]
print(x)
# [1, 4, 9, 16, 25, 36, 49, 64, 81]
```

```Python
x = [(i, i ** 2) for i in range(6)]
print(x)

# [(0, 0), (1, 1), (2, 4), (3, 9), (4, 16), (5, 25)]
```

```Python
x = [i for i in range(100) if (i % 2) != 0 and (i % 3) == 0]
print(x)

# [3, 9, 15, 21, 27, 33, 39, 45, 51, 57, 63, 69, 75, 81, 87, 93, 99]
```

```Python
a = [(i, j) for i in range(0, 3) for j in range(0, 3)]
print(a)

# [(0, 0), (0, 1), (0, 2), (1, 0), (1, 1), (1, 2), (2, 0), (2, 1), (2, 2)]
```

```Python
x = [[i, j] for i in range(0, 3) for j in range(0, 3)]
print(x)
# [[0, 0], [0, 1], [0, 2], [1, 0], [1, 1], [1, 2], [2, 0], [2, 1], [2, 2]]

x[0][0] = 10
print(x)
# [[10, 0], [0, 1], [0, 2], [1, 0], [1, 1], [1, 2], [2, 0], [2, 1], [2, 2]]
```

```Python
a = [(i, j) for i in range(0, 3) if i < 1 for j in range(0, 3) if j > 1]
print(a)

# [(0, 2)]
```

### 元组推导式

```Python
( expr for value in collection [if condition] )
```

例

```Python
a = (x for x in range(10))
print(a)

# <generator object <genexpr> at 0x0000025BE511CC48>

print(tuple(a))

# (0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
```

```Python
b = {i: i % 2 == 0 for i in range(10) if i % 3 == 0}
print(b)
# {0: True, 3: False, 6: True, 9: False}
```

```Python
c = {i for i in [1, 2, 3, 4, 5, 5, 6, 4, 3, 2, 1]}
print(c)
# {1, 2, 3, 4, 5, 6}
```

### 字典推导式

语法

```Python
{ key_expr: value_expr for value in collection [if condition] }
```

例

```Python
b = {i: i % 2 == 0 for i in range(10) if i % 3 == 0}
print(b)
# {0: True, 3: False, 6: True, 9: False}
```

#### 集合推导式

语法

```Python
{ expr for value in collection [if condition] }
```

例

```Python
c = {i for i in [1, 2, 3, 4, 5, 5, 6, 4, 3, 2, 1]}
print(c)
# {1, 2, 3, 4, 5, 6}
```

## next(iterator [, default])

Return the next item from the iterator.

If default is given and the iterator is exhausted, it is returned instead of raising StopIteration.

例

```Python
e = (i for i in range(10))
print(e)
# <generator object <genexpr> at 0x0000007A0B8D01B0>

print(next(e))  # 0
print(next(e))  # 1

for each in e:
    print(each, end=' ')

# 2 3 4 5 6 7 8 9
```

```Python
s = sum([i for i in range(101)])
print(s)  # 5050
s = sum((i for i in range(101)))
print(s)  # 5050
```

## 异常处理

异常就是运行期检测到的错误。

计算机语言针对可能出现的错误定义了异常类型，某种错误引发对应的异常时，异常处理程序将被启动，从而恢复程序的正常运行。

### Python 标准异常总结

| 异常类型                | 说明                               |
| :---------------------- | ---------------------------------- |
| `BaseException`         | 所有异常的基类                     |
| `Exception`             | 常规异常的基类                     |
| `StandardError`         | 所有的内建标准异常的基类           |
| `ArithmeticError`       | 所有数值计算异常的基类             |
| `FloatingPointError`    | 浮点计算异常                       |
| `OverflowError`         | 数值运算超出最大限制               |
| `ZeroDivisionError`     | 除数为零                           |
| `AssertionError`        | 断言语句失败                       |
| `AttributeError`        | 尝试访问未知的对象属性             |
| `EOFError`              | 没有内建输入，到达EOF标记          |
| `EnvironmentError`      | 操作系统异常的基类                 |
| `IOError`               | 输入输出操作失败                   |
| `OSError`               | 操作系统产生的异常                 |
| `WindowsError`          | 系统调用失败                       |
| `ImportError`           | 导入模块失败的时候                 |
| `KeyboardInterrupt`     | 用户中断执行                       |
| `LookupError`           | 无效数据查询的基类                 |
| `IndexError`            | 索引超出序列的范围                 |
| `KeyError`              | 字典中查找一个不存在的关键字       |
| `MemoryError`           | 内存溢出                           |
| `NameError`             | 尝试访问一个不存在的变量           |
| `UnboundLocalError`     | 访问未初始化的本地变量             |
| `ReferenceError`        | 弱引用试图访问已经垃圾回收了的对象 |
| `RuntimeError`          | 一般的运行时异常                   |
| `NotImplementedError`   | 尚未实现的方法                     |
| `SyntaxError`           | 语法错误导致的异常                 |
| `IndentationError`      | 缩进错误导致的异常                 |
| `TabError`              | Tab 和空格混用                     |
| `SystemError`           | 一般的解释器系统异常               |
| `TypeError`             | 不同类型间的无效操作               |
| `ValueError`            | 传入无效的参数                     |
| `UnicodeError`          | Unicode 相关的异常                 |
| `UnicodeDecodeError`    | Unicode 解码时的异常               |
| `UnicodeEncodeError`    | Unicode 编码错误导致的异常         |
| `UnicodeTranslateError` | Unicode 转换错误导致的异常         |

异常体系内部有层次关系，部分层次关系如下

![object](Python.assets/object.svg)

## 标准警告

| 类型名                      | 说明                           |
| --------------------------- | ------------------------------ |
| `Warning`                   | 警告的基类                     |
| `DeprecationWarning`        | 关于被弃用的特征的警告         |
| `FutureWarning`             | 关于构造将来语义会有改变的警告 |
| `UserWarning`               | 用户代码生成的警告             |
| `PendingDeprecationWarning` | 关于特性将会被废弃的警告       |
| `RuntimeWarning`            | 可疑的运行时行为的警告         |
| `SyntaxWarning`             | 可疑语法的警告                 |
| `ImportWarning`             | 用于在导入模块过程中触发的警告 |
| `UnicodeWarning`            | 与 Unicode 相关的警告          |
| `BytesWarning`              | 与字节或字节码相关的警告       |
| `ResourceWarning`           | 与资源使用相关的警告           |

## try - except 语句

语法

```Python
try:
    检测范围
except Exception [as reason]:
    出现异常后的处理代码
```

例

```Python
try:
    f = open('test.txt')
    print(f.read())
    f.close()
except OSError:
    print('打开文件出错')

# 打开文件出错
```

```Python
try:
    f = open('test.txt')
    print(f.read())
    f.close()
except OSError as error:
    print('打开文件出错\n原因是：' + str(error))

# 打开文件出错
# 原因是：[Errno 2] No such file or directory: 'test.txt'
```

```Python
try:
    int("abc")
    s = 1 + '1'
    f = open('test.txt')
    print(f.read())
    f.close()
except OSError as error:
    print('打开文件出错\n原因是：' + str(error))
except TypeError as error:
    print('类型出错\n原因是：' + str(error))
except ValueError as error:
    print('数值出错\n原因是：' + str(error))

# 数值出错
# 原因是：invalid literal for int() with base 10: 'abc'
```

```Python
dict1 = {'a': 1, 'b': 2, 'v': 22}
try:
    x = dict1['y']
except LookupError:
    print('查询错误')
except KeyError:
    print('键错误')
else:
    print(x)

# 查询错误
```

```Python
dict1 = {'a': 1, 'b': 2, 'v': 22}
try:
    x = dict1['y']
except KeyError:
    print('键错误')
except LookupError:
    print('查询错误')
else:
    print(x)

# 键错误
```

```Python
try:
    s = 1 + '1'
    int("abc")
    f = open('test.txt')
    print(f.read())
    f.close()
except (OSError, TypeError, ValueError) as error:
    print('出错了！\n原因是：' + str(error))

# 出错了！
# 原因是：unsupported operand type(s) for +: 'int' and 'str'
```

### try - except - finally 语句

不管`try`子句里面有没有发生异常，`finally`子句都会执行。

```Python
def divide(x, y):
    try:
        result = x / y
        print("result is", result)
    except ZeroDivisionError:
        print("division by zero!")
    finally:
        print("executing finally clause")


divide(2, 1)
# result is 2.0
# executing finally clause
divide(2, 0)
# division by zero!
# executing finally clause
divide("2", "1")
# executing finally clause
# TypeError: unsupported operand type(s) for /: 'str' and 'str'
```

### try - except - else 语句

如果在 `try` 子句执行时没有发生异常，`Python` 将执行 `else` 语句后的语句

```Python
try:
    检测范围
except:
    出现异常后的处理代码
else:
    如果没有异常执行这块代码
```

`else` 语句的存在必须以 `except` 语句的存在为前提，在没有 `except` 语句的 `try` 语句中使用 `else` 语句，会引发语法错误。

### raise语句

Python 使用`raise`语句抛出一个指定的异常。

```Python
try:
    raise NameError('HiThere')
except NameError:
    print('An exception flew by!')
    
# An exception flew by!
```

## 函数

