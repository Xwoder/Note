# Python 示例代码之 Person

## 例1

```Python
class Person:
    def __init__(self, name, weight):
        self.name = name
        self.weight = weight

    def __str__(self):
        return '我的名字是 %s, 体重是 %.2f 公斤' % (self.name, self.weight)

    def run(self):
        self.weight -= 0.5

    def eat(self):
        self.weight += 1


jack = Person('Jack', 60)

# 我的名字是 Jack, 体重是 60.00 公斤
print(jack)

jack.eat()
# 我的名字是 Jack, 体重是 61.00 公斤
print(jack)

jack.run()
# 我的名字是 Jack, 体重是 60.50 公斤
print(jack)

jack.run()
# 我的名字是 Jack, 体重是 60.00 公斤
print(jack)
```

