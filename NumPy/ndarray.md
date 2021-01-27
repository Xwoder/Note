# ndarray

[TOC]

## 属性

### `dtype`

> 数据类型

### `shape`

> 形状
>

### `ndim`

> 维度
>

## 创建

### 从内置类型创建

#### 从 `list` 创建

``` Python
l = [1, 2, 3, 1, 2, 3, 4]
arr = np.array(l)
```

#### 从 `set` 创建

``` Python
s = {1, 2, 3, 1, 2, 3, 4}
arr = np.array(s)
```

#### 从 `tuple` 创建

``` Python
t = (1, 2, 3, 1, 2, 3, 4)
arr = np.array(t)
```

### 快捷创建

### 特殊创建

#### `ones`

创建元素值全为 `1` 的 `ndarray`

``` Python
np.ones(shape=(2, 3))
```

将得到

``` 
array([[1., 1., 1.],
    [1., 1., 1.]])
```

#### `zeros`

创建元素值全为 `0` 的 `ndarray`

``` Python
np.zeros(shape=(2, 3))
```

将得到

``` 
array([[0., 0., 0.],
    [0., 0., 0.]])
```

#### `full`

创建元素值全部为特定值的 `ndarray`，值通过参数 `fill_value` 参数指定

``` Python
np.full(shape=(2, 3), fill_value=3)
```

将得到

``` Python
array([[3, 3, 3],
    [3, 3, 3]])
```

#### `eye`

创建二维单位矩阵

##### 例子

* 例1

``` Python
np.eye(2)
```

将得到

``` Python
array([[1., 0.],
    [0., 1.]])
```

* 例2

``` Python
np.eye(4)
```

将得到

``` Python
array([[1., 0., 0., 0.],
    [0., 1., 0., 0.],
    [0., 0., 1., 0.],
    [0., 0., 0., 1.]])
```

* 例3

``` Python
np.eye(4,2)
```

将得到

``` Python
array([[1., 0.],
    [0., 1.],
    [0., 0.],
    [0., 0.]])
```

## 元素分析

### `alltrue`

判断 `ndarray` 元素是否全部为 `true`

```Python
arr = np.array([-1, 0, 1, True, False])

# False
np.alltrue(arr)
```

## 运算

### 加法

```Python
import numpy as np

# [[0 1 2]
#  [3 4 5]
#  [6 7 8]]
arr1 = np.arange(0, 9).reshape(3, 3)
print(arr1)

# [[8 7 6]
#  [5 4 3]
#  [2 1 0]]
arr2 = np.arange(8, -1, -1).reshape(3, 3)
print(arr2)

# [[8 8 8]
#  [8 8 8]
#  [8 8 8]]
arr3 = arr1 + arr2
print(arr3)
```

#### 加法广播

##### 标量加法广播

```Python
import numpy as np

# [[0 1 2]
#  [3 4 5]
#  [6 7 8]]
arr1 = np.arange(0, 9).reshape(3, 3)
print(arr1)

# [[10 11 12]
#  [13 14 15]
#  [16 17 18]]
arr2 = arr1 + 10
print(arr2)
```

##### 向量加法广播

```Python
import numpy as np

# [[0 1 2]
#  [3 4 5]
#  [6 7 8]]
arr1 = np.arange(0, 9).reshape(3, 3)
print(arr1)

# [1 2 3]
arr2 = np.arange(1, 4)
print(arr2)

# [[ 1  3  5]
#  [ 4  6  8]
#  [ 7  9 11]]
arr3 = arr1 + arr2
print(arr3)
```

```python
import numpy as np

# [[0 1 2]
#  [3 4 5]
#  [6 7 8]]
arr1 = np.arange(0, 9).reshape(3, 3)
print(arr1)

# [[1]
#  [2]
#  [3]]
arr2 = np.arange(1, 4).reshape(-1, 1)

print(arr2)

# [[ 1  2  3]
#  [ 5  6  7]
#  [ 9 10 11]]
arr3 = arr1 + arr2
print(arr3)
```

### 乘法

#### 乘法广播

```Python
import numpy as np

# [[0 1 2]
#  [3 4 5]
#  [6 7 8]]
arr1 = np.arange(0, 9).reshape(3, 3)
print(arr1)

# [[10 11 12]
#  [13 14 15]
#  [16 17 18]]
arr2 = arr1 * 10
print(arr2)
```

