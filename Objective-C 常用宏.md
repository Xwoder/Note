# Objective-C 常用宏

[TOC]

## Key Path

```Objective-C
#define keyPath(objc, keyPath) @(((void)objc.keyPath, #keyPath))
```

## 变量交换

```Objective-C
#define SWAP(x, y) do { typeof(x) tempVar = x; x = y; y = tempVar; } while (0)
```


## 自动类型推断

```Objective-C
#define let __auto_type const
#define var __auto_type
```