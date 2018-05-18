# [ObjC][Runtime] class_getSuperclass

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

    // dog 实例对象 的类，即为 Dog 类
    Class dogClass = object_getClass(dog);
    NSLog(@"dogClass = %s<%p>, isMetaClass = %@",
          class_getName(dogClass), /* Dog */
          dogClass,
          class_isMetaClass(dogClass) ? @"YES" : @"NO") /* NO */;

    // dog 实例对象 的类 的父类，即为 NSObject 类
    Class superClassOfDogClass = class_getSuperclass(dogClass);
    NSLog(@"superClassOfDogClass = %s<%p>, isMetaClass = %@",
          class_getName(superClassOfDogClass), /* NSObject */
          superClassOfDogClass,
          class_isMetaClass(superClassOfDogClass) ? @"YES" : @"NO") /* NO */;

    // dog 实例对象 的类 的父类 的父类，即为 nil
    Class superClassOfSuperClassOfDogClass = class_getSuperclass(superClassOfDogClass);
    NSLog(@"superClassOfSuperClassOfDogClass = %s<%p>, isMetaClass = %@",
          class_getName(superClassOfSuperClassOfDogClass) /* nil */,
          superClassOfSuperClassOfDogClass,
          class_isMetaClass(superClassOfSuperClassOfDogClass) ? @"YES" : @"NO") /* NO */;
}

@end
```

###  输出

```console
dogClass = Dog<0x1086c2e80>, isMetaClass = NO

superClassOfDogClass = NSObject<0x1096faea8>, isMetaClass = NO

superClassOfSuperClassOfDogClass = nil<0x0>, isMetaClass = NO
```

