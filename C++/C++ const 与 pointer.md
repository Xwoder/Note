# C++ const 与 pointer

```C++
#include <cstdlib>

int main() {

    int intArray[] = {1, 3, 5, 7, 9};

    // 普通的指针
    int *p1 = intArray;
    // 可以改变指针指向的位置
    p1 += 2;
    // 可以修改指针指向的值
    *p1 = 2;

    // 指向常量的指针
    int const *p2 = intArray;
    // 可以改变指针指向的位置
    p2 += 2;
    // 不可以修改指针指向的值
    // *p2 = 2;

    // 指针常量
    int *const p3 = intArray;
    // 不可以改变指针指向的位置
    // p3 += 2;
    // 可以修改指针指向的值
    *p3 = 2;

    // 指向常量的指针常量
    int const *const p4 = intArray;
    // 不可以改变指针指向的位置
    // p4 += 2;
    // 不可以修改指针指向的值
    // *p4 = 2;

    return EXIT_SUCCESS;
}
```

