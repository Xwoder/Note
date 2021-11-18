# R 语言之关系运算

[TOC]

## 符号

```R
TRUE == TRUE
# TRUE
```

```R
FALSE == FALSE
# TRUE
```

```R
TRUE == FALSE
# FALSE
```

```R
"Hello" == "Goodbye"
# FALSE
```

```R
"Hello" != "Goodbye"
# TRUE
```

```R
3 == 2
# FALSE
```

```R
3 != 2
# TRUE
```

```R
3 < 5
# TRUE
```

```R
3 > 5
# FALSE
```

```R
"Hello" > "Goodbye"
# TRUE
```

```R
TRUE > FALSE
# TRUE
```

```R
5 >= 5
# TRUE
```

```R
5 >= 3
# TRUE
```

```R
"Hello" <= "Goodbye"
# FALSE
```

```R
TRUE <= FALSE
# FALSE
```

```R
-6 * 5 + 2 >= -10 + 1
# FALSE
```

```R
"raining" <= "raining dogs"
# TRUE
```

```R
TRUE > FALSE
# TRUE
```

```R
linkedin <- c(16, 9, 13, 5, 2, 17, 14)
last <- tail(linkedin, 1)
last
# 14

last < 5 | last > 10
# TRUE

last > 15 & last >= 20
# FALSE
```

## 与容器类型结合使用

### 与 vector 结合使用

```R
linkedin <- c(16, 9, 13, 5, 2, 17, 14)
facebook <- c(17, 7, 5, 16, 8, 13, 14)

linkedin > 15
# TRUE FALSE FALSE FALSE FALSE TRUE FALSE

linkedin <= 5
# FALSE FALSE FALSE TRUE TRUE FALSE FALSE

linkedin > facebook
# FALSE TRUE TRUE FALSE FALSE TRUE FALSE
```

### 与 matrix 结合使用

```R
linkedin <- c(16, 9, 13, 5, 2, 17, 14)
facebook <- c(17, 7, 5, 16, 8, 13, 14)
views <- matrix(c(linkedin, facebook), nrow = 2, byrow = TRUE)

views == 13
#       [,1]  [,2]  [,3]  [,4]  [,5]  [,6]  [,7]
# [1,] FALSE FALSE  TRUE FALSE FALSE FALSE FALSE
# [2,] FALSE FALSE FALSE FALSE FALSE  TRUE FALSE

views <= 14
#       [,1] [,2] [,3]  [,4] [,5]  [,6] [,7]
# [1,] FALSE TRUE TRUE  TRUE TRUE FALSE TRUE
# [2,] FALSE TRUE TRUE FALSE TRUE  TRUE TRUE
```

```R
linkedin
# 16  9 13  5  2 17 14

facebook
# 17  7  5 16  8 13 14

views
#      [,1] [,2] [,3] [,4] [,5] [,6] [,7]
# [1,]   16    9   13    5    2   17   14
# [2,]   17    7    5   16    8   13   14

linkedin > 10 & facebook < 10
# FALSE FALSE TRUE FALSE FALSE FALSE FALSE

linkedin >= 12 | facebook >= 12
# TRUE FALSE TRUE TRUE FALSE TRUE  TRUE

views > 11 & views <= 14
#       [,1]  [,2]  [,3]  [,4]  [,5]  [,6] [,7]
# [1,] FALSE FALSE  TRUE FALSE FALSE FALSE TRUE
# [2,] FALSE FALSE FALSE FALSE FALSE  TRUE TRUE
```

