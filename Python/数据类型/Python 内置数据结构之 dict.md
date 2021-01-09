# Python 内置数据结构之 dict

[TOC]

## 创建

### 使用大括号创建

这种创建方式使用 `{}` 定义字典

```Python
score_dict = {
    'Tracy': 60,
    'Michael': 95,
    'Bob': 75,
    'Tracy': 85
}
```

创建一个空字典

```Python
empty_dict = {}
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

#### 从键值对创建

上例中的 `score_dict` 字典可以等价的使用如下语法创建

```Python
score_dict = dict(Michael=95, Bob=75, Tracy=85)
```

创建一个空字典

```Python
empty_dict = dict()
```

#### 从两元素序列创建

```Python
city_list = [("0551", "合肥"), ("021", "上海"), ("010", "北京"), ]

# {'0551': '合肥', '021': '上海', '010': '北京'}
city_dict = dict(city_list)
```

```Python
city_list = ('12', '34', '56')

# {'1': '2', '3': '4', '5': '6'}
city_dict = dict(city_list)

```

## 访问

### 通过键访问

```Python
score_dict['Michael']

# 输出
# 95
```

若键不存在，则报错。

### 通过 `get()` 访问

```Python
# 100
score_dict.get('Michael')
```

若指定的键在字典中不存在，则返回 `None`

```Python
# None
score_dict.get('Jack')
```

可以通过第 2 个参数指定一个值，即默认值，当键在字典中不存在时，返回该默认值。

```Python
# 100
score_dict.get('Michael', -1)

# -1
score_dict.get('Jack', -1)
```

## 新增 / 修改

对于使用

```Python
dict[key] = value
```

语法来改变字典，如果`key`已在字典中存在，则修改该`key`对应的`value`，否则新增一个`key`和对应的`value`

```Python
# 修改
score_dict['Michael'] = 100
# score_dict == {'Michael': 100, 'Bob': 75, 'Tracy': 85}

# 新增
score_dict['Ross'] = 80
# score_dict == {'Michael': 100, 'Bob': 75, 'Tracy': 85, 'Ross': 80}
```

## `in` / `not in`

```Python
'Jack' in score_dict
'Jack' not in score_dict

'Michael' in score_dict
'Michael' not in score_dict
```

## 删除

### pop

```Python
# 100
score_dict.pop('Michael')

# score_dict == {'Bob': 75, 'Tracy': 85}
```

### del

根据键来删除指定的元素

```Python
del score_dict['Bob']

# score_dict == {'Tracy': 85}
```

## 遍历

对于字典

```Python
score_dict = {'Michael': 95, 'Bob': 75, 'Tracy': 85}
```

### 遍历`key`与`value`

```Python
for name, score in score_dict.items():
    print('name = %s, score = %d' % (name, score))

# 输出
# name = Michael, score = 95
# name = Bob, score = 75
# name = Tracy, score = 85
```

示例代码在遍历的同时，对 `items()` 方法的键值对进行了拆包

### 遍历`key`

```Python
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

### 遍历 `value()`

```Python
for score in score_dict.values():
    print('score = %d' % score)
    
# 输出
# score = 95
# score = 75
# score = 85
```

## 长度 / `len()`

```Python
len(score_dict)
```

## 更新 / `update()`

```Python
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

```Python
score_dict.clear()
```

## 复制 / `copy()`

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

## 生成

### 从键值列表生成字典

#### 循环式

```Python
key_lists = ['021', '0551', '101']
value_lists = ['上海', '合肥', '北京']

d = {}
for key, value in zip(key_lists, value_lists):
    d[key] = value

# d = {'021': '上海', '0551': '合肥', '101': '北京'}
```

#### 构造函数式

```Python
dict(zip(key_lists, value_lists))
{'021': '上海', '0551': '合肥', '101': '北京'}
```