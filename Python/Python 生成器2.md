# Python 生成器

```python
def MyGenRange(num):
    for i in range(num):
        yield i * 2

gen = MyGenRange(10)

print(gen)

for i in gen:
    print(i)
```

