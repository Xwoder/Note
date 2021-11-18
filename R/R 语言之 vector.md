# R 语言之 vector

[TOC]

向量，即一维数组，只能存储类型相同的元素。

```R
numeric_vector <- c(1, 2, 3)
character_vector <- c("a", "b", "c")
boolean_vector <- c(TRUE, FALSE, TRUE)
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
```

## 命名

```R
days = c(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
names(days) = c("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")
```

## 访问

### 通过索引值访问

对于向量，第1个元素对应的下标为1；第2个元素对应的下标为2，以此类推。

#### 访问单个元素

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

poker_wednesday <- poker_vector[3]
```

#### 访问多个元素

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

### 通过向量元素名称访问

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

### 通过布尔向量访问

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

## 运算

### 算数运算

#### 加

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

### 统计运算

#### 均值

```R
poker_vector <- c(140, -50, 20, -120, 240)
roulette_vector <- c(-24, -50, 100, -350, 10)
days_vector <- c("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

names(poker_vector) <- days_vector
names(roulette_vector) <- days_vector

poker_start <- poker_vector[c("Monday", "Tuesday", "Wednesday")]

mean(poker_start)
# 36.66667
```

### 关系运算

```R
c(4, 5, 6) > 5
# FALSE FALSE TRUE
```

```R
c(4, 5, 6) > c(3, 5, 7)
# TRUE FALSE FALSE

c(4, 5, 6) <= c(5, 4, 3)
# TRUE FALSE FALSE
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

### 合并

将多个向量依次合并为一个向量。

```R
a = c(1, 2, 3)
b = c(4, 5, 6)
c = c(7, 8, 9)

d = c(a, b, c)

d
# 1 2 3 4 5 6 7 8 9
```

## 排序

```R
a <- c(100, 10, 1000)

order(a)
# 2 1 3

a[order(a)]
# 10  100 1000
```

