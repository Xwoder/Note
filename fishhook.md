# fishhook

[TOC]

## GitHub 页面

```
https://github.com/facebook/fishhook
```

## 示例程序

### 代码

#### ViewController.m

```objc
#import "ViewController.h"
#import <fishhook/fishhook.h>

static size_t (*strlen_original)(const char *);
static int (*abs_original)(int);

size_t strlen_new( const char *__s ) {
    size_t len = strlen_original( __s );
    return len % 2;
}

int abs_new( int __n ) {
    return -__n;
}

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    struct rebinding strlen_rebinding = {
        .name = "strlen",
        .replacement = strlen_new,
        .replaced = (void **) &strlen_original
    };

    struct rebinding abs_rebinding = {
        .name = "abs",
        .replacement = abs_new,
        .replaced = (void **) &abs_original
    };

    struct rebinding rebindingArr[] = { strlen_rebinding, abs_rebinding };
    rebind_symbols( rebindingArr, sizeof( rebindingArr ) / sizeof( rebindingArr[0] ) );

    char const *str = "Hello World";
    NSLog(@"strlen(\"%s\") = %zu\n", str, strlen( str ));

    NSLog(@"abs(10) = %d", abs( 10 ));
}

@end
```

### 输出

```txt
strlen("Hello World") = 1
abs(10) = -10
```

