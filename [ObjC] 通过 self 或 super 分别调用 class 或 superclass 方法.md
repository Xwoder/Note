# [ObjC] 通过 self 或 super 分别调用 class 或 superclass 方法

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

#### YellowDog 类

```objc
#import "Dog.h"

@interface YellowDog : Dog

@end

@implementation YellowDog

- (instancetype)init {
    self = [super init];
    if (self) {
        NSLog(@"[self class] = %@", [self class]);
        NSLog(@"[self superclass] = %@", [self superclass]);
        NSLog(@"[super class] = %@", [super class]);
        NSLog(@"[super superclass] = %@", [super superclass]);
    }
    return self;
}

@end
```

#### main.m

```objc
#import <Foundation/Foundation.h>
#import "YellowDog.h"

int main(int argc, const char *argv[]) {
    @autoreleasepool {
        [[YellowDog alloc] init];
    }
    return 0;
}
```

### 输出

```objc
1. [self class] = YellowDog
2. [self superclass] = Dog
3. [super class] = YellowDog
4. [super superclass] = Dog
```

## 说明

使用 `clang` 指令将 `YellowDog` 的实现部分重写为 `C++`

```bash
$ xcrun -sdk iphoneos clang -arch arm64 -rewrite-objc YellowDog.m -o YellowDog-arm64.cpp
```

将该指令生成的 `YellowDog-arm64.cpp` 文件简化，并对照 `ObjC` 代码中的实现，可以得到

```cpp
objc_msgSend(self, sel_registerName("class"));
objc_msgSend(self, sel_registerName("superclass"));
objc_msgSendSuper((__rw_objc_super){self, class_getSuperclass(objc_getClass("YellowDog"))}, sel_registerName("class"));
objc_msgSendSuper((__rw_objc_super){self, class_getSuperclass(objc_getClass("YellowDog"))}, sel_registerName("superclass"));
```

### 通过 `self` 调用方法

#### 通过 `self` 调用 `-class` 方法

根据对应关系可以知道

```objc
[self class]
```

对应

```cpp
objc_msgSend(self, sel_registerName("class"));
```

`objc_msgSend` 函数向 `self` 对象发送了消息，并指定处理消息的 `SEL` 为名为 `class` 的方法。该方法的实现为


```objc++
- (Class)class {
    return object_getClass(self);
}
```

此时的 `self` 为 `YellowDog` 类的实例对象，于是该方法返回 `YellowDog` 类对象。

#### 通过 `self` 调用 `-superclass` 方法

根据对应关系可以知道

```objc
[self superclass]
```

对应

```cpp
objc_msgSend(self, sel_registerName("superclass"))
```

此时，处理消息的 `SEL` 为名为 `superclass` 的方法，其实现为

```objc++
- (Class)superclass {
    return [self class]->superclass;
}
```

其返回的是 `self` 对应的类对象的 `superclass` 指针指向的父类对象。本例中，`self` 为 `YellowDog` 类的实例对象，所以其最终返回的是 `YellowDog` 类对象的父类对象，即 `Dog`类对象。

### 通过 `super` 调用方法

#### 通过 `super` 调用 `-class` 方法

类似的，我们可以知道

```objc
[super class]
```

对应

```cpp
objc_msgSendSuper((__rw_objc_super){self, class_getSuperclass(objc_getClass("YellowDog"))}, sel_registerName("class"))
```

使用 `super` 调用方法，使用的是 `objc_msgSendSuper` 函数，其原型为

```c
id objc_msgSendSuper(struct objc_super *super, SEL op, ...);
```

其第 1 个参数为一个类型为 `objc_super` 的结构体指针，第 2 个参数为用于处理消息的 `SEL`。

而 `objc_super` 结构体的定义为

```c
struct objc_super {
    /// Specifies an instance of a class.
    __unsafe_unretained _Nonnull id receiver;

    /// Specifies the particular superclass of the instance to message. 
    __unsafe_unretained _Nonnull Class super_class;
};
```

其第 1 个成员 `receiver` 指定消息接受者，第 2 个成员 `super_class` 则指定消息接受者的父类，它是方法查找的起点。

在实际调用时，`receiver` 成员被赋值为 `self`，`super_class` 成员则被赋值为 `self` 对应的类对象的父类对象。

本例中，方法查找的起点是父类对象，即 `Dog` 类对象，但是 `-class` 方法的实际定义存在于 `NSObject` 类中，所以在 `Dog` 类对象中未能查找到方法实现后，需要在其 `superclass` 指针指向的父类对象中查找，在查找到 `-class` 方法的实现以后，对 `objc_super` 结构体中的 `receiver` 成员，即消息接受者，调用 `-class` 方法。本例中，`self` 为 `YellowDog` 实例对象，所以 `-class` 方法返回的是消息接受者的类对象，即 `YellowDog` 类对象。

#### 通过 `super` 调用 `-superclass` 方法

对于

```objc
[super superclass]
```

则同样可以解释。

在 `-superclass` 方法中，`self` 在本例中实际为 `YelloDog` 实例对象，所以最终返回的是实例对象的类对象的父类对象，即 `Dog` 类对象。



