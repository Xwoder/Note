# C++ swap函数之引用实现

```C++
#include <iostream>

using std::cout;
using std::endl;

template<typename T>
void swapValue(T a, T b);

template<typename T>
void swapReference(T &a, T &b);

int main() {
    int a = 10;
    int b = 90;
    cout << "a = " << a << ", b = " << b << endl;
    // Output: a = 10, b = 90

    swapValue(a, b);
    cout << "a = " << a << ", b = " << b << endl;
    // Output: a = 10, b = 90
    
    swapReference(a, b);
    cout << "a = " << a << ", b = " << b << endl;
    // Output: a = 90, b = 10

    return 0;
}

template<typename T>
void swapValue(T a, T b) {
    T const temp = a;
    a = b;
    b = temp;
}

template<typename T>
void swapReference(T &a, T &b) {
    T const temp = a;
    a = b;
    b = temp;
}
```

