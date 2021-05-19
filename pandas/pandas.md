# pandas

[TOC]

`pandas` 是 `Python data analysis` 的缩写。其基于 `numpy`。

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

#### 创建

##### 由 Series 创建

```python
s_name = pd.Series(['Jack', 'Rose'])
s_age = pd.Series([25, 26])
s_job = pd.Series(['Driver', 'Programmer'])

df_person = pd.DataFrame({
    'name': s_name,
    'age': s_age,
    'job': s_job
})
```

将得到

```
   name  age         job
0  Jack   25      Driver
1  Rose   26  Programmer
```

可通过 columns 参数指定列的顺序

```python
s_name = pd.Series(['Jack', 'Rose'])
s_age = pd.Series([25, 26])
s_job = pd.Series(['Driver', 'Programmer'])

df_person = pd.DataFrame({
    'name': s_name,
    'age': s_age,
    'job': s_job
}, columns=['name', 'job', 'age'])
```

将得到

```
   name         job  age
0  Jack      Driver   25
1  Rose  Programmer   26
```

可通过 index 参数指定行索引

##### 由文件获取

###### read_csv

见 read_csv 节

#### 属性

##### shape 属性

获取 `DataFrame` 对象的维度信息

```python
df.shape
```

可得到

```python
(999, 7)
```

其表示 df 是一个拥有 997 行 7 列的 `DataFrame` 对象

##### columns 属性

获取 DataFrame 对象的列的名称信息

```python
df.columns
```

可得到

```python
Index(['Unnamed: 0', 'close', 'open', 'high', 'low', 'volume', 'pct_change'], dtype='object')
```

##### dtypes 属性

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

#### 方法

##### info() 方法

```python
df.info()
```

可得到

```
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

##### set_index()

指定索引

```python
df.set_index('user_id')
```

##### reset_index()

重置索引

##### rename()

重命名列名或行索引名

```python
index_rename = {'2019-01-01': '2019/01/01'}
column_rename = {'name': 'Name', 'age': 'Age'}
df.rename(index=index_rename, columns=column_rename)
```

##### describe()

显示描述信息

```python
df.describe()

df.describe(include=[np.object, pd.Categorical])
```

`include` 参数用于指定需要展示描述信息的列

##### sort_values()

对单个列进行升序排序

```python
df.sort_values(['title_year'])
```

对单个列进行降序排序

```python
df.sort_values(['title_year'],  ascending=False)
```

对多个列进行降序排序

```python
df.sort_values(['title_year', 'imdb_score'], ascending=False)
```

对多个列进行排序的同时，指定各列的升降序情况

```python
df.sort_values(['title_year', 'imdb_score'], ascending=[False, True])
```

##### drop_duplicates()

移除各列上的值完全一样的行

```python
df.drop_duplicates()
```

依据指定列上的值，移除重复的行

```python
df.drop_duplicates(subset=['title_year'])

df.drop_duplicates(subset=['title_year', 'director_name'])
```

##### concat()

拼接数据

###### 拼接多个 DataFrame

现有以下 `DataFrame` 对象

df1:

```
    A   B   C   D
0  a0  b0  c0  d0
1  a1  b1  c1  d1
2  a2  b2  c2  d2
3  a3  b3  c3  d3
```

df2:

```
    A   B   C   D
0  a4  b4  c4  d4
1  a5  b5  c5  d5
2  a6  b6  c6  d6
3  a7  b7  c7  d7
```

df3:

```
     A    B    C    D
0   a8   b8   c8   d8
1   a9   b9   c9   d9
2  a10  b10  c10  d10
3  a11  b11  c11  d11
```

合并上述 `DataFrame` 对象

```python
pd.concat([df1, df2, df3])
```

可得到

```
     A    B    C    D
0   a0   b0   c0   d0
1   a1   b1   c1   d1
2   a2   b2   c2   d2
3   a3   b3   c3   d3
0   a4   b4   c4   d4
1   a5   b5   c5   d5
2   a6   b6   c6   d6
3   a7   b7   c7   d7
0   a8   b8   c8   d8
1   a9   b9   c9   d9
2  a10  b10  c10  d10
3  a11  b11  c11  d11
```

由此可知，连接多个 `DataFrame` 对象时，默认是在行方向上连接

###### 拼接 DataFrame 和 Series

现有 Series 对象如下

```python
n_series = pd.Series(['n1', 'n2', 'n3', 'n4'])
```

即

```
0    n1
1    n2
2    n3
3    n4
dtype: object
```

将其与 `df1` 对象拼接

```python
pd.concat([df1, n_series])
```

可得到

```
     A    B    C    D    0
0   a0   b0   c0   d0  NaN
1   a1   b1   c1   d1  NaN
2   a2   b2   c2   d2  NaN
3   a3   b3   c3   d3  NaN
0  NaN  NaN  NaN  NaN   n1
1  NaN  NaN  NaN  NaN   n2
2  NaN  NaN  NaN  NaN   n3
3  NaN  NaN  NaN  NaN   n4
```

参数 

##### append()

拼接单个 `DataFrame` 对象至尾部

```python
df.append(df)
```

还可以拼接字典

```python
df1.append({'A':'n1', 'B':'n2', 'C':'n3', 'D':'n4', 'E':'n5'}, ignore_index=True)
```

#### 操作

##### 添加列

###### 方括号

```python
df['列名'] = 数据
```

例

```python
df['rank'] = 0
```

```python
df['total_score'] = df['chinese'] + df['english'] + df['math']
```

###### insert

`loc` 参数用于指定插入位置

```python
df.insert(loc=0, column='profit', value=df['revenue'] - df['cost'])
```

##### drop()

```python
df.drop('age', axis='columns', inplace=True)
```

`inplace` 参数用于指定是否原地操作

##### dropna()

```python
df.dropna(subset=['age'], how='any', inplace=True)
```

subset 参数用于指定删除空值所依据的列

how 参数用于指定删除方式

inplace 参数指定是否原地操作

##### fillna()

```python
df.fillna(0, inplace=True)

df['Age'].fillna(df['Age'].mean(), inplace=True)
```

`method` 参数用于指明填充方式，常见的取值有：`ffill`、`bfill`

### Series

#### 创建

例1

```python
pd.Series(['banana', 42])
```

将得到

```
0    banana
1        42
dtype: object
```

例2

```
pd.Series(['Wes McKinney', 'Male'], index=['Name', 'Gender'])
```

将得到

```
Name      Wes McKinney
Gender            Male
dtype: object
```

#### 获取

对于 `DataFrame` 对象，其每一行就都可以视作一个 `Series` 对象

```python
# pandas.core.series.Series
type(df.iloc[0])
```



#### 属性

##### loc

```
df.iloc[0].loc['year']
```

得到

```
2017
```

##### iloc

```
df.iloc[0].iloc[0]
```

得到

```
2017
```

##### dtype

```
df.iloc[0].dtype
```

可得到

```
dtype('O')
```

##### dtypes

同 `dtype`  属性

##### values

```
df.iloc[0].values
```

得到

```
array([2017, 'physics', nan, 'Rainer', 'Weiss',
       '"for decisive contributions to the LIGO detector and the observation of gravitational waves"',
       2], dtype=object)
```



##### index

```python
df.iloc[0].index
```

将得到

```python
Index(['year', 'category', 'overallMotivation', 'firstname', 'surname', 'motivation', 'share'],
      dtype='object')
```

##### T

```
df.iloc[0].T
```



##### size

```
first_row.size
```

##### shape

```
df.iloc[0].shape
```

得到

```
(7,)
```

#### 方法

##### keys()

```
df.iloc[0].keys()
```

可得到

```python
Index(['year', 'category', 'overallMotivation', 'firstname', 'surname', 'motivation', 'share'],
      dtype='object')
```

##### count()

非空值计数

```
pd.Series([1, 2, pd.NA, 4, 5, 6, 7, 8, pd.NA]).count()
```

将得到

```
7
```

##### value_counts()

对于如下 `Series` 类型数据

```
pd.Series([1, 2, pd.NA, 4, 5, 6, 7, 8, 8, pd.NA])
```

调用 `value_counts()` 方法得到

```
8    2
1    1
2    1
4    1
5    1
6    1
7    1
dtype: int64
```

结果中不显示空值的相关信息

##### describe()

例1

```
pd.Series([1, 2, 4, 5, 6, 7, 8, 8], dtype=int).describe()
```

可得到

```
count    8.000000
mean     5.125000
std      2.642374
min      1.000000
25%      3.500000
50%      5.500000
75%      7.250000
max      8.000000
dtype: float64
```

例2

```
pd.Series(['Jack', 'Rose', 'Tom', 'Jack', pd.NA], dtype=str).describe()
```

可得到

```
count        4
unique       3
top       Jack
freq         2
dtype: object
```

##### 其他

| 函数名 | 作用   |
| :----: | ------ |
| `min`  | 最小值 |
| `max`  | 最大值 |
| `std`  | 标准差 |
| `mean` | 均值   |

### Categorical

通过 `pd.Categorical()` 方法创建数据，例

```python
pd.Categorical(['a', 'b', 'c', 'd'], categories=['A', 'B', 'C'])
```

还可以通过 `ordered` 参数指明是否有序

```python
pd.Categorical(['a', 'b', 'c', 'd'], categories=['A', 'B', 'C'], ordered=True)
```

### 类型转换

#### astype()

#### to_numberic()

### 遍历操作

#### apply()

对于给定 `DataFrame` 对象

```python
df = pd.DataFrame(
    {
        'A': [1, 2, 3],
        'B': [4, 5, 6]
    }
)
```

以如下方式调用 `apply` 方法

```python
df.apply(lambda x: x ** 2)
```

将得到

```
   A   B
0  1  16
1  4  25
2  9  36
```

可以通过指定 `axis` 参数的值，指定轴向

```
df.apply(lambda x: np.sum(x), axis=0)
```

将得到

```
A     6
B    15
dtype: int64
```



#### transform()



## 文件操作

### CSV

#### read_csv()

读取 `csv` 文件

```python
df = pd.read_csv('goods.csv', sep=',')
```

```python
df = pd.read_csv('goods.tsv', sep='\t')
```

##### 参数

###### index_col

指定索引列

```python
pd.read_csv('goods.csv', index_col='id')
```

###### sep

分隔符

```python
pd.read_csv('goods.csv', sep=',')
```

#### to_csv()

保存至 CSV 文件

```python
pd.to_csv('goods.csv', index_col='id')
```

##### 参数

###### index

是否保存索引

### EXCEL

#### read_excel()

读取

### PICKLE

文件扩展名可以是：`.p`、`.pkl`、`.pickle`

#### read_pickle()

读取

```python
df.read_pickle('文件路径')
```

#### to_pickle()

保存

```python
df.to_pickle('文件路径')
```

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

## 聚合

```python
df.groupby('year')['lifeExp'].mean()

df.groupby('year')

df.groupby('year')['lifeExp']
df.groupby(['year', 'continent'])[['lifeExp', 'gdpPercap']].mean()
```

## 复合索引

未完成

## 分组频数

```python
df.groupby('continent')['country'].nunique()
```

w