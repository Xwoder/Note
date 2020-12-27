# Python 注释

[TOC]

## 单行注释

### 语法

```Python
# 注释内容
```

### 例

```Python
# 输出你好世界
print("Hello World")
```

## 多行注释

### 语法

```Python
"""
注释的第1行
注释的第2行
注释的第3行
"""
```

其中的双引号也可替换为**单引号**

```Python
'''
注释的第1行
注释的第2行
注释的第3行
'''
```

### 例

```Python
"""
print 函数可用于打印字符串
其默认行为会在行尾添加一个换行符
"""
print("Hello World")
```

```Python
'''
print 函数可用于打印字符串
其默认行为会在行尾添加一个换行符
'''
print("Hello World")
```

```Python
'''
Author: Xwoder
Date: 2020-01-01
'''
```

## 文档注释

多用于为函数添加帮助注释信息

### 例

```Python
def add_numbers(a, b):
  """
  Add two numbers together
  Returns
  -------
  the_sum : type of arguments
  """
  return a + b
```
