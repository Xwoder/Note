# Python __new__ 方法

```Python
class Dog(object):

    def __new__(cls, *args, **kwargs):
        # 调用父类的 __new__ 方法
        instance = super().__new__(cls)
        # 返回实例对象
        return instance
```

##  单例

```Python
class Earth(object):
    instance = None

    init_flag = False

    def __new__(cls, *args, **kwargs):
        if cls.instance is None:
            # 调用父类的 __new__ 方法
            cls.instance = super().__new__(cls)
        return cls.instance

    def __init__(self):
        if Earth.init_flag:
            return

        print("初始化")
        Earth.init_flag = True
```