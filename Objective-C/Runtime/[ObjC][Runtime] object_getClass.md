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
    
    {
        NSObject *obj = [[NSObject alloc] init];
        
        // 对 object_getClass 函数，传入一个实例对象，返回一个类对象
        Class objClass1 = object_getClass(obj);
        NSLog(@"%@<%p>, isMetaClass = %@", objClass1, objClass1, class_isMetaClass(objClass1) ? @"YES" : @"NO");
        
        // 对 object_getClass 函数，传入一个类对象，返回一个元类对象
        Class objMetaClass1 = object_getClass(objClass1);
        NSLog(@"%@<%p>, isMetaClass = %@", objMetaClass1, objMetaClass1, class_isMetaClass(objMetaClass1) ? @"YES" : @"NO");
        
        // 对 object_getClass 函数，传入一个实例对象，返回一个类对象
        Class objClass2 = [obj class];
        NSLog(@"%@<%p>, isMetaClass = %@", objClass2, objClass2, class_isMetaClass(objClass2) ? @"YES" : @"NO");
        
        // 对 object_getClass 函数，传入一个类对象，返回一个元类对象
        Class objMetaClass2 = object_getClass(objClass2);
        NSLog(@"%@<%p>, isMetaClass = %@", objMetaClass2, objMetaClass2, class_isMetaClass(objMetaClass2) ? @"YES" : @"NO");
    }
    
    {
        Dog *dog = [[Dog alloc] init];
        
        // 对 object_getClass 函数，传入一个实例对象，返回一个类对象
        Class dogClass1 = object_getClass(dog);
        NSLog(@"%@<%p>, isMetaClass = %@", dogClass1, dogClass1, class_isMetaClass(dogClass1) ? @"YES" : @"NO");
        // 对 object_getClass 函数，传入一个类对象，返回一个元类对象
        Class dogMetaClass1 = object_getClass(dogClass1);
        NSLog(@"%@<%p>, isMetaClass = %@", dogMetaClass1, dogMetaClass1, class_isMetaClass(dogMetaClass1) ? @"YES" : @"NO");
        
        // 对 object_getClass 函数，传入一个实例对象，返回一个类对象
        Class dogClass2 = [dog class];
        NSLog(@"%@<%p>, isMetaClass = %@", dogClass2, dogClass2, class_isMetaClass(dogClass2) ? @"YES" : @"NO");
        // 对 object_getClass 函数，传入一个类对象，返回一个元类对象
        Class dogMetaClass2 = object_getClass(dogClass2);
        NSLog(@"%@<%p>, isMetaClass = %@", dogMetaClass2, dogMetaClass2, class_isMetaClass(dogMetaClass2) ? @"YES" : @"NO");
    }
}

@end
```

###  输出

```console
NSObject<0x10e76aea8>, isMetaClass = NO
NSObject<0x10e76ae58>, isMetaClass = YES
NSObject<0x10e76aea8>, isMetaClass = NO
NSObject<0x10e76ae58>, isMetaClass = YES

Dog<0x10d07fde8>, isMetaClass = NO
Dog<0x10d07fdc0>, isMetaClass = YES
Dog<0x10d07fde8>, isMetaClass = NO
Dog<0x10d07fdc0>, isMetaClass = YES
```

### 说明

`object_getClass` 函数的函数原型为

```objc
Class object_getClass(id obj);
```

该函数根据传入的参数不同，返回的可能是类对象,也可能是元类对象。具体表现可记为：

* object_getClass(`实例对象`)，返回`类对象`
* object_getClass(`类对象`)，返回`元类对象`


