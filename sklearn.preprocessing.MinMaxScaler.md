# sklearn.preprocessing.MinMaxScaler

归一化

```python
import numpy as np
from sklearn.preprocessing import MinMaxScaler

array = np.array([1, 2, 3, 4, 5])

# 最小值
min = np.min(array)
# 最大值
max = np.max(array)

# 极差
range = max - min

array_norm_1 = (array - min) / range
minMaxScaler = MinMaxScaler()

array_norm_2 = minMaxScaler.fit_transform(array.reshape(-1, 1)).T[0]

print(array_norm_1)
print(array_norm_2)
```