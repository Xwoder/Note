# Python 类

[TOC]

##  私有属性

以两个下划线开头的属性就是**私有属性**

```Python
class Person:
    def __init__(self, name, age):
        self.name = name
        self.__age = age

    def __str__(self):
        return 'name = %s, age = %d' % (self.name, self.__age)


p = Person('Jack', 18)
print(p)

# 错误
# AttributeError: 'Person' object has no attribute '__age'
print(p.__age)
```

## 私有方法

以两个下划线开头的属性就是**私有方法**

```Python
class Person:
    def __init__(self, name, age):
        self.name = name
        self.__age = age

    def __print_age(self):
        return 'age = %d' % self.__age


p = Person('Jack', 18)

# AttributeError: 'Person' object has no attribute '__print_age'
print(p.__print_age())
```

## 说明

在 `Python` 中，没有真正意义上的私有属性和私有方法

可以通过在原私有属性和私有方法名前面加上 `_类名` 的方法来访问它们

```Python
class Person:
    def __init__(self, name, age):
        self.name = name
        self.__age = age


p = Person('Jack', 18)

print(p._Person__age)
```

```Python
class Person:
    def __init__(self, name, age):
        self.name = name
        self.__age = age

    def __print_age(self):
        return 'age = %d' % self.__age


p = Person('Jack', 18)

print(p._Person__print_age())
```

### 可访问性

子类无法访问父类私有属性和私有方法，即：子类无法访问父类的私有成员。