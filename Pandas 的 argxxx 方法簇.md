# Pandas 的 argxxx 方法簇

## argsort

```Python
import numpy as np

arr = np.array([3, 2, 1, 5, 4])
print(arr)
```

将得到 `ndarray` 对象

```
[3, 2, 1, 5, 4]
```

调用 `argsort` 方法对其进行排序，得到的返回结果是排序后的索引

```Python
argsort = np.argsort(arr)
print(argsort)
```

将得到

```
[2 1 0 4 3]
```

