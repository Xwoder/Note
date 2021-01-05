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

