# C++ 示例程序之 MyDate

[TOC]

## MyDate.h

```C++
class MyDate {
private:
    unsigned int year;
    unsigned int month;
    unsigned int day;

public:
    MyDate();

    MyDate(unsigned int year, unsigned int month, unsigned int day);

    void setDate(unsigned int year, unsigned int month, unsigned int day);

    void setDate(MyDate const &date);

    MyDate &getDate(void);

    unsigned int getYear() const;

    void setYear(unsigned int year);

    unsigned int getMonth() const;

    void setMonth(unsigned int month);

    unsigned int getDay() const;

    void setDay(unsigned int day);

    void print(void) const;

};
```

## MyDate.cpp

```C++
#include "MyDate.h"
#include <iostream>

using std::cout;
using std::endl;

MyDate::MyDate() {}

MyDate::MyDate(unsigned int year, unsigned int month, unsigned int day) : year(year), month(month), day(day) {}

void MyDate::setYear(unsigned int year) {
    MyDate::year = year;
}

void MyDate::setMonth(unsigned int month) {
    MyDate::month = month;
}

void MyDate::setDay(unsigned int day) {
    MyDate::day = day;
}

void MyDate::setDate(unsigned int year, unsigned int month, unsigned int day) {
    setYear(year);
    setMonth(month);
    setDay(day);
}

unsigned int MyDate::getYear() const {
    return year;
}

unsigned int MyDate::getMonth() const {
    return month;
}

unsigned int MyDate::getDay() const {
    return day;
}

void MyDate::print(void) const {
    cout << getYear() << "-" << getMonth() << "-" << getDay() << endl;
}

MyDate &MyDate::getDate(void) {
    return *this;
}

void MyDate::setDate(MyDate const &date) {
    setDate(date.year, date.month, date.day);
}
```

## main.cpp

```C++
#include <iostream>
#include "MyDate.h"

using std::cout;
using std::endl;

int main() {
    MyDate date(2020, 6, 1);
    date.print();

    date.setYear(2019);
    date.print();

    date.setMonth(10);
    date.print();

    date.setDay(31);
    date.print();

    cout << date.getYear() << endl;
    cout << date.getMonth() << endl;
    cout << date.getDay() << endl;

    date.setDate(2000, 1, 1);
    date.print();

    date.setDate(MyDate(2030, 1, 1));
    date.print();

    return EXIT_SUCCESS;
}
```