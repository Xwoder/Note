# Python 交互模式与文件执行

[TOC]

## 文件执行

```shell
$ python py文件

# 例
$ python test.py
```

### 可执行 py 文件

仅需在 `py` 文件的头部加上


```
#!/usr/bin/env python3
```

例如


```python
#!/usr/bin/env python3

print("Hello World")
```

保存该文件，并赋予可执行权限


```shell
$ chmod +x py文件名
```

此时，该 `py` 文件就成为了一个可执行文件


## 交互模式

### 进入

```shell
python
```

### 退出

```python
exit()
```

