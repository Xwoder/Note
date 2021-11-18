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

