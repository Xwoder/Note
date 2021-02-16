# Python 的 collections 模块之 Counter

导入 `collections` 模块

```Python
from collections import Counter
```

生成数组

```Python
arr = [1, 1, 2, 0, 0, 1, 2, 3]
print(arr)
```

利用待计数的数组，实例化 `Counter` 对象

```Python
counter = Counter(arr)
print(counter)
```
将得到
```
Counter({1: 3, 2: 2, 0: 2, 3: 1})
```

`Counter` 类还提供了一个名为 `most_common` 的方法用于将计数结果字典转为按照计数结果降序排列的列表

```Python
most_common_all = counter.most_common()
print(most_common_all)
# [(1, 3), (2, 2), (0, 2), (3, 1)]
```

还可以通过传入传入参数，以获得最大的 n 个计数结果

```Python
most_common_top1 = counter.most_common(1)
print(most_common_top1)
# [(1, 3)]
```