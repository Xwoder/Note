# tibble

 部分例子中使用到的数据来自内置数据集 `diamonds` 

## 描述

### str

```R
str(diamonds)
#  tibble [53,940 × 10] (S3: tbl_df/tbl/data.frame)
#   $ carat  : num [1:53940] 0.23 0.21 0.23 0.29 0.31 0.24 0.24 0.26 0.22 0.23 ...
#   $ cut    : Ord.factor w/ 5 levels "Fair"<"Good"<..: 5 4 2 4 2 3 3 3 1 3 ...
#   $ color  : Ord.factor w/ 7 levels "D"<"E"<"F"<"G"<..: 2 2 2 6 7 7 6 5 2 5 ...
#   $ clarity: Ord.factor w/ 8 levels "I1"<"SI2"<"SI1"<..: 2 3 5 4 2 6 7 3 4 5 ...
#   $ depth  : num [1:53940] 61.5 59.8 56.9 62.4 63.3 62.8 62.3 61.9 65.1 59.4 ...
#   $ table  : num [1:53940] 55 61 65 58 58 57 57 55 61 61 ...
#   $ price  : int [1:53940] 326 326 327 334 335 336 336 337 337 338 ...
#   $ x      : num [1:53940] 3.95 3.89 4.05 4.2 4.34 3.94 3.95 4.07 3.87 4 ...
#   $ y      : num [1:53940] 3.98 3.84 4.07 4.23 4.35 3.96 3.98 4.11 3.78 4.05 ...
#   $ z      : num [1:53940] 2.43 2.31 2.31 2.63 2.75 2.48 2.47 2.53 2.49 2.39 ...
```

### summary

```R
summary(diamonds)

#     carat               cut        color        clarity          depth      
# Min.   :0.2000   Fair     : 1610   D: 6775   SI1    :13065   Min.   :43.00  
# 1st Qu.:0.4000   Good     : 4906   E: 9797   VS2    :12258   1st Qu.:61.00  
# Median :0.7000   Very Good:12082   F: 9542   SI2    : 9194   Median :61.80  
# Mean   :0.7979   Premium  :13791   G:11292   VS1    : 8171   Mean   :61.75  
# 3rd Qu.:1.0400   Ideal    :21551   H: 8304   VVS2   : 5066   3rd Qu.:62.50  
# Max.   :5.0100                     I: 5422   VVS1   : 3655   Max.   :79.00  
#                                    J: 2808   (Other): 2531                  
#     table           price             x                y                z         
# Min.   :43.00   Min.   :  326   Min.   : 0.000   Min.   : 0.000   Min.   : 0.000  
# 1st Qu.:56.00   1st Qu.:  950   1st Qu.: 4.710   1st Qu.: 4.720   1st Qu.: 2.910  
# Median :57.00   Median : 2401   Median : 5.700   Median : 5.710   Median : 3.530  
# Mean   :57.46   Mean   : 3933   Mean   : 5.731   Mean   : 5.735   Mean   : 3.539  
# 3rd Qu.:59.00   3rd Qu.: 5324   3rd Qu.: 6.540   3rd Qu.: 6.540   3rd Qu.: 4.040  
# Max.   :95.00   Max.   :18823   Max.   :10.740   Max.   :58.900   Max.   :31.800                                                              
```

### summarise

```R
summarise(diamonds, avg_price = mean(price))

# A tibble: 1 × 1
#   avg_price
#       <dbl>
# 1     3933.
```

## 过滤

### filter

```R
filter(diamonds, cut == "Ideal" & carat >= 3)
# A tibble: 4 × 10
#   carat cut   color clarity depth table price     x     y     z
#   <dbl> <ord> <ord> <ord>   <dbl> <dbl> <int> <dbl> <dbl> <dbl>
# 1  3.22 Ideal I     I1       62.6    55 12545  9.49  9.42  5.92
# 2  3.5  Ideal H     I1       62.8    57 12587  9.65  9.59  6.03
# 3  3.01 Ideal J     SI2      61.7    58 16037  9.25  9.2   5.69
# 4  3.01 Ideal J     I1       65.4    60 16538  8.99  8.93  5.86
```

### slice

查看前行

```R
slice(diamonds, 1:10)
# A tibble: 10 × 10
#    carat cut       color clarity depth table price     x     y     z
#    <dbl> <ord>     <ord> <ord>   <dbl> <dbl> <int> <dbl> <dbl> <dbl>
#  1  0.23 Ideal     E     SI2      61.5    55   326  3.95  3.98  2.43
#  2  0.21 Premium   E     SI1      59.8    61   326  3.89  3.84  2.31
#  3  0.23 Good      E     VS1      56.9    65   327  4.05  4.07  2.31
#  4  0.29 Premium   I     VS2      62.4    58   334  4.2   4.23  2.63
#  5  0.31 Good      J     SI2      63.3    58   335  4.34  4.35  2.75
#  6  0.24 Very Good J     VVS2     62.8    57   336  3.94  3.96  2.48
#  7  0.24 Very Good I     VVS1     62.3    57   336  3.95  3.98  2.47
#  8  0.26 Very Good H     SI1      61.9    55   337  4.07  4.11  2.53
#  9  0.22 Fair      E     VS2      65.1    61   337  3.87  3.78  2.49
# 10  0.23 Very Good H     VS1      59.4    61   338  4     4.05  2.39
```

查看后10行

```R
slice(diamonds, (n()-9):n())
# A tibble: 10 × 10
#     carat cut       color clarity depth table price     x     y     z
#     <dbl> <ord>     <ord> <ord>   <dbl> <dbl> <int> <dbl> <dbl> <dbl>
#   1  0.71 Premium   E     SI1      60.5    55  2756  5.79  5.74  3.49
#   2  0.71 Premium   F     SI1      59.8    62  2756  5.74  5.73  3.43
#   3  0.7  Very Good E     VS2      60.5    59  2757  5.71  5.76  3.47
#   4  0.7  Very Good E     VS2      61.2    59  2757  5.69  5.72  3.49
#   5  0.72 Premium   D     SI1      62.7    59  2757  5.69  5.73  3.58
#   6  0.72 Ideal     D     SI1      60.8    57  2757  5.75  5.76  3.5 
#   7  0.72 Good      D     SI1      63.1    55  2757  5.69  5.75  3.61
#   8  0.7  Very Good D     SI1      62.8    60  2757  5.66  5.68  3.56
#   9  0.86 Premium   H     SI2      61      58  2757  6.15  6.12  3.74
#  10  0.75 Ideal     D     SI2      62.2    55  2757  5.83  5.87  3.64
```

### select

过滤变量

```R
select(diamonds, carat, price)
select(diamonds, -x, -y, -z)
```

## 修改

### mutate

增加列

```R
mutate(diamonds, 
	volume = x * y * z, 
	price_per_carat = price / carat, 
	price_per_volume = price / volume)
```

## 排序

### arange

```R
# 按照价格升序排列
arrange(diamonds, price)

# 按照克拉数和价格升序排列
arrange(diamonds, carat, price)

# 按照克拉数升序和价格降序排列
arrange(diamonds, carat, desc(price))
```