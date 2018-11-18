# iOS ObjC 常用宏

[TOC]

## Key Path

### 定义

```objc
#define keyPath(objc, keyPath) @(((void)objc.keyPath, #keyPath))
```

## 变量交换

### 定义

```objc
#define SWAP(x, y) do { typeof(x) tempVar = x; x = y; y = tempVar; } while (0)
```


