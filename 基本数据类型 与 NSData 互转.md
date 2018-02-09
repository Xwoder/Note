# 基本数据类型 与 NSData 互转

```ObjC
NSUInteger a = 10;
size_t length = sizeof a;

// 基本数据类型 转 NSData
NSData *data = [NSData dataWithBytes:&a length:length];

// NSData 转 基本数据类型
NSUInteger b = 0;
[data getBytes:&b length:length];
```

