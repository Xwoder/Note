# [ObjC] 枚举的前向声明

[TOC]

首先，对于一个枚举 `NS_ENUM`，如

```objc
typedef NS_ENUM(NSUInteger, ConnectState) {
    ConnectStateDisconnected,
    ConnectStateConnecting,
    ConnectStateConnected,
};
```

则与之对应的前向声明为

```objc
typedef NS_ENUM(NSUInteger, ConnectState);
```

在预处理阶段，其将被展开为

```objc
typedef enum __attribute__((enum_extensibility(open))) ConnectState : NSUInteger ConnectState;
enum ConnectState : NSUInteger;
```

`NS_OPTIONS` 是一种特殊的枚举，所以也支持前向声明，例如

```objc
typedef NS_OPTIONS(NSUInteger, PermittedDirection) {
    PermittedDirectionUp = 1 << 0,
    PermittedDirectionLeft = 1 << 1,
    PermittedDirectionDown = 1 << 2,
    PermittedDirectionRight = 1 << 3,
};
```

对于该 `NS_OPTIONS`， 其对应的前向声明为

```objc
typedef NS_OPTIONS(NSUInteger, PermittedDirection);
```

在预处理阶段，其将被展开为

```objc
typedef enum __attribute__((flag_enum,enum_extensibility(open))) PermittedDirection : NSUInteger PermittedDirection;
enum PermittedDirection : NSUInteger;
```

