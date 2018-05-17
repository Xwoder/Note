# [ObjC][Runtime] object_getClass

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

    Dog *dog = [[Dog alloc] init];
    Class dogClass1 = object_getClass(dog);
    NSLog(@"dogClass1 = %@<%p>", dogClass1, dogClass1);

    const char *className = class_getName([Dog class]);
    Class dogClass2 = objc_getClass(className);
    NSLog(@"dogClass2 = %@<%p>", dogClass2, dogClass2);

    NSLog(@"dogClass1 %@ dogClass2", dogClass1 == dogClass2 ? @"==" : @"!=");
}

@end
```

###  输出

```console
dogClass1 = Dog<0x10f82de28>
dogClass2 = Dog<0x10f82de28>

dogClass1 == dogClass2
```

