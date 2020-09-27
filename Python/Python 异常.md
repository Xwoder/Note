# Python 异常

[TOC]

## 捕获

### 语法

```Python
try:
    pass
except ZeroDivisionError:
    pass
except ValueError as result:
    # 用一个变量引用错误错误类型对象
    pass
except (ZeroDivisionError, ValueError):
    # 同时捕获处理多种错误
    pass
except Exception as result:
    pass
else:
    # 只有不产生任何异常，才将执行 else 部分的语句
    pass
finally:
    # 不论是否产生异常，都将执行 finally 部分的语句
    pass
```

`except`子句可以省略错误类型

`else` 子句中语句在不发生不发生任何异常时执行

`finally` 子句中语句，不论是否发生异常均执行

### 例

```Python
try:
    n = int(input("请输入一个整数:"))
except:
    print("产生异常")
```

```Python
try:
    n = int(input("请输入一个整数:"))
    r = 10 / n
except ValueError:
    print("输入错误")
except ZeroDivisionError:
    print("除零错误")
```

```Python
try:
    n = int(input("请输入一个整数:"))
    r = 10 / n
except ValueError:
    print("输入错误")
except Exception as result:
    print("其他异常", result)
```

```Python
try:
    n = int(input("请输入一个整数:"))
    r = 10 / n
except ValueError as result:
    print("输入错误, %s" % result)
except ZeroDivisionError as result:
    print("除零错误, %s" % result)
except Exception as result:
    print("其他异常, %s" % result)

```

## 抛出

```Python
def input_password():
    password = input("请输入密码：")

    if len(password) < 8:
        raise Exception("密码长度不足")
    else:
        return password


try:
    print("用户输入的密码：%s" % input_password())
except Exception as error:
    print("捕获到异常，异常信息：%s" % error)
```







