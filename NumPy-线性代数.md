# Numpy 线性代数

[TOC]

## 矩阵

### 矩阵乘法

```Python
ndarray.dot(b, out=None)
```

例

```Python
import numpy as np

x = np.array([1, 2, 3, 4, 5])
y = np.array([2, 3, 4, 5, 6])
z = np.dot(x, y)
# 70
print(z)

x = np.array([[1, 2, 3], 
              [3, 4, 5],
              [6, 7, 8]])
# [[1 2 3]
#  [3 4 5]
#  [6 7 8]]
print(x)

y = np.array([[5, 4, 2],
              [1, 7, 9], 
              [0, 4, 5]])
# [[5 4 2]
#  [1 7 9]
#  [0 4 5]]
print(y)

z = np.dot(x, y)
# [[  7  30  35]
#  [ 19  60  67]
#  [ 37 105 115]]
print(z)

z = np.dot(y, x)
# [[ 29  40  51]
#  [ 76  93 110]
#  [ 42  51  60]]
print(z)
```

### 特征值与特征向量

```Python
numpy.linalg.eig(a)
```

计算方阵的特征值和特征向量

```Python
numpy.linalg.eigvals(a)
```

计算方阵的特征值

例

```Python
import numpy as np

# 创建一个对角矩阵
x = np.diag((1, 2, 3))
print(x)
# [[1 0 0]
#  [0 2 0]
#  [0 0 3]]

print(np.linalg.eigvals(x))
# [1. 2. 3.]

eigenvalue, eigenvector = np.linalg.eig(x)
# 特征值保存在 eigenvalue 中，特征向量保存在 eigenvector 中
print(eigenvalue)
# [1. 2. 3.]
print(eigenvector)
# [[1. 0. 0.]
#  [0. 1. 0.]
#  [0. 0. 1.]]

# 检验特征值与特征向量是否正确
for i in range(3):
    if np.allclose(eigenvalue[i] * eigenvector[:, i], np.dot(x, eigenvector[:, i])):
        print('Right')
    else:
        print('Error')
# Right
# Right
# Right
```

特征值全部为正数的矩阵称为正定矩阵

正定矩阵的判断

```Python
import numpy as np

# [[ 0  1  2  3]
#  [ 4  5  6  7]
#  [ 8  9 10 11]
#  [12 13 14 15]]
A = np.arange(16).reshape(4, 4)

# [[ 0  5 10 15]
#  [ 5 10 15 20]
#  [10 15 20 25]
#  [15 20 25 30]]
A = A + A.T  # 将方阵转换成对称阵
	
# [ 6.74165739e+01 -7.41657387e+00  1.82694656e-15 -1.72637110e-15]
eigenvalues = np.linalg.eigvals(A)

if np.all(eigenvalues > 0):
    print('Yes')
else:
    print('No')
# No
```

### 分解

#### SVD 分解

```Python
numpy.linalg.svd(a, 
                 full_matrices=True, 
                 compute_uv=True, 
                 hermitian=False)
```

例

```Python
import numpy as np

# [[ 4 11 14]
#  [ 8  7 -2]]
A = np.array([[4, 11, 14], [8, 7, -2]])


u, s, vh = np.linalg.svd(A, full_matrices=False)
print(u.shape)  # (2, 2)
print(u)
# [[-0.9486833  -0.31622777]
#  [-0.31622777  0.9486833 ]]

print(s.shape)  # (2,)
print(np.diag(s))
# [[18.97366596  0.        ]
#  [ 0.          9.48683298]]

print(vh.shape)  # (2, 3)
print(vh)
# [[-0.33333333 -0.66666667 -0.66666667]
#  [ 0.66666667  0.33333333 -0.66666667]]

a = np.dot(u, np.diag(s))
a = np.dot(a, vh)
print(a)
# [[ 4. 11. 14.]
#  [ 8.  7. -2.]]
```

#### QR 分解

```Python
import numpy as np

A = np.array([[2, -2, 3], [1, 1, 1], [1, 3, -1]])
print(A)
# [[ 2 -2  3]
#  [ 1  1  1]
#  [ 1  3 -1]]

q, r = np.linalg.qr(A)
print(q.shape)  # (3, 3)
print(q)
# [[-0.81649658  0.53452248  0.21821789]
#  [-0.40824829 -0.26726124 -0.87287156]
#  [-0.40824829 -0.80178373  0.43643578]]

print(r.shape)  # (3, 3)
print(r)
# [[-2.44948974  0.         -2.44948974]
#  [ 0.         -3.74165739  2.13808994]
#  [ 0.          0.         -0.65465367]]

print(np.dot(q, r))
# [[ 2. -2.  3.]
#  [ 1.  1.  1.]
#  [ 1.  3. -1.]]

a = np.allclose(np.dot(q.T, q), np.eye(3))
print(a)  # True
```

```Python
import numpy as np

A = np.array([[1, 1], [1, -2], [2, 1]])
print(A)
# [[ 1  1]
#  [ 1 -2]
#  [ 2  1]]

q, r = np.linalg.qr(A, mode='complete')
print(q.shape)  # (3, 3)
print(q)
# [[-0.40824829  0.34503278 -0.84515425]
#  [-0.40824829 -0.89708523 -0.16903085]
#  [-0.81649658  0.27602622  0.50709255]]

print(r.shape)  # (3, 2)
print(r)
# [[-2.44948974 -0.40824829]
#  [ 0.          2.41522946]
#  [ 0.          0.        ]]

print(np.dot(q, r))
# [[ 1.  1.]
#  [ 1. -2.]
#  [ 2.  1.]]

a = np.allclose(np.dot(q, q.T), np.eye(3))
print(a)  # True
```

```Python
import numpy as np

A = np.array([[1, 1], [1, -2], [2, 1]])
print(A)
# [[ 1  1]
#  [ 1 -2]
#  [ 2  1]]

q, r = np.linalg.qr(A)
print(q.shape)  # (3, 2)
print(q)
# [[-0.40824829  0.34503278]
#  [-0.40824829 -0.89708523]
#  [-0.81649658  0.27602622]]

print(r.shape)  # (2, 2)
print(r)
# [[-2.44948974 -0.40824829]
#  [ 0.          2.41522946]]

print(np.dot(q, r))
# [[ 1.  1.]
#  [ 1. -2.]
#  [ 2.  1.]]

a = np.allclose(np.dot(q.T, q), np.eye(2))
print(a)  # True
```

#### Cholesky分解

```Python
import numpy as np

A = np.array([[1, 1, 1, 1], [1, 3, 3, 3],
              [1, 3, 5, 5], [1, 3, 5, 7]])
print(A)
# [[1 1 1 1]
#  [1 3 3 3]
#  [1 3 5 5]
#  [1 3 5 7]]

print(np.linalg.eigvals(A))
# [13.13707118  1.6199144   0.51978306  0.72323135]

L = np.linalg.cholesky(A)
print(L)
# [[1.         0.         0.         0.        ]
#  [1.         1.41421356 0.         0.        ]
#  [1.         1.41421356 1.41421356 0.        ]
#  [1.         1.41421356 1.41421356 1.41421356]]

print(np.dot(L, L.T))
# [[1. 1. 1. 1.]
#  [1. 3. 3. 3.]
#  [1. 3. 5. 5.]
#  [1. 3. 5. 7.]]
```

## 范数

```Python
import numpy as np

x = np.array([1, 2, 3, 4])

print(np.linalg.norm(x, ord=1)) 
# 10.0
print(np.sum(np.abs(x)))  
# 10

print(np.linalg.norm(x, ord=2))  
# 5.477225575051661
print(np.sum(np.abs(x) ** 2) ** 0.5)  
# 5.477225575051661

print(np.linalg.norm(x, ord=-np.inf))  
# 1.0
print(np.min(np.abs(x)))  
# 1

print(np.linalg.norm(x, ord=np.inf))  
# 4.0
print(np.max(np.abs(x)))  
# 4
```

```c
import numpy as np

A = np.array([[1, 2, 3, 4], [2, 3, 5, 8],
              [1, 3, 5, 7], [3, 4, 7, 11]])

print(A)
# [[ 1  2  3  4]
#  [ 2  3  5  8]
#  [ 1  3  5  7]
#  [ 3  4  7 11]]

print(np.linalg.norm(A, ord=1))  # 30.0
print(np.max(np.sum(A, axis=0)))  # 30

print(np.linalg.norm(A, ord=2))  
# 20.24345358700576
print(np.max(np.linalg.svd(A, compute_uv=False)))  
# 20.24345358700576

print(np.linalg.norm(A, ord=np.inf))  # 25.0
print(np.max(np.sum(A, axis=1)))  # 25

print(np.linalg.norm(A, ord='fro'))  
# 20.273134932713294
print(np.sqrt(np.trace(np.dot(A.T, A))))  
# 20.273134932713294
```

## 行列式

```Python
import numpy as np

x = np.array([[1, 2], [3, 4]])
print(x)
# [[1 2]
#  [3 4]]

print(np.linalg.det(x))
```

## 秩

```Python
import numpy as np

I = np.eye(3)  # 先创建一个单位阵
print(I)
# [[1. 0. 0.]
#  [0. 1. 0.]
#  [0. 0. 1.]]

r = np.linalg.matrix_rank(I)
print(r)  # 3

I[1, 1] = 0  # 将该元素置为0
print(I)
# [[1. 0. 0.]
#  [0. 0. 0.]
#  [0. 0. 1.]]

r = np.linalg.matrix_rank(I)  # 此时秩变成2
print(r)  # 2
```

## 迹

```Python
import numpy as np

x = np.array([[1, 2, 3], [3, 4, 5], [6, 7, 8]])
print(x)
# [[1 2 3]
#  [3 4 5]
#  [6 7 8]]

y = np.array([[5, 4, 2], [1, 7, 9], [0, 4, 5]])
print(y)
# [[5 4 2]
#  [1 7 9]
#  [0 4 5]]

print(np.trace(x))  # A的迹等于A.T的迹
# 13
print(np.trace(np.transpose(x)))
# 13

print(np.trace(x + y))  # 和的迹 等于 迹的和
# 30
print(np.trace(x) + np.trace(y))
# 30
```

## 逆矩阵

```Python
import numpy as np

A = np.array([[1, -2, 1], [0, 2, -1], [1, 1, -2]])
print(A)
# [[ 1 -2  1]
#  [ 0  2 -1]
#  [ 1  1 -2]]

# 求A的行列式，不为零则存在逆矩阵
A_det = np.linalg.det(A)  
print(A_det)
# -2.9999999999999996

A_inverse = np.linalg.inv(A)  # 求A的逆矩阵
print(A_inverse)
# [[ 1.00000000e+00  1.00000000e+00 -1.11022302e-16]
#  [ 3.33333333e-01  1.00000000e+00 -3.33333333e-01]
#  [ 6.66666667e-01  1.00000000e+00 -6.66666667e-01]]

x = np.allclose(np.dot(A, A_inverse), np.eye(3))
print(x)  # True
x = np.allclose(np.dot(A_inverse, A), np.eye(3))
print(x)  # True

A_companion = A_inverse * A_det  # 求A的伴随矩阵
print(A_companion)
# [[-3.00000000e+00 -3.00000000e+00  3.33066907e-16]
#  [-1.00000000e+00 -3.00000000e+00  1.00000000e+00]
#  [-2.00000000e+00 -3.00000000e+00  2.00000000e+00]]
```

## 方程组求解

```Python
#  x + 2y +  z = 7
# 2x -  y + 3z = 7
# 3x +  y + 2z =18

import numpy as np

A = np.array([[1, 2, 1], [2, -1, 3], [3, 1, 2]])
b = np.array([7, 7, 18])
x = np.linalg.solve(A, b)
print(x)  # [ 7.  1. -2.]

x = np.linalg.inv(A).dot(b)
print(x)  # [ 7.  1. -2.]

y = np.allclose(np.dot(A, x), b)
print(y)  # True
```

