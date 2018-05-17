# [ObjC][Runtime] class_replaceMethod

[TOC]

## 示例程序

### 代码

#### Dog 类

```objc
#import <Foundation/Foundation.h>

@interface Dog : NSObject

- (void)eat;

@end

@implementation Dog

- (void)eat {
    NSLog(@"-[Dog eat]");
}

@end
```

#### Cat 类

```objc
#import <Foundation/Foundation.h>

@interface Cat : NSObject

- (void)eat;

@end

@implementation Cat

- (void)eat {
    NSLog(@"-[Cat eat]");
}

@end
```

#### ViewController.m

```objc
#import "ViewController.h"
#import <objc/runtime.h>

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    Dog *dog = [[Dog alloc] init];
    Cat *cat = [[Cat alloc] init];

    SEL sel = @selector(eat);
    Class dogClass = [Dog class];
    Class catClass = [Cat class];

    NSLog(@"Before class_replaceMethod():");
    [dog eat];
    [cat eat];

    Method method = class_getInstanceMethod(dogClass, sel);
    IMP imp = class_getMethodImplementation(dogClass, sel);
    const char *encoding = method_getTypeEncoding(method);
    class_replaceMethod(catClass, sel, imp, encoding);

    NSLog(@"After class_replaceMethod():");
    [dog eat];
    [cat eat];
}

@end
```

###  输出

```console
Before class_replaceMethod():
-[Dog eat]
-[Cat eat]

After class_replaceMethod():
-[Dog eat]
-[Dog eat]
```

