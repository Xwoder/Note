# [ObjC] os_unfair_lock

[TOC]

## 示例程序

### 代码

#### ViewController.m

```objc
#import "ViewController.h"
#import <os/lock.h>

@interface ViewController () {
    os_unfair_lock _unfairLock;
    NSUInteger _num;
}

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    _unfairLock = OS_UNFAIR_LOCK_INIT;

    _num = 0;

    dispatch_queue_t concurrentDispatchQueue = dispatch_queue_create("queue", DISPATCH_QUEUE_CONCURRENT);

    for (NSUInteger i = 0; i < 10; ++i) {
        dispatch_async(concurrentDispatchQueue, ^{
            [self increaseNum];
        });
    }
}

- (void)increaseNum {
    os_unfair_lock_lock(&(_unfairLock));
    NSLog(@"_num = %tu", ++_num);
    os_unfair_lock_unlock(&(_unfairLock));
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

如果不使用 `os_unfair_lock` 对临界区进行加锁，则可能的输出为

```txt
_num = 1
_num = 2
_num = 4
_num = 3
_num = 5
_num = 6
_num = 9
_num = 10
_num = 7
_num = 8
```

在 `OSSpinLock` 被标记为弃用以后，Apple 推荐开发者使用 `os_unfair_lock` 取代之。

