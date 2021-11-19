# R 语言之 for

[TOC]

```R
for (i in 1:10) {
  print(i)
}

# 1
# 2
# 3
# 4
# 5
# 6
# 7
# 8
# 9
# 10
```

```R
nums = c(10, 20, 30, 40, 50)

for (i in 1:5) {
  print(nums[i])
}

# 10
# 20
# 30
# 40
# 50
```

```R
seq_along(nums)
# 1 2 3 4 5

for (i in seq_along(nums)) {
  print(nums[i])
}

# 10
# 20
# 30
# 40
# 50
```

```R
for (num in nums) {
  print(num)
}

# 10
# 20
# 30
# 40
# 50
```

```R
nums = matrix(1:6, nrow = 2, ncol = 3)

nums

for (i in seq_len(nrow(nums))) {
  for (j in seq_len(ncol(nums))) {
    print(nums[i, j])
  }
}

# 1
# 3
# 5
# 2
# 4
# 6
```

