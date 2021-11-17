# R Language

[TOC]

## 数据类型

### 分类

#### string / 字符串

#### numerics

#### integers / 整型

#### logical / 逻辑值

逻辑值

可能的取值范围有：`TRUE` 或 `FALSE`

#### characters / 字符

#### vector / 向量

向量，即一维数组

```R
numeric_vector <- c(1, 2, 3)
character_vector <- c("a", "b", "c")
boolean_vector <- c(TRUE, FALSE, TRUE)
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
```

##### 命名

```R
days = c(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
names(days) = c("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
```

##### 访问

###### 通过索引访问

对于向量，第1个元素对应的下标为1；第2个元素对应的下标为2，以此类推。

访问单个元素

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

poker_wednesday <- poker_vector[3]
```

访问多个元素

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

roulette_selection_vector <- roulette_vector[c(2, 3, 4)]
```

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

roulette_selection_vector <- roulette_vector[2:5]
```

###### 通过向量元素名称访问

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

poker_vector["Monday"]
poker_vector[c("Monday", "Tuesday")]
poker_vector[c("Monday", "Tuesday", "Wednesday")]
```

###### 通过布尔向量访问

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

selection_vector <- poker_vector > 0
selection_vector

#   Monday Tuesday Wednesday Thursday Friday 
#     TRUE   FALSE      TRUE    FALSE   TRUE 

poker_winning_days <- poker_vector[selection_vector]
poker_winning_days

# Monday Wednesday Friday 
#    140        20    240 
```

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

selection_vector <- roulette_vector > 0
selection_vector

#   Monday   Tuesday Wednesday  Thursday    Friday 
#    FALSE     FALSE      TRUE     FALSE      TRUE 

roulette_winning_days <- roulette_vector[selection_vector]
roulette_winning_days

# Wednesday    Friday 
#      100        10 
```



##### 运算

###### 加

```R
A_vector <- c(1, 2, 3)
B_vector <- c(4, 5, 6)
total_vector <- A_vector + B_vector
```

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

total_daily <- poker_vector + roulette_vector
```

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

total_poker <- sum(poker_vector)
total_roulette <- sum(roulette_vector)
total_week <- sum(total_poker, total_roulette)
```

###### 均值

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

poker_start <- poker_vector[c("Monday", "Tuesday", "Wednesday")]

mean(poker_start)
```

###### 逻辑运算

```R
c(4, 5, 6) > 5
# FALSE FALSE TRUE
```

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

selection_vector <- poker_vector > 0
  
selection_vector

#   Monday Tuesday Wednesday Thursday Friday 
#     TRUE   FALSE      TRUE    FALSE   TRUE 
```

###### 合并

将多个向量依次合并为一个向量。

```R
a = c(1, 2, 3)
b = c(4, 5, 6)
c = c(7, 8, 9)

abc = c(a, b, c)

abc
# 1 2 3 4 5 6 7 8 9
```

#### matrix / 矩阵

矩阵只能存储同一种类型的数据，行数与列数固定，所以又称其为**二维矩阵**。

##### 创建

```R
matrix(1:9, byrow = TRUE, nrow = 3)

#      [,1] [,2] [,3]
# [1,]    1    2    3
# [2,]    4    5    6
# [3,]    7    8    9
```

上例中，`1:9` 是`c(1, 2, 3, 4, 5, 6, 7, 8, 9)` 的缩写。

`byrow` 参数指定矩阵的矩阵的元素是否按照行顺序依次填充。

`nrow` 参数指定矩阵的行数。

```R
matrix(c(1:6), byrow = TRUE, nrow = 2)
#      [,1] [,2] [,3]
# [1,]    1    2    3
# [2,]    4    5    6

matrix(c(1:6), byrow = TRUE, nrow = 3)
#      [,1] [,2]
# [1,]    1    2
# [2,]    3    4
# [3,]    5    6

matrix(c(1:6), byrow = FALSE, nrow = 2)
#      [,1] [,2] [,3]
# [1,]    1    3    5
# [2,]    2    4    6

matrix(c(1:6), byrow = FALSE, nrow = 3)
#      [,1] [,2]
# [1,]    1    4
# [2,]    2    5
# [3,]    3    6
```

```R
new_hope <- c(460.998, 314.4)
empire_strikes <- c(290.475, 247.900)
return_jedi <- c(309.306, 165.8)

box_office <- c(new_hope, empire_strikes, return_jedi)

star_wars_matrix <- matrix(box_office, nrow=3, byrow=TRUE)
```

##### 命名

为矩阵的行命名可使用 `rownames()` 函数

```R
rownames(my_matrix) <- row_names_vector
```

为矩阵的列命名可使用 `colnames()` 函数

```R
colnames(my_matrix) <- col_names_vector
```

示例

```R
new_hope <- c(460.998, 314.4)
empire_strikes <- c(290.475, 247.900)
return_jedi <- c(309.306, 165.8)

star_wars_matrix <- matrix(c(new_hope, empire_strikes, return_jedi), 
                           nrow = 3, 
                           byrow = TRUE)

region <- c("US", "non-US")
titles <- c("A New Hope", "The Empire Strikes Back", "Return of the Jedi")

# 为矩阵的列命名
colnames(star_wars_matrix) <- region

# 为矩阵的行命名
rownames(star_wars_matrix) <- titles

star_wars_matrix

#                              US non-US
# A New Hope              460.998  314.4
# The Empire Strikes Back 290.475  247.9
# Return of the Jedi      309.306  165.8
```

也可以在创建矩阵时，通过`matrix()` 函数的 `dimnames` 参数指定列名

```R
box_office <- c(460.998, 314.4, 290.475, 247.900, 309.306, 165.8)
region <- c("US", "non-US")
titles <- c("A New Hope", 
                 "The Empire Strikes Back", 
                 "Return of the Jedi")
               
star_wars_matrix <- matrix(box_office, 
                           nrow = 3, 
                           byrow = TRUE,
                           dimnames = list(titles, region))
```

##### 求和

###### 按行求和

`rowSums()` 函数可以对矩阵依次按照各行求和，并返回一个新的矩阵。 

```R
box_office <- c(460.998, 314.4, 290.475, 247.900, 309.306, 165.8)
region <- c("US", "non-US")
titles <- c("A New Hope", 
            "The Empire Strikes Back", 
            "Return of the Jedi")

star_wars_matrix <- matrix(box_office, 
                           nrow = 3, byrow = TRUE,
                           dimnames = list(titles, region))
star_wars_matrix
#                              US non-US
# A New Hope              460.998  314.4
# The Empire Strikes Back 290.475  247.9
# Return of the Jedi      309.306  165.8

worldwide_vector <- rowSums(star_wars_matrix)
worldwide_vector
# A New Hope  The Empire Strikes Back  Return of the Jedi 
#    775.398                  538.375             475.106
```

###### 按列求和

```R
all_wars_matrix
#                            US non-US
# A New Hope              461.0  314.4
# The Empire Strikes Back 290.5  247.9
# Return of the Jedi      309.3  165.8
# The Phantom Menace      474.5  552.5
# Attack of the Clones    310.7  338.7
# Revenge of the Sith     380.3  468.5

total_revenue_vector <- colSums(all_wars_matrix)
total_revenue_vector
#     US non-US 
# 2226.3 2087.8 
```

##### 增加列

```R
box_office <- c(460.998, 314.4, 290.475, 247.900, 309.306, 165.8)
region <- c("US", "non-US")
titles <- c("A New Hope", "The Empire Strikes Back", "Return of the Jedi")

star_wars_matrix <- matrix(box_office, 
                           nrow = 3, 
                           byrow = TRUE,
                           dimnames = list(titles, region))

worldwide_vector <- rowSums(star_wars_matrix)
worldwide_vector
# A New Hope  The Empire Strikes Back  Return of the Jedi 
#    775.398                  538.375             475.106 

all_wars_matrix <- cbind(star_wars_matrix, worldwide_vector)
all_wars_matrix
#                              US non-US worldwide_vector
# A New Hope              460.998  314.4          775.398
# The Empire Strikes Back 290.475  247.9          538.375
# Return of the Jedi      309.306  165.8          475.106
```

##### 增加行

```R
star_wars_matrix
#                            US non-US
# A New Hope              461.0  314.4
# The Empire Strikes Back 290.5  247.9
# Return of the Jedi      309.3  165.8

star_wars_matrix2
#                         US non-US
# The Phantom Menace   474.5  552.5
# Attack of the Clones 310.7  338.7
# Revenge of the Sith  380.3  468.5

all_wars_matrix <- rbind(star_wars_matrix, star_wars_matrix2)
all_wars_matrix
#                            US non-US
# A New Hope              461.0  314.4
# The Empire Strikes Back 290.5  247.9
# Return of the Jedi      309.3  165.8
# The Phantom Menace      474.5  552.5
# Attack of the Clones    310.7  338.7
# Revenge of the Sith     380.3  468.5
```

#### 访问

```R
all_wars_matrix
#                            US non-US
# A New Hope              461.0  314.4
# The Empire Strikes Back 290.5  247.9
# Return of the Jedi      309.3  165.8
# The Phantom Menace      474.5  552.5
# Attack of the Clones    310.7  338.7
# Revenge of the Sith     380.3  468.5

# 第1行，第2列
all_wars_matrix[1, 2]
# 314.4

# 第3行，第1列
all_wars_matrix[3, 1]
#  309.3

# 第3行
all_wars_matrix[3, ]
#     US non-US
#  309.3  165.8

# 第2列
all_wars_matrix[, 2]
# A New Hope  The Empire Strikes Back  Return of the Jedi The Phantom Menace  Attack of the Clones  Revenge of the Sith 
#      314.4                    247.9               165.8              552.5                 338.7                468.5

# 第3~4行，第1~2列
all_wars_matrix[3:4, 1:2]
#                       US non-US
# Return of the Jedi 309.3  165.8
# The Phantom Menace 474.5  552.5
```

##### 算数运算

###### 标量除法

```R
all_wars_matrix
#                            US non-US
# A New Hope              461.0  314.4
# The Empire Strikes Back 290.5  247.9
# Return of the Jedi      309.3  165.8
# The Phantom Menace      474.5  552.5
# Attack of the Clones    310.7  338.7
# Revenge of the Sith     380.3  468.5

all_wars_matrix / 5
#                            US non-US
# A New Hope              92.20  62.88
# The Empire Strikes Back 58.10  49.58
# Return of the Jedi      61.86  33.16
# The Phantom Menace      94.90 110.50
# Attack of the Clones    62.14  67.74
# Revenge of the Sith     76.06  93.70
```

###### 矩阵除法

```R
all_wars_matrix
#                            US non-US
# A New Hope              461.0  314.4
# The Empire Strikes Back 290.5  247.9
# Return of the Jedi      309.3  165.8
# The Phantom Menace      474.5  552.5
# Attack of the Clones    310.7  338.7
# Revenge of the Sith     380.3  468.5

ticket_prices_matrix
#                          US non-US
# A New Hope              5.0    5.0
# The Empire Strikes Back 6.0    6.0
# Return of the Jedi      7.0    7.0
# The Phantom Menace      4.0    4.0
# Attack of the Clones    4.5    4.5
# Revenge of the Sith     4.9    4.9

visitors <- all_wars_matrix / ticket_prices_matrix
visitors
#                                US    non-US
# A New Hope               92.20000  62.88000
# The Empire Strikes Back  48.41667  41.31667
# Return of the Jedi       44.18571  23.68571
# The Phantom Menace      118.62500 138.12500
# Attack of the Clones     69.04444  75.26667
# Revenge of the Sith      77.61224  95.61224
```

### 类型查看

`class()` 函数用于返回数据的类型

```R
my_logical <- FALSE 

class(42)
class(my_logical)
```

## 数学运算法

| 运算符 | 含义 |   示例    | 示例结果 |
| :----: | :--: | :-------: | :------: |
|  `+`   |  加  |  `2 + 3`  |   `5`    |
|  `-`   |  减  |  `2 - 3`  |   `-1`   |
|  `*`   |  乘  |  `2 * 3`  |   `6`    |
|  `/`   |  除  |  `6 / 2`  |   `3`    |
|  `^`   |  幂  |   `2^3`   |   `8`    |
|  `%%`  |  模  | `12 %% 5` |   `2`    |

## 逻辑运算

| 运算符 |    含义    |   示例   | 示例结果 |
| :----: | :--------: | :------: | :------: |
|  `>`   |    大于    | `2 > 1`  |  `TRUE`  |
|  `>=`  | 大于或等于 | `2 >= 2` |  `TRUE`  |
|  `<`   |    小于    | `2 < 1`  | `FALSE`  |
|  `<=`  | 小于或等于 | `2 <= 2` | `FALSE`  |
|  `==`  | 等于 | `2 == 2` | `TRUE`  |
|  `!=`  | 不等于 | `2 != 2` | `FALSE`  |

## 变量

### 赋值

```R
age <- 18
my_apples <- 5
```

### 运算

```R
my_apples <- 5
my_oranges <- 6

my_fruit <- my_apples + my_oranges
```

