# Python 类

[TOC]

## 继承

```Python
class Animal:

    def eat(self):
        print("吃")

    def drink(self):
        print("喝")

    def run(self):
        print("跑")

    def sleep(self):
        print("睡")


class Dog(Animal):

    def bark(self):
        print("吠")


d = Dog()

# 吃
d.eat()

# 喝
d.drink()

# 跑
d.run()

# 睡
d.sleep()

# 吠
d.bark()
```

如果现在子类中调用父类方法则需要使用如下语法

```Python
super().父类方法名([实参列表])
# 或
super(父类名, self).父类方法名([实参列表])
```

上述二者是等价的。但是，不推荐使用第二种方式，因为父类名可能发生变化。

## 重写

```Python
class Animal:
    pass


class Dog(Animal):

    def bark(self):
        print("吠")


class BabyDog(Dog):

    def bark(self):
        print("嘤")


dog = Dog()
# 吠
dog.bark()

babyDog = BabyDog()
# 嘤
babyDog.bark()
```

