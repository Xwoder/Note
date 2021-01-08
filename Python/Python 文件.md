# Python 文件

[TOC]

## 文件类型分类

* 二进制文件
* 文本文件

严格意义上来说，`文本文件`也是`二进制文件`的一种


## 路径分类

* 相对路径
* 绝对路径

### 绝对路径

`/` 表示根路径
`~` 表示当前用户的 HOME 目录路径

### 相对路径

#### 当前路径

```
./
```

#### 上级路径

```
../
```

## 基本操作

### 打开

####  `open()`

```Python
file = open("文件名")
file = open("文件名", 打开方式)
file = open("文件名", 打开方式, encoding="编码名称")
```

##### 模式

| 符号  | 含义                                                         |                                            |
| :---- | :----------------------------------------------------------- | ------------------------------------------ |
| `'r'` | 读文件；默认模式                                             |                                            |
| `'w'` | 写文件，首先截断文件                                         | 若文件不存在，则创建；若文件已存在，则覆盖 |
| `'x'` | open for exclusive creation, failing if the file already exists |                                            |
| `'a'` | 写文件，若文件存在则追加至末尾                               | 若文件不存在，则创建                       |
| `'b'` | 二进制模式                                                   |                                            |
| `'t'` | 文本模式；默认模式                                           |                                            |
| `'+'` | 更新文件，即读和写                                           |                                            |

以上模式可以组合使用，例如：`w+` 、`r+`

### 关闭 

####  `close()`

```Python
file.close()
```

### 读取

`open()` 函数返回的文件对象是一个可迭代对象

#### `for` 循环

```Python
input_file_name = "file_to_read.txt"

file = open(input_file_name, "r")

for row in file:
    print(row)

file.close()
```

#### `read()`

```Python
file = open("note.txt", "r")

content = file.read()

# <class 'str'>
print(type(content))

# I am not yours, not lost in you,
# Not lost, although I long to be
# Lost as a candle lit at noon,
# Lost as a snowflake in the sea.
print(content)

file.close()
```

```Python
with open('helloworld.txt', 'r') as file:
    # 读取 5 个字节
    str_hello = file.read(5)
    # 读取 1 个字节
    _ = file.read(1)
    # 读取剩余全部内容
    str_world = file.read()

print(str_hello)
print(str_world)
```

#### `readline()`

```Python
file = open("note.txt", "r")

while True:
    line = file.readline()

    if len(line) <= 0:
        break

    line = line.replace("\n", "")

    print(line)

file.close()
```

#### `readlines()`

```Python
file = open("note.txt", "r")

lines = file.readlines()

# <class 'list'>
print(type(lines))

# [
#     'I am not yours, not lost in you,\n',
#     'Not lost, although I long to be\n',
#     'Lost as a candle lit at noon,\n',
#     'Lost as a snowflake in the sea.'
# ]
print(lines)

file.close()
```

#### `with...as...`

```Python
input_file_name = "file_to_read.txt"

with open(input_file_name, "r") as file_reader:
    for row in file_reader:
        print(row, end="")
```

使用 `with...as...`语句，文件会在 `with` 代码块结束后自动关闭

### 写入

`write()`

```python
file = open('helloworld.txt', 'w')
file.write('Hello World')
file.close()
```

或者

```python
with open('helloworld.txt', 'w') as file:
    file.write('Hello World')
```

