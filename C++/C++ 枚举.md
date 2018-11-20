# C++ 枚举

## 枚举类

示例如下

```cpp
#include <iostream>

enum class Days {
    Mon,
    Tues,
    Wed,
    Thur,
    Fri,
    Sat,
    Sun,
};

int main(int argc, const char *argv[]) {

    Days d = Days::Mon;

    return 0;
}
```


