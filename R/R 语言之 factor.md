# R 语言之 factor

[TOC]

factor 是用来存储分类变量的统计数据类型。

## 分类变量

有两种类型的分类变量：

- `nominal categorical variable` / 名义分类变量
- `ordinal categorical variable` / 有序分类变量

### 名义分类变量

```R
animals_vector <- c("Elephant", "Giraffe", "Donkey", "Horse")
factor_animals_vector <- factor(animals_vector)
factor_animals_vector
```

### 定序分类变量

如果是定序分类变量，在通过 `factor()` 创建时，需要通过 `order` 参数的值指定为 `TRUE`，同时通过 `levels` 参数来表明顺序。

```R
temperature_vector <- c("High", "Low", "High","Low", "Medium")
factor_temperature_vector <- factor(temperature_vector, 
                                    order = TRUE, 
                                    levels = c("Low", "Medium", "High"))
factor_temperature_vector
```

对于一个已经存在的 `factor` 对象，可以通过 `levels()` 函数改变它们的名字。

需要注意的是，传入  `levels()`  的向量中值的顺序十分重要。

```R
survey_vector <- c("M", "F", "F", "M", "M")
factor_survey_vector <- factor(survey_vector)

factor_survey_vector
# M F F M M
# Levels: F M

levels(factor_survey_vector) <- c("Female", "Male")

factor_survey_vector
# Male Female Female Male Male  
# Levels: Female Male
```

## 创建

利用 `factor()` 函数可以根据一个向量的值创建因子。

```R
# 性别值向量
sex_vector <- c("Male", "Female", "Female", "Male", "Male")

# 将性别值向量转换为一个因子
factor_sex_vector <- factor(sex_vector)

factor_sex_vector

# Male   Female Female Male   Male  
# Levels: Female Male
```

```R
speed_vector <- c("medium", "slow", "slow", "medium", "fast")

factor_speed_vector <- factor(speed_vector, 
                              ordered = TRUE, 
                              levels = c("slow", "medium", "fast"))

factor_speed_vector
# medium slow   slow   medium fast  
# Levels: slow < medium < fast

summary(factor_speed_vector)
#   slow medium   fast 
#      2      2      1 
```

## 摘要

```R
survey_vector <- c("M", "F", "F", "M", "M")
factor_survey_vector <- factor(survey_vector)
levels(factor_survey_vector) <- c("Female", "Male")

factor_survey_vector
# Male   Female Female Male   Male  
# Levels: Female Male

summary(survey_vector)
#    Length     Class      Mode 
#         5 character character 

summary(factor_survey_vector)
# Female   Male 
#      2      3 
```

## 比较运算

只有定序分类变量的因子才可以进行比较运算

```R
speed_vector <- c("medium", "slow", "slow", "medium", "fast")
factor_speed_vector <- factor(speed_vector, ordered = TRUE, levels = c("slow", "medium", "fast"))

da2 <- factor_speed_vector[2]
da5 <- factor_speed_vector[5]

da2 > da5
# FALSE

da2 < da5
# TRUE
```

