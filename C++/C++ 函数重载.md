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