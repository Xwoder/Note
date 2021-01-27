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

