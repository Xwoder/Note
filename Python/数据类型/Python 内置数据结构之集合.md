# Python 内置数据结构之集合

[TOC]

```python
str_a = 'abc'
str_b = 'bcd'

set_a = set(str_a)
set_b = frozenset(str_b)

{'c', 'b', 'a'}
set_a

# frozenset({'c', 'b', 'd'})
set_b
```

## 运算

### 包含

#### `in`

```python
# True
'b' in set_a
```

#### `not in`

```python
# False
'b' not in set_a
```

### 相等性

#### `==`

```python
# False
set_a == set_b
```

#### `!=`

```python
# True
set_a != set_b
```

### 子集

#### `<`

```python
# False
set_a < set_b
```

#### `<=`

```python
# False
set_a <= set_b
```

该符号的功能与 `issubset()` 的功能等价

### 超集

#### `>`

```python
# False
set_a > set_b
```

#### `>=`

```python
# False
set_a >= set_b
```

该符号的功能与 `issuperset()` 的功能等价

## 交集

```python
# {'b', 'c'}
set_a & set_b
```

`&` 与 `=` 号可构成符合运算符 `&=`

可通过 `intersection()` 方法求交集

## 并集

```python
# {'c', 'd', 'a', 'b'}
set_a | set_b
```

`^` 与 `=` 号可构成符合运算符 `^=`

可通过 `union()` 方法求并集

## 差集

```python
# {'a'}
set_a - set_b
```

`-` 与 `=` 号可构成符合运算符 `-=`

可通过 `difference()` 方法求差集

## 对称差集

```python
# {'a', 'd'}
set_a ^ set_b
```

`^` 与 `=` 号可构成符合运算符 `^=`

可通过 `symmetric_difference()` 方法求差集

