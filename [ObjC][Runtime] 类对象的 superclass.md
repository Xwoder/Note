# [ObjC][Runtime] 类对象的 superclass

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

#### YellowDog 类

```objc
#import "Dog.h"

@interface YellowDog : Dog

@end

@implementation YellowDog

@end
```

#### ViewController.m

```objc
#import "ViewController.h"
#import "Dog.h"
#import "YellowDog.h"
#import <objc/runtime.h>

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    YellowDog *yellowDog = [[YellowDog alloc] init];

    // YellowDog 类对象
    Class yellowDogClass = object_getClass(yellowDog);
    [self printClassInfoWithPromptMessage:@"yellowDogClass"
                                    class:yellowDogClass];

    // YellowDog 类对象的 superclass, 即 Dog 类对象
    Class superclassOfYellowDogClass = class_getSuperclass(yellowDogClass);
    [self printClassInfoWithPromptMessage:@"superclassOfYellowDogClass"
                                    class:superclassOfYellowDogClass];

    // Dog 类对象的 superclass, 即 NSObject 类对象
    Class superclassOfSuperclassOfYellowDogClass = class_getSuperclass(superclassOfYellowDogClass);
    [self printClassInfoWithPromptMessage:@"superclassOfSuperclassOfYellowDogClass"
                                    class:superclassOfSuperclassOfYellowDogClass];

    // NSObject 类对象的 superclass, 即 null
    Class superclassOfSuperclassOfSuperclassOfYellowDogClass = class_getSuperclass(superclassOfSuperclassOfYellowDogClass);
    [self printClassInfoWithPromptMessage:@"superclassOfSuperclassOfSuperclassOfYellowDogClass"
                                    class:superclassOfSuperclassOfSuperclassOfYellowDogClass];
}

- (void)printClassInfoWithPromptMessage:(NSString *)promptMessage class:(Class)class {
    NSLog(@"%@ = %@<%p>, isMetaClass = %@", promptMessage, class, class, class_isMetaClass(class) ? @"YES" : @"NO");
}

@end
```

###  输出

```
yellowDogClass = YellowDog<0x10c999f70>, isMetaClass = NO
superclassOfYellowDogClass = Dog<0x10c999fc0>, isMetaClass = NO
superclassOfSuperclassOfYellowDogClass = NSObject<0x10d9d3ea8>, isMetaClass = NO
superclassOfSuperclassOfSuperclassOfYellowDogClass = (null)<0x0>, isMetaClass = NO
```

## 说明

### 类结构

* `YellowDog`类继承自`Dog`类
* `Dog`类继承自`NSObject`类

### 分析

* `YellowDog`类对象的 `superclass`为`Dog`类对象
* `Dog`类对象的`superclass`为`NSObject`类对象
* `NSObject`类对象的`superclass`为`null`


