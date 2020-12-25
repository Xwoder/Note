# R

[TOC]

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



