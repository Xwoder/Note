# C++ 返回引用的函数

```C++
#include <iostream>

using std::cout;
using std::endl;

class Number {
public:
    Number(int n) : n(n) {}

    int getValue() const {
        return n;
    }

    void setValue(int n) {
        Number::n = n;
    }

    Number &inc(void) {
        setValue(getValue() + 1);
        return *this;
    }

private:
    int n;
};

int main() {
    Number number(0);
    cout << number.getValue() << endl;
    // Output: 0

    number.inc();
    cout << number.getValue() << endl;
    // Output: 1

    number.inc().inc();
    cout << number.getValue() << endl;
    // Output: 3

    return 0;
}
```