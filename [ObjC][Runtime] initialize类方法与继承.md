# [ObjC][Runtime] initialize类方法与继承

[TOC]

## 示例程序

### 代码

#### Dog 类

```objc
#import <Foundation/Foundation.h>

@interface Dog : NSObject

@end

@implementation Dog

+ (void)initialize {
    NSLog(@"%s", __FUNCTION__);
}

@end
```

#### YellowDog 类

```objc
#import "Dog.h"

@interface YellowDog : Dog

@end

@implementation YellowDog

@end
```

#### SmallYellowDog 类

```objc
#import "YellowDog.h"

@interface SmallYellowDog : YellowDog

@end

@implementation SmallYellowDog

@end
```

#### ViewContorller.m

```objc
#import "ViewController.h"
#import "Dog.h"
#import "YellowDog.h"
#import "SmallYellowDog.h"

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    [Dog alloc];
    [YellowDog alloc];
    [SmallYellowDog alloc];
}

@end
```

### 输出

```
+[Dog initialize]
+[Dog initialize]
+[Dog initialize]
```

## 说明

由于`+initialize`类方法的调用是基于消息机制的`objc_msgSend`函数调用的，所以，对于存在继承层次的类结构，如果子类没有实现`+initialize`方法，会调用父类的`+initialize`方法，所以父类的`+initialize`方法会被调用多次。



