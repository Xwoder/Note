# Python 类方法

```Python
class Tool:

    # 类方法
    @classmethod
    def print(cls):
        print("Class named Tool")


t = Tool()

# 通过实例名访问类方法
t.print()

# 通过类名访问类方法
Tool.print()
```