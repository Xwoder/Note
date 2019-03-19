# ObjC 的 +initialize 方法

`+initialize` 方法是类在且仅在第一次接收到消息的时候，由 `runtime` 主动调用的。

`+initialize`  在 `runtime` 层面的最终调用是由 `callInitialize` 函数执行的，其实现如下

```c++
void callInitialize(Class cls)
{
    ((void(*)(Class, SEL))objc_msgSend)(cls, SEL_initialize);
    asm("");
}
```

 由此，可以得知，`+initialize` 方法的最终调用是由消息机制来进行的。

而负责调用 `callInitialize` 函数的是 `_class_initialize` 函数，其实现如下

```C++
void _class_initialize(Class cls)
{
    assert(!cls->isMetaClass());

    Class supercls;
    bool reallyInitialize = NO;

    // Make sure super is done initializing BEFORE beginning to initialize cls.
    // See note about deadlock above.
    supercls = cls->superclass;
    if (supercls  &&  !supercls->isInitialized()) {
        _class_initialize(supercls);
    }
    
    // Try to atomically set CLS_INITIALIZING.
    {
        monitor_locker_t lock(classInitLock);
        if (!cls->isInitialized() && !cls->isInitializing()) {
            cls->setInitializing();
            reallyInitialize = YES;
        }
    }
    
    if (reallyInitialize) {
        // We successfully set the CLS_INITIALIZING bit. Initialize the class.
        
        // Record that we're initializing this class so we can message it.
        _setThisThreadIsInitializingClass(cls);

        if (MultithreadedForkChild) {
            // LOL JK we don't really call +initialize methods after fork().
            performForkChildInitialize(cls, supercls);
            return;
        }
        
        // Send the +initialize message.
        // Note that +initialize is sent to the superclass (again) if 
        // this class doesn't implement +initialize. 2157218
        if (PrintInitializing) {
            _objc_inform("INITIALIZE: thread %p: calling +[%s initialize]",
                         pthread_self(), cls->nameForLogging());
        }

        // Exceptions: A +initialize call that throws an exception 
        // is deemed to be a complete and successful +initialize.
        //
        // Only __OBJC2__ adds these handlers. !__OBJC2__ has a
        // bootstrapping problem of this versus CF's call to
        // objc_exception_set_functions().
#if __OBJC2__
        @try
#endif
        {
            callInitialize(cls);

            if (PrintInitializing) {
                _objc_inform("INITIALIZE: thread %p: finished +[%s initialize]",
                             pthread_self(), cls->nameForLogging());
            }
        }
#if __OBJC2__
        @catch (...) {
            if (PrintInitializing) {
                _objc_inform("INITIALIZE: thread %p: +[%s initialize] "
                             "threw an exception",
                             pthread_self(), cls->nameForLogging());
            }
            @throw;
        }
        @finally
#endif
        {
            // Done initializing.
            lockAndFinishInitializing(cls, supercls);
        }
        return;
    }
    
    else if (cls->isInitializing()) {
        // We couldn't set INITIALIZING because INITIALIZING was already set.
        // If this thread set it earlier, continue normally.
        // If some other thread set it, block until initialize is done.
        // It's ok if INITIALIZING changes to INITIALIZED while we're here, 
        //   because we safely check for INITIALIZED inside the lock 
        //   before blocking.
        if (_thisThreadIsInitializingClass(cls)) {
            return;
        } else if (!MultithreadedForkChild) {
            waitForInitializeToComplete(cls);
            return;
        } else {
            // We're on the child side of fork(), facing a class that
            // was initializing by some other thread when fork() was called.
            _setThisThreadIsInitializingClass(cls);
            performForkChildInitialize(cls, supercls);
        }
    }
    
    else if (cls->isInitialized()) {
        // Set CLS_INITIALIZING failed because someone else already 
        //   initialized the class. Continue normally.
        // NOTE this check must come AFTER the ISINITIALIZING case.
        // Otherwise: Another thread is initializing this class. ISINITIALIZED 
        //   is false. Skip this clause. Then the other thread finishes 
        //   initialization and sets INITIALIZING=no and INITIALIZED=yes. 
        //   Skip the ISINITIALIZING clause. Die horribly.
        return;
    }
    
    else {
        // We shouldn't be here. 
        _objc_fatal("thread-safe class init in objc runtime is buggy!");
    }
}
```

并且通过该函数中的以下代码

```C++
// Make sure super is done initializing BEFORE beginning to initialize cls.
// See note about deadlock above.
supercls = cls->superclass;
if (supercls  &&  !supercls->isInitialized()) {
    _class_initialize(supercls);
}
```

可以得知，在调用一个类的 `+initialize` 方法之前，会对该类的父类递归调用该函数自身，从而确保父类的 `+initialize` 方法优先被调用。

通过函数的如下代码

```C++
// Try to atomically set CLS_INITIALIZING.
{
    monitor_locker_t lock(classInitLock);
    if (!cls->isInitialized() && !cls->isInitializing()) {
        cls->setInitializing();
        reallyInitialize = YES;
    }
}
```

可以得知，如果一个类既没有被初始化过，也不是正处于初始化中，则会将该类标记为正在初始化中，并标记变量 `reallyInitialize` 的值为 `YES`，从而根据该变量在后面的代码中触发实际调用 `+initialize` 方法，也就是去调用 `callInitialize` 函数。

在 `callInitialize` 函数调用完成后，会调用 `lockAndFinishInitializing` 函数完成后续操作并标记该类已初始化完成。

```C++
{
    // Done initializing.
    lockAndFinishInitializing(cls, supercls);
}
```

在该函数内部，最终会调用 `objc_class` 结构体的 `setInitialized` 成员函数，标记初始化完成。

由此，如果一个类的初始化已经完成，在后续的执行中，对于该类再次调用 `_class_initialize` 函数，但是由于该类已经标记初始化完成，所以 `reallyInitialize` 变量的值为假，所以后续会跳过针对该类的初始化流程，从而防止一个类被多次初始化。

需要注意的是，`+initialize` 方法中的实现的逻辑，并不就是类的初始化流程，其仅仅是在类的初始化过程中，由 `runtime` 主动调用的一个普通的类方法。

但是，需要说明的是，虽然一个类只会被初始化一次，且在这个过程中也只会调用一次该类的 `+initialize` 方法，但并不代表一个类的 `+initialize` 方法只会被调用一次。理解这一点需要认识到：对 `+initialize` 方法的调用是通过消息机制进行的；该方法在本质上而言也是一个类方法。

对于一个存在着继承关系的类层次，如下图所示，

![](http://assets.processon.com/chart_image/5c8f69cbe4b02b2ce49cce0d.png)

其中父类 `Father` 重写了 `+initialize` 方法，而其子类 `Son` 没有重写该方法。

当我们向 `Son` 类发送第一条消息时，`runtime` 会确保其父类 `Father` 的 `+initialize` 方法被首先调用，然后再调用自己的 `+initialize` 方法。但是，`Son` 自己没有实现或重写  `+initialize` 方法，按照 `ObjC` 的消息机制调用方法的规则，其会尝试调用父类的 `+initialize` 方法，而其父类 `Father` 类又实现了该方法，所以导致父类的 `+initialize` 方法被调用了 2 次。

示例代码如下

```objective-c
#import <Foundation/Foundation.h>

/** Father 类 */
@interface Father : NSObject

@end

@implementation Father

+ (void)initialize {
    NSLog(@"+[Father initialize]");
}

@end

/** Son 类 -> Father 类 */
@interface Son : Father

@end

@implementation Son

@end

/** main 函数  */
int main(int argc, const char * argv[]) {
    @autoreleasepool {
        [Son class];
    }
    return 0;
}
```

运行以上代码将输出

```txt
+[Father initialize]
+[Father initialize]
```

对于 `+initialize` 方法会被调用多次的特点，`Apple` 的开发文档中有专门的说明。

下图是 `+initialize` 方法在 `runtime` 内部的调用流程图

![](http://assets.processon.com/chart_image/5c904e5ae4b0f88919b2ea4d.png)

位于流程图起点的 `main` 函数表示的是，在 `main` 函数内部对某个类发送了一个消息。

