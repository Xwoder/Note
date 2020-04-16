# [ObjC][Runtime] 元类对象的 isa 指针

[TOC]

## 示例程序

### 代码

#### Dog 类

```objc
@interface Dog : NSObject

@end

@implementation Dog

@end
```

#### YellowDog 类

```objc
@interface YellowDog : Dog

@end

@implementation YellowDog

@end
```

#### ViewContorller.m

```objc
#import "ViewController.h"
#import <objc/runtime.h>

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    {
        NSObject *obj = [[NSObject alloc] init];
        char const *className = object_getClassName(obj);
        Class metaClass = objc_getMetaClass(className);
        Class classOfMetaClass = object_getClass(metaClass);
        NSLog(@"%@<%p>, isMetaClass = %@", classOfMetaClass, classOfMetaClass, class_isMetaClass(classOfMetaClass) ? @"YES" : @"NO");
    }
    
    {
        Dog *dog = [[Dog alloc] init];
        char const *className = object_getClassName(dog);
        Class metaClass = objc_getMetaClass(className);
        Class classOfMetaClass = object_getClass(metaClass);
        NSLog(@"%@<%p>, isMetaClass = %@", classOfMetaClass, classOfMetaClass, class_isMetaClass(classOfMetaClass) ? @"YES" : @"NO");
    }
    
    {
        YellowDog *yellowDog = [[YellowDog alloc] init];
        char const *className = object_getClassName(yellowDog);
        Class metaClass = objc_getMetaClass(className);
        Class classOfMetaClass = object_getClass(metaClass);
        NSLog(@"%@<%p>, isMetaClass = %@", classOfMetaClass, classOfMetaClass, class_isMetaClass(classOfMetaClass) ? @"YES" : @"NO");
    }
}

@end
```

### 输出

```txt
NSObject<0x106261e58>, isMetaClass = YES
NSObject<0x106261e58>, isMetaClass = YES
NSObject<0x106261e58>, isMetaClass = YES
```

## 说明

> 观察输出可知
> 
> 任何元类对象的 `isa` 指针都指向 `NSObject` 元类对象
> 进一步可知：`NSObject` 元类对象的 `isa` 指针指向其自身



