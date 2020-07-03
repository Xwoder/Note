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

```python
def greeting():
    """ 无参数无返回值 """
    print("Hello")
```

```python
def calc_sum(num1, num2):
    """ 有参数无返回值 """
    print('%d + %d = %d' % (num1, num2, num1 + num2))
```

```python
def calc_sum(num1, num2):
    """ 有参数有返回值 """
    return num1 + num2
```

## 调用

```python
返回值 = 函数名([实参列表])
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

