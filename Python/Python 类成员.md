# Python 类成员

```Python
class Tool:
    # 类属性
    counter = 0

    def __init__(self, name):
        # 修改类属性
        Tool.counter += 1
        self.name = name

    def getCounter(self):
        # 在实例方法中访问类成员
        return Tool.counter


t1 = Tool("斧子")
t2 = Tool("锤子")
t3 = Tool("螺丝刀")

print(t1.getCounter())

# 通过实例名访问类属性
print(t1.counter)

# 通过类名访问类属性
print(Tool.counter)
```

## 属性查找机制

```Python
class Tool:
    # 类属性
    name = "类属性"

    def __init__(self):
        # 修改类属性
        self.name = "实例属性"


t = Tool()

print(t.name)
print(Tool.name)
```

上例中，存在2个同名属性，一个为**类属性**，一个为**实例属性**。

使用类名访问属性，将只能访问到类属性。

通过实例名访问属性，将优先访问实例属性，如果不存在指定名称的实例属性，则尝试访问指定名称的类属性。

```Python
class Tool:
    # 类属性
    name = "类属性值"

    def __init__(self):
        # 类属性
        self.name = "实例属性值"


t = Tool()

# 实例属性值
print(t.name)

# 类属性值
print(Tool.name)
```

如果是通过赋值语句对使用实例名.属性名的方式修改属性的值，而恰好该属性在`__init__`中又没有定义，则解释器会自动为该实例创建一个实例成员，即该属性仅对该实例有效。

```Python
class Tool:
    pass


t1 = Tool()
t2 = Tool()

t1.name = "t1"

# 实例属性值
print(t1.name)

# 错误
print(t2.name)
```