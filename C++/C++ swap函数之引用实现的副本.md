# C++ 拷贝构造函数

[TOC]

## 代码

### main.cpp

```C++
#include <cstdlib>
#include "Student.h"

using namespace std;

int main(void) {

    Student s1("Jack", 18);
    s1.print();
    Student s2 = s1;
    s2.print();

    Student s3(s1);
    s3.print();

    // 调用3次拷贝构造函数
    Student ss[] = {s1, s2, s3};
    ss[0].print();
    ss[1].print();
    ss[2].print();

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

    Student(Student const &s);

    string const &getName() const;

    void setName(string const &name);

    int getAge() const;

    void setAge(int age);

    void print(void) const;
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

Student::Student(Student const &s) : Student(s.getName(), s.getAge()) {
    cout << __PRETTY_FUNCTION__ << endl;
}

void Student::print(void) const {
    cout << getName() << ", " << getAge() << endl;
}
```

## 输出

```text
Jack, 18

Student::Student(const Student &)
Jack, 18

Student::Student(const Student &)
Jack, 18

Student::Student(const Student &)
Student::Student(const Student &)
Student::Student(const Student &)
Jack, 18
Jack, 18
Jack, 18

```

