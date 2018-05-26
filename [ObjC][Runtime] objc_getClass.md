# [ObjC][Runtime] objc_getClass

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
    
    {
        const char *className = class_getName([NSObject class]);
        Class class = objc_getClass(className);
        NSLog(@"%@<%p>, isMetaClass = %@", class, class, class_isMetaClass(class) ? @"YES" : @"NO");
    }
    
    {
        const char *className = class_getName([Dog class]);
        Class class = objc_getClass(className);
        NSLog(@"%@<%p>, isMetaClass = %@", class, class, class_isMetaClass(class) ? @"YES" : @"NO");
    }
    
    {
        // 获得类对象的名称
        const char *className = class_getName([Dog class]);
        // 根据类对象的名称获得元类对象
        Class metaClass = objc_getMetaClass(className);
        // 获得元类对象的名称
        const char *metaClassName = class_getName(metaClass);
        // 将元类对象的名称传给 objc_getClass 函数，得到的依然是类对象
        Class class = objc_getClass(metaClassName);
        NSLog(@"%@<%p>, isMetaClass = %@", class, class, class_isMetaClass(class) ? @"YES" : @"NO");
        
        // 类对象的名称 与 元类对象的名称 是相同的
        NSLog(@"className = %s, metaClassName = %s", className, metaClassName);
    }
}

@end
```

###  输出

```console
NSObject<0x10f6efea8>, isMetaClass = NO
Dog<0x10e6b7e08>, isMetaClass = NO
Dog<0x10e6b7e08>, isMetaClass = NO
className = Dog, metaClassName = Dog
```

### 说明

`objc_getClass`函数的函数原型为

```objc
id objc_getClass(const char *name);
```

其接受一个类对象的名称，返回其对应的类对象。

由于类对象的名称与元类对象的名称是相同的，所以不论将以上2个名称中的任何一个传递给该函数，都只会返回与该名称对应的类对象，而不是元类对象。

