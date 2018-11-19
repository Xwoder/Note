# C++ 变量初始化的方式

`C++` 提供了 2 种方式用于初始化变量。

## 代码

```cpp
#include <iostream>

using namespace std;

int main(int argc, const char *argv[]) {

    // 方式1
    int num1 = 10;

    // 方式2
    int num2(10);

    cout << "num1 = " << num1 << endl;
    cout << "num2 = " << num2 << endl;

    return 0;
}
```

## 输出

```txt
num1 = 10
num2 = 10
```

