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

其实，所有的 `Python` 函数在函数体结尾处都隐式返回 `Void`

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
返回值 = 函数名([实参列表])
```

例如

```Python
s = calc_sum(10, 20)

# s == 30
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

## 默认参数

```python
def greeting(name=None):
    print("Hello")
```

上例中的函数有一个参数 `name`，其默认值为 `None`

