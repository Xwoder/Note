# [ObjC][Runtime] objc_allocateClassPair

[TOC]

## 示例程序

### 代码

#### ViewController.m

```objc
#import "ViewController.h"
#import <objc/runtime.h>

void eat(id self, SEL _cmd) {
    NSLog(@"-[Dog eat]");
}

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wundeclared-selector"
    SEL sel = @selector(eat);
#pragma clang diagnostic pop

    const char *className = "Dog";
    Class dogClass = objc_allocateClassPair([NSObject class], className, 0);
    objc_registerClassPair(dogClass);

    Method method = class_getInstanceMethod(dogClass, sel);
    const char *typeEncoding = method_getTypeEncoding(method);
    class_addMethod(dogClass, sel, (IMP)eat, typeEncoding);

    id dog = class_createInstance(dogClass, 0);

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

