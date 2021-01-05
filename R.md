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

