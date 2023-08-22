# RxPy

[TOC]

## of

代码：

```python
import reactivex as rx

rx.of(1, 2, 3, 4, 5).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
    on_error=lambda e: print(f"on_error: {e}"),
    on_completed=lambda: print("on_completed")
)
```

输出：

```text
on_next: 1
on_next: 2
on_next: 3
on_next: 4
on_next: 5
on_completed
```

## from_

代码：

```python
import reactivex as rx

rx.from_([1, 2, 3, 4, 5]).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
    on_error=lambda e: print(f"on_error: {e}"),
    on_completed=lambda: print("on_completed")
)
```

输出：

```text
on_next: 1
on_next: 2
on_next: 3
on_next: 4
on_next: 5
on_completed
```

## range

代码：

```Python
import reactivex as rx

rx.range(5).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 0
on_next: 1
on_next: 2
on_next: 3
on_next: 4
```

### 其他示例

```Python
import reactivex as rx

rx.range(start=5, stop=9).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

```Python
import reactivex as rx

rx.range(start=0, stop=9, step=3).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

## repeat_value

`repeat_count` 参数用于指定重复次数，若不指定，则为无限次重复

代码：

```Python
import reactivex as rx

rx.repeat_value(value=47, repeat_count=5).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 47
on_next: 47
on_next: 47
on_next: 47
on_next: 47
```

## pipe

### 单个操作

代码：

```python
import reactivex as rx

from reactivex import operators as ops

rx.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).pipe(
    ops.filter(lambda v: v % 2 == 0)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
    on_error=lambda e: print(f"on_error: {e}"),
    on_completed=lambda: print("on_completed")
)
```

输出：

```text
on_next: 2
on_next: 4
on_next: 6
on_next: 8
on_next: 10
on_completed
```

### 多个操作

代码：

```python
import reactivex as rx

from reactivex import operators as ops

rx.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).pipe(
    ops.map(lambda x: x * 10),
    ops.map(lambda x: x + 7),
    ops.filter(lambda x: x % 3 == 0)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
    on_error=lambda e: print(f"on_error: {e}"),
    on_completed=lambda: print("on_completed")
)
```

输出：

```text
on_next: 27
on_next: 57
on_next: 87
on_completed
```

## operators

### filter

根据值进行过滤

代码：

```python
import reactivex as rx

from reactivex import operators as ops

rx.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).pipe(
    ops.filter(lambda value: value % 2 == 0)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 2
on_next: 4
on_next: 6
on_next: 8
on_next: 10
```

### filter_indexed

根据值和索引进行过滤

代码：

```Python
rx.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).pipe(
    ops.filter_indexed(lambda value, index: index % 3 == 0)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 1
on_next: 4
on_next: 7
on_next: 10
```

### distinct

#### 例1

代码：

```Python
import reactivex as rx
from reactivex import operators as ops

rx.of(0, 1, 1, 2, 3, 2, 3, 0, 4, 5, 4, 1).pipe(
    ops.distinct()
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```
on_next: 0
on_next: 1
on_next: 2
on_next: 3
on_next: 4
on_next: 5
```

#### 例2

指定键和比较方法参数

```Python
import reactivex as rx
from reactivex import operators as ops


class Person:
    __name: str
    __classId: int

    def __init__(self, classId, name):
        self.__classId = classId
        self.__name = name

    def getClassId(self):
        return self.__classId

    def getName(self):
        return self.__name

    def __repr__(self) -> str:
        return f"Person(name={self.getName()}, classId={self.getClassId()})"


rx.of(Person(1, "Jack"),
      Person(2, "Ross"),
      Person(2, "John"),
      Person(1, "Bob")).pipe(
    ops.distinct(
        key_mapper=lambda x: x.getClassId(),
        comparer=lambda a, b: a == b
    )
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

代码：

```text
on_next: Person(name=Jack, classId=1)
on_next: Person(name=Ross, classId=2)
```

### distinct_until_changed

去除与上一个值相同的值

代码：

```Python
rx.of(0, 0, 1, 1, 1, 2, 2, 3, 4, 5, 5).pipe(
    ops.distinct_until_changed()
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 0
on_next: 1
on_next: 2
on_next: 3
on_next: 4
on_next: 5
on_next: 1
```

### skip

跳过指定数量

代码：

```python
rx.of(1, 2, 3, 4, 5).pipe(
    ops.skip(2)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```
on_next: 3
on_next: 4
on_next: 5
```

### skip_while

当满足条件时跳过

代码：

```python
rx.of(1, 2, 3, 4, 5).pipe(
    ops.skip_while(lambda x: x <= 3)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```
on_next: 4
on_next: 5
```

### skip_last

代码：

```python
rx.of(1, 2, 3, 4, 5).pipe(
    ops.skip_last(2)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 1
on_next: 2
on_next: 3
```

### start_with

代码：

```Python
rx.of(1, 2, 3, 4, 5).pipe(
    ops.start_with(-2, -1, 0)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: -2
on_next: -1
on_next: 0
on_next: 1
on_next: 2
on_next: 3
on_next: 4
on_next: 5
```

### last

#### 例1

最后1个

代码：

```python
rx.of(1, 2, 3, 4, 5, 4, 3, 2, 1).pipe(
    ops.last()
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 3
```

#### 例2

满足给定条件的最后1个

代码：

```python
rx.of(1, 2, 3, 4, 5, 4, 3, 2, 1).pipe(
    ops.last(lambda x: x >= 3)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 3
```

### last_or_default

最后一个，或者满足给定条件的最后一个，否则返回默认值

代码：

```python
rx.of(1, 2, 3, 4, 5, 4, 3, 2, 1).pipe(
    ops.last_or_default(-1, lambda x: x >= 10)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

代码：

```
on_next: -1
```

### all

是否全部满足给定条件

代码：

```
rx.of(2, 4, 6, 8, 10).pipe(
    ops.all(lambda x: x % 2 == 0)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: True
```

### take

仅获取指定数量的前几个元素

代码：

```Python
rx.of(10, 20, 30, 40, 50).pipe(
    ops.take(3)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 10
on_next: 20
on_next: 30
```

### take_last

仅获取指定数量的后几个元素

代码：

```Python
rx.of(10, 20, 30, 40, 50).pipe(
    ops.take_last(2)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 40
on_next: 50
```

### take_while

仅获取头几个满足指定条件的元素

代码：

```python
rx.of(1, 2, 2, 4, 2, 2, 1).pipe(
    ops.take_while(lambda x: x <= 3)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 1
on_next: 2
on_next: 2
```

### take_last_buffer

输出：

```python
rx.of(1, 2, 3, 4, 5).pipe(
    ops.take_last_buffer(2)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

代码：

```text
on_next: [4, 5]
```

### map

映射，不带有索引，仅元素

代码：

```python
rx.of(1, 2, 3, 4, 5).pipe(
    ops.map(lambda x: x * 10)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 10
on_next: 20
on_next: 30
on_next: 40
on_next: 50
```

### map_indexed

映射，带有索引

代码：

```python
rx.of(10, 20, 30, 40, 50).pipe(
    ops.map_indexed(lambda value, index: (index, value))
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: (0, 10)
on_next: (1, 20)
on_next: (2, 30)
on_next: (3, 40)
on_next: (4, 50)
```

### sum

代码：

```Python
rx.range(start=1, stop=10, step=1).pipe(
    ops.sum()
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 45
```

### average

代码：

```Python
rx.of(1, 2, 3, 4, 5).pipe(
    ops.average()
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 3.0
```

### max

代码：

```python
rx.of(2, 4, 6, 8, 10, 1, 3, 5, 7, 9).pipe(
    ops.max()
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 9
```

### min

代码：

```Python
rx.of(2, 4, 6, 8, 10, 1, 3, 5, 7, 9).pipe(
    ops.min()
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 1
```

### count

代码：

```python
rx.of(2, 4, 6, 8, 10, 1, 3, 5, 7, 9).pipe(
    ops.count()
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 10
```

### element_at

 代码：

```Python
rx.of(0, 10, 20, 30, 40, 50).pipe(
    ops.element_at(2)
).subscribe(
    on_next=lambda x: print(f"on_next: {x}"),
)
```

输出：

```text
on_next: 20
```



