# Python 静态方法

```Python
class Dog:

    # 静态方法
    @staticmethod
    def desc():
        print("Class Dog")


# Class Dog
Dog.desc()
```

不需要创建类的实例就可以调用类的静态方法。

静态方法不能访问以下4种成员

- 实例属性
- 实例方法
- 类属性
- 类方法