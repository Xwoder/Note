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
#import "Dog.h"
#import <objc/runtime.h>

void eatFunc(id self, SEL _cmd) {
    NSLog(@"-[Dog eat]");
}

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    Dog *dog = [[Dog alloc] init];
    Class dogClass = [Dog class];

    Method method = class_getInstanceMethod(dogClass, @selector(eat));
    const char *typeEncoding = method_getTypeEncoding(method);

    class_addMethod(dogClass, @selector(eat), (IMP)eatFunc, typeEncoding);

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

## 说明

上例中，通过 `class_addMethod` 函数将一个名为 `eatFunc` 的 C 函数添加到 `Dog` 类中作为 `-eat` 方法的实现。

不仅限于此，也可以将一个 `ObjC` 方法添加到某个类中，作为指定方法的实现。代码如下


```objc
#import "ViewController.h"
#import "Dog.h"
#import <objc/runtime.h>

@implementation ViewController

- (void)eatMethod {
    NSLog(@"-[Dog eat]");
}

- (void)viewDidLoad {
    [super viewDidLoad];

    Dog *dog = [[Dog alloc] init];
    Class dogClass = [Dog class];

    Method method = class_getInstanceMethod(dogClass, @selector(eat));
    const char *typeEncoding = method_getTypeEncoding(method);

    Method eatMethod = class_getInstanceMethod([ViewController class], @selector(eatMethod));
    IMP eatMethodImp = method_getImplementation(eatMethod);

    class_addMethod(dogClass, @selector(eat), eatMethodImp, typeEncoding);

    NSLog(@"After class_addMethod():");
    [dog eat];
}

@end
```


