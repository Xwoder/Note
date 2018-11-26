# C++ 字符串

## 原始字符串字面值

使用该语法定义的字符串，不会对其中的字符进行转译。

示例如下

```cpp
#include <iostream>
#include <string>

using namespace std;

int main(int argc, const char *argv[]) {

    string path1 = "C:\\Windows";
    cout << path1 << endl;
    
    string path2 = R"(C:\Windows)";
    cout << path2 << endl;

    return 0;
}
```

上述程序将输出

```txt
C:\Windows
C:\Windows
```


