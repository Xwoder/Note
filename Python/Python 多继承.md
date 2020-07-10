# Python 多继承

## 语法

```Python
class 子类(父类1, 父类2, ...):
    pass
```

## 例

```Python
class Father:
    def repair(self):
        print('repair')


class Monther:
    def cook(self):
        print('cook')


class Child(Father, Monther):
    pass


child = Child()
child.repair()
child.cook()
```

## 多继承的方法调用

为了明确多继承时，类的方法的查找顺序，`Python` 为每个类提供了一个内置的名为 `__mro__` 的属性，可以通过打印该属性的值，来查看方法调用时的查找顺序。

对于上例中的 `Child` 的类，其 `__mro__` 方法的输出如下

```text
(<class '__main__.Child'>, <class '__main__.Father'>, <class '__main__.Monther'>, <class 'object'>)
```

当方法调用时，系统先在 `Child` 类中查找，如果查找成功，则调用该方法，否则继续在 `Father` 类中查找，以此类推，直到查找成功或失败。