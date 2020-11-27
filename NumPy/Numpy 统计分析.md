# NumPy 统计分析

## 最小值

```Python
numpy.amin(a, 
           axis=None, 
           out=None, 
           keepdims=<no value>, 
           initial=<no value>, 
           where=<no value>)
```

例

```Python
import numpy as np

x = np.array([[11, 12, 13, 24, 15],
              [16, 17, 18, 19, 20],
              [21, 22, 23, 14, 25],
              [26, 27, 28, 29, 30],
              [31, 32, 33, 34, 35]])

# 11
y = np.amin(x)

# [11 12 13 14 15]
y = np.amin(x, axis=0)

# [11 16 21 26 31]
y = np.amin(x, axis=1)
```

## 最大值

```Python
numpy.amax(a, 
           axis=None, 
           out=None, 
           keepdims=<no value>, 
           initial=<no value>, 
           where=<no value>)
```

例

```Python
import numpy as np

x = np.array([[11, 12, 13, 24, 15],
              [16, 17, 18, 19, 20],
              [21, 22, 23, 14, 25],
              [26, 27, 28, 29, 30],
              [31, 32, 33, 34, 35]])

# 35
y = np.amax(x)

# [31 32 33 34 35]
y = np.amax(x, axis=0)

# [24 20 25 30 35]
y = np.amax(x, axis=1)
```

## 极差

```Python
numpy.ptp(a, 
          axis=None, 
          out=None, 
          keepdims=<no value>)
```

例

```Python
import numpy as np

x = [[56, 65, 79, 11, 97],
     [82, 60, 19, 68, 65],
     [65, 67, 46, 60, 54],
     [48, 62, 13, 10, 65]]

# min = 10
# max = 97
# ptp = 87
print("min = {0}".format(np.amin(x)))
print("max = {0}".format(np.amax(x)))
print("ptp = {0}".format(np.ptp(x)))

# min = [48 60 13 10 54]
# max = [82 67 79 68 97]
# ptp = [34  7 66 58 43]
print("min = {0}".format(np.amin(x, axis=0)))
print("max = {0}".format(np.amax(x, axis=0)))
print("ptp = {0}".format(np.ptp(x, axis=0)))

# min = [11 19 46 10]
# max = [97 82 67 65]
# ptp = [86 63 21 55]
print("min = {0}".format(np.amin(x, axis=1)))
print("max = {0}".format(np.amax(x, axis=1)))
print("ptp = {0}".format(np.ptp(x, axis=1)))
```

## 百分位数

```Python
numpy.percentile(a, 
                 q, 
                 axis=None, 
                 out=None, 
                 overwrite_input=False, 
                 interpolation='linear', 
                 keepdims=False)
```

例

```Python
import numpy as np

# [[ 1  2  3  4  5  6  7  8  9]
#  [10 11 12 13 14 15 16 17 18]
#  [19 20 21 22 23 24 25 26 27]
#  [28 29 30 31 32 33 34 35 36]
#  [37 38 39 40 41 42 43 44 45]
#  [46 47 48 49 50 51 52 53 54]
#  [55 56 57 58 59 60 61 62 63]
#  [64 65 66 67 68 69 70 71 72]
#  [73 74 75 76 77 78 79 80 81]
#  [82 83 84 85 86 87 88 89 90]
#  [91 92 93 94 95 96 97 98 99]]
x = np.arange(1, 100).reshape(11, 9)
print(x)

# 50.0
print(np.percentile(x, 50))
# 50.0
print(np.median(x))

# [46. 47. 48. 49. 50. 51. 52. 53. 54.]
print(np.percentile(x, 50, axis=0))

# [ 5. 14. 23. 32. 41. 50. 59. 68. 77. 86. 95.]
print(np.percentile(x, 50, axis=1))

# [[ 3. 12. 21. 30. 39. 48. 57. 66. 75. 84. 93.]
#  [ 7. 16. 25. 34. 43. 52. 61. 70. 79. 88. 97.]]
print(np.percentile(x, (25, 75), axis=1))
```

还可以使用 `median` 函数使用求中位数

## 算数平均数

```Python
numpy.mean(a, 
           axis=None, 
           dtype=None, 
           out=None, 
           keepdims=<no value>)
```

## 加权平均数

```Python
numpy.average(a, 
              axis=None, 
              weights=None, 
              returned=False)
```

`weights` 函数用于指定权重；若不指定，则其运行效果与 `mean` 函数是等价的。

## 方差

```Python
numpy.var(a, 
          axis=None, 
          dtype=None, 
          out=None, 
          ddof=0, 
          keepdims=<no value>)
```

`ddof` 参数用于指定自由度。

## 标准差

```Python
numpy.std(a, 
          axis=None, 
          dtype=None, 
          out=None, 
          ddof=0, 
          keepdims=<no value>)
```

## 协方差

```Python
numpy.cov(m, 
          y=None, 
          rowvar=True, 
          bias=False, 
          ddof=None, 
          fweights=None, 
          aweights=None)
```

## 相关系数

```Python
numpy.corrcoef(x, 
               y=None, 
               rowvar=True, 
               bias=<no value>, 
               ddof=<no value>)
```

## 数字化

```Python
numpy.digitize(x, 
               bins, 
               right=False)
```

例

```Python
import numpy as np

x = np.array([0.2, 6.4, 3.0, 1.6])

bins = np.array([0.0, 1.0, 2.5, 4.0, 10.0])

indices = np.digitize(x, bins)

# [1 4 3 2]
print(indices)

# 0.0 <= 0.2 < 1.0
# 4.0 <= 6.4 < 10.0
# 2.5 <= 3.0 < 4.0
# 1.0 <= 1.6 < 2.5
for n in range(x.size):
    print(bins[indices[n] - 1], "<=", x[n], "<", bins[indices[n]])
```

