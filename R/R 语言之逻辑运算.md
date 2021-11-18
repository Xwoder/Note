# R 语言之逻辑运算

[TOC]

## AND

```R
TRUE == TRUE
# TRUE

TRUE == FALSE
# FALSE

FALSE == FALSE
# TRUE

FALSE == TRUE
# FALSE
```

```R
x <- 12
x > 5 & x < 15
# TRUE
```

```R
x <- 17
x > 5 & x < 15
# FALSE
```

## OR

```R
TRUE | TRUE
# TRUE

TRUE | FALSE
# TRUE

FALSE | FALSE
# FALSE

FALSE | TRUE
# TRUE
```

```R
y <- 4
y < 5 | y > 15
# TRUE
```

## NOT

```R
!TRUE
# FALSE
```

```R
!FALSE
# TRUE
```

```R
x <- 12
!(x < 5)
# TRUE
```

