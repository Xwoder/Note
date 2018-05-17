# [ObjC][Runtime] objc_getClass 与 objc_getMetaClass

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

    const char *className = class_getName([Dog class]);
    Class dogClass = objc_getClass(className);
    BOOL isMetaClassOfDogClass = class_isMetaClass(dogClass);
    NSLog(@"dogClass = %@<%p>, isMetaClass = %@", dogClass, dogClass, isMetaClassOfDogClass ? @"YES" : @"NO");

    Class dogMetaClass = objc_getMetaClass(className);
    BOOL isMetaClassOfDogMetaClass = class_isMetaClass(dogMetaClass);
    NSLog(@"dogMetaClass = %@<%p>, isMetaClass = %@", dogMetaClass, dogMetaClass, isMetaClassOfDogMetaClass ? @"YES" : @"NO");
}

@end
```

###  输出

```console
dogClass = Dog<0x101e0ce40>, isMetaClass = NO
dogMetaClass = Dog<0x101e0ce18>, isMetaClass = YES
```

