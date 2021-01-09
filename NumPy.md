# Numpy

[TOC]

## 统计

### 均值

```python
import numpy as np
np.mean(data)
```

### 中位数

```python
import numpy as np
np.median(data)
```

### 众数

未提供

## 文件

### 文件类型

#### `.npy`

一种简单的格式，用于将 numpy 数组的完整信息保存到磁盘。

`.npy` 是 NumPy 中用于在磁盘上持久化任何单个数组的标准二进制文件格式。

该格式存储了用于重构数组所需的所有形状和类型信息，即使在具有不同体系结构的另一台机器上也是如此。

#### `.npz`

The `.npz` format is the standard format for persisting *multiple* NumPy arrays on disk. 

A `.npz` file is a zip file containing multiple `.npy` files, one for each array.

#### 文本文件

##### `CSV` 文件

一种有特定格式的文本文件

### 文件操作

#### 保存

##### `save()`

将单个数组保存到 `.npy` 格式的二进制文件中

原型如下：

````Python
numpy.save(file, arr, allow_pickle=True, fix_imports=True)
````

函数

##### `savez()`

将多个数组保存到非压缩的 `.npz` 格式的单个文件中

```Python
numpy.savez(file, *args, **kwds)
```

##### `savetxt()`

将一个数组保存至文本文件

```Python
numpy.savetxt(fname, X, fmt='%.18e', delimiter=' ', newline='n', header='', footer='', comments='# ', encoding=None)
```

参数

* fname：文件路径
* X：数组
* fmt：写入文件中每个元素的字符串格式；默认'%.18e'（保留18位小数的浮点数形式）
* delimiter：分割字符串，默认以空格分隔。

#### 读取

##### `load()`

Load arrays or pickled objects from `.npy`, `.npz` or pickled files.

```Python
numpy.load(file, mmap_mode=None, allow_pickle=False, fix_imports=True, encoding='ASCII')
```

##### `loadtxt()`

从文本文件加载数据；文件中的每一行都必须具备相同数量的值

```Python
numpy.loadtxt(fname, dtype=<class 'float'>, comments='#', delimiter=None, converters=None, skiprows=0, usecols=None, unpack=False, ndmin=0, encoding='bytes', max_rows=None)
```

###### 例

```Python
import numpy as np

outfile = r'.\data.csv'

# 使用 loadtxt 函数从指定文件加载数据，使用逗号作为定界符，跳过第1行
x = np.loadtxt(outfile, delimiter=',', skiprows=1)
print(x)
# [[  1.  123.    1.4  23. ]
#  [  2.  110.    0.5  18. ]
#  [  3.  164.    2.1  19. ]]

# 使用 loadtxt 函数从指定文件加载数据，使用逗号作为定界符，跳过第1行，只保留第1、2列
x = np.loadtxt(outfile, delimiter=',', skiprows=1, usecols=(1, 2))
print(x)
# [[123.    1.4]
#  [110.    0.5]
#  [164.    2.1]]

# 使用 loadtxt 函数从指定文件加载数据，使用逗号作为定界符，跳过第1行，只保留第1、2列，自动拆包
val1, val2 = np.loadtxt(outfile, delimiter=',', skiprows=1, usecols=(1, 2), unpack=True)
print(val1)  # [123. 110. 164.]
print(val2)  # [1.4 0.5 2.1]
```

##### `genfromtxt()`

从文本文件加载数据，同时指定一个缺失值处理句柄

```Python
numpy.genfromtxt(fname, dtype=<class 'float'>, comments='#', delimiter=None, skip_header=0, skip_footer=0, converters=None, missing_values=None, filling_values=None, usecols=None, names=None, excludelist=None, deletechars=" !#$%&'()*+, -./:;<=>?@[]^{|}~", replace_space='_', autostrip=False, case_sensitive=True, defaultfmt='f%i', unpack=None, usemask=False, loose=True, invalid_raise=True, max_rows=None, encoding='bytes')
```

###### 例

```Python
import numpy as np

outfile = r'.\data.csv'

x = np.genfromtxt(outfile, delimiter=',', names=True)

print(x)
# [(1., 123., 1.4, 23.) (2., 110., 0.5, 18.) (3., 164., 2.1, 19.)]

print(type(x))  
# <class 'numpy.ndarray'>

print(x.dtype)
# [('id', '<f8'), ('value1', '<f8'), ('value2', '<f8'), ('value3', '<f8')]

print(x['id'])  # [1. 2. 3.]
print(x['value1'])  # [123. 110. 164.]
print(x['value2'])  # [1.4 0.5 2.1]
print(x['value3'])  # [23. 18. 19.]
```

```Python
import numpy as np

outfile = r'.\data1.csv'

x = np.genfromtxt(outfile, delimiter=',', names=True)

print(x)
# [(1., 123., 1.4, 23.) (2., 110., nan, 18.) (3.,  nan, 2.1, 19.)]

print(type(x))  
# <class 'numpy.ndarray'>

print(x.dtype)
# [('id', '<f8'), ('value1', '<f8'), ('value2', '<f8'), ('value3', '<f8')]

print(x['id'])  # [1. 2. 3.]
print(x['value1'])  # [123. 110.  nan]
print(x['value2'])  # [1.4 nan 2.1]
print(x['value3'])  # [23. 18. 19.]
```

#### 综例

```Python
import numpy as np

# 文件名
outfile = r'.\test.npy'

# 初始化随机数种子
np.random.seed(20200619)

# 生成取值范围在区间 [0, 1) 且服从均匀分布的二维随机数数组
x = np.random.uniform(0, 1, [3, 5])

# 将随机数数组保存至指定文件
np.save(file=outfile, arr=x)

# 从指定文件位置加载数据
y = np.load(outfile)

# 打印数组
print(y)

# [[0.01123594 0.66790705 0.50212171 0.7230908  0.61668256]
#  [0.00668332 0.1234096  0.96092409 0.67925305 0.38596837]
#  [0.72342998 0.26258324 0.24318845 0.98795012 0.77370715]]
```

```Python
import numpy as np

# 文件名
outfile = r'.\test.npz'
x = np.linspace(0, np.pi, 5)
y = np.sin(x)
z = np.cos(x)
np.savez(outfile, x, y, z_d=z)
data = np.load(outfile)
np.set_printoptions(suppress=True)
print(data.files)  
# ['z_d', 'arr_0', 'arr_1']

print(data['arr_0'])
# [0.         0.78539816 1.57079633 2.35619449 3.14159265]

print(data['arr_1'])
# [0.         0.70710678 1.         0.70710678 0.        ]

print(data['z_d'])
# [ 1.          0.70710678  0.         -0.70710678 -1.        ]
```

```Python
import numpy as np

outfile = r'.\test.txt'

x = np.arange(0, 10).reshape(2, -1)

np.savetxt(outfile, x)

y = np.loadtxt(outfile)

print(y)

# [[0. 1. 2. 3. 4.]
#  [5. 6. 7. 8. 9.]]
```

```Python
import numpy as np

outfile = r'.\test.csv'

x = np.arange(0, 10, 0.5).reshape(4, -1)

np.savetxt(outfile, x, fmt='%.3f', delimiter=',')

y = np.loadtxt(outfile, delimiter=',')

print(y)

# [[0.  0.5 1.  1.5 2. ]
#  [2.5 3.  3.5 4.  4.5]
#  [5.  5.5 6.  6.5 7. ]
#  [7.5 8.  8.5 9.  9.5]]
```

## 打印

### 选项

#### 设置 / `set_printoptions`

设置打印选项

```Python
numpy.set_printoptions(precision=None, 
                       threshold=None, 
                       edgeitems=None, 
                       linewidth=None, 
                       suppress=None, 
                       nanstr=None, 
                       infstr=None, 
                       formatter=None, 
                       sign=None, 
                       floatmode=None, 
                       *, 
                       legacy=None)
```

##### 例

```Python
import numpy as np

# 精确度
np.set_printoptions(precision=4)
x = np.array([1.123456789])
print(x)
# [1.1235]

# 触发汇总展示的阈值
np.set_printoptions(threshold=20)
x = np.arange(50)
print(x)
# [ 0  1  2 ... 47 48 49]

np.set_printoptions(threshold=np.iinfo(np.int).max)
print(x)
# [ 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
#  24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47
#  48 49]

eps = np.finfo(float).eps
x = np.arange(4.)
x = x ** 2 - (x + eps) ** 2
print(x)
# [-4.9304e-32  -4.4409e-16  0.0000e+00  0.0000e+00]

np.set_printoptions(suppress=True)
print(x)
# [-0. -0.  0.  0.]

x = np.linspace(0, 10, 10)
print(x)
# [ 0.  1.1111  2.2222  3.3333  4.4444  5.5556  6.6667  7.7778  8.8889  10.]

np.set_printoptions(precision=2, suppress=True, threshold=5)
print(x)
# [ 0.  1.11  2.22 ...  7.78  8.89 10.  ]
```

#### 获取 / `get_printoptions`

返回打印选项

```Python
numpy.get_printoptions()
```

##### 例

```Python
import numpy as np

x = np.get_printoptions()
print(x)

# {
#     'edgeitems': 3,
#     'threshold': 1000,
#     'floatmode': 'maxprec',
#     'precision': 8,
#     'suppress': False,
#     'linewidth': 75,
#     'nanstr': 'nan',
#     'infstr': 'inf',
#     'sign': '-',
#     'formatter': None,
#     'legacy': False
# }
```

## 备注

### 测试数据

#### data.csv

```csv
id,value1,value2,value3
1,123,1.4,23
2,110,0.5,18
3,164,2.1,19
```

#### data1.csv

```text
id,value1,value2,value3
1,123,1.4,23
2,110,,18
3,,2.1,19
```

## 常见概率分布

### 二项分布

```Python
numpy.random.binomial(n, p, size=None)
```

从一个二项分布中抽取样本

**参数**

* n
    * 试验次数
* p
    * 单次试验事件发生的概率
* size
    * 采样次数

**返回值**

从参数化的二项分布中抽取样本，其中每个样本等于 $n$ 次试验中成功的次数。

例

```Python
import numpy as np
import matplotlib.pyplot as plt
from scipy import stats

# 用来正常显示中文标签
plt.rcParams['font.sans-serif'] = ['Heiti TC']
# 用来正常显示负号
plt.rcParams['axes.unicode_minus'] = False

np.random.seed(47)

n = 100
p = 0.5
size = 100
x = np.random.binomial(n, p, size)

plt.hist(x)
plt.ylabel('指定事件发生x次的次数')
plt.xlabel('n次试验中指定事件发生指定的次数')
plt.show()
```

还可以使用 `scipy` 提供的 `stats` 类计算指定事件发生特定次数的概率

```Python
# n次试验中，指定事件发生指定次数的概率
s = stats.binom.pmf(range(n + 1), n, p)
print(np.around(s, 3))
# [
#     0.    0.    0.    0.    0.    0.    0.    0.    0.    0.    
#     0.    0.    0.    0.    0.    0.    0.    0.    0.    0.
#     0.    0.    0.    0.    0.    0.    0.    0.    0.    0.
#     0.    0.    0.    0.    0.    0.001 0.002 0.003 0.004 0.007
#     0.011 0.016 0.022 0.03  0.039 0.048 0.058 0.067 0.074 0.078 
#     0.08  0.078 0.074 0.067 0.058 0.048 0.039 0.03  0.022 0.016
#     0.011 0.007 0.004 0.003 0.002 0.001 0.    0.    0.    0.    
#     0.    0.    0.    0.    0.    0.    0.    0.    0.    0.
#     0.    0.    0.    0.    0.    0.    0.    0.    0.    0.
#     0.    0.    0.    0.    0.    0.    0.    0.    0.    0.
#     0.   
# ]
```

### 泊松分布

```Python
numpy.random.poisson(lam=1.0, size=None)
```

从一个泊松分布中抽取样本

**参数**

* `lam` 单位时间内，事件发生次数的平均值

```Python
import numpy as np
import matplotlib.pyplot as plt
from scipy import stats

# 用来正常显示中文标签
plt.rcParams['font.sans-serif'] = ['Heiti TC']
# 用来正常显示负号
plt.rcParams['axes.unicode_minus'] = False

np.random.seed(47)
lam = 7
size = 50000
x = np.random.poisson(lam, size)

plt.hist(x)

plt.xlabel('每十分钟接到订票电话的次数')
plt.ylabel('所有样本中随便变量发生指定次数的次数')

plt.show()
```

### 超几何分布

```Python
numpy.random.hypergeometric(ngood, nbad, nsample, size=None)
```

### 均匀分布

```Python
numpy.random.uniform(low=0.0, high=1.0, size=None)
```

### 正态分布

```Python
numpy.random.standard_normal(size=None)
```

该函数对应标准正态分布。如果需要使用非标准正态分布应当使用函数

```Python
numpy.random.normal(loc=0.0, scale=1.0, size=None)
```

该函数的默认实现也对应标准正态分布

### 指数分布

```Python
numpy.random.exponential(scale=1.0, size=None)
```

## 随机

### 随机数

```Python
numpy.random.seed(seed=None)
```

`seed()` 用户设定随机数种子

### 随机抽数

```Python
numpy.random.choice(a, size=None, replace=True, p=None)
```

### 随机乱序

```Python
numpy.random.shuffle(x)
```

原地操作原集合

```Python
numpy.random.permutation(x)
```

## `ndarray`

表示 `The N-dimensional array`，即 N 维数组

### 结构

#### 维度 / `dimension`

维度（`dimension`）亦成为轴（`axis`），编号从 `0` 开始，分别称为 第 0 轴、第 1 轴等，以此类推。

### 创建

#### `array()`

参加其他节

#### `arange()`

```python
arr = np.arange(1, 10, 2)

# [1 3 5 7 9]
print(arr)
```

#### `random()`

```python
arr = np.random.random((2, 3))

# [[0.51085155 0.67346889 0.58884268]
#  [0.3702711  0.63469232 0.99166862]]
arr
```

#### `randint()`

```python
arr = np.random.randint(low=1, 
                        high=10, 
                        size=(2, 3))

# [[4 5 4]
#  [9 5 3]]
arr
```

#### `linspace()`

```python
arr = np.linspace(start=1,
                  stop=10,
                  num=19,
                  endpoint=True)

# [ 1.   1.5  2.   2.5  3.   3.5  4.   4.5  5.   5.5  6.   6.5  7.   7.5  8.   8.5  9.   9.5 10. ]
arr
```

#### `fromfunction()`

```python
arr = np.fromfunction(function=lambda i, j: (i + 1) * (j + 1), 
                      shape=(9, 9), 
                      dtype=np.int64)

# [[ 1  2  3  4  5  6  7  8  9]
#  [ 2  4  6  8 10 12 14 16 18]
#  [ 3  6  9 12 15 18 21 24 27]
#  [ 4  8 12 16 20 24 28 32 36]
#  [ 5 10 15 20 25 30 35 40 45]
#  [ 6 12 18 24 30 36 42 48 54]
#  [ 7 14 21 28 35 42 49 56 63]
#  [ 8 16 24 32 40 48 56 64 72]
#  [ 9 18 27 36 45 54 63 72 81]]
arr
```

#### `ones()`

创建全 `1` 数组

```python
# array([[1., 1., 1.],
#        [1., 1., 1.]])
np.ones((2, 3))
```

#### `ones_like()`

创建与指定数组同形的全 `1` 数组

#### `zeros()`

创建全 `0` 数组

```python
# array([[0., 0., 0.],
#        [0., 0., 0.]])
np.zeros((2, 3))
```

#### `zeros_like()`

创建与指定数组同形的全 `0` 数组

#### `full()`

#### `full_like()`

### 属性

#### `dtype`

> 数据类型

例

```Python
arr = np.array([1, 2, 3])
arr.dtype

# dtype('int64')
```

```Python
arr = np.array([
    [1, 3, 5, 7, 9], 
    [2, 4, 6, 8, 10]
])

# dtype('int64')
arr.dtype
```

```Python
deftype = (
    [
        ('col1', np.int),
        ('col2', np.int),
        ('col3', np.int),
    ]
)
arr = np.array([
    [1, 3, 5],
    [2, 4, 6]
], dtype=deftype)

# dtype([('col1', '<i8'), ('col2', '<i8'), ('col3', '<i8')])
arr.dtype
```

#### `ndim`

> 维度

例

```Python
arr = np.array([1, 2, 3])
arr.ndim

# 1
```

```Python
arr = np.array([
    [1, 3, 5, 7, 9], 
    [2, 4, 6, 8, 10]
])
arr.ndim

# 2
```

#### `shape`

> 形状

例

```Python
arr = np.array([1, 2, 3])
arr.shape

# (3,)
```

```Python
arr = np.array([
    [1, 3, 5, 7, 9], 
    [2, 4, 6, 8, 10]
])
arr.shape

# (2, 5)
```

### 运算

#### 加

对于 2 个 `shape` 完全相同的数组，如果它们的元素定义了合法的加法操作，则可以用 `+` 运算符来执行相加操作，其运行结果为 2 个数组对应位置元素相加。

```python
arr1 + arr2
```

加法的广播特性

如果将一个标量与一个数组相加，则 `NumPy` 会自动将该标量与数组的各个元素逐个相加。例如：

```Python
np.array(object=[[1, 2, 3], [4, 5, 6]]) + 1
```

将得到

```
[[2 3 4]
 [5 6 7]]
```

### 指定类型

当在 `ndarray` 中存储元组时，可以使用 `dtype` 来明确表示元组各个位置上的元素类型。例：


```Python
deftype = (
    [
        ('data', np.str, 10),
        ('close', np.float),
        ('vol', np.int)
    ]
)

np.array(
    [('2009-1-1', 11, 13000),
     ('2010-1-1', 12, 11000),
     ('2011-1-1', 13, 12000)],
    dtype=deftype
)
```

## 帮助

* `np.info()`

    例：

    ```Python
    np.info(np.abs)
    ```

* `help()`

* `?`