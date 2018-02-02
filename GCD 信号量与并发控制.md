# GCD 信号量与并发控制

## 未使用 GCD 信号量控制并发

### 代码

```objc
#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        for (NSUInteger i = 0;  i < 10; ++i) {
            NSLog(@"Task - 1");
        }
    });
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        for (NSUInteger i = 0;  i < 10; ++i) {
            NSLog(@"Task - 2");
        }
    });
}

@end
```

### 输出

```txt
Task - 1
Task - 2
Task - 1
Task - 2
Task - 1
Task - 2
Task - 1
Task - 2
Task - 1
Task - 2
Task - 1
Task - 2
Task - 1
Task - 2
Task - 1
Task - 2
Task - 1
Task - 2
Task - 1
Task - 2
```

## 使用 GCD 信号量控制并发

### 代码

```objc
#import "ViewController.h"

@interface ViewController ()

@property (nonatomic, strong) dispatch_semaphore_t semaphore;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.semaphore = dispatch_semaphore_create(1);

    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        dispatch_semaphore_wait(self.semaphore, DISPATCH_TIME_FOREVER);
        for (NSUInteger i = 0;  i < 10; ++i) {
            NSLog(@"Task - 1");
        }
        dispatch_semaphore_signal(self.semaphore);
    });
    
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
        dispatch_semaphore_wait(self.semaphore, DISPATCH_TIME_FOREVER);
        for (NSUInteger i = 0;  i < 10; ++i) {
            NSLog(@"Task - 2");
        }
        dispatch_semaphore_signal(self.semaphore);
    });
}

@end
```

### 输出

```txt
Task - 1
Task - 1
Task - 1
Task - 1
Task - 1
Task - 1
Task - 1
Task - 1
Task - 1
Task - 1
Task - 2
Task - 2
Task - 2
Task - 2
Task - 2
Task - 2
Task - 2
Task - 2
Task - 2
Task - 2
```

