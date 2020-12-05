# Python if

## if 语句

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

## if - else 语句

```Python
if expression:
    expr_true_suite
else:
    expr_false_suite
```

Python 提供与 `if` 搭配使用的 `else`，如果 `if` 语句的条件表达式结果布尔值为假，那么程序将执行 `else` 语句后的代码。

##  if - elif - else 语句

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

## 嵌套

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

## 