# Python 身份运算符

## 概念

**身份运算符**用于比较两个对象的内存地址是否一致

* is
    * 类似于 `id(x) == id(y)`

* not is
    * 类似于 `id(x) != id(y)`

## 与 `==` 的区别

```Python
a = [1, 2, 3]
b = [1, 2, 3]

# True
# 列表 a 与 b 的内容相同
print(a == b)

# False
# 列表 a 与 b 指向的不是同一块内存空间
print(a is b)

c = a
# True
# 列表 a 与 b 的内容相同
print(a == c)

# True
# 列表 a 与 b 指向的是同一块内存空间
print(a is c)
```

