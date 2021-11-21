# R 语言之 Data Frame

[TOC]

同一列的数据元素必须为相同类型，不同列的数据元素的可以为不同类型。

## 创建

```R
name <- c("Mercury", "Venus", "Earth", 
          "Mars", "Jupiter", "Saturn", 
          "Uranus", "Neptune")
type <- c("Terrestrial planet", 
          "Terrestrial planet", 
          "Terrestrial planet", 
          "Terrestrial planet", "Gas giant", 
          "Gas giant", "Gas giant", "Gas giant")
diameter <- c(0.382, 0.949, 1, 0.532, 
              11.209, 9.449, 4.007, 3.883)
rotation <- c(58.64, -243.02, 1, 1.03, 
              0.41, 0.43, -0.72, 0.67)
rings <- c(FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, TRUE)

# Create a data frame from the vectors
planets_df <- data.frame(name, type, diameter, rotation, rings)

planets_df
#      name               type diameter rotation rings
# 1 Mercury Terrestrial planet    0.382    58.64 FALSE
# 2   Venus Terrestrial planet    0.949  -243.02 FALSE
# 3   Earth Terrestrial planet    1.000     1.00 FALSE
# 4    Mars Terrestrial planet    0.532     1.03 FALSE
# 5 Jupiter          Gas giant   11.209     0.41  TRUE
# 6  Saturn          Gas giant    9.449     0.43  TRUE
# 7  Uranus          Gas giant    4.007    -0.72  TRUE
# 8 Neptune          Gas giant    3.883     0.67  TRUE
```

## 查看

### 头部 / `head()`

```R
head(planets_df)
#      name               type diameter rotation rings
# 1 Mercury Terrestrial planet    0.382    58.64 FALSE
# 2   Venus Terrestrial planet    0.949  -243.02 FALSE
# 3   Earth Terrestrial planet    1.000     1.00 FALSE
# 4    Mars Terrestrial planet    0.532     1.03 FALSE
# 5 Jupiter          Gas giant   11.209     0.41  TRUE
# 6  Saturn          Gas giant    9.449     0.43  TRUE
```

### 尾部 / `tail()`

```R
tail(planets_df)
#      name               type diameter rotation rings
# 3   Earth Terrestrial planet    1.000     1.00 FALSE
# 4    Mars Terrestrial planet    0.532     1.03 FALSE
# 5 Jupiter          Gas giant   11.209     0.41  TRUE
# 6  Saturn          Gas giant    9.449     0.43  TRUE
# 7  Uranus          Gas giant    4.007    -0.72  TRUE
# 8 Neptune          Gas giant    3.883     0.67  TRUE
```

### 描述 / `str()`

这个命令的来自于英文 `structure`

```R
str(planets_df)
'data.frame':   8 obs. of  5 variables:
#  $ name    : Factor w/ 8 levels "Earth","Jupiter",..: 4 8 1 3 2 6 7 5
#  $ type    : Factor w/ 2 levels "Gas giant","Terrestrial planet": 2 2 2 2 1 1 1 1
#  $ diameter: num  0.382 0.949 1 0.532 11.209 ...
#  $ rotation: num  58.64 -243.02 1 1.03 0.41 ...
#  $ rings   : logi  FALSE FALSE FALSE FALSE TRUE TRUE ...
```

### 子集 / `subset()`

```R
planets_df
#      name               type diameter rotation rings
# 1 Mercury Terrestrial planet    0.382    58.64 FALSE
# 2   Venus Terrestrial planet    0.949  -243.02 FALSE
# 3   Earth Terrestrial planet    1.000     1.00 FALSE
# 4    Mars Terrestrial planet    0.532     1.03 FALSE
# 5 Jupiter          Gas giant   11.209     0.41  TRUE
# 6  Saturn          Gas giant    9.449     0.43  TRUE
# 7  Uranus          Gas giant    4.007    -0.72  TRUE
# 8 Neptune          Gas giant    3.883     0.67  TRUE

subset(planets_df, subset = rings)
#      name      type diameter rotation rings
# 5 Jupiter Gas giant   11.209     0.41  TRUE
# 6  Saturn Gas giant    9.449     0.43  TRUE
# 7  Uranus Gas giant    4.007    -0.72  TRUE
# 8 Neptune Gas giant    3.883     0.67  TRUE

subset(planets_df, subset = diameter < 1)
#      name               type diameter rotation rings
# 1 Mercury Terrestrial planet    0.382    58.64 FALSE
# 2   Venus Terrestrial planet    0.949  -243.02 FALSE
# 4    Mars Terrestrial planet    0.532     1.03 FALSE
```

## 访问

### 索引

```R
planets_df[1, 3]
# 0.382

planets_df[4, ]
#   name               type diameter rotation rings
# 4 Mars Terrestrial planet    0.532     1.03 FALSE

planets_df[5, "diameter"]
# 11.209
```

#### 布尔索引

```R
planets_df[rings_vector, ]
#      name      type diameter rotation rings
# 5 Jupiter Gas giant   11.209     0.41  TRUE
# 6  Saturn Gas giant    9.449     0.43  TRUE
# 7  Uranus Gas giant    4.007    -0.72  TRUE
# 8 Neptune Gas giant    3.883     0.67  TRUE
```

```R
planets_df[rings_vector, "name"]
# Jupiter Saturn  Uranus  Neptune
# Levels: Earth Jupiter Mars Mercury Neptune Saturn Uranus Venus
```

```R
rings_vector = planets_df["rings"]

planets_df[rings_vector, c("name", "diameter", "rotation")]
#      name diameter rotation
# 5 Jupiter   11.209     0.41
# 6  Saturn    9.449     0.43
# 7  Uranus    4.007    -0.72
# 8 Neptune    3.883     0.67
```

### 列名

```R
planets_df["rings"]
# FALSE FALSE FALSE FALSE  TRUE  TRUE  TRUE  TRUE

planets_df[3:6, "rings"]
# FALSE FALSE  TRUE  TRUE
```

```R
planets_df$rings
# FALSE FALSE FALSE FALSE  TRUE  TRUE  TRUE  TRUE
```

## 排序

```R
planets_df
#      name               type diameter rotation rings
# 1 Mercury Terrestrial planet    0.382    58.64 FALSE
# 2   Venus Terrestrial planet    0.949  -243.02 FALSE
# 3   Earth Terrestrial planet    1.000     1.00 FALSE
# 4    Mars Terrestrial planet    0.532     1.03 FALSE
# 5 Jupiter          Gas giant   11.209     0.41  TRUE
# 6  Saturn          Gas giant    9.449     0.43  TRUE
# 7  Uranus          Gas giant    4.007    -0.72  TRUE
# 8 Neptune          Gas giant    3.883     0.67  TRUE

positions <- order(planets_df$diameter)
# 1 4 2 3 8 7 6 5

planets_df[positions, ]
#      name               type diameter rotation rings
# 1 Mercury Terrestrial planet    0.382    58.64 FALSE
# 4    Mars Terrestrial planet    0.532     1.03 FALSE
# 2   Venus Terrestrial planet    0.949  -243.02 FALSE
# 3   Earth Terrestrial planet    1.000     1.00 FALSE
# 8 Neptune          Gas giant    3.883     0.67  TRUE
# 7  Uranus          Gas giant    4.007    -0.72  TRUE
# 6  Saturn          Gas giant    9.449     0.43  TRUE
# 5 Jupiter          Gas giant   11.209     0.41  TRUE
```



