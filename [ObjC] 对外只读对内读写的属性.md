# [ObjC] 对外只读对内读写的属性

[TOC]

## 示例程序

### 代码

#### Person.h

```objc
#import <Foundation/Foundation.h>

@interface Person : NSObject

@property (nonatomic, copy, readonly) NSString *name;

@property (nonatomic, assign, readonly) NSUInteger age;

- (instancetype)initWithName:(NSString *)name age:(NSUInteger)age;

@end
```

#### Person.m

```objc
#import "Person.h"

@interface Person ()

@property (nonatomic, copy) NSString *name;

@property (nonatomic, assign) NSUInteger age;

@end

@implementation Person

- (instancetype)initWithName:(NSString *)name age:(NSUInteger)age {
    self = [super init];
    if (self) {
        _name = [name copy];
        _age = age;
    }
    return self;
}

@end
```


## 说明

在头文件中暴露的 2 个属性，均被指定为只读属性，这意味着，在类的实现部分，只能使用这 2 个属性对应的成员变量和 get 方法来访问它们。

而为了在实现部分也能使用属性自动生成的 set 方法，可以在类的私有扩展或分类中，将属性重新定义为读写属性。

在 `UIKit` 框架中也可以看到这种设计。在 `UIGestureRecognizerSubclass.h` 头文件中，为 `UIGestureRecognizer` 类扩展了一个的分类，并在其中将原本定义于类中的只读属性

```objc
@property (nonatomic, readonly) UIGestureRecognizerState state;
```

重新定义为读写属性

```objc
@property (nonatomic, readwrite) UIGestureRecognizerState state;
```
