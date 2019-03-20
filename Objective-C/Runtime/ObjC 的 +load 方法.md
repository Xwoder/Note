# ObjC 的 +load 方法

在 `Objective-C` 中，一个类或分类的 `+load` 方法，是当这个类或分类被加载至 `runtime` 的时候调用的。 

在 `Runtime` 自动调用 `+load` 方法时，是通过 `+load` 方法的函数指针直接调用的。

负责调用类的 `+load` 方法的是 `call_class_loads` 函数，其实现如下

```Objective-C++
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

而负责调用分类的 `+load` 方法的是 `call_category_loads` 函数，其实现如下

```Objective-C++
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

对于 `call_class_loads` 函数，其核心逻辑如下

```Objective-C++
for (i = 0; i < used; i++) {
     Class cls = classes[i].cls;
     load_method_t load_method = (load_method_t)classes[i].method;
     (*load_method)(cls, SEL_load);
}
```

而类似的，`call_category_loads` 函数，其核心逻辑如下

```Objective-C++
for (i = 0; i < used; i++) {
    Category cat = cats[i].cat;
    load_method_t load_method = (load_method_t)cats[i].method;

    (*load_method)(cls, SEL_load);
}
```

可以看出，不论是对于 `Class` 还是 `Category`，均是通过获得其对应的 `+load` 方法的函数地址，然后直接调用的。

下图是 `+load` 方法在 `runtime` 内部的调用流程图

![](http://assets.processon.com/chart_image/5b18faaee4b02e4b26fb6535.png)

