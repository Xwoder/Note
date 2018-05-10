# ObjC 可继承单例

[TOC]

## 代码

### `InheritableSingleton.h` 文件

```objc
#import <Foundation/Foundation.h>

@interface InheritableSingleton : NSObject

+ (instancetype)sharedInstance;

@end
```

### `InheritableSingleton.m` 文件

```objc
#import "InheritableSingleton.h"
#import <objc/runtime.h>

@implementation InheritableSingleton

+ (instancetype)sharedInstance {
    Class selfClass = [self class];
    @synchronized(self) {
        id instance = objc_getAssociatedObject(selfClass, @selector(sharedInstance));
        if (!instance) {
            instance = [[selfClass alloc] init];
            objc_setAssociatedObject(selfClass, @selector(sharedInstance), instance, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
        }
        return instance;
    }
}

@end
```

## 实例

### 代码

```objc
#import "ViewController.h"
#import "InheritableSingleton.h"

/** InheritableSingletonA 类 */
@interface InheritableSingletonA : InheritableSingleton

@end

@implementation InheritableSingletonA

@end

/** InheritableSingletonB 类 */
@interface InheritableSingletonB : InheritableSingleton

@end

@implementation InheritableSingletonB

@end

/** ViewController */
@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    for (NSUInteger i = 0; i < 10; ++i) {
        dispatch_async(dispatch_queue_create(0, DISPATCH_QUEUE_CONCURRENT), ^{
            NSLog(@"currentThread = %@, InheritableSingleton = %p", [NSThread currentThread], [InheritableSingleton sharedInstance]);
        });
    }

    for (NSUInteger i = 0; i < 10; ++i) {
        dispatch_async(dispatch_queue_create(0, DISPATCH_QUEUE_CONCURRENT), ^{
            NSLog(@"currentThread = %@, InheritableSingletonA = %p", [NSThread currentThread], [InheritableSingletonA sharedInstance]);
        });
    }

    for (NSUInteger i = 0; i < 10; ++i) {
        dispatch_async(dispatch_queue_create(0, DISPATCH_QUEUE_CONCURRENT), ^{
            NSLog(@"currentThread = %@, InheritableSingletonB = %p", [NSThread currentThread], [InheritableSingletonB sharedInstance]);
        });
    }
}

@end
```

### 输出

```txt
currentThread = <NSThread: 0x604000273fc0>{number = 3, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x604000274100>{number = 5, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x604000274040>{number = 4, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x6040002741c0>{number = 7, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x604000274140>{number = 6, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x6040002742c0>{number = 8, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x604000274100>{number = 5, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x604000273fc0>{number = 3, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x6000002780c0>{number = 9, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x600000278000>{number = 10, name = (null)}, InheritableSingleton = 0x604000013780
currentThread = <NSThread: 0x600000278380>{number = 11, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x6000002784c0>{number = 12, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x604000274040>{number = 4, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x600000277240>{number = 13, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x6040002741c0>{number = 7, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x604000274140>{number = 6, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x604000275100>{number = 14, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x600000278840>{number = 15, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x604000275140>{number = 16, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x6000002789c0>{number = 17, name = (null)}, InheritableSingletonA = 0x600000013ea0
currentThread = <NSThread: 0x600000278340>{number = 18, name = (null)}, InheritableSingletonB = 0x600000013ee0
currentThread = <NSThread: 0x604000275500>{number = 20, name = (null)}, InheritableSingletonB = 0x600000013ee0
currentThread = <NSThread: 0x6040002754c0>{number = 19, name = (null)}, InheritableSingletonB = 0x600000013ee0
currentThread = <NSThread: 0x600000278e80>{number = 21, name = (null)}, InheritableSingletonB = 0x600000013ee0
currentThread = <NSThread: 0x6040002742c0>{number = 8, name = (null)}, InheritableSingletonB = 0x600000013ee0
currentThread = <NSThread: 0x604000275580>{number = 22, name = (null)}, InheritableSingletonB = 0x600000013ee0
currentThread = <NSThread: 0x604000274100>{number = 5, name = (null)}, InheritableSingletonB = 0x600000013ee0
currentThread = <NSThread: 0x604000275600>{number = 23, name = (null)}, InheritableSingletonB = 0x600000013ee0
currentThread = <NSThread: 0x6040002753c0>{number = 24, name = (null)}, InheritableSingletonB = 0x600000013ee0
currentThread = <NSThread: 0x604000273fc0>{number = 3, name = (null)}, InheritableSingletonB = 0x600000013ee0
```

