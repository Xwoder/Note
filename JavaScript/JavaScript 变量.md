# JavaScript 变量与常量

[TOC]

## 变量

### 使用 `var` 定义

使用 `var` 关键字定义变量

```javascript
var a = 10;
a = 20;
```

使用 `var` 关键字定义的变量的作用域要么是**全局作用域**，要么是**函数作用域**，并且可以重复声明

```javascript
{
    var a = 10;
    var a = 20;
}
console.log(a);
```

上述代码可以正常运行，输出变量的值。

### 使用 `let` 定义

使用 let 关键字定义的变量具有块作用域。下面的代码，运行将会报错：

```javascript
{
    let a = 10;
}
console.log(a);
```

下面的代码由于重复定义了变量，运行也会报错：

```

let a = 10;
let a = 20;
```

## 常量

使用 `const` 关键字定义变量，常量的值不能修改

```javascript
const a = 10;
```

## 弱类型

不论是常量还是变量，都是**弱类型**。

```javascript
let value = 10;
value = "Hello World";
value = 3.14;
```

