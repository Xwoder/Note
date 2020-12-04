# 日期与时间

[TOC]

## 日期时间 / `datetime`

```Python
from _datetime import datetime, date, time

dt = datetime(2020, 10, 1, 8, 30, 0)
print("year = {0:d}, month = {1:d}, day = {2:d}, hour = {3:d}, minute = {4:d}, second = {5:d}, microsecond = {6:d}".format(
    dt.year,
    dt.month,
    dt.day,
    dt.hour,
    dt.minute,
    dt.second,
    dt.microsecond))
```

## 转换

### `datatime` 转 `data`

```Python
d = dt.date()
# <class 'datetime.date'>
print(type(d))
# 2020-10-01
print(d)
```

### `datatime` 转 `time`

```Python
t = dt.time()
# <class 'datetime.time'>
print(type(t))
# 08:30:00
print(t)
```

### `datatime` 转 `str`

```Python
# 2020-10-01 08:30:00
dtStr = dt.strftime("%Y-%m-%d %H:%M:%S")
print(dtStr)
```

### `str` 转 `datetime`

```Python
# 2020-10-01 08:30:00
dt = datetime.strptime("2020-10-01 08:30:00", "%Y-%m-%d %H:%M:%S")
print(type(dt))
```

## 修改 / `replace`

```Python
# 2020-05-20 08:30:00
dt.replace(month=5, day=20, second=0)
```

由于 `datetime` 是不可变类型，所以 `replace` 方法总是返回一个新的 `datetime` 类型的对象。

## 间隔 / `-`

```Python
dt1 = datetime.datetime(2020, 10, 1, 8, 30, 00)
dt2 = datetime.datetime(2020, 10, 1, 8, 00, 00)

# -1 day, 23:30:00
delta = dt2 - dt1
```

`datetime`  类型之差的类型是 `timedelta`。

将一个 `datetime` 类型的数据与 `timedelta` 类型的数据相加，将得到偏移后的日期数据。

## 日期格式字符串

