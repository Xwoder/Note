# pandas 函数向量化

现有如下函数

```Python
def add(x: pd.Series, y: pd.Series):
    if x == 0:
        return y
    elif y == 0:
        return x
    else:
        return x + y
```

以及 `DataFrame` 对象

```Python
df: DataFrame = pd.DataFrame({
    'A': [1, 2, 3],
    'B': [4, 5, 6]
})
```

对其调用 `add` 函数

```Python
add(df['A'], df['B'])
```

会出现如下错误

```
The truth value of a Series is ambiguous. Use a.empty, a.bool(), a.item(), a.any() or a.all().
```

这是因为上述函数并不是一个向量化函数，可通过将函数向量化来解决这个问题。

有两种方法可实现函数的向量化：

* 使用 `np.vectorize()` 方法

    ```Python
    add_vec = np.vectorize(add)
    ```

* 使用 `@np.vectorize` 装饰器

    ```Python
    @np.vectorize
    def add(x: pd.Series, y: pd.Series):
        if x == 0:
            return y
        elif y == 0:
            return x
        else:
            return x + y
    ```
