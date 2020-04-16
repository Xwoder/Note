# [ObjC][Runtime] +initialize 方法

[TOC]

## 示例程序

### 代码

#### Dog 类

```objc
#import <Foundation/Foundation.h>

@interface Dog : NSObject

@end

@implementation Dog

+ (void)initialize {
    NSLog(@"%s", __FUNCTION__);
}

@end
```

#### main.m

```objc
#import <Foundation/Foundation.h>
#import "Dog.h"

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        [Dog new];
    }
    return 0;
}
```

### 输出

```txt
Hello, World!
+[Dog initialize]
```

## 说明

### 结论1

> `+initialize` 方法会所在类第一次接收到消息的时候调用

修改示例程序的中的 `main` 函数的实现

```objc
int main(int argc, const char * argv[]) {
    @autoreleasepool {
        NSLog(@"Hello, World!");

        [Dog new];
        [Dog new];
    }
    return 0;
}
```

运行程序将得到输出

```txt
Hello, World!
+[Dog initialize]
```

在 `main` 函数中，调用了 2 次 `Dog` 类的 `+new` 方法，而在输出中，只显示了 1 次来自 `+initialize` 方法的打印，例证了本结论。

想要从源码层面证明该结论，涉及到了 `ObjC` 内部的消息机制的实现原理，而这一部分并未开源。

目前，根据 `ObjC` 版本号为 `723` 的开源代码，可以知道，`+initialize` 方法第一次的调用栈为

```
#0  0x0000000100000e6e in +[Dog initialize] at Project/debug-objc/Dog.m:13
#1  0x0000000100310fbf in _CALLING_SOME_+initialize_METHOD at Project/runtime/objc-initialize.mm:372
#2  0x00000001002feb5a in ::_class_initialize(Class) at Project/runtime/objc-initialize.mm:537
#3  0x000000010032c67d in ::lookUpImpOrForward(Class, SEL, id, bool, bool, bool) at Project/runtime/objc-runtime-new.mm:4632
#4  0x00000001002fe9c2 in ::_class_lookupMethodAndLoadCache3(id, SEL, Class) at Project/runtime/objc-runtime-new.mm:4573
#5  0x00000001002fe514 in _objc_msgSend_uncached at Project/runtime/Messengers.subproj/objc-msg-x86_64.s:1142
```

### 结论2

> `+initialize` 方法的调用是基于 `ObjC` 的消息机制

在 `Runtime` 源代码中，最终负责调用 `+initialize` 方法的是 `callInitialize` 函数

```c
void callInitialize(Class cls)
{
    ((void(*)(Class, SEL))objc_msgSend)(cls, SEL_initialize);
    asm("");
}
```

由此可知，`+initialize` 方法是利用 `objc_msgSend` 函数基于消息机制调用的

### 结论3

> 如果分类实现了 `+initialize` 方法，在调用上，会覆盖父类的 `+initialize` 方法

在示例代码的基础上，为 `Dog` 类新增一个分类 `ExtA`

```objc
#import "Dog.h"

@interface Dog (ExtA)

@end

@implementation Dog (ExtA)

+ (void)initialize {
    NSLog(@"%s", __FUNCTION__);
}

@end
```

运行程序会得到输出

```txt
Hello, World!
+[Dog(ExtA) initialize]
```

这一特性与 `ObjC` 的消息机制的内部实现有关，在此暂不论述。

### 结论4

> 一个类的 `+initialize` 方法可能会被调用多次

这一特性也与 `ObjC` 的消息机制的内部实现有关，将 `+initialize` 视为一个普通的类方法会很容易理解这一特性。

下面这种场景就是该结论的一个展现

> 如果子类没有实现 `+initialize` 方法，而当需要调用子类的`+initialize`方法时，会调用父类的 `+initialize` 方法。

下面将用代码验证次场景。

首先，为 `Dog` 类新增一个名为 `YellowDog` 的子类

```objc
#import "Dog.h"

@interface YellowDog : Dog

@end

@implementation YellowDog

@end
```

该子类不实现 `+initialize` 方法

然后，修改 `main` 函数的代码为

```objc
int main(int argc, const char * argv[]) {
    @autoreleasepool {
        NSLog(@"Hello, World!");
        [Dog new];
        [YellowDog new];
    }
    return 0;
}
```

运行该程序将得到输出

```txt
Hello, World!
+[Dog(ExtA) initialize]
+[Dog(ExtA) initialize]
```

输出的第 2 行是由 `main` 函数代码中的

```objc
[Dog new];
```

语句导致的；而输出的第 3 行是由

```objc
[YellowDog new];
```

语句导致的。

调用 `YellowDog` 类对象的 `new` 方法，而类又没有实现 `+initialize` 方法，此时根据 `ObjC` 的消息机制的实现原理，会在类对象的 `superclass` 指针指向的父类对象中查找 `+initialize` 方法的实现。如果存在，则调用之；如果不存在，则会再次根据父类对象的 `superclass` 指针指向的父类对象的父类对象中去查找 `+initialize` 方法的实现，直至最终调用成功或失败。


