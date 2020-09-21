# Python 内置数据结构之 dict

[TOC]

## 创建

### 使用大括号创建

这种创建方式使用 `{}` 定义字典

```Python
empty_dict = {}
```

```python
score_dict = {
    'Tracy': 60,
    'Michael': 95,
    'Bob': 75,
    'Tracy': 85
}
```

`key`  与  `value`  之间使用 `:` 分隔

一个字典中的`key` 必须是唯一的。所以，上例中定义的字典等价于如下定义

```Python
score_dict = {
    'Michael': 95,
    'Bob': 75,
    'Tracy': 85
}
```

`key` 的类型只能为字符串、数字或元组；`value` 的类型可以是任何数据类型。

### 使用构造函数创建

上例中的 `score_dict` 字典可以等价的使用如下语法创建

```Python
score_dict = dict(Michael=95, Bob=75, Tracy=85)
```

## 访问

### 通过 key 访问

```python
score_dict['Michael']

# 输出
# 95
```

### 通过 `get()` 访问

对于 `get()` 方法，如果第 1 个参数指定的键在字典中不存在，则返回第2个参数指定的默认值。

```python
# 100
score_dict.get('Michael')

# 100
score_dict.get('Michael', -1)

# -1
score_dict.get('Jack', -1)
# 指定key并不存在于字典中，所以返回指定默认值-1
```

## 新增/修改

对于使用

```python
dict[key] = value
```

语法来改变字典，如果`key`已在字典中存在，则修改该`key`对应的`value`，否则新增一个`key`和对应的`value`

```python
# 修改
score_dict['Michael'] = 100
# score_dict == {'Michael': 100, 'Bob': 75, 'Tracy': 85}

# 新增
score_dict['Ross'] = 80
# score_dict == {'Michael': 100, 'Bob': 75, 'Tracy': 85, 'Ross': 80}
```

## `in` / `not in`

```python
'Jack' in score_dict
'Jack' not in score_dict

'Michael' in score_dict
'Michael' not in score_dict
```

## 删除

### pop

```python
# 100
score_dict.pop('Michael')

# score_dict == {'Bob': 75, 'Tracy': 85}
```

### del

```Python
del score_dict['Bob']

# score_dict == {'Tracy': 85}
```

## 遍历

对于字典

```python
score_dict = {'Michael': 95, 'Bob': 75, 'Tracy': 85}
```

同时遍历`key`与`value`

```python
for name, score in score_dict.items():
    print('name = %s, score = %d' % (name, score))

# 输出
# name = Michael, score = 95
# name = Bob, score = 75
# name = Tracy, score = 85
```

仅遍历`key` 

```python
for name in score_dict.keys():
    print('name = %s' % name)
    
# 输出
# name = Michael
# name = Bob
# name = Tracy
```

或者

```Python
for name in score_dict:
    print(name)
```

仅遍历 `value`

```python
for score in score_dict.values():
    print('score = %d' % score)
    
# 输出
# score = 95
# score = 75
# score = 85
```

## 长度 / `len()`

```python
len(score_dict)
```

## 更新 / `update()`

```python
score_dict = {
    'Tracy': 60,
    'Michael': 95,
    'Bob': 75,
}

score_new_dict = {
    'Tracy': 80,
    'Tom': 90
}

score_dict.update(score_new_dict)

# score_dict == {'Tracy': 80, 'Michael': 95, 'Bob': 75, 'Tom': 90}
```

## 清空 / `clear()`

```python
score_dict.clear()
```

## 复制

```Python
area_code.copy()
```

## 生成式

```Python
d = {
    1: 10,
    2: 20,
    3: 30,
    4: 40,
    5: 50
}

d_filter = {key: value for key, value in d.items() if key % 2 == 0}
# d_filter == {
#     2: 20, 4: 40
# }
```