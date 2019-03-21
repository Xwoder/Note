# ObjC 的 +load 方法

[TOC]

在 `Objective-C` 中，一个类或分类的 `+load` 方法，是当这个类或分类被加载至 `runtime` 的时候被调用的。

同时，`runtime` 加载类或分类的时机在调用 `main` 函数之前，所以 `+load` 方法会在 `main` 函数被调用之前被调用。

可以用一个简单的程序进行例证

```objc
#import <Foundation/Foundation.h>

@interface MyObject : NSObject

@end

@implementation MyObject

+ (void)load {
    NSLog(@"+[MyObject load]");
}

@end

@interface MyObject (Ext)

@end

@implementation MyObject (Ext)

+ (void)load {
    NSLog(@"%s", __func__);
}

@end

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        NSLog(@"main");
    }
    return 0;
}
```

运行上述程序将在控制台得到输出

```txt
+[MyObject load]
+[MyObject(Ext) load]
main
```

下图是 `+load` 方法在 `runtime` 内部的调用流程图

![](http://assets.processon.com/chart_image/5b18faaee4b02e4b26fb6535.png)

整个流程可以人为的分为 2 个阶段

* 准备阶段
* 调用阶段

**准备阶段**的主要工作就是把所有需要调用 `+load` 方法的类或分类与其 `+load` 方法组建为某种结构并按照一定先后次序缓存在内存中的阶段。而其可以按照先后次序分为 2 个小阶段

1. 类的 `+load` 方法的调用的准备阶段
2. 分类的 `+load` 方法的调用的准备阶段

**调用阶段**则是依据在准备阶段确定的先后次序，逐个调用类或分类的 `+load` 方法的阶段。类似的，**调用阶段**也可以分为 2 个小阶段

1. 类的 `+load` 方法的调用阶段
2. 分类的 `+load` 方法的调用阶段

以上 2 个大阶段的先后次序和触发逻辑在 `load_images` 函数里得到了反应，这也是以上 2 个大阶段及其后续过程的开端，其源码如下

```C++
void
load_images(const char *path __unused, const struct mach_header *mh)
{
    // Return without taking locks if there are no +load methods here.
    if (!hasLoadMethods((const headerType *)mh)) return;

    recursive_mutex_locker_t lock(loadMethodLock);

    // Discover load methods
    {
        mutex_locker_t lock2(runtimeLock);
        prepare_load_methods((const headerType *)mh);
    }

    // Call +load methods (without runtimeLock - re-entrant)
    call_load_methods();
}
```

其中 `prepare_load_methods` 函数的实现对应了**准备阶段**的工作，`prepare_load_methods` 函数对应了**调用阶段**的工作。

## 准备阶段

准备阶段的入口函数 `prepare_load_methods` 实现如下

```C++
void prepare_load_methods(const headerType *mhdr)
{
    size_t count, i;

    runtimeLock.assertLocked();

    classref_t *classlist = 
        _getObjc2NonlazyClassList(mhdr, &count);
    for (i = 0; i < count; i++) {
        schedule_class_load(remapClass(classlist[i]));
    }

    category_t **categorylist = _getObjc2NonlazyCategoryList(mhdr, &count);
    for (i = 0; i < count; i++) {
        category_t *cat = categorylist[i];
        Class cls = remapClass(cat->cls);
        if (!cls) continue;  // category for ignored weak-linked class
        realizeClass(cls);
        assert(cls->ISA()->isRealized());
        add_category_to_loadable_list(cat);
    }
}
```

其中的 `schedule_class_load` 函数对应准备阶段的第 1 个小阶段，即类的 `+load` 方法的调用的准备阶段；而 `add_category_to_loadable_list` 函数对应第 2 个小阶段，即分类的 `+load` 方法的调用的准备阶段。

### 第 1 个小阶段

类的 `+load` 方法的调用的准备阶段的 `schedule_class_load` 函数的实现如下

```C++
static void schedule_class_load(Class cls)
{
    if (!cls) return;
    assert(cls->isRealized());  // _read_images should realize

    if (cls->data()->flags & RW_LOADED) return;

    // Ensure superclass-first ordering
    schedule_class_load(cls->superclass);

    add_class_to_loadable_list(cls);
    cls->setInfo(RW_LOADED); 
}
```

可以看到，在接近函数尾部的地方，调用了另一个函数 `add_class_to_loadable_list`，这个函数的主要工作就是将需要调用 `+load` 方法的类及该方法的 `IMP` 构建成一个结构，并缓存在一个数组中。其内部实现将在后面讨论。

但是需要注意的是，在调用该函数之前，其对指定的类父类，递归调用了自身，即如下代码

```C++
schedule_class_load(cls->superclass);
```

这一调用方式，确保了该类的父类会被首先调用 `add_class_to_loadable_list` 函数，从而在调用阶段实现父类的 `+load` 方法会先于子类被调用这一个特性。

上文中的 `add_class_to_loadable_list` 的函数实现如下

```C++
/***********************************************************************
* add_class_to_loadable_list
* Class cls has just become connected. Schedule it for +load if
* it implements a +load method.
**********************************************************************/
void add_class_to_loadable_list(Class cls)
{
    IMP method;

    loadMethodLock.assertLocked();

    method = cls->getLoadMethod();
    if (!method) return;  // Don't bother if cls has no +load method
    
    if (PrintLoading) {
        _objc_inform("LOAD: class '%s' scheduled for +load", 
                     cls->nameForLogging());
    }
    
    if (loadable_classes_used == loadable_classes_allocated) {
        loadable_classes_allocated = loadable_classes_allocated*2 + 16;
        loadable_classes = (struct loadable_class *)
            realloc(loadable_classes,
                              loadable_classes_allocated *
                              sizeof(struct loadable_class));
    }
    
    loadable_classes[loadable_classes_used].cls = cls;
    loadable_classes[loadable_classes_used].method = method;
    loadable_classes_used++;
}
```

其中的 `loadable_classes` 是一个数组，其内缓存的结构类型是 `struct loadable_class`，定义如下

```C++
struct loadable_class {
    Class cls;  // may be nil
    IMP method;
};
```

其中的 `method` 成员存储的就是类的 `+load` 方法的 `IMP`。

### 第 2 个小阶段

在类的 `+load` 方法的调用的准备阶段完成后，会进入第 2 个小阶段，完成分类的 `+load` 方法的调用的准备工作。

这一阶段对应函数 `add_category_to_loadable_list` 的具体实现

```C++
/***********************************************************************
* add_category_to_loadable_list
* Category cat's parent class exists and the category has been attached
* to its class. Schedule this category for +load after its parent class
* becomes connected and has its own +load method called.
**********************************************************************/
void add_category_to_loadable_list(Category cat)
{
    IMP method;

    loadMethodLock.assertLocked();

    method = _category_getLoadMethod(cat);

    // Don't bother if cat has no +load method
    if (!method) return;

    if (PrintLoading) {
        _objc_inform("LOAD: category '%s(%s)' scheduled for +load", 
                     _category_getClassName(cat), _category_getName(cat));
    }
    
    if (loadable_categories_used == loadable_categories_allocated) {
        loadable_categories_allocated = loadable_categories_allocated*2 + 16;
        loadable_categories = (struct loadable_category *)
            realloc(loadable_categories,
                              loadable_categories_allocated *
                              sizeof(struct loadable_category));
    }

    loadable_categories[loadable_categories_used].cat = cat;
    loadable_categories[loadable_categories_used].method = method;
    loadable_categories_used++;
}
```

其大体逻辑与 `add_class_to_loadable_list` 函数的实现相似，只不过数组名为 `loadable_categories`，其元素类型为 `struct loadable_category`

```C++
struct loadable_category {
    Category cat;  // may be nil
    IMP method;
};
```

## 调用阶段

在 `runtime` 中，负责调用类的 `+load` 方法的是 `call_load_methods` 函数，其也是**调用阶段**的入口函数，其实现如下

```C++
void call_load_methods(void)
{
    static bool loading = NO;
    bool more_categories;

    loadMethodLock.assertLocked();

    // Re-entrant calls do nothing; the outermost call will finish the job.
    if (loading) return;
    loading = YES;

    void *pool = objc_autoreleasePoolPush();

    do {
        // 1. Repeatedly call class +loads until there aren't any more
        while (loadable_classes_used > 0) {
            call_class_loads();
        }

        // 2. Call category +loads ONCE
        more_categories = call_category_loads();

        // 3. Run more +loads if there are classes OR more untried categories
    } while (loadable_classes_used > 0  ||  more_categories);

    objc_autoreleasePoolPop(pool);

    loading = NO;
}
```

其内部，对于一个类，通过先后调用 `call_class_loads` 函数和 `call_category_loads` 函数实现对类或分类的 `+load` 方法的调用。



### 第 1 个小阶段

负责调用分类的 `+load` 方法的是 `call_class_loads` 函数，其实现如下

```C++
/***********************************************************************
* call_class_loads
* Call all pending class +load methods.
* If new classes become loadable, +load is NOT called for them.
*
* Called only by call_load_methods().
**********************************************************************/
static void call_class_loads(void)
{
    int i;
    
    // Detach current loadable list.
    struct loadable_class *classes = loadable_classes;
    int used = loadable_classes_used;
    loadable_classes = nil;
    loadable_classes_allocated = 0;
    loadable_classes_used = 0;
    
    // Call all +loads for the detached list.
    for (i = 0; i < used; i++) {
        Class cls = classes[i].cls;
        load_method_t load_method = (load_method_t)classes[i].method;
        if (!cls) continue; 

        if (PrintLoading) {
            _objc_inform("LOAD: +[%s load]\n", cls->nameForLogging());
        }
        (*load_method)(cls, SEL_load);
    }
    
    // Destroy the detached list.
    if (classes) free(classes);
}
```

其核心逻辑如下

```Objective-C++
for (i = 0; i < used; i++) {
     Class cls = classes[i].cls;
     load_method_t load_method = (load_method_t)classes[i].method;
     (*load_method)(cls, SEL_load);
}
```



### 第 2 个小阶段

而负责调用分类的 `+load` 方法的是 `call_category_loads` 函数，其实现如下

```C++
static bool call_category_loads(void)
{
    int i, shift;
    bool new_categories_added = NO;
    
    // Detach current loadable list.
    struct loadable_category *cats = loadable_categories;
    int used = loadable_categories_used;
    int allocated = loadable_categories_allocated;
    loadable_categories = nil;
    loadable_categories_allocated = 0;
    loadable_categories_used = 0;

    // Call all +loads for the detached list.
    for (i = 0; i < used; i++) {
        Category cat = cats[i].cat;
        load_method_t load_method = (load_method_t)cats[i].method;
        Class cls;
        if (!cat) continue;

        cls = _category_getClass(cat);
        if (cls  &&  cls->isLoadable()) {
            if (PrintLoading) {
                _objc_inform("LOAD: +[%s(%s) load]\n", 
                             cls->nameForLogging(), 
                             _category_getName(cat));
            }
            (*load_method)(cls, SEL_load);
            cats[i].cat = nil;
        }
    }

    // Compact detached list (order-preserving)
    shift = 0;
    for (i = 0; i < used; i++) {
        if (cats[i].cat) {
            cats[i-shift] = cats[i];
        } else {
            shift++;
        }
    }
    used -= shift;

    // Copy any new +load candidates from the new list to the detached list.
    new_categories_added = (loadable_categories_used > 0);
    for (i = 0; i < loadable_categories_used; i++) {
        if (used == allocated) {
            allocated = allocated*2 + 16;
            cats = (struct loadable_category *)
                realloc(cats, allocated *
                                  sizeof(struct loadable_category));
        }
        cats[used++] = loadable_categories[i];
    }

    // Destroy the new list.
    if (loadable_categories) free(loadable_categories);

    // Reattach the (now augmented) detached list. 
    // But if there's nothing left to load, destroy the list.
    if (used) {
        loadable_categories = cats;
        loadable_categories_used = used;
        loadable_categories_allocated = allocated;
    } else {
        if (cats) free(cats);
        loadable_categories = nil;
        loadable_categories_used = 0;
        loadable_categories_allocated = 0;
    }

    if (PrintLoading) {
        if (loadable_categories_used != 0) {
            _objc_inform("LOAD: %d categories still waiting for +load\n",
                         loadable_categories_used);
        }
    }

    return new_categories_added;
}
```

而类似的，其核心逻辑如下，与调用类的 `+load` 方法的 `call_class_loads` 函数的核心逻辑相似

```C++
for (i = 0; i < used; i++) {
    Category cat = cats[i].cat;
    load_method_t load_method = (load_method_t)cats[i].method;

    (*load_method)(cls, SEL_load);
}
```

可以看出，不论是对于 `Class` 还是 `Category`，在 `runtime` 自动调用 `+load` 方法时，都是通过 `+load` 方法的函数指针直接调用的，而不是使用 `ObjC` 的消息机制。这也是由 `runtime` 主动调用的类方法中，`+load` 类方法与 `+initialize` 等其他类方法的一个最大不同。

由此也产生了很多不同的现象

1. 在一个类的分类中重写 `+load` 方法，不会影响类中的 `+load` 方法的调用。
2. 如果一个类或分类，没有实现 `+load` 方法，则 `runtime` 不会去调用它们的 `+load` 方法。

对于第 1 点，本文开头的例证程序已经说明了这一点。


对于第 2 点，是因为没有 `+load` 方法可供调用。特别的，如果一个类实现了 `+load` 方法，而其子类没有实现该方法，则 `runtime` 不会去调用子类的 `+load` 方法，更不会因此去调用父类的实现。这一点与由 `runtime` 经由消息机制主动调用的 `+initialize` 方法不同。

下面是一个例证程序

```objc
#import <Foundation/Foundation.h>

@interface Father : NSObject

@end

@implementation Father

+ (void)load {
    NSLog(@"%s", __FUNCTION__);
}

@end

@interface Son : Father

@end

@implementation Son

@end

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        NSLog(@"main");
    }
    return 0;
}
```

运行上述程序将在控制台得到输出

```
+[Father load]
main
```

在 `runtime` 的内部实现中，存在着 2 个结构体数组，分别名为 `loadable_classes` 和 `loadable_categories`，其元素类型分别为 `struct loadable_class` 和 `struct loadable_category`，其内部结构如下

```C++
struct loadable_category {
    Category cat;  // may be nil
    IMP method;
};
```

这两个结构体有一个共同的成员 `IMP method`，将运行时，该成员将被赋值为 `+load` 方法的实现，即一个 `IMP` 类型的值。

获取一个类或分类的 `+load` 方法的实现并分别添加至上述两个数组的过程分别在 `add_class_to_loadable_list` 和 `add_category_to_loadable_list` 函数内完成，其实现如下


在 `add_class_to_loadable_list` 和 `add_category_to_loadable_list` 函数全部调用完毕后，`runtime` 主动调用 `+load` 方法的**准备阶段**就算完成了，在此之后，会进入下一个阶段，即**调用阶段**，在该阶段会调用一个名为 `call_load_methods` 的函数，并在内部又先后调用 `call_class_loads` 和 `call_category_loads` 函数，从而最终调用 `+load` 方法。




