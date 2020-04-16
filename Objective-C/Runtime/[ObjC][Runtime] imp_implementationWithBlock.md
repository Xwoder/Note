# [ObjC][Runtime] imp_implementationWithBlock

[TOC]

## 示例程序

### 代码

#### Dog 类

```objc
#import <Foundation/Foundation.h>

@interface Dog : NSObject

@end

@implementation Dog

@end
```

#### ViewController.m

```objc
#import "ViewController.h"
#import <objc/runtime.h>

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    void (^block)(id self) = ^(id self) {
        NSLog(@"-[Dog eat]");
    };

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wundeclared-selector"
    SEL sel = @selector(eat);
#pragma clang diagnostic pop
    Method method = class_getInstanceMethod([Dog class], sel);
    const char *typeEncoding = method_getTypeEncoding(method);
    IMP imp = imp_implementationWithBlock(block);
    class_getInstanceMethod([Dog class], sel);
    class_addMethod([Dog class], sel, imp, typeEncoding);

    Dog *dog = [[Dog alloc] init];
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Warc-performSelector-leaks"
    [dog performSelector:sel];
#pragma clang diagnostic pop
}

@end
```

###  输出

```console
-[Dog eat]
```

