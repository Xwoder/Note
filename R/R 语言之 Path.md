# R 语言之 Path

## path.expand

`path.expand` 用于将含有表示用户根目录的`~`符号展为完整路径

```R
path.expand("~")
# "/Users/Username"
```

```R

path.expand("~/Desktop")
# "/Users/Username/Desktop"
```