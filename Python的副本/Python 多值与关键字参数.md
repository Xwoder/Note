# Python 多值与关键字参数

[TOC]

```Python
def demo(num, *args, **kwargs):
    print(num)
    print(args)
    print(kwargs)


# 1
# ()
# {}
demo(1)

# 10
# (20, 30, 40, 50)
# {}
demo(10, 20, 30, 40, 50)

# 10
# (20, 30, 40, 50)
# {'name': 'Jack', 'age': 18}
demo(10, 20, 30, 40, 50, name="Jack", age=18)
```

## 多值参数

```Python
def sum_numbers(*args):
    sum = 0
    for n in args:
        sum += n
    return sum

# 450
sum_numbers(10, 20, 30, 40, 50, 60, 70, 80, 90)
```

### 拆包

```Python
def sum_numbers(*args):
    sum = 0
    for n in args:
        sum += n
    return sum


numbers = (10, 20, 30, 40, 50, 60, 70, 80, 90)
# 450
sum_numbers(*numbers)
```

## 关键字参数

```Python
def func(**kwargs):
    print(kwargs)

# {'name': 'Jack', 'age': 18}
func(name="Jack", age=18)
```

### 拆包

```Python
def func(**kwargs):
    print(kwargs)


info = {'name': 'Jack', 'age': 18};
func(**info)
```