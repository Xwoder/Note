# C++ swap函数之类模板引用实现

[TOC]

## 代码

### main.cpp

```C++
#include <iostream>
#include <string>
#include <cstdlib>
#include "Student.h"

using namespace std;

template<typename T>
void mySwap(T &a, T &b);

int main(void) {
    {
        int a = 10;
        int b = 20;
        cout << "a = " << a << ", b = " << b << endl;
        mySwap(a, b);
        cout << "a = " << a << ", b = " << b << endl;
    }

    {
        string a = "Hello";
        string b = "World";
        cout << "a = " << a << ", b = " << b << endl;
        mySwap(a, b);
        cout << "a = " << a << ", b = " << b << endl;
    }

    {
        Student s1("Jack");
        Student s2("Rose");
        cout << "s1 = " << s1 << ", s2 = " << s2 << endl;
        mySwap(s1, s2);
        cout << "s1 = " << s1 << ", s2 = " << s2 << endl;
    }

    return EXIT_SUCCESS;
}

template<typename T>
void mySwap(T &a, T &b) {
    T temp = a;
    a = b;
    b = temp;
}
```

### Person.h

```C++
#ifndef TEMPLATEFUNCTION_STUDENT_H
#define TEMPLATEFUNCTION_STUDENT_H

#include <string>
#include <ostream>

using std::string;
using std::ostream;

class Student {
private:
    string name;
public:
    Student(string const &name);

    string const &getName() const;

    void setName(string const &name);

    friend ostream &operator<<(ostream &os, Student const &s);
};


#endif //TEMPLATEFUNCTION_STUDENT_H

```

### Person.cpp

```C++
#include "Student.h"

using namespace std;

Student::Student(string const &name) : name(name) {}

string const &Student::getName() const {
    return name;
}

void Student::setName(string const &name) {
    Student::name = name;
}

ostream &operator<<(ostream &os, Student const &s) {
    os << s.getName();
    return os;
}
```

## 输出

```text
a = 10, b = 20
a = 20, b = 10

a = Hello, b = World
a = World, b = Hello

s1 = Jack, s2 = Rose
s1 = Rose, s2 = Jack
```

