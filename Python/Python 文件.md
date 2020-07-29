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

## 读取

```Python
input_file_name = "file_to_read.txt"

file_reader = open(input_file_name, "r")

for row in file_reader:
    print(row, end="")

file_reader.close()
```

### `with...as...`

```Python
input_file_name = "file_to_read.txt"

with open(input_file_name, "r") as file_reader:
    for row in file_reader:
        print(row, end="")
```

