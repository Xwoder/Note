# pandas

[TOC]

`pandas` 是 `Python data analysis` 的缩写。

## 导入

```python
import pandas as pd
```

## 读取

### Excel 文件

```python
df = pd.read_excel("文件名")
```

```python
xls = pd.ExcelFile("文件名")
df = xls.parse("工作表名")
```

### CSV 文件

```python
df = pd.read_csv("文件名")
```

