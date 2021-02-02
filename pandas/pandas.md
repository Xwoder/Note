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

#### 常用属性和方法

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

### read_excel

## 访问

### head()

```python
# 前 5 条数据，等价于 df.head(5)
df.head()

# 前 10 条数据
df.head(10)
```

### tail()

```python
# 后 5 条数据，等价于 df.tail(5)
df.tail()

# 后 10 条数据
df.tail(10)
```

### 列名访问

以下方式的获取得到的数据类型是 `Series`

```python
df['列名1']
```

以下方式的获取得到的数据类型是 `DataFrame`

```python
df[['列名']]

df[['列名1', '列名2']]
```

### 行标签访问

通过 `.loc` 方法实现，其返回的的数据类型是 `DataFrame`

#### 获取单行数据

```python
# 获取第 0 行数据
df.loc['2019-1-1']
```

可得到

```
close              9.39000
open              11.62000
high              11.62000
low                9.39000
volume        194447.00000
pct_change         0.19191
Name: 2010-01-02, dtype: float64
```

#### 获取多行数据

```python
# 获取第 0 行数据
df.loc[['2010-01-02', '2010-01-02', '2010-01-04']]
```

可得到

```
            close   open   high   low  volume  pct_change
2010-01-02   9.39  11.62  11.62  9.39  194447    0.191910
2010-01-02   9.39  11.62  11.62  9.39  194447    0.191910
2010-01-04   8.93   9.47   9.47  8.93  156091    0.057022
```

#### 获取子数据

##### 全部行全部列

```python
df.loc[:, :]
```

##### 指定行

```python
df.loc[['2010-01-02', '2010-01-06'], :]
```

可得到

```
            close   open   high   low  volume  pct_change
2010-01-02   9.39  11.62  11.62  9.39  194447    0.191910
2010-01-06   7.70  10.87  10.87  7.70  178237    0.291628
```

##### 指定列

```python
# 所有行
# open 和 close 列
df.loc[:, ['open', 'close']]
```

可得到

```
             open  close
2010-01-02  11.62   9.39
2010-01-03   9.39   9.47
              ...    ...
2012-09-25   9.93  10.35
2012-09-26  10.35   9.81
[999 rows x 2 columns]
```

##### 指定行指定列

```python
# 2010-01-03 和 2010-01-04 行
# open 和 close 列
df.loc[['2010-01-03', '2010-01-04'], ['open', 'close']]
```

可得到

```
            open  close
2010-01-03  9.39   9.47
2010-01-04  9.47   8.93
```

##### 范围行

```python
df.loc['2010-01-02':'2010-01-06', :]
```

##### 范围列

```python
df.loc[:, 'close':'low']
```



### 行索引访问

通过 `.iloc` 方法实现，其返回的的数据类型是 `DataFrame`

#### 获取单行数据

```python
# 获取第 0 行数据
df.iloc[0]
```

可得到

```
close              9.39000
open              11.62000
high              11.62000
low                9.39000
volume        194447.00000
pct_change         0.19191
Name: 2010-01-02, dtype: float64
```

获取最后一行数据

```python
df.iloc[df.shape[0] - 1]
# 或
df.iloc[-1]
```

#### 获取多行数据

```python
print(df.iloc[[0, 1, 2]])
```

可得到

```
            close   open   high   low  volume  pct_change
2010-01-02   9.39  11.62  11.62  9.39  194447    0.191910
2010-01-03   9.47   9.39   9.47  9.39  166905    0.008520
2010-01-04   8.93   9.47   9.47  8.93  156091    0.057022
```

#### 获取子数据

##### 指定行指定列

```python
# 第 1 和第 2 行
# 第 0 和第 1 列
df.iloc[[1, 2], [0, 1]]
```

可得到

```
            close  open
2010-01-03   9.47  9.39
2010-01-04   8.93  9.47
```

##### 指定行

```python
df.iloc[[2, 3], :]
```

可得到

```
            close  open   high   low  volume  pct_change
2010-01-04   8.93  9.47   9.47  8.93  156091    0.057022
2010-01-05  10.87  8.93  10.87  8.93  176409    0.217245
```

##### 指定列

```python
df.iloc[:, [0, 1]]
```

可得到

```
            close   open
2010-01-02   9.39  11.62
2010-01-03   9.47   9.39
              ...    ...
2012-09-25  10.35   9.93
2012-09-26   9.81  10.35
[999 rows x 2 columns]
```

##### 范围列

```python
df.iloc[:, 0:3]
```

可得到

```
            close   open   high
2010-01-02   9.39  11.62  11.62
2010-01-03   9.47   9.39   9.47
              ...    ...    ...
2012-09-25  10.35   9.93  10.35
2012-09-26   9.81  10.35  10.35
[999 rows x 3 columns]
```

##### 范围行

```python
df.iloc[0:5, :]
```

可得到

```
            close   open   high   low  volume  pct_change
2010-01-02   9.39  11.62  11.62  9.39  194447    0.191910
2010-01-03   9.47   9.39   9.47  9.39  166905    0.008520
2010-01-04   8.93   9.47   9.47  8.93  156091    0.057022
2010-01-05  10.87   8.93  10.87  8.93  176409    0.217245
2010-01-06   7.70  10.87  10.87  7.70  178237    0.291628
```

##### 全部行全部列

```python
df.iloc[:, :]
```

