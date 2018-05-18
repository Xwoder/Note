# [ObjC][Runtime] object_setClass

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

#### YelloDog 类


```objc
#import <Foundation/Foundation.h>

@interface YellowDog : NSObject

@end

@implementation YellowDog

@end
```

#### ViewController.m

```objc
#import "ViewController.h"
#import <objc/runtime.h>

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    YellowDog *yellowDog = [[YellowDog alloc] init];
    Dog *dog = [[Dog alloc] init];

    NSLog(@"[dog class] = %@, [yellowDog class] = %@", [dog class], [yellowDog class]);

    Class previousClass = object_setClass(yellowDog, [dog class]);

    NSLog(@"[dog class] = %@, [yellowDog class] = %@, previousClass = %@", [dog class], [yellowDog class], previousClass);
}

@end
```

###  输出

```console
[dog class] = Dog, [yellowDog class] = YellowDog

[dog class] = Dog, [yellowDog class] = Dog, previousClass = YellowDog
```

