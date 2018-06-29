# [ObjC] dispatch_barrier_async 与多读单写

[TOC]

## 示例程序

### 代码

#### ViewController.m

```objc
#import "ViewController.h"

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    dispatch_queue_t concurrentDispatchQueue = dispatch_queue_create("rwQueue", DISPATCH_QUEUE_CONCURRENT);

    for (NSUInteger i = 0; i < 10; ++i) {

        dispatch_async(concurrentDispatchQueue, ^{
            [self read];
        });

        dispatch_async(concurrentDispatchQueue, ^{
            [self read];
        });

        dispatch_barrier_async(concurrentDispatchQueue, ^{
            [self write];
        });

        dispatch_barrier_async(concurrentDispatchQueue, ^{
            [self write];
        });
    }
}

- (void)read {
    [NSThread sleepForTimeInterval:1];
    NSLog(@"%s", __FUNCTION__);
}

- (void)write {
    [NSThread sleepForTimeInterval:1];
    NSLog(@"%s", __FUNCTION__);
}

@end
```

### 输出

```txt
11:26:27 -[ViewController read]
11:26:27 -[ViewController read]

11:26:28 -[ViewController write]

11:26:29 -[ViewController write]

11:26:30 -[ViewController read]
11:26:30 -[ViewController read]

11:26:31 -[ViewController write]

11:26:32 -[ViewController write]

11:26:33 -[ViewController read]
11:26:33 -[ViewController read]

11:26:34 -[ViewController write]

11:26:35 -[ViewController write]

11:26:36 -[ViewController read]
11:26:36 -[ViewController read]

11:26:37 -[ViewController write]

11:26:38 -[ViewController write]

11:26:39 -[ViewController read]
11:26:39 -[ViewController read]

11:26:40 -[ViewController write]

11:26:41 -[ViewController write]

11:26:42 -[ViewController read]
11:26:42 -[ViewController read]

11:26:43 -[ViewController write]

11:26:44 -[ViewController write]

11:26:45 -[ViewController read]
11:26:45 -[ViewController read]

11:26:46 -[ViewController write]

11:26:47 -[ViewController write]

11:26:48 -[ViewController read]
11:26:48 -[ViewController read]

11:26:49 -[ViewController write]

11:26:50 -[ViewController write]

11:26:51 -[ViewController read]
11:26:51 -[ViewController read]

11:26:52 -[ViewController write]

11:26:53 -[ViewController write]

11:26:54 -[ViewController read]
11:26:54 -[ViewController read]

11:26:55 -[ViewController write]

11:26:56 -[ViewController write]
```

为便于理解，输出格式经过调整和分组。连续的多行打印表示它们几乎是在同一时间打印的。单独的一行打印则表示，该打印距离上一次打印间隔了一段时间。

连续输出的

```txt
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

需要注意的是，指定给 `dispatch_barrier_async` 函数的队列，应该是一个使用 `dispatch_queue_create` 函数创建的并发队列，而不应该是串行队列或全局并发队列，否则该函数的表现将类似于 `dispatch_async` 函数。

想要实现同样的效果还可以使用 `pthread_rwlock` 锁。

