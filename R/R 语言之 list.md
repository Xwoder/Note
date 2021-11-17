# R 语言之 list

可以存储任何类型的对象。

## 创建

```R
my_vector <- 1:10 

my_matrix <- matrix(1:9, ncol = 3)

my_df <- mtcars[1:10,]

my_list <- list(my_vector, my_matrix, my_df)
```

```R
my_vector <- 1:10 

my_matrix <- matrix(1:9, ncol = 3)

my_df <- mtcars[1:10,]

my_list <- list(my_vector, my_matrix, my_df)
names(my_list) = c("vec", "mat", "df")

my_list
# 
$vec
#  [1]  1  2  3  4  5  6  7  8  9 10
# 
# $mat
#      [,1] [,2] [,3]
# [1,]    1    4    7
# [2,]    2    5    8
# [3,]    3    6    9
# 
# $df
#                    mpg cyl  disp  hp drat    wt  qsec vs am gear carb
# Mazda RX4         21.0   6 160.0 110 3.90 2.620 16.46  0  1    4    4
# Mazda RX4 Wag     21.0   6 160.0 110 3.90 2.875 17.02  0  1    4    4
# Datsun 710        22.8   4 108.0  93 3.85 2.320 18.61  1  1    4    1
# Hornet 4 Drive    21.4   6 258.0 110 3.08 3.215 19.44  1  0    3    1
# Hornet Sportabout 18.7   8 360.0 175 3.15 3.440 17.02  0  0    3    2
# Valiant           18.1   6 225.0 105 2.76 3.460 20.22  1  0    3    1
# Duster 360        14.3   8 360.0 245 3.21 3.570 15.84  0  0    3    4
# Merc 240D         24.4   4 146.7  62 3.69 3.190 20.00  1  0    4    2
# Merc 230          22.8   4 140.8  95 3.92 3.150 22.90  1  0    4    2
# Merc 280          19.2   6 167.6 123 3.92 3.440 18.30  1  0    4    4
```

```R
mov
# "The Shining"

act
# "Jack Nicholson"   "Shelley Duvall"   "Danny Lloyd"      "Scatman Crothers"
# "Barry Nelson"    

rev
#   scores sources                                              comments
# 1    4.5   IMDb1                     Best Horror Film I Have Ever Seen
# 2    4.0   IMDb2 A truly brilliant and scary film from Stanley Kubrick
# 3    5.0   IMDb3                 A masterpiece of psychological horror

shining_list <- list(moviename = mov, 
                     actors = act, 
                     reviews = rev)
```

```R
shining_list
# shining_list
# $moviename
# [1] "The Shining"

# $actors
# [1] "Jack Nicholson"   "Shelley Duvall"   "Danny Lloyd"      "Scatman Crothers"
# [5] "Barry Nelson"    

# $reviews
#   scores sources                                              comments
# 1    4.5   IMDb1                     Best Horror Film I Have Ever Seen
# 2    4.0   IMDb2 A truly brilliant and scary film from Stanley Kubrick
# 3    5.0   IMDb3                 A masterpiece of psychological horror


shining_list["actors"]
# $actors
# "Jack Nicholson" "Shelley Duvall" "Danny Lloyd" "Scatman Crothers" "Barry Nelson"

shining_list[["actors"]]
# "Jack Nicholson" "Shelley Duvall" "Danny Lloyd" "Scatman Crothers" "Barry Nelson"

shining_list$actors
# "Jack Nicholson" "Shelley Duvall" "Danny Lloyd" "Scatman Crothers" "Barry Nelson"

shining_list[["actors"]][2]
# "Shelley Duvall"

shining_list$actors[2]
# "Shelley Duvall"
```

