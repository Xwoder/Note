# C++ 流插入提取运算符重载

[TOC]

## 代码

### main.cpp

```C++
#include <iostream>
#include <cstdlib>
#include "Student.h"

using namespace std;

int main(void) {

    Student s("null", 0);
    cout << s << endl;

    cin >> s;
    cout << s << endl;

    return EXIT_SUCCESS;
}
```

### Person.h

```C++
#ifndef TEMPLATEFUNCTION_STUDENT_H
#define TEMPLATEFUNCTION_STUDENT_H

#include <string>
#include <iostream>

using std::string;
using std::ostream;
using std::istream;

class Student {
private:
    string name;
    int age;
public:
    Student(string const &name, int age);

    string const &getName() const;

    void setName(string const &name);

    int getAge() const;

    void setAge(int age);

    // 重载流插入运算符
    friend ostream &operator<<(ostream &os, Student const &s);

    // 重载流提取运算符
    friend istream &operator>>(istream &is, Student &s);
};

#endif //TEMPLATEFUNCTION_STUDENT_H
```

### Person.cpp

```C++
#include "Student.h"

using namespace std;

Student::Student(string const &name, int age) : name(name), age(age) {}

string const &Student::getName() const {
    return name;
}

void Student::setName(string const &name) {
    Student::name = name;
}

int Student::getAge() const {
    return age;
}

void Student::setAge(int age) {
    Student::age = age;
}

ostream &operator<<(ostream &os, Student const &s) {
    os << s.getName() << ", " << s.getAge();
    return os;
}

istream &operator>>(istream &is, Student &s) {
    string name;
    int age;
    is >> name >> age;
    s.setName(name);
    s.setAge(age);
    return is;
}
```

