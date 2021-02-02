# pandas

[TOC]

`pandas` 是 `Python data analysis` 的缩写。

## 导入

```python
import pandas as pd
```

## 设置

```python
pd.set_option('display.expand_frame_repr', False)
pd.set_option('display.max_rows', 10)
pd.set_option('display.max_columns', 6)
pd.set_option('precision', 2)
```

## 数据类型

### DataFrame

```python
type(df)
```

将得到

```python
pandas.core.frame.DataFrame
```

#### 属性和方法

#### shape 属性

获取 `DataFrame` 对象的维度信息

```python
df.shape
```

可得到

```python
(999, 7)
```

其表示 df 是一个拥有 997 行 7 列的 `DataFrame` 对象

#### columns 属性

获取 DataFrame 对象的列的名称信息

```python
df.columns
```

可得到

```python
Index(['Unnamed: 0', 'close', 'open', 'high', 'low', 'volume', 'pct_change'], dtype='object')
```

#### dtypes 属性

```python
df.dtypes
```

可得到

```python
Unnamed: 0     object
close         float64
open          float64
high          float64
low           float64
volume          int64
pct_change    float64
dtype: object
```

#### info() 方法

```python
df.info()
```

可得到

```python
<class 'pandas.core.frame.DataFrame'>
RangeIndex: 999 entries, 0 to 998
Data columns (total 7 columns):
 #   Column      Non-Null Count  Dtype  
---  ------      --------------  -----  
 0   Unnamed: 0  999 non-null    object 
 1   close       999 non-null    float64
 2   open        999 non-null    float64
 3   high        999 non-null    float64
 4   low         999 non-null    float64
 5   volume      999 non-null    int64  
 6   pct_change  999 non-null    float64
dtypes: float64(5), int64(1), object(1)
memory usage: 54.8+ KB
```

## 读取

### read_csv

读取 `csv` 文件

```python
df = pd.read_csv('goods.csv', sep=',')
```

```python
df = pd.read_csv('goods.tsv', sep='\t')
```

查看

```python
# 前 5 条数据，等价于 df.head(5)
df.head()

# 前 10 条数据
df.head(10)
```

