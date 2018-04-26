# NSDictionary 与 plist 文件

```objc
- (void)viewDidLoad {
    [super viewDidLoad];

    NSString *numberStringPairDictPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES).firstObject stringByAppendingPathComponent:@"numberStringPairDict.plist"];
    NSLog(@"numberStringPairDictPath = %@", numberStringPairDictPath);
    NSDictionary<NSNumber *, NSString *> *numberStringPairDict = @{
        @(1) : @"Beijing",
        @(2) : @"Shanghai",
    };
    NSLog(@"字典 numberStringPairDict 写入至文件，%@", [numberStringPairDict writeToFile:numberStringPairDictPath atomically:YES] ? @"成功" : @"失败");

    NSDictionary<NSString *, NSNumber *> *stringNumberPairDict = @{
        @"Beijing" : @(1),
        @"Shanghai" : @(2),
    };
    NSString *stringNumberPairDictPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES).firstObject stringByAppendingPathComponent:@"stringNumberPairDict.plist"];
    NSLog(@"stringNumberPairDictPath = %@", stringNumberPairDictPath);
    NSLog(@"字典 stringNumberPairDict 写入至文件，%@", [stringNumberPairDict writeToFile:stringNumberPairDictPath atomically:YES] ? @"成功" : @"失败");
}
```

## 输出

```txt
numberStringPairDictPath = .../Documents/numberStringPairDict.plist
字典 numberStringPairDict 写入至文件，失败

stringNumberPairDictPath = .../Documents/stringNumberPairDict.plist
字典 stringNumberPairDict 写入至文件，成功
```

## 说明

> 如果字典 NSDictionary 和 CFDictionary 的键不是字符串对象，那么该字典就不是一个 plist 对象

