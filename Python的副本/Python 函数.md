# Python 函数

[TOC]

## 定义

```python
def 函数名():
    函数体
    
def 函数名([形参列表]):
    函数体
```

### 例

#### 无参数无返回值

```python
def greeting():
    print("Hello")
```

其实，所有的 `Python` 函数在函数体结尾处都隐式返回 `None`

#### 无参数有返回值

```Python
def PI():
    return 3.1415926
```

#### 有参数无返回值

```python
def calc_sum(num1, num2):
    print('%d + %d = %d' % (num1, num2, num1 + num2))
```

#### 有参数有返回值

```python
def calc_sum(num1, num2):
    return num1 + num2
```

## 调用

```python
函数名([实参列表])
返回值 = 函数名([实参列表])
```

例如

```Python
s = calc_sum(10, 20)

# s == 30
```

`Python` 允许函数调用时参数的顺序与声明时不一致，因为 `Python` 解释器能够用参数名匹配参数值。例如

```Python
def printInfo(name, age=18):
    print('Name:{0}, Age:{1}'.format(name, age))

printInfo(age=8, name='小马')
# Name:小马, Age:8
```

## 命名规则

* 小写字母
* 数字
    * 不能作为开头
* 下划线

## 注释

```python
def greeting(name=None):
    """ 打招呼 """
    print("Hello")
```

## 参数

### 位置参数（positional argument）

```Python
def funcName(arg0, arg1):
    pass
```

传递参数时需按照函数参数的位置逐一传递

### 默认参数（default argument）

```Python
def funcName(arg0 = "Hello", arg1 = "World"):
    pass
```

默认参数一定要放在位置参数后面，不然程序会报错。

```python
def printInfo(name, age=18):
    print('Name:{0}, Age:{1}'.format(name, age))


printInfo('小马')
# Name:小马, Age:18

printInfo('小马', 10)
# Name:小马,Age:10
```

### 可变参数（variable argument）

```Python
def mySum(result=0, *args):
    for arg in args:
        result += arg
    return result


print(mySum(0, 10, 20))
print(mySum(0, 10, 20, 40, 50))
```

### 关键字参数（keyword argument）

```Python
def printInfo(arg1, *args, **kwargs):
    print(arg1)
    print(args)
    print(kwargs)


printInfo(70, 60, 50)
# 70
# (60, 50)
# {}

printInfo(70, 60, 50, a=1, b=2)
# 70
# (60, 50)
# {'a': 1, 'b': 2}
```

上例中的 `kwargs` 就是关键字参数名，调用时可传入多个关键字参数，会被自动组装成字典

### 命名关键字参数（name keyword argument）

```Python
def printInfo(arg1, *, nkw, **kwargs):
    print(arg1)
    print(nkw)
    print(kwargs)


printInfo(70, nkw=10, a=1, b=2)
# 70
# 10
# {'a': 1, 'b': 2}

printInfo(70, 10, a=1, b=2)
# TypeError: printinfo() takes 1 positional argument but 2 were given
```

### 参数组合

在 `Python` 中定义函数，可以用位置参数、默认参数、可变参数、命名关键字参数和关键字参数，这 5 种参数中的 4 个都可以一起使用，但是注意，参数定义的顺序必须是：

* 位置参数、默认参数、可变参数和关键字参数。
* 位置参数、默认参数、命名关键字参数和关键字参数。

## 变量作用域

* 定义在函数内部的变量拥有局部作用域，该变量称为**局部变量**
* 定义在函数外部的变量拥有全局作用域，该变量称为**全局变量**
* 局部变量只能在其被声明的函数内部访问，而全局变量可以在整个程序范围内访问