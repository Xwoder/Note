# R 语言之 function

```R
my_add <- function(x, y) {
  x + y
}

my_add(3, 5)
# 8
```

```R
my_power <- function(x, n) {
  x^n
}
 
my_power(10, 2)
# 100

my_power(10, 3)
# 1000
```

## 默认值参数

```R
my_power <- function(x, n = 2) {
  x^n
}

my_power(10)
# 100

my_power(10, 2)
# 100

my_power(10, 3)
# 1000
```

## 关键字参数

```R
my_data = rnorm(100)

sd(my_data)
# 1.111496
sd(x = my_data)
# 1.111496
sd(x = my_data, na.rm = TRUE)
# 1.111496
sd(na.rm = TRUE, x = my_data)
# 1.111496
sd(na.rm = TRUE, my_data)
# 1.111496
```

## 惰性求值

尽管下面的函数有2个形式参数，但是由于第2个参数并在函数体中并没有使用到，所以在调用该函数时，是否给该参数传值，都不会报错。

```R
f <- function(a, b) {
  a^2
}

f(10,20)
# 100

f(10)
# 100
```

## ... 参数

通常用于向其他参数传递参数

```R
my_draw <- function (x, y, type, ...) {
  plot(x, y,type = type, ...)
}
```

或者，当实际参数的个数无法提前得知是，也需要使用 `...` 参数

```R
paste("1", "2", "3")
# "1 2 3"

paste("1", "2", "3", "4", "5", sep = ",")
# "1,2,3,4,5"
```

