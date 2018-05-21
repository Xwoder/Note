# [ObjC] 字符串反转

[TOC]

## 示例

### 代码

```objc
#import <Foundation/Foundation.h>

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        NSString *str = @"The quick brown fox jump sover the lazy dog.";

        NSMutableString *reversedStrM = [NSMutableString stringWithCapacity:str.length];
        for (NSUInteger i = 0; i < str.length; ++i) {
            @autoreleasepool {
                NSString *subStr = [str substringWithRange:NSMakeRange(str.length - i - 1, 1)];
                [reversedStrM appendString:subStr];
            }
        }

        NSLog(@"%@", reversedStrM);
    }
    return 0;
}
```

### 输出


```console
.god yzal eht revos pmuj xof nworb kciuq ehT
```

