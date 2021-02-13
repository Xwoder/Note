# Python zip

创建一个从每个可迭代对象中聚合元素的迭代器。

```python
names = ['xiaoming', 'xiaohong', 'xiaogang']
ages = [25, 18, 20]

for (name, age) in zip(names, ages):
    print(f'{name} --> {age}')
```

将得到

```python
xiaoming --> 25
xiaohong --> 18
xiaogang --> 20
```

