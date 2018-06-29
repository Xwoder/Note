# [ObjC] pthread_rwlock 与多读单写

[TOC]

## 示例程序

### 代码

#### ViewController.m

```objc
#import "ViewController.h"
#import <pthread.h>

@interface ViewController ()

@property (nonatomic, assign) pthread_rwlock_t rwLock;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    pthread_rwlock_init(&_rwLock, NULL);
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event {

    for (NSUInteger i = 0; i < 10; ++i) {
        dispatch_async(dispatch_queue_create("readQueue1", DISPATCH_QUEUE_CONCURRENT), ^{
            [self read];
        });

        dispatch_async(dispatch_queue_create("readQueue2", DISPATCH_QUEUE_CONCURRENT), ^{
            [self read];
        });

        dispatch_async(dispatch_queue_create("writeQueue1", DISPATCH_QUEUE_CONCURRENT), ^{
            [self write];
        });

        dispatch_async(dispatch_queue_create("writeQueue2", DISPATCH_QUEUE_CONCURRENT), ^{
            [self write];
        });
    }
}

- (void)read {
    pthread_rwlock_rdlock(&_rwLock);
    NSLog(@"%s", __FUNCTION__);
    [NSThread sleepForTimeInterval:1];
    pthread_rwlock_unlock(&_rwLock);
}

- (void)write {
    pthread_rwlock_wrlock(&_rwLock);  
    NSLog(@"%s", __FUNCTION__);
    [NSThread sleepForTimeInterval:1];
    pthread_rwlock_unlock(&_rwLock);
}

- (void)dealloc {
    pthread_rwlock_destroy(&_rwLock);
}

@end
```

### 输出

```txt
11:11:09 -[ViewController read]
11:11:09 -[ViewController read]

11:11:10 -[ViewController write]

11:11:11 -[ViewController write]

11:11:12 -[ViewController read]
11:11:12 -[ViewController read]

11:11:13 -[ViewController write]

11:11:14 -[ViewController write]

11:11:15 -[ViewController read]
11:11:15 -[ViewController read]

11:11:16 -[ViewController write]

11:11:18 -[ViewController write]

11:11:19 -[ViewController read]
11:11:19 -[ViewController read]

11:11:20 -[ViewController write]

11:11:21 -[ViewController write]

11:11:22 -[ViewController read]
11:11:22 -[ViewController read]

11:11:23 -[ViewController write]

11:11:24 -[ViewController write]

11:11:25 -[ViewController read]
11:11:25 -[ViewController read]

11:11:26 -[ViewController write]

11:11:27 -[ViewController write]

11:11:28 -[ViewController read]
11:11:28 -[ViewController read]

11:11:29 -[ViewController write]

11:11:30 -[ViewController write]

11:11:31 -[ViewController read]
11:11:31 -[ViewController read]

11:11:32 -[ViewController write]

11:11:33 -[ViewController write]

11:11:34 -[ViewController read]
11:11:34 -[ViewController read]

11:11:35 -[ViewController write]

11:11:36 -[ViewController write]

11:11:37 -[ViewController read]
11:11:37 -[ViewController read]

11:11:38 -[ViewController write]

11:11:39 -[ViewController write]
```

为便于理解，输出格式经过调整和分组。连续的多行打印表示它们几乎是在同一时间打印的。单独的一行打印则表示，该打印距离上一次打印间隔了一段时间。

连续输出的

```objc
-[ViewController read]
```

表示有两个或以上的读操作同时进行；而

```objc
-[ViewController write]
```

没有连续输出，因为不允许在同一时间进行多个写操作。

## 说明

示例程序实现了如下功能

* 同一时间，只能有一个线程进行写的操作
* 同一时间，允许有多个线程进行读的操作
* 同一时间，不允许既有写的操作，又有读的操作


