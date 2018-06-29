# [ObjC] OSSpinLock

[TOC]

## 示例程序

### 代码

#### ViewController.m

```objc
#import "ViewController.h"
#import <libkern/OSAtomic.h>

@interface ViewController () {
    OSSpinLock _spinLock;
    NSUInteger _num;
}

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    _spinLock = OS_SPINLOCK_INIT;

    _num = 0;

    dispatch_queue_t concurrentDispatchQueue = dispatch_queue_create("queue", DISPATCH_QUEUE_CONCURRENT);

    for (NSUInteger i = 0; i < 10; ++i) {
        dispatch_async(concurrentDispatchQueue, ^{
            [self increaseNum];
        });
    }

}

- (void)increaseNum {
    OSSpinLockLock(&_spinLock);
    NSLog(@"_num = %tu", ++_num);
    OSSpinLockUnlock(&_spinLock);
}

@end
```

### 输出

```txt
_num = 1
_num = 2
_num = 3
_num = 4
_num = 5
_num = 6
_num = 7
_num = 8
_num = 9
_num = 10
```

## 说明

如果不使用 `OSSpinLock` 对临界区进行加锁，则可能的输出为

```txt
_num = 1
_num = 3
_num = 2
_num = 4
_num = 5
_num = 5
_num = 6
_num = 7
_num = 9
_num = 8
```

`OSSpinLock` 自 `iOS 10.0` 开始已经被显式地标记为弃用。

`OSSpinLock` 是一种自旋锁。

