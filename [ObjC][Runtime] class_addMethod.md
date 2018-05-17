# [ObjC][Runtime] class_addMethod

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

@end
```

> 注意：
> 
> 在 Dog 类中没有方法的具体实现

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

    Dog *dog = [[Dog alloc] init];
    Class dogClass = [Dog class];

    // 在此处执行以下代码为导致程序产生错误
    // [dog eat];

    Method method = class_getInstanceMethod(dogClass, @selector(eat));
    const char *typeEncoding = method_getTypeEncoding(method);
    class_addMethod(dogClass, @selector(eat), (IMP)eat, typeEncoding);

    NSLog(@"After class_addMethod():");
    [dog eat];
}

@end
```

###  输出

```console
After class_addMethod():
-[Dog eat]
```

