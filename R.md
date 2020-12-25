# R

[TOC]

## 包 / packages

### 安装

```R
install.package("包名")
```

例

```R
install.package("ggplot2")
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

