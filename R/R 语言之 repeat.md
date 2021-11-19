# R 语言之 repeat

`repeat`循 必须配合 `break`语句强制结束。

```R
count <- 10

repeat {
  if (count <= 0) {
    break
  } else {
    print(count)
    count <- count - 1
  }
}
```

```R
count <- 10

repeat {
  if (count > 0) {
    print(count)
    count <- count - 1
  } else {
    break
  }
}
```

```R
i = 12

repeat {
  print(i)

  if (i == 1) {
    break
  }

  if (i %% 2 == 0) {
    i <- i / 2
  } else {
    i <- 3 * i + 1
  }
}
```

