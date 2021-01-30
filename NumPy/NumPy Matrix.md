# NumPy matrix

类型

```python
import numpy as np

# <class 'numpy.matrix'>
print(type(np.mat(0)))
```

定义

```python
# [[1 3 5]
#  [2 4 6]]
matrix_a = np.mat('1 3 5; 2 4 6')
print(matrix_a)

# [[1 3 5]
#  [2 4 6]]
matrix_b = np.mat([[1, 3, 5], [2, 4, 6]])
print(matrix_b)
```

转换

```python
import numpy as np

# [[0 1 2]
#  [3 4 5]
#  [6 7 8]]
# <class 'numpy.ndarray'>
arr = np.arange(0, 9).reshape(3, 3)
print(arr)
print(type(arr))

# [[0 1 2]
#  [3 4 5]
#  [6 7 8]]
# <class 'numpy.matrix'>
mat = np.mat(arr)
print(mat)
print(type(mat))
```

点乘

```python
import numpy as np

# [[1 2]
#  [3 4]]
mat = np.mat(np.arange(1, 5).reshape(2, 2))
print(mat)

# [[ 7 10]
#  [15 22]]
dot = np.dot(mat, mat)
print(dot)
```

