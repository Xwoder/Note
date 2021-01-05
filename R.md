# R

[TOC]

## R 源文件

R 源文件后缀名为 `R`

## 工作区

获取当前工作区绝对路径

```R
getwd()
```

设置工作区

```R
setwd(路径)
```

## 文件操作

### 文件夹

|      函数      |    含义    | 例   |
| :------------: | :--------: | ---- |
| `dir.create()` | 创建文件夹 |      |
|    `dir()`     |    列示    |      |

### 文件

|     函数名      |       含义       | 例                                                        |
| :-------------: | :--------------: | --------------------------------------------------------- |
| `file.remove()` |     删除文件     |                                                           |
| `file.create()` |     创建文件     | `dir.create(file.path('dir1', 'dir2'), recursive = TRUE)` |
| `file.exists()` | 判断文件是否存在 |                                                           |
|  `file.info()`  |   查看文件信息   |                                                           |
| `file.rename()` |    重命名文件    | `file.rename("1.R", to = "2.R")`                          |
|  `file.copy()`  |     复制文件     | `file.copy("1.R", to = "2.R")`                            |
|  `file.path()`  |     文件路径     | `file.path("mytest3.R")`                                  |



## 包 / packages

### 安装

```R
install.packages("包名")
```

例

```R
install.packages("ggplot2")
```

### 查看

```R
library()
```

或

```R
installed.packages()
```

### 加载

```R
library(包名)
```

例

```R
library(ggplot2)
```

### 更新

查看已过时的包

```R
old.packages()
```

更新已过时的包

```R
update.packages()
```

### 卸载

```R
detach()
```

```R
detach("package:包名", unload = TRUE)
```

### 删除

```R
remove.packages()
```

```R
remove.packages("ggplot2")
```

### 帮助信息

```R
help()
```

或

```R
help(package = "包名")
```

或，在浏览器中查看文档

```
browseVignettes("ggplot2")
```

## 版本

### 查看

```R
version
```

或，通过会话信息查看

```R
sessionInfo()
```

## 帮助

查看帮助信息

```R
help()
```

或，在浏览器中查看帮助文档

```R
browseVignettes()
```

## 注释

```R
## 年龄
age <- 18
```

## 打印

### 显示打印

```R
print(变量名对象名)
print(对象名)
```

### 隐式打印

```R
变量名
对象名 
```

## 运算符

### 赋值运算符

`<-` 运算符是 `R` 中的赋值运算符

```R
x <- 10
```

## 引用

```R
source("xxx.R")
```

## 对象类型

### 字符 / character

### 数值型 / numeric

在 `R` 中的数值，默认都会被视作数值型

所有的数值类型都被当做双精度浮点型对待

`Inf` 是一个特殊的数值型，其代表无穷大

有**正无穷大**和**负无穷大**之分，即 `+Inf` 和 `-Inf`

```R
# 正无穷大
> 1 / 0
[1] Inf

# 负无穷大
> -1 / 0
[1] -Inf
```

`NaN` 也是一个特殊的数值型，其代表 `Not a Number`

### 整型 / integer

定义时，需在尾部追加一个 `L` 后缀

### 复数型 / complex

### 逻辑型 / logical

### 向量 / vector

只能存储相同类型的对象

#### 创建

#####  `c()` 

```R
x <- c(0.5, 0.6)
x <- c(TRUE, FALSE)
x <- c(T, F)
x <- c("a", "b", "c")
x <- 10:20
x <- c(1+3i, 5-4i)
```

其中

```R
x <- 10:20
```

等价于

```
c(10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L)
```

##### `vector()`

```R
x <- vector("numeric", length = 10)

# 0 0 0 0 0 0 0 0 0 0
```

#### 混合

```R
y <- c(1.7, "a")  # "1.7" "a"
y <- c(TRUE, FALSE, 10)  # 1 0 5
y <- c("a", TRUE, FALSE)  # "a" "TRUE" "FALSE"
```

### 添加元素

```R
c(my_char, "Xwoder")
```

### paste

| 参数       | 含义                 | 例                                                           |
| ---------- | -------------------- | ------------------------------------------------------------ |
| `sep`      | 成组拼接时所用分隔符 | `paste(1:3, c("X", "Y", "Z"), sep="")` / `paste(LETTERS, 1:4, sep = "-")` |
| `collapse` | 顺序拼接时所用分隔符 | `paste(my_name, collapse=" ")`                               |
|            |                      |                                                              |



### 列表 / List

可以容纳不同的数据类型的容器

### 矩阵 / Matrix

创建

`matrix()`

```R
m <- matrix(nrow = 2, ncol = 3)

#      [,1] [,2] [,3]
# [1,]   NA   NA   NA
# [2,]   NA   NA   NA
```

```R
m <- matrix(1:6, nrow = 2, ncol = 3)

#      [,1] [,2] [,3]
# [1,]    1    3    5
# [2,]    2    4    6
```

`dim()`

还可以通过向量来创建矩阵

```R
m = 1:6
dim(m) <- c(2,3)
```

`cbind()`

```R
> a <- 1:6
> b <- 7:12

> cbind(a, b)
#      a  b
# [1,] 1  7
# [2,] 2  8
# [3,] 3  9
# [4,] 4 10
# [5,] 5 11
# [6,] 6 12
```

`rbind()`

```R
> a <- 1:6
> b <- 7:12

> rbind(a, b)
#   [,1] [,2] [,3] [,4] [,5] [,6]
# a    1    2    3    4    5    6
# b    7    8    9   10   11   12
```

#### 方法

##### `dim()`

返回矩阵的维

```R
dim(m)

# 2 3
```

#### 属性

```R
attributes(m)

# $dim
# [1] 2 3
```

## 类型转换

### 显式类型转换

```R
x <- 6
class(x)  # "numeric"

y = as.integer(x)
class(y)  # "integer"
```

```R
x <- 10:20
class(x)  # "integer"

y <- as.numeric(x)
class(y)  # "numeric"
```

```R
x <- c(-2, -1, 0, 1, 2)

y <- as.logical(x)
# TRUE  TRUE FALSE  TRUE  TRUE
```

## 对象属性 / attribute

## 因子 / factor

用于表示分类数据

可以是有序的，也可以是无序的

一个因子（`factor`）内又包含多个水平（`level`）

```R
> x <- factor(c("yes", "no", "yes", "yes", "yes", "no"))
# yes no  yes yes yes no 
# Levels: no yes

> table(x)
# x
#  no yes 
#   2   4 

> unclass(x)
# 2 1 2 2 2 1
# attr(,"levels")
# "no"  "yes"
```

通过 `levels` 参数指定记基水平

```R
x <- factor(c("yes", "no", "yes", "yes", "yes", "no"), levels = c("yes", "no"))

# yes no  yes yes yes no 
# Levels: yes no
```

## 缺失值 / missing value

用 `NA` 或 `NaN` 表示

`NA` 是有类型的，所以存在 数值型 `NA`，字符型 `NA` 等

`NaN` 是 `NA`，但是 `NA` 不是 `NaN`

```R
is.nan(NA)  # FALSE
is.na(NaN)  # TRUE
```

测试是否是 `NA`

```
is.na()
```

测试是否是 `NaN`

```
is.nan()
```

```R
x <- c(1, 2, NA, NaN)

> is.na(x)
# [1] FALSE FALSE  TRUE  TRUE

> is.nan(x)
# [1] FALSE FALSE FALSE  TRUE
```

## 命名

为向量中的元素命名

```R
x <- c(1, 2, 3)

names(x)  # NULL

names(x) <- c("foo", "bar", "norf")

names(x)
# [1] "foo"  "bar"  "norf"
```

为列表中的元素命名

```R
x <- list(a = 1, b = 2, c = 3)

x
# $a
# [1] 1
# 
# $b
# [1] 2
# 
# $c
# [1] 3
```

为矩阵命名

```R
> x = matrix(1:6, nrow = 2, ncol = 3)

#      [,1] [,2] [,3]
# [1,]    1    3    5
# [2,]    2    4    6

dimnames(x) <- list(c("r1", "r2"), c("c1", "c2", "c3"))

#    c1 c2 c3
# r1  1  3  5
# r2  2  4  6
```

##  输入输出

### 输入

#### `read`

##### `read.table()`

###### 参数

|       参数名       | 类型     | 含义               |              |
| :----------------: | -------- | ------------------ | ------------ |
|       `file`       | 字符串型 | 文件名             |              |
|      `header`      | 逻辑型   | 是否包含表头       |              |
|       `sep`        | 字符串型 | 分隔字符           | 默认值为空格 |
|    `colClasses`    | 向量型   | 列的数据类型       |              |
|       `nrow`       | 整型     | 行数               |              |
|       `skip`       | 整型     | 跳过的行数         |              |
| `stringsAsFactors` |          |                    |              |
|   `comment.char`   | 字符型   | 表明注释内容的字符 |              |

**读取大数据表**

```R
initial <- read.table("dataset.txt", nrows = 100)
classes <- sapply(initial, class)
tabAll <- read.table("dataset.txt", colClasses = classes)
```

##### `read.csv()`

与 `read.table()` 等价，除 `sep` 参数默认值为 `,`

`file()`

`gzfile()`

`url()`

## 帮助

### 问号 `?`

```R
?函数名
```

 例

```R
?c
```

### `args()`

查看函数所需的参数信息

```R
args(read.csv)
```

## 序列

| 符号/函数 | 含义               | 例                   |
| --------- | ------------------ | -------------------- |
| `:`       | 生成序列           | `1:10`               |
| `seq()`   | 生成序列           | `seq(1, 10)`         |
| `rep()`   | 生成重复元素的序列 | `rep(0, times = 10)` |

### seq()

| 参数         |  含义  | 例                         |
| ------------ | :----: | -------------------------- |
| `from`       | 起始值 | `seq(from = 1, 10)`        |
| `to`         |  终值  | `seq(1, to = 10)`          |
| `by`         |  步长  | `seq(1, 10, by = 2)`       |
| `length`     |  长度  | `seq(1, 10, length = 20)`  |
| `along.with` |        | `seq(along.with = my_seq)` |

`seq(along.with = my_seq)` 等价于 `seq_along(my_seq)`

### req()

该函数生成重复元素的函数，这些元素可能来自标量，也可以来自一个向量，如

```R
rep(0, times = 40)

# 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
```

```R
rep(c(0, 1, 2), times = 10)

#  0 1 2 0 1 2 0 1 2 0 1 2 0 1 2 0 1 2 0 1 2 0 1 2 0 1 2 0 1 2
```



|  参数   | 含义                   | 例                            |
| :-----: | ---------------------- | ----------------------------- |
| `times` | 依次重复的次数         | `rep(c(0, 1, 2), times = 10)` |
| `each`  | 单个元素连续重复的次数 | `rep(c(0, 1, 2), each = 10)`  |

