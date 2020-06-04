# C++ 自增减运算符重载

[TOC]

## 成员函数实现

### 代码

#### MyInteger.h

```C++
class MyInteger {

public:
#pragma mark - 构造函数

    MyInteger(int n);

    MyInteger();

#pragma mark - Getter & Setter

    int getN(void) const;

    void setN(int n);

#pragma mark - 自增减运算符

    /* 重载前置自增运算符 */
    MyInteger &operator++();

    /* 重载前置自减运算符 */
    MyInteger &operator--();

    /* 重载后置自增运算符 */
    MyInteger operator++(int i);

    /* 重载后置自减运算符 */
    MyInteger operator--(int i);

private:
    int n;
};
```

#### MyInteger.cpp

```C++
#include "MyInteger.h"

int MyInteger::getN(void) const {
    return n;
}

void MyInteger::setN(int n) {
    this->n = n;
}

MyInteger::MyInteger(int n) : n(n) {}

MyInteger::MyInteger() : MyInteger(0) {}

MyInteger &MyInteger::operator++() {
    setN(getN() + 1);
    return *this;
}

MyInteger &MyInteger::operator--() {
    setN(getN() - 1);
    return *this;
}

MyInteger MyInteger::operator++(int i) {
    MyInteger temp(*this);
    setN(getN() + 1);
    return temp;
}

MyInteger MyInteger::operator--(int i) {
    MyInteger temp(*this);
    setN(getN() - 1);
    return temp;
}
```

#### Main.cpp

```C++
#include <iostream>
#include "MyInteger.h"

using std::cout;
using std::endl;

using namespace std;

int main() {

    MyInteger integer = MyInteger();
    cout << integer.getN() << endl;

    cout << "调用前置自增运算符" << endl;
    ++integer;
    cout << integer.getN() << endl;

    cout << "调用前置自减运算符" << endl;
    --integer;
    cout << integer.getN() << endl;

    cout << "调用后置自增运算符" << endl;
    MyInteger old1 = integer++;
    cout << old1.getN() << endl;
    cout << integer.getN() << endl;

    cout << "调用后置自减运算符" << endl;
    MyInteger old2 = integer--;
    cout << old2.getN() << endl;
    cout << integer.getN() << endl;

    return 0;
}
```

### 输出

```text
0
调用前置自增运算符
1
调用前置自减运算符
0
调用后置自增运算符
0
1
调用后置自减运算符
1
0
```

## 全局函数实现

### 代码

#### MyInteger.h

```C++
class MyInteger {

public:
#pragma mark - 构造函数

    MyInteger(int n);

    MyInteger();

#pragma mark - Getter & Setter

    int getN(void) const;

    void setN(int n);

#pragma mark - 自增减运算符
    
    friend MyInteger &operator++(MyInteger &integer);

    friend MyInteger &operator--(MyInteger &integer);

    friend MyInteger operator++(MyInteger &integer, int i);

    friend MyInteger operator--(MyInteger &integer, int i);

private:
    int n;
};
```

#### MyInteger.cpp

```C++
#include "MyInteger.h"

int MyInteger::getN(void) const {
    return n;
}

void MyInteger::setN(int n) {
    this->n = n;
}

MyInteger::MyInteger(int n) : n(n) {}

MyInteger::MyInteger() : MyInteger(0) {}

MyInteger &operator++(MyInteger &integer) {
    integer.setN(integer.getN() + 1);
    return integer;
}

MyInteger &operator--(MyInteger &integer) {
    integer.setN(integer.getN() - 1);
    return integer;
}

MyInteger operator++(MyInteger &integer, int i) {
    MyInteger temp(integer);
    integer.setN(integer.getN() + 1);
    return temp;
}

MyInteger operator--(MyInteger &integer, int i) {
    MyInteger temp(integer);
    integer.setN(integer.getN() - 1);
    return temp;
}
```

#### Main.cpp

```C++
#include <iostream>
#include "MyInteger.h"

using std::cout;
using std::endl;

using namespace std;

int main() {

    MyInteger integer = MyInteger();
    cout << integer.getN() << endl;

    cout << "调用前置自增运算符" << endl;
    ++integer;
    cout << integer.getN() << endl;

    cout << "调用前置自减运算符" << endl;
    --integer;
    cout << integer.getN() << endl;

    cout << "调用后置自增运算符" << endl;
    MyInteger old1 = integer++;
    cout << old1.getN() << endl;
    cout << integer.getN() << endl;

    cout << "调用后置自减运算符" << endl;
    MyInteger old2 = integer--;
    cout << old2.getN() << endl;
    cout << integer.getN() << endl;

    return 0;
}
```

### 输出

```text
0
调用前置自增运算符
1
调用前置自减运算符
0
调用后置自增运算符
0
1
调用后置自减运算符
1
0
```

