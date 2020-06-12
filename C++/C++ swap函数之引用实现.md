# C++ swap函数之引用实现

[TOC]

## 代码

### Person.h

```C++
#ifndef SWAPTEMPLATE_PERSON_H
#define SWAPTEMPLATE_PERSON_H

#include <string>
#include <ostream>

using std::string;

class Person {
public:
    Person(string const &name);

    friend std::ostream &operator<<(std::ostream &os, Person const &person);

private:
    string name;
};

#endif //SWAPTEMPLATE_PERSON_H
```

### Person.cpp

```C++
#include "Person.h"

Person::Person(string const &name) : name(name) {}

std::ostream &operator<<(std::ostream &os, Person const &person) {
    os << person.name;
    return os;
}
```

### main.cpp

```C++
#include <iostream>
#include "Person.h"

using std::cout;
using std::endl;

template<typename T>
void swap(T &a, T &b);

int main() {
    {
        int a = 10;
        int b = 20;
        cout << "a = " << a << ", b = " << b << endl;
        swap(a, b);
        cout << "a = " << a << ", b = " << b << endl;
    }

    {
        Person p1("Jack");
        Person p2("Rose");
        cout << "p1 = " << p1 << ", p2 = " << p2 << endl;
        swap(p1, p2);
        cout << "p1 = " << p1 << ", p2 = " << p2 << endl;
    }
    return 0;
}

template<typename T>
void swap(T &a, T &b) {
    T temp = a;
    a = b;
    b = temp;
}
```