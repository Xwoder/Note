# [ObjC][Runtime] class_addMethod

[TOC]

## 添加实例方法

### 示例程序

#### 代码

##### Dog 类

```objc
#import <Foundation/Foundation.h>

@interface Dog : NSObject

- (void)instanceMethod;

@end

@implementation Dog

@end
```

> 注意
> 
> 在 Dog 类中没有方法的具体实现

##### ViewController.m

```objc
#import "ViewController.h"
#import "Dog.h"
#import <objc/runtime.h>

@implementation ViewController

- (void)cusInstanceMethod {
    NSLog(@"-[Dog instanceMethod]");
}

- (void)viewDidLoad {
    [super viewDidLoad];

    Dog *dog = [[Dog alloc] init];
    Class dogClass = [Dog class];

    Method method = class_getInstanceMethod(dogClass, @selector(instanceMethod));
    const char *typeEncoding = method_getTypeEncoding(method);

    Method cusInstanceMethod = class_getInstanceMethod([ViewController class], @selector(cusInstanceMethod));
    IMP cusInstanceMethodImp = method_getImplementation(cusInstanceMethod);

    class_addMethod(dogClass, @selector(instanceMethod), cusInstanceMethodImp, typeEncoding);

    NSLog(@"After class_addMethod():");
    [dog instanceMethod];

}

@end
```

####  输出

```console
After class_addMethod():
-[Dog instanceMethod]
```

### 说明

上例中，通过 `class_addMethod` 函数将一个 C 函数添加到 `Dog` 类中作为 `-instanceMethod` 方法的实现。

不仅限于此，也可以将一个 `ObjC` 方法添加到某个类中，作为指定方法的实现。代码如下

```objc
#import "ViewController.h"
#import "Dog.h"
#import <objc/runtime.h>

@implementation ViewController

- (void)cusInstanceMethod {
    NSLog(@"-[Dog instanceMethod]");
}

- (void)viewDidLoad {
    [super viewDidLoad];

    Dog *dog = [[Dog alloc] init];
    Class dogClass = [Dog class];
    SEL sel = @selector(instanceMethod);

    Method method = class_getInstanceMethod(dogClass, sel);
    const char *typeEncoding = method_getTypeEncoding(method);

    Method cusInstanceMethod = class_getInstanceMethod([ViewController class], @selector(cusInstanceMethod));
    IMP cusInstanceMethodImp = method_getImplementation(cusInstanceMethod);

    class_addMethod(dogClass, sel, cusInstanceMethodImp, typeEncoding);

    NSLog(@"After class_addMethod():");
    [dog instanceMethod];

}

@end
```

## 添加类方法

### 示例程序

#### 代码

##### Dog 类

```objc
#import <Foundation/Foundation.h>

@interface Dog : NSObject

+ (void)classMethod;

@end

@implementation Dog

@end
```

##### ViewController.m

```objc
#import "ViewController.h"
#import "Dog.h"
#import <objc/runtime.h>

@implementation ViewController

+ (void)cusClassMethod {
    NSLog(@"+[Dog classMethod]");
}

- (void)viewDidLoad {
    [super viewDidLoad];

    Class dogMetaClass = object_getClass([Dog class]);
    SEL dogClassMethod = @selector(classMethod);

    Method method = class_getClassMethod(dogMetaClass, dogClassMethod);
    const char *typeEncoding = method_getTypeEncoding(method);

    Method cusClassMethod = class_getClassMethod([ViewController class], @selector(cusClassMethod));
    IMP cusClassMethodImp = method_getImplementation(cusClassMethod);

    class_addMethod(dogMetaClass, dogClassMethod, cusClassMethodImp, typeEncoding);

    NSLog(@"After class_addMethod():");
    [Dog classMethod];
}

@end
```

#### 输出

```txt
After class_addMethod():
+[Dog classMethod]
```

### 说明

代码逻辑与添加实例方法的示例程序中的代码基本相同。


