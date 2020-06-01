# C++ 函数重载

```C++
#include <iostream>
#include <cstdlib>

using std::cout;
using std::endl;

void max(char a, char b);

void max(short a, short b);

void max(int a, int b);

void max(long a, long b);

void max(float a, float b);

void max(double a, double b);

int main() {
    char ch1 = 10, ch2 = 90;
    max(ch1, ch2);

    short short1 = 10, short2 = 90;
    max(short1, short2);

    int int1 = 10, int2 = 90;
    max(int1, int2);

    long long1 = 10, long2 = 90;
    max(long1, long2);

    float float1 = 10, float2 = 90;
    max(float1, float2);

    double double1 = 10, double2 = 90;
    max(double1, double2);

    return EXIT_SUCCESS;
}

void max(char a, char b) {
    cout << "void max(char, char)" << endl;
}

void max(short a, short b) {
    cout << "void max(short, short)" << endl;
}

void max(int a, int b) {
    cout << "void max(int, int)" << endl;
}

void max(long a, long b) {
    cout << "void max(long, long)" << endl;
}

void max(float a, float b) {
    cout << "void max(float, float)" << endl;
}

void max(double a, double b) {
    cout << "void max(double, double)" << endl;
}
```

## 重载函数的二义性

### 由默认参数引发的二义性

```C++
#include <cstdlib>

int sum(int a, int b);

int sum(int a, int b, int c = 0);

int main() {

    sum(10, 20);

    return EXIT_SUCCESS;
}

int sum(int a, int b) {
    return 0;
}

int sum(int a, int b, int c) {
    return 0;
}
```

编译上述程序，将报告类似如下内容的错误

> error: call to 'sum' is ambiguous

### 由引用参数引发的二义性

```C++
#include <cstdlib>

void print(int n);

void print(int &n);

int main() {

    int n = -1;
    print(n);

    return EXIT_SUCCESS;
}

void print(int n) {
}

void print(int &n) {
}
```

编译上述程序，将报告类似如下内容的错误

> error: call to 'sum' is ambiguous