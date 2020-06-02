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

#pragma mark - 构造函数

    /* 无参构造函数 */
    MyDate();

    /* 带参构造函数 */
    MyDate(unsigned int year, unsigned int month, unsigned int day);

#pragma mark - 复制构造函数

    /* 复制构造函数；const 参数版 */
    MyDate(MyDate const &date);

    /* 复制构造函数；非 const 参数版 */
    MyDate(MyDate &date);

#pragma mark - Getter & Setter

    void setDate(unsigned int year, unsigned int month, unsigned int day);

    void setDate(MyDate const &date);

    unsigned int getYear() const;

    void setYear(unsigned int year);

    unsigned int getMonth() const;

    void setMonth(unsigned int month);

    unsigned int getDay() const;

    void setDay(unsigned int day);

    void print(void) const;

#pragma mark - 析构函数

    virtual ~MyDate();

};
```

## MyDate.cpp

```C++
#include "MyDate.h"
#include <iostream>

using std::cout;
using std::endl;

MyDate::MyDate() {
    cout << __PRETTY_FUNCTION__ << endl;
}

MyDate::MyDate(unsigned int year, unsigned int month, unsigned int day) : year(year), month(month), day(day) {
    cout << __PRETTY_FUNCTION__ << endl;
}

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

void MyDate::setDate(MyDate const &date) {
    setDate(date.year, date.month, date.day);
}

MyDate::MyDate(MyDate const &date) {
    cout << __PRETTY_FUNCTION__ << endl;
    setDate(date.getYear(), date.getMonth(), date.getDay());
}

MyDate::MyDate(MyDate &date) {
    cout << __PRETTY_FUNCTION__ << endl;
    setDate(date.getYear(), date.getMonth(), date.getDay());
}

MyDate::~MyDate() {
    cout << __PRETTY_FUNCTION__ << endl;
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

    MyDate const unixDate(1970, 1, 1);

    // 调用参数类型为const修饰的构造函数
    MyDate unixDate1(unixDate);

    // 调用参数类型为非const修饰的构造函数
    MyDate unixDate2(unixDate2);

    // 调用构造函数
    MyDate newYearDate = MyDate(2020, 1, 1);
    // 调用复制构造函数
    MyDate newYearDateCopyed = newYearDate;

    return EXIT_SUCCESS;
}
```