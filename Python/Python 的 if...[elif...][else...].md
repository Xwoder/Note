# Python 的 if...[elif...][else...]

[TOC]

## if...

```python
if 条件表达式:
    # code...
```

例

```python
is_employee = True

if not is_employee:
    print('请勿进入')
```

## if...elif...

```python
if 条件表达式:
    # code...
elif 条件表达式:
    # code...
```

## if...else...

```python
if 条件表达式:
    # code...
else:
    # code...
```

例


```python
age = 50

if age <= 120 and age >= 0:
    print('Good')
else:
    print('Bad')
```

```python
score_c = 90
score_java = 50

if score_c >= 60 or score_java >= 60:
    print('Pass')
else:
    print('Fail')
```


```python
is_employee = True

if not is_employee:
    print('请勿进入')
else:
    print('欢迎光临')
```


## if...elif...else...

```python
if 条件表达式:
    # code...
elif 条件表达式:
    # code...
elif 条件表达式:
    # code...
else:
    # code...
```


