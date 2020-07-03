# Python 格式化字符串

```python
print('name = %s, age = %d, height = %.2f' % ('Jack', 26, 1.75))
```

跟随在 `%` 之后的有圆括号包围的部分，本质上是一个元组。

由此，上述语句可改写为

```python
person_info = ('Jack', 26, 1.75)
print('name = %s, age = %d, height = %.2f' % person_info)
```

