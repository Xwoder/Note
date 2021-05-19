# Python JSON

[TOC]

## 模块

`JSON`

## 方法

### `dumps()`

`Python` 对象转 `JSON` 字符串

### `loads()`

`JSON` 对象转 `Python` 字符串

### `dump()`

将一个对象序列化为 `JSON` 格式的流并输出至一个文件

### `load()`

从文件读取 `JSON` 格式的流并反序列化将一个对象

## 例

### 例1

```Python
import json

person_dict = {
    'name': 'Jack',
    'age': 18,
    'address': {
        'country': 'China',
        'province': 'Shanghai',
        'road': '550 Dalian Road (W)'
    }
}

# {'name': 'Jack', 'age': 18, 'address': {'country': 'China', 'province': 'Shanghai', 'road': '550 Dalian Road (W)'}}
print(person_dict)

# {"name": "Jack", "age": 18, "address": {"country": "China", "province": "Shanghai", "road": "550 Dalian Road (W)"}}
person_json_str = json.dumps(person_dict)
print(person_json_str)

# {'name': 'Jack', 'age': 18, 'address': {'country': 'China', 'province': 'Shanghai', 'road': '550 Dalian Road (W)'}}
person_dict_from_json_str = json.loads(person_json_str)
print(person_dict_from_json_str)
```

### 例2