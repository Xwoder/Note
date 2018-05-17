# [ObjC][Runtime] method_exchangeImplementations

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
#import "Dog.h"
#import "Cat.h"
#import <objc/runtime.h>

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    Dog *dog = [[Dog alloc] init];
    Cat *cat = [[Cat alloc] init];

    NSLog(@"Before method_exchangeImplementations():");

    [dog eat];
    [cat eat];

    Method m1 = class_getInstanceMethod([Dog class], @selector(eat));
    Method m2 = class_getInstanceMethod([Cat class], @selector(eat));
    method_exchangeImplementations(m1, m2);

    NSLog(@"After method_exchangeImplementations():");
    [dog eat];
    [cat eat];
}

@end
```

###  输出

```console
Before method_exchangeImplementations():
-[Dog eat]
-[Cat eat]

After method_exchangeImplementations():
-[Cat eat]
-[Dog eat]
```

