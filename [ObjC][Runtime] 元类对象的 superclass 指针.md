# [ObjC][Runtime] 元类对象的 superclass 指针

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
#import <Foundation/Foundation.h>

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
        YellowDog *yellowDog = [[YellowDog alloc] init];
        char const *className = object_getClassName(yellowDog);
        Class metaClass = objc_getMetaClass(className);
        Class superclassOfMetaClass = class_getSuperclass(metaClass);
        NSLog(@"%@<%p>, isMetaClass = %@", superclassOfMetaClass, superclassOfMetaClass, class_isMetaClass(superclassOfMetaClass) ? @"YES" : @"NO");
    }
    
    {
        Dog *dog = [[Dog alloc] init];
        char const *className = object_getClassName(dog);
        Class metaClass = objc_getMetaClass(className);
        Class superclassOfMetaClass = class_getSuperclass(metaClass);
        NSLog(@"%@<%p>, isMetaClass = %@", superclassOfMetaClass, superclassOfMetaClass, class_isMetaClass(superclassOfMetaClass) ? @"YES" : @"NO");
    }
    
    {
        NSObject *obj = [[NSObject alloc] init];
        char const *className = object_getClassName(obj);
        Class metaClass = objc_getMetaClass(className);
        Class superclassOfMetaClass = class_getSuperclass(metaClass);
        NSLog(@"%@<%p>, isMetaClass = %@", superclassOfMetaClass, superclassOfMetaClass, class_isMetaClass(superclassOfMetaClass) ? @"YES" : @"NO");
        
        // NSObject 类对象
        Class class = object_getClass(obj);
        NSLog(@"%@<%p>, isMetaClass = %@", class, class, class_isMetaClass(class) ? @"YES" : @"NO");
    }
}

@end
```

### 输出

```
Dog<0x10df1fe78>, isMetaClass = YES
NSObject<0x10ef58e58>, isMetaClass = YES
NSObject<0x10ef58ea8>, isMetaClass = NO
NSObject<0x10ef58ea8>, isMetaClass = NO
```

## 说明

第 1 行输出

```
Dog<0x10df1fe78>, isMetaClass = YES
```

表明 `YellowDog` 元类对象的 `superclass` 指针指向 `Dog` 元类对象。

第 2 行输出

```
NSObject<0x10ef58e58>, isMetaClass = YES
```

表明 `Dog` 元类对象的 `superclass` 指针指向 `NSObject` 元类对象。

以上指向关系与类之间的继承关系相对应，容易理解。

但是，第 3、4 行输出

```
NSObject<0x10ef58ea8>, isMetaClass = NO
NSObject<0x10ef58ea8>, isMetaClass = NO
```

表明 `NSObject` 元类对象的 `superclass` 指针指向的是 `NSObject` 类对象。



